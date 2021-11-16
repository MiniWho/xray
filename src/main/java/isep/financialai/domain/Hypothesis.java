package isep.financialai.domain;

import isep.financialai.domain.enums.AuxEnum;
import isep.financialai.domain.enums.EvidenceEnum;

public class Hypothesis extends Fact{

    private EvidenceEnum description;
    private AuxEnum value;

    public Hypothesis(EvidenceEnum description, AuxEnum value) {
        this.description = description;
        this.value = value;
    }

    public EvidenceEnum getDescription() {
        return description;
    }

    public void setDescription(EvidenceEnum description) {
        this.description = description;
    }

    public AuxEnum getValue() {
        return value;
    }

    public void setValue(AuxEnum value) {
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
