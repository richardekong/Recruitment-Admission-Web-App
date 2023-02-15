package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository repository;

    @Autowired
    void setRepository(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        Optional<Candidate> optionalCandidate = Optional.of(
                repository.findCandidateByStudentNo(candidate.getStudentNo())
        );
        optionalCandidate.ifPresentOrElse(theCandidate -> repository.save(candidate),
                () -> {
                    throw new CustomException(
                            "Candidate with " + candidate.getStudentNo() + " exists", HttpStatus.CONFLICT);
                });
        return candidate;
    }

    @Override
    public List<Candidate> saveAll(LinkedList<Candidate> candidates) {
        List<Candidate> unsavedCandidates = candidates.stream().filter(candidate -> !repository.existsById(candidate.getStudentNo()))
                .collect(Collectors.toList());
        return repository.saveAll(unsavedCandidates);
    }

    @Override
    public Candidate findByStudentNo(String studentNo) {
        return repository.findById(studentNo).orElseThrow(() -> {
            throw new CustomException("Candidate not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public List<Candidate> findAll() {
        return repository.findAll();
    }

    @Override
    public  Candidate updateCandidate(Candidate candidateToUpdate) {
        return repository.save(candidateToUpdate);
    }

    @Override
    public Integer countCandidatesByOfferEmailSent(YesOrNoOption option) {
        return repository.countCandidatesByOfferEmailSent(YesOrNoOption.YES);
    }

    @Override
    public Slice<Candidate> findCandidateByStudentNo(String studentNo, Pageable pageable) {
        return repository.findCandidateByStudentNo(studentNo, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByFirstName(String firstName, Pageable pageable) {
        return repository.findCandidatesByFirstName(firstName, pageable);
    }
    @Override
    public Slice<Candidate> findCandidateBySurname(String surname, Pageable pageable) {
        return repository.findCandidateBySurname(surname, pageable);
    }

    @Override
    public Slice<Candidate> findCandidatesByFirstNameAndSurname(String firstName, String surname, Pageable pageable) {
        boolean namesAreValid = Stream.of(firstName, surname)
                .allMatch(name -> name.matches("^[a-zA-z\\d]+$"));
        if (!namesAreValid) {
            throw new  CustomException("Invalid names", HttpStatus.BAD_REQUEST);
        }
        return repository.findCandidatesByFirstNameAndSurnameContainingIgnoreCase(firstName, surname, pageable);
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

