package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class HistoricalData {
    @Id
    private Integer academicYear;
    private Integer fundedPlaces;
    private Integer offersMade;
    private double ratio;

    public double getRatio() {
        return Double.valueOf(getFundedPlaces())/Double.valueOf(getOffersMade());
    }

}
