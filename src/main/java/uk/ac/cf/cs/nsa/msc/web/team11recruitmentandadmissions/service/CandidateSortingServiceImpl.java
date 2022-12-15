package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidatePagingAndSortingRepository;

import java.time.LocalDate;

@Service
public class CandidateSortingServiceImpl implements CandidateSortingService{

    private CandidatePagingAndSortingRepository repository;

    @Autowired
    void setRepository(CandidatePagingAndSortingRepository repository){
        this.repository = repository;
    }

    @Override
    public Slice<Candidate> findCandidateByStudentNo(String studentNo, Pageable pageable) {
        return repository.findCandidateByStudentNo(studentNo, pageable);
    }

    @Override
    public Slice<Candidate> findCandidateBySurname(String surname, Pageable pageable) {
        return repository.findCandidateBySurname(surname, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByApplicationStatusCode(ApplicationStatusCode code, Pageable pageable) {
        return repository.findCandidatesByApplicationStatusCode(code, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByOfferConditions(String offerCondition, Pageable pageable) {
        return repository.findCandidatesByOfferConditions(offerCondition, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByRecordFirstCreated(LocalDate createdOn, Pageable pageable) {
        return repository.findCandidatesByRecordFirstCreated(createdOn, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByPersonalID(String personalID, Pageable pageable) {
        return repository.findCandidatesByPersonalID(personalID, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByDateOfBirth(LocalDate dateOfBirth, Pageable pageable) {
        return repository.findCandidatesByDateOfBirth(dateOfBirth, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByTotalPersonalStatementScore(int score, Pageable pageable) {
        return repository.findCandidatesByTotalPersonalStatementScore(score, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByTotalInterviewScore(int score, Pageable pageable) {
        return repository.findCandidatesByTotalInterviewScore(score, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByInviteInterview(YesOrNoOption option, Pageable pageable) {
        return repository.findCandidatesByInviteInterview(option, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByOfferEmailSent(YesOrNoOption option, Pageable pageable) {
        return repository.findCandidatesByOfferEmailSent(option, pageable);
    }
}

