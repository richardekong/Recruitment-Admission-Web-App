package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
public class HistoricalData {
    @Id
    private Integer academicYear;
    private Integer fundedPlaces;
    private Integer offersMade;

    private double ratio;



    public void setRatio(){
         ratio = (getFundedPlaces())/Double.valueOf(getOffersMade());
    }


}
