package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum FeeStatus {
    HOME("H"),
    INTERNATIONAL("I");

    private final String feeStatus;

    FeeStatus (String feeStatus){
        this.feeStatus = feeStatus;
    }

    public String getFeeStatus(){
        return feeStatus;
    }
}
