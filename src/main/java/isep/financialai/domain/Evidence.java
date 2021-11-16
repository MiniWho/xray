package isep.financialai.domain;

import isep.financialai.domain.enums.EvidenceEnum;

public class Evidence extends Fact {

    private EvidenceEnum description;
    private Object value;

    public Evidence(EvidenceEnum description, Object value) {
        this.description = description;
        this.value = value;
    }

    public EvidenceEnum getDescription() {
        return description;
    }

    public void setDescription(EvidenceEnum description) {
        this.description = description;
    }

    public Object getValue() {
        return value;
    }

    public String getStringValue() {
        if (value.getClass() == String.class) {
            return (String) value;
        } else {
            return null;
        }
    }

    public Float getFloatValue() {
        if (value.getClass() == Float.class) {
            return (Float) value;
        } else {
            return null;
        }
    }

    public Boolean getBooleanValue() {
        if (value.getClass() == Float.class) {
            return (boolean) value;
        } else {
            return null;
        }
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
