package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CustomException extends RuntimeException{

    private final HttpStatus status;

    public CustomException(String message, @NotNull HttpStatus status){
        super(message);
        this.status = status;
    }

    public CustomException(@NotNull HttpStatus status){
        super(status.getReasonPhrase());
        this.status = status;
    }
}

