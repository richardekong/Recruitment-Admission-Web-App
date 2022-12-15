package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum WelshSpeaker {
    YES("Y"),
    NO("N"),
    NA("N/A");

    private final String response;

    WelshSpeaker (String response) { this.response = response;}
    public String getResponse() {return response;}


}
