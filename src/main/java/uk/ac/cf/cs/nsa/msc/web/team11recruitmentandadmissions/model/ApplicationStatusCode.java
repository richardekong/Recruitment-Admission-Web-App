package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum ApplicationStatusCode {
    APPLICATION("A");

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
