package isep.financialai.domain;

import isep.financialai.domain.enums.AuxEnum;

public class Conclusion extends Fact{
    private AuxEnum description;

    public Conclusion(AuxEnum description) {
        this.description = description;
    }

    public AuxEnum getDescription() {
        return description;
    }

    public void setDescription(AuxEnum description) {
        this.description = description;
    }

    public String toString() {
        return ("Conclusion: " + description);
    }
}
