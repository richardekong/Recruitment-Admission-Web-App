package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum FeeStatus {
    Home("H"),
    International("I");

    private final String feeStatus;

    FeeStatus (String feeStatus){
        this.feeStatus = feeStatus;
    }

    public String getFeeStatus(){
        return feeStatus;
    }
}
