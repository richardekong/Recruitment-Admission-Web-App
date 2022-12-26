package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository repository;

    @Autowired
    void setRepository(CandidateRepository repository) {
        this.repository = repository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        Candidate existingCandidate = repository.findCandidateByStudentNo(candidate.getStudentNo());
        if (existingCandidate == null){
            existingCandidate = repository.save(candidate);
        }else {
            throw new CustomException(
                    "Candidate with " + existingCandidate.getStudentNo() + " exists", HttpStatus.CONFLICT);
        }
        return existingCandidate;
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
    public Candidate updateCandidate(Candidate candidateToUpdate) {
        return repository.save(candidateToUpdate);
    }

    @Override
    public Integer countCandidatesByOfferEmailSent(YesOrNoOption option) {
        return repository.countCandidatesByOfferEmailSent(YesOrNoOption.YES);
    }
}

