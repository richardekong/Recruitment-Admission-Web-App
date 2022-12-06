package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
}
