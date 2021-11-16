package isep.financialai.domain;

import isep.financialai.domain.enums.RecommendationEnum;
import isep.financialai.domain.enums.EvidenceEnum;

public class Hypothesis extends Fact{

    private EvidenceEnum description;
    private RecommendationEnum value;

    public Hypothesis(EvidenceEnum description, RecommendationEnum value) {
        this.description = description;
        this.value = value;
    }

    public EvidenceEnum getDescription() {
        return description;
    }

    public void setDescription(EvidenceEnum description) {
        this.description = description;
    }

    public RecommendationEnum getValue() {
        return value;
    }

    public void setValue(RecommendationEnum value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Hypothesis{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
