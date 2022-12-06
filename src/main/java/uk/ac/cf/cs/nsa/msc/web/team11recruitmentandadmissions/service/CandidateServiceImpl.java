package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
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
}
