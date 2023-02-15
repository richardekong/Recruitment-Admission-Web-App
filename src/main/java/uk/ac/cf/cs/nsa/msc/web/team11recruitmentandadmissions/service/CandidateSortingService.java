package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;

import java.util.List;

public interface CandidateSortingService {

    Slice<Candidate> sortNothing();
    Slice<Candidate> sort(Pageable pageable);
    Slice<Candidate> sortByApplicationStatusCode(Pageable pageable);

    Slice<Candidate> sortByGender(Pageable pageable);

    Slice<Candidate> sortByFeeStatus(Pageable pageable);

    Slice<Candidate> sortByCorrespondenceLangWelsh(Pageable pageable);

    Slice<Candidate> sortByWelshSpeaker(Pageable pageable);

    Slice<Candidate> sortByContextualFlag(Pageable pageable);

    Slice<Candidate> sortByKeepingWarmEmailSent(Pageable pageable);

    Slice<Candidate> sortByInviteInterview(Pageable pageable);

    Slice<Candidate> sortByFTPChecked(Pageable pageable);

    Slice<Candidate> sortByNonStandardQualificationsChaserEmail(Pageable pageable);

    Slice<Candidate> sortByOfferEmailSent(Pageable pageable);

}


