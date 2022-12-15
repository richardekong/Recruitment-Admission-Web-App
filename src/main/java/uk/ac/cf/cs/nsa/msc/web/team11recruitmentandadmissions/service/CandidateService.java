package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public interface CandidateService {


    Candidate save(Candidate candidate);

    List<Candidate> saveAll(LinkedList<Candidate> candidates);

    List<Candidate> findAll();

    Candidate findByStudentNo(String studentNo);

    Candidate updateCandidate(Candidate candidateToUpdate);

    Integer countCandidatesByOfferEmailSent(YesOrNoOption option);
}
