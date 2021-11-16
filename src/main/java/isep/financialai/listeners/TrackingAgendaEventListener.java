package isep.financialai.listeners;

import isep.financialai.domain.Fact;
import isep.financialai.domain.Justification;
import isep.financialai.web.rest.ApiResource;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("restriction")
public class TrackingAgendaEventListener extends DefaultAgendaEventListener {
    private List<Match> matchList = new ArrayList<>();
    private List<Fact> lhs = new ArrayList<>();
    private List<Fact> rhs = new ArrayList<>();

    public void resetLhs() {
        lhs.clear();
    }

    public void addLhs(Fact f) {
        lhs.add(f);
    }

    public void resetRhs() {
        rhs.clear();
    }

    public void addRhs(Fact f) {
        rhs.add(f);
    }

    @Override
    public void matchCancelled(MatchCancelledEvent event) {
        resetLhs();
        resetRhs();
    }

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        Rule rule = event.getMatch().getRule();

        String ruleName = rule.getName();
        Map<String, Object> ruleMetaDataMap = rule.getMetaData();

        //System.out.println("LHS:");
        List <Object> list = event.getMatch().getObjects();
        for (Object e : list) {
            if (e instanceof Fact) {
                lhs.add((Fact)e);
            }
        }
        for (Fact f : lhs) {
            //System.out.println(f.getId() + ":" + f);
        }

        //System.out.println("RHS:");
        for (Fact f: rhs) {
            //System.out.println(f.getId() + ":" + f);
            Justification j = new Justification(ruleName, lhs, f);
            ApiResource.justifications.put(f.getId(), j);
        }

        resetLhs();
        resetRhs();

        matchList.add(event.getMatch());
        StringBuilder sb = new StringBuilder();
        sb.append("Rule fired: ").append(ruleName);

        if (ruleMetaDataMap.size() > 0) {
            sb.append("\n  With [").append(ruleMetaDataMap.size()).append("] meta-data:");
            for (String key : ruleMetaDataMap.keySet()) {
                sb.append("\n    key=").append(key).append(", value=").append(ruleMetaDataMap.get(key));
            }
        }

        //System.out.println(sb.toString());
    }
}
