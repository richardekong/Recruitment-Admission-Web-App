package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.PlacesOfferedRepository;

import java.util.LinkedList;

@Service
public class PlacesOfferedServiceImpl implements PlacesOfferedService {

    private PlacesOfferedRepository repository;

    @Autowired
    private void setRepository(PlacesOfferedRepository repository){
        this.repository = repository;
    }


    @Override
    public Integer getMostRecentPlacesOffered() {
        LinkedList<Integer> values = (LinkedList<Integer>) repository.findAll();
        return values.getLast();
    }
}
