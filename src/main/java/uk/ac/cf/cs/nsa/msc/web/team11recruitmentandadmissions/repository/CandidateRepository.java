package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate,String> {
        Candidate findCandidateByStudentNo(String studentNo);
        @Query("SELECT c FROM Candidate c")
        List<Candidate> findAllCandidates();

        Integer countCandidatesByOfferEmailSent(YesOrNoOption option);

}
