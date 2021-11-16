package isep.financialai.domain.enums;

import java.util.Arrays;

public enum AuxEnum {

    INCREASE_OWN_CAPITAL("aumentar_capital_proprio"),
    INCREASE_SOCIAL_CAPITAL("aumentar_capital_social"),
    HIGH_OUTSIDE_INVESTMENT("elevado_investimento_alheio"),
    MAKE_ADDITIONAL_INSTALLMENT("realizar_prestacoes_suplementares"),
    REVALUE_TANGIBLE_ASSETS("revalorizacao_ativos_fixos_tangiveis"),
    MAKE_MONEY_DEPOSIT("realizar_deposito_dinheiro"),
    CELEBRATE_PUBLIC_DEED("celebrar_escritura_publica"),
    REGISTER_CONSERVATORY_COMMERCIAL_REGISTRATION("registar_conservatoria_registo_comercial"),
    MAKE_CASH_DEPOSIT_MEMBERSHIP_REFUND("realizar_deposito_dinheiro_restituicao_socios"),
    EVALUATE_COMPANY_PROPERTIES("avaliar_imoveis_empresa"),
    GOOD_FINANCIAL_AUTONOMY("autonomia_financeira_aceitavel"),
    ANALYZE_OWN_CAPITAL_REPLACEMENT("analise_substituicao_capital_proprio"),
    RAISE_OWN_CAPITAL_DEBT_REFUND("aumento_capital_proprio_devolucao_endividamento"),
    HAS_FINANCIAL_CAPABILITY("tem_capacidade_financeira"),
    HIGH_OUTSIDE_INDEBTEDNESS("elevado_investimento_alheio"),
    FINANCIAL_CAPACITY_FOR_ACTIVITIES("capacidade_financeira_atividades"),
    INDEBTEDNESS_STRUCTURE("estrutura_endividamento"),
    TREASURY_BREAKS("quebras_tesouraria"),
    PRIVILEGE_CREDIT_LINES("privilegiar_linhas_credito"),
    FULFILLING_SHORT_TERM_RESPONSIBILITIES("cumprir_responsabilidades_curto_prazo"),
    SHORT_TERM_BREAKDOWN_RISK("risco_rotura_curto_prazo"),
    APPEAL_MEDIUM_LONG_TERM_FINANCING("recorrer_financiamento_medio_longo_prazo"),
    ANALYZE_FUNCTIONING_RATIO_RAISE_STOCK_ROTATION("analisar_racios_funcionamento_aumentar_rotacao_stocks"),
    LOW_RISK_COMPANY_CREDITORS("baixo_risco_credores_empresa"),
    GOOD_PROFITABILITY("boa_rentabilidade"),
    ACCEPTABLE_PROFITABILITY("rentabilidade_aceitavel"),
    EXPENDITURE_ANALYSIS("analisar_gastos"),
    MARGIN_ACCEPTABLE("margem_operacional_aceitavel");

    private final String value;

    AuxEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @return the Enum representation for the given string.
     * @throws IllegalArgumentException if unknown string.
     */
    public static AuxEnum fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(AuxEnum.values())
                .filter(v -> v.value.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
