package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidatePagingAndSortingRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Service
public class CandidateSortingServiceImpl implements CandidateSortingService {

    private CandidatePagingAndSortingRepository pagingAndSortingRepo;

    private CandidateRepository candidateRepository;

    @Autowired
    void setPagingAndSortingRepo(
            CandidatePagingAndSortingRepository pagingAndSortingRepo) {
        this.pagingAndSortingRepo = pagingAndSortingRepo;
    }

    @Autowired
    void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Slice<Candidate> sortNothing() {
        return createSlice(candidateRepository.findAll(), PageRequest.of(0, 100));
    }

    @Override
    public Slice<Candidate> sort(Pageable pageable) {
        return pagingAndSortingRepo.findAll(pageable);
    }

    @Override
    public Slice<Candidate> sortByApplicationStatusCode(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getApplicationStatusCode().getCode()), pageable);
    }

    @Override
    public Slice<Candidate> sortByGender(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getGender().getGender()), pageable);
    }

    @Override
    public Slice<Candidate> sortByFeeStatus(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getFeeStatus().getFeeStatus()), pageable);
    }

    @Override
    public Slice<Candidate> sortByCorrespondenceLangWelsh(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getCorrespondenceLangWelsh().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByWelshSpeaker(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getWelshSpeaker().getResponse()), pageable);
    }

    @Override
    public Slice<Candidate> sortByContextualFlag(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getContextualFlag().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByKeepingWarmEmailSent(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getKeepingWarmEmailSent().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByInviteInterview(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getInviteInterview().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByFTPChecked(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getFTPChecked().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByNonStandardQualificationsChaserEmail(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getNonStandardQualificationsChaserEmail().getOption()), pageable);
    }

    @Override
    public Slice<Candidate> sortByOfferEmailSent(Pageable pageable) {
        return sortBy(comparing(candidate -> candidate.getOfferEmailSent().getOption()), pageable);
    }

    private Slice<Candidate> createSlice(List<Candidate> content, Pageable pageable) {
        return new SliceImpl<>(content, pageable, true);
    }

    private Stream<Candidate> getParallelStreamOfCandidates() {
        return candidateRepository.findAll().parallelStream();
    }

    private Slice<Candidate> sortBy(Comparator<Candidate> sortFunction, Pageable pageable) {
        return pageable.getSort().stream().sorted()
                .map(order -> order.getDirection().isAscending())
                .map(isAscending -> isAscending
                        ? getParallelStreamOfCandidates()
                        .sorted(sortFunction)
                        .collect(Collectors.toList())
                        : getParallelStreamOfCandidates()
                        .sorted(sortFunction.reversed())
                        .collect(Collectors.toList()))
                .map(candidates -> createSlice(candidates, pageable))
                .findFirst()
                .orElseThrow(() -> new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

