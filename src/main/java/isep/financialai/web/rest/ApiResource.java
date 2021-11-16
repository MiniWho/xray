package isep.financialai.web.rest;

import isep.financialai.domain.*;
import isep.financialai.domain.enums.ConclusionEnum;
import isep.financialai.domain.enums.RecommendationEnum;
import isep.financialai.domain.enums.EvidenceEnum;
import isep.financialai.helpers.How;
import isep.financialai.listeners.TrackingAgendaEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;
import org.kie.api.runtime.rule.Row;
import org.kie.api.runtime.rule.ViewChangedEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiResource {

    private final Logger log = LoggerFactory.getLogger(ApiResource.class);

    public static Map<Long, Justification> justifications;
    public static TrackingAgendaEventListener agendaEventListener;

    @GetMapping("/simpleget")
    public ResponseEntity<EngineResponse> getSimpleGet() {
        FinancialHealthConclusion financialHealthConclusion = new FinancialHealthConclusion(ConclusionEnum.NON_SATISFYING);
        ProfitabilityConclusion profitabilityConclusion = new ProfitabilityConclusion(ConclusionEnum.ACCEPTABLE);
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(new Recommendation(RecommendationEnum.CELEBRATE_PUBLIC_DEED));
        recommendations.add(new Recommendation(RecommendationEnum.ACCEPTABLE_PROFITABILITY));
        return ResponseEntity.ok().body(new EngineResponse(financialHealthConclusion, profitabilityConclusion, recommendations));
    }

    @GetMapping("/simplepost")
    public String getSimplePost() {
        return "ola crl";
    }

    @GetMapping("/getFinancialHealth")
    public ResponseEntity<EngineResponse> getFinancialHealth(InputStream inputStream) {
        File file = new File("src/main/resources/Temp/output.csv");
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            String line;
            String split = ";";
            int count_line = 0;
            int year_1 = 0;
            int year_2 = 0;
            List<Evidence> evidences = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                List<String> element = Arrays.asList(line.split(split));
                if (count_line > 6 && element.size() > 3 && element.get(0).indexOf('(') != -1) {
                    List<String> el = Arrays.asList(element.get(0).split("\\("));
                    el.set(1, el.get(1).replace("(", ""));
                    el.set(1, el.get(1).replace(")", ""));
                    element.set(1, element.get(1).replace(",", "."));
                    element.set(2, element.get(2).replace(",", "."));
                    evidences.add(new Evidence(EvidenceEnum.fromString(element.get(0).trim()), Float.parseFloat(element.get(1).trim())));
                }
                count_line++;
            }
            return ResponseEntity.ok().body(runEngine(evidences));
                    /*cleanResponse();
                    addInResponse("{\"success\":\"dados carregados com sucesso\"}");
                    file.delete();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    public static EngineResponse runEngine(List<Evidence> evidences) {
        EngineResponse response = new EngineResponse();
        try {
            justifications = new TreeMap<>();
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            agendaEventListener = new TrackingAgendaEventListener();
            kSession.addEventListener(agendaEventListener);

            // Query listener
            ViewChangedEventListener listener = new ViewChangedEventListener() {
                @Override
                public void rowDeleted(Row row) {
                }

                @Override
                public void rowInserted(Row row) {
                    if (row.get("$financialHealthConclusion") != null) {
                        response.setFinancialHealthConclusion((FinancialHealthConclusion) row.get("$financialHealthConclusion"));
                        System.out.println(">>>" + (FinancialHealthConclusion) row.get("$financialHealthConclusion"));
                        How how = new How(justifications);
                        System.out.println(how.getHowExplanation(response.getFinancialHealthConclusion().getId()));
                        if (response.getProfitabilityConclusion() != null) {
                            kSession.halt();
                        }
                    }
                    if (row.get("$profitabilityConclusion") != null) {
                        response.setProfitabilityConclusion((ProfitabilityConclusion) row.get("$profitabilityConclusion"));
                        System.out.println(">>>" + (ProfitabilityConclusion) row.get("$profitabilityConclusion"));
                        How how = new How(justifications);
                        System.out.println(how.getHowExplanation(response.getProfitabilityConclusion().getId()));
                        if (response.getFinancialHealthConclusion() != null) {
                            kSession.halt();
                        }
                    }
                    if (row.get("$recommendation") != null) {
                        response.getRecommendations().add((Recommendation) row.get("$recommendation"));
                        System.out.println(">>>" + (Recommendation) row.get("$recommendation"));
                    }
                }

                @Override
                public void rowUpdated(Row row) {
                }
            };

            evidences.forEach(kSession::insert);

            LiveQuery queryFinancialHealthConclusions = kSession.openLiveQuery("FinancialHealthConclusions", null, listener);
            LiveQuery queryProfitabilityConclusions = kSession.openLiveQuery("ProfitabilityConclusions", null, listener);
            LiveQuery queryRecommendations = kSession.openLiveQuery("Recommendations", null, listener);

            kSession.fireAllRules();
            queryFinancialHealthConclusions.close();
            queryProfitabilityConclusions.close();
            queryRecommendations.close();
            return response;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return response;
    }
}
