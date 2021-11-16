package isep.financialai.helpers;

import isep.financialai.domain.Fact;
import isep.financialai.domain.Hypothesis;
import isep.financialai.domain.Justification;

import java.util.Map;

public class How {
    private Map<Long, Justification> justifications;

    public How(Map<Long, Justification> justifications) {
        this.justifications = justifications;
    }

    public String getHowExplanation(Long factNumber) {
        return (getHowExplanation(factNumber, 0));
    }

    private String getHowExplanation(Long factNumber, int level) {
        StringBuilder sb = new StringBuilder();
        Justification j = justifications.get(factNumber);
        if (j != null) { // justification for Fact factNumber was found
            sb.append(getIdentation(level));
            sb.append(j.getConclusion()).append(" was obtained by rule ").append(j.getRuleName()).append(" because");
            sb.append('\n');
            int l = level + 1;
            for (Fact f : j.getLhs()) {
                sb.append(getIdentation(l));
                sb.append(f);
                sb.append('\n');
                if (f instanceof Hypothesis) {
                    String s = getHowExplanation(f.getId(), l + 1);
                    sb.append(s);
                }
            }
        }

        return sb.toString();
    }

    private String getIdentation(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(Math.max(0, level)));
        return sb.toString();
    }
}
