package isep.financialai.web.rest;

import isep.financialai.domain.Evidence;
import isep.financialai.domain.enums.EvidenceEnum;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ApiResourceIT {
    static final Logger LOG = LoggerFactory.getLogger(ApiResourceIT.class);

    @Test
    public void xrayEngine(){
        List<Evidence> evidences = new ArrayList<>();
        evidences.add(new Evidence(EvidenceEnum.FINANCIAL_AUTONOMY, Float.valueOf("10")));
        evidences.add(new Evidence(EvidenceEnum.SOLUBILITY, Float.valueOf("60")));
        ApiResource.runEngine(evidences);
    }
}
