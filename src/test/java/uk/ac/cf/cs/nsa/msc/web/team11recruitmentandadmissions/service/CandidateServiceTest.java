package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

;

@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @Mock //create a mock of the real candidate repository reference
    CandidateRepository repository;

    @InjectMocks // Injects a mock of the real candidate service implementation
    CandidateServiceImpl candidateService;

    private Candidate candidate;

    private LinkedList<Candidate> candidates;


    @BeforeEach
    void setUp() {
        candidate = TestData.createCandidate();
        candidates = new LinkedList<>();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * A unit test that verifies  the candidate service's
     * ability to save a new Candidate's record to the database.
     * Here Behavior Driven Development technique is applied.
     **/
    @Test
    void save() {
        //precondition to ensure that the candidate to be saved doesn't exist in the database
        given(repository.findCandidateByStudentNo(candidate.getStudentNo())).willReturn(null);

        //determine if the save operation will create a new candidate record in the database
        given(repository.save(candidate)).willReturn(candidate);

        //Save the candidate and maintain a reference to the saved candidate
        Candidate theReturnedCandidate = candidateService.save(candidate);

        //The candidateService's save(..) method should delegate the repository to save the new candidate's record to the database
        then(repository).should().save(candidate);

        assertThat(theReturnedCandidate).isNotNull();
    }

    @Test
    void saveAll() {

        var candidatesTobeAdded = List.of(new Candidate(), new Candidate());

        candidates.addAll(candidatesTobeAdded);

        //satisfy a precondition requiring that none of the candidates to be saved exists in the database
        given(repository.saveAll(
                candidates
                        .stream()
                        .filter(c -> !repository.existsById(c.getStudentNo()))
                        .collect(Collectors.toList()))
        ).willReturn(candidates);

        //save the candidates and maintain their references in a list
        var theReturnedCandidates = candidateService.saveAll(candidates);

        //then spy method saveAll(...) to verify if the repository was delegated to invoke its saveAll(...) methods
        then(repository).should().saveAll(candidates);

        assertThat(theReturnedCandidates).isNotNull();

        assertThat(theReturnedCandidates).asList().isNotEmpty();

        assertThat(theReturnedCandidates).asList().size().isEqualTo(candidatesTobeAdded.size());

    }

    @Test
    void findAll() {
    }

    @Test
    void findByStudentNo() {
    }

    @Test
    void updateCandidate() {
    }

    @Test
    void countCandidatesByOfferEmailSent() {
    }
}