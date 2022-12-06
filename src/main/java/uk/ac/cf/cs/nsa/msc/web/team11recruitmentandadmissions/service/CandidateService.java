package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;

import java.util.LinkedList;
import java.util.List;

public interface CandidateService{


    Candidate save(Candidate candidate);

    List<Candidate> saveAll(LinkedList<Candidate> candidates);

    Candidate findByStudentNo(String studentNo);
}
