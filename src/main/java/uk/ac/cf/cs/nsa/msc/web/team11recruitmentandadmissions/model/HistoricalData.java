package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class HistoricalData {
    @Id
    private String academicYear;
    private Integer fundedPlaces;
    private Integer offersMade;

    private double ratio;

    public double getRatio(){
        return Double.valueOf(fundedPlaces)/Double.valueOf(offersMade);
    }


}
