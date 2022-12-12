package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;

import java.time.LocalDate;

public interface CandidatePagingAndSortingRepository extends PagingAndSortingRepository<Candidate, String> {


    Slice<Candidate> findCandidateByStudentNo(String studentNo, Pageable pageable);

    Slice<Candidate> findCandidateBySurname(String surname, Pageable pageable);

    Slice<Candidate> findCandidatesByApplicationStatusCode(ApplicationStatusCode code, Pageable pageable);

    Slice<Candidate> findCandidatesByOfferConditions(String offerCondition, Pageable pageable);

    Slice<Candidate> findCandidatesByRecordFirstCreated(LocalDate createdOn, Pageable pageable);

    Slice<Candidate> findCandidatesByPersonalID(String personalID, Pageable pageable);

    Slice<Candidate> findCandidatesByDateOfBirth(LocalDate dateOfBirth, Pageable pageable);

    Slice<Candidate> findCandidatesByTotalPersonalStatementScore(int score, Pageable pageable);

    Slice<Candidate> findCandidatesByTotalInterviewScore(int score, Pageable pageable);

    Slice<Candidate> findCandidatesByInviteInterview(YesOrNoOption option, Pageable pageable);

    Slice<Candidate> findCandidatesByOfferEmailSent(YesOrNoOption option, Pageable pageable);

}

