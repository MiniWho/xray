package isep.financialai.domain.enums;

import java.util.Arrays;

public enum ConclusionEnum {

    NON_SATISFYING("nao_satisfatoria"),
    FRAIL("debil"),
    ACCEPTABLE("aceitavel"),
    VERY_SATISFYING("muito_satisfatoria"),
    NON_CONCLUSIVE("nao_conclusivo");

    private final String value;

    ConclusionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @return the Enum representation for the given string.
     * @throws IllegalArgumentException if unknown string.
     */
    public static ConclusionEnum fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(ConclusionEnum.values())
            .filter(v -> v.value.equals(s))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }

}
