package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum ApplicationStatusCode {
    Applicant("A");

    private final String code;

    ApplicationStatusCode (String code){
        this.code = code;
    }

    public String getApplicationStatusCode() {
        return code;
    }
}
