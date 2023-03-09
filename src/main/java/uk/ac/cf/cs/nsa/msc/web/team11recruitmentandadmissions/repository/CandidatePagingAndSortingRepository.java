package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;

import java.time.LocalDate;

public interface CandidatePagingAndSortingRepository extends PagingAndSortingRepository<Candidate, String> {

}

