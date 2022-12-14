package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

public interface HistoricalDataRepository extends JpaRepository<HistoricalData,Integer> {

}
