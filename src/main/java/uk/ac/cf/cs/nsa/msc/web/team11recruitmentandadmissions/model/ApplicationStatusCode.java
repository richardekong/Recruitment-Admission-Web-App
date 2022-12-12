package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum ApplicationStatusCode {
    APPLICATION("A"),
    GATHERED_FIELD_ON_HOLD("GF"),
    OTHER_REASON_ON_HOLD("OR"),
    AWAITING_INTERVIEW_ON_HOLD("OH"),
    REJECTED("R"),
    CONDITIONAL_OFFER_MADE("C"),
    CONDITIONAL_OFFER_FIRMED("CF"),
    CONDITIONAL_OFFER_INSURED("CI"),
    CONDITIONAL_OFFER_DECLINED("CD"),
    UNCONDITIONAL_OFFER_MADE("U"),
    UNCONDITIONAL_OFFER_FIRMED("UF"),
    UNCONDITIONAL_OFFER_INSURED("UI"),
    UNCONDITIONAL_OFFER_DECLINED("UD"),
    WITHDRAWN("W");

    private String code;

    ApplicationStatusCode (String code){
        this.code = code;
    }

    public void setCode(String code){
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
