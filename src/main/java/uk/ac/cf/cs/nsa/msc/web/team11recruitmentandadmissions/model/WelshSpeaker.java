package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

public enum WelshSpeaker {
    YES("Y"),
    NO("N"),
    NA("N/A");

    private final String welshSpeaker;

    WelshSpeaker (String welshSpeaker) { this.welshSpeaker = welshSpeaker;}
    public String getWelshSpeaker() {return welshSpeaker;}


}
