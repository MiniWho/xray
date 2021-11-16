package isep.financialai.domain;

import isep.financialai.domain.enums.RecommendationEnum;

public class Recommendation extends Fact{

    private RecommendationEnum description;

    public Recommendation(RecommendationEnum description) {
        this.description = description;
    }

    public RecommendationEnum getDescription() {
        return description;
    }

    public void setDescription(RecommendationEnum description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
            "description=" + description +
            '}';
    }
}
