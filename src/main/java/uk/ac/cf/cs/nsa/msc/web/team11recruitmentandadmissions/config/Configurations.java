package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.config;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;

@Configuration
public class Configurations {

    @Bean
    DataFormatter cellDataFormatter(){
        return new DataFormatter();
    }

    @Bean
    DateConverter dateConverter(){
        return new DateConverter();
    }
}
