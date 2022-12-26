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

    Candidate candidate;


    @BeforeEach
    void setUp() {
        candidate = TestData.createCandidate();
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