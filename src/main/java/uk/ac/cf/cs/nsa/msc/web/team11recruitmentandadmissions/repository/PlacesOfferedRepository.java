package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;

import java.util.List;

public interface PlacesOfferedRepository extends JpaRepository<PlacesOffered, Integer> {

    @Query(value="SELECT p FROM PlacesOffered  p ORDER BY p.id DESC")
    List<PlacesOffered> findAllPlacesOfferedDescendingOrder();
}
