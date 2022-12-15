package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

import java.util.List;

public interface HistoricalDataRepository extends JpaRepository<HistoricalData,Integer> {

    @Query("SELECT ratio from HistoricalData")
    List<Double> selectRatiosFromHistorical ();
}
