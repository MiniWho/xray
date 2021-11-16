package isep.financialai.domain;

import java.util.List;

public class EngineResponse {

    private FinancialHealthConclusion financialHealthConclusion;
    private ProfitabilityConclusion profitabilityConclusion;
    private List<Recommendation> recommendations;

    public EngineResponse() {
    }

    public EngineResponse(FinancialHealthConclusion financialHealthConclusion, ProfitabilityConclusion profitabilityConclusion, List<Recommendation> recommendations) {
        this.financialHealthConclusion = financialHealthConclusion;
        this.profitabilityConclusion = profitabilityConclusion;
        this.recommendations = recommendations;
    }

    public FinancialHealthConclusion getFinancialHealthConclusion() {
        return financialHealthConclusion;
    }

    public void setFinancialHealthConclusion(FinancialHealthConclusion financialHealthConclusion) {
        this.financialHealthConclusion = financialHealthConclusion;
    }

    public ProfitabilityConclusion getProfitabilityConclusion() {
        return profitabilityConclusion;
    }

    public void setProfitabilityConclusion(ProfitabilityConclusion profitabilityConclusion) {
        this.profitabilityConclusion = profitabilityConclusion;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }



    @Override
    public String toString() {
        return "EngineResponse{" +
            "financialHealthConclusion=" + financialHealthConclusion +
            ", profitabilityConclusion=" + profitabilityConclusion +
            ", recommendations=" + recommendations +
            '}';
    }
}
