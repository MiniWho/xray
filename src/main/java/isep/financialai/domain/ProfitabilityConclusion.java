package isep.financialai.domain;

import isep.financialai.domain.enums.ConclusionEnum;

public class ProfitabilityConclusion extends Fact{
    private ConclusionEnum description;

    public ProfitabilityConclusion(ConclusionEnum description) {
        this.description = description;
    }

    public ConclusionEnum getDescription() {
        return description;
    }

    public void setDescription(ConclusionEnum description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FinancialHealthConclusion{" +
            "description=" + description +
            '}';
    }
}
