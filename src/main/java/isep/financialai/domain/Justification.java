package isep.financialai.domain;

import java.util.ArrayList;
import java.util.List;

public class Justification {
    private String rule;
    private List<Fact> lhs;
    private Fact conclusion;

    public Justification(String rule, List<Fact> lhs, Fact conclusion) {
        this.rule = rule;
        this.lhs = new ArrayList<>(lhs);
        this.conclusion = conclusion;
    }

    public String getRuleName() {
        return this.rule;
    }

    public List<Fact> getLhs() {
        return this.lhs;
    }

    public Fact getConclusion() {
        return this.conclusion;
    }

    @Override
    public String toString() {
        return "Justification{" +
                "rule='" + rule + '\'' +
                ", lhs=" + lhs +
                ", conclusion=" + conclusion +
                '}';
    }
}
