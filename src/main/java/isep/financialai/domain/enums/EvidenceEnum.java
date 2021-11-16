package isep.financialai.domain.enums;

import java.util.Arrays;

public enum EvidenceEnum {
    FINANCIAL_AUTONOMY("Autonomia financeira (%)"),
    SOLUBILITY("Solvabilidade (%)"),
    INDEBTEDNESS("Endividamento (%)"),
    GENERAL_LIQUIDITY("Liquidez geral"),
    SALES_CONTRIBUTION_MARGIN("Rentabilidade das vendas e serviços prestados (%)"),
    MARGIN_EBITDA("Margem EBIT (%)"),
    HAS_PROPERTIES("tem_imoveis"),
    OTHERS("");

    private final String value;

    EvidenceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @return the Enum representation for the given string.
     * @throws IllegalArgumentException if unknown string.
     */
    public static EvidenceEnum fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(EvidenceEnum.values())
                .filter(v -> v.value.equals(s))
                .findFirst()
                .orElse(OTHERS);
                //.orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
