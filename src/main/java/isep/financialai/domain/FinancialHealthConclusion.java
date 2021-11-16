package isep.financialai.domain;

import isep.financialai.domain.enums.ConclusionEnum;

public class FinancialHealthConclusion extends Fact{
    private ConclusionEnum description;

    public FinancialHealthConclusion(ConclusionEnum description) {
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
