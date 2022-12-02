package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int status;
    private String message;
    private long time;
}
