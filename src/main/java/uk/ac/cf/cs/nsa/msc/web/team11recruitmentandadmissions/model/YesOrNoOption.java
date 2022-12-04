package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum YesOrNoOption {

    YES("Y"), NO("N");

    private final String option;

    YesOrNoOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return this.option;
    }

}
