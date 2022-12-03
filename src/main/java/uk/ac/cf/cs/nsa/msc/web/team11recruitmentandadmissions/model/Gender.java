package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;


public enum Gender {
    Male("M"),
    Female("F");
    private final String gender;
    Gender (String gender){
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
