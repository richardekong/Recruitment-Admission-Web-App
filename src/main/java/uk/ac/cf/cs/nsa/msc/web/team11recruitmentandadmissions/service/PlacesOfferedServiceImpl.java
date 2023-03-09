package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.PlacesOfferedRepository;

import java.util.LinkedList;

@Service
public class PlacesOfferedServiceImpl implements PlacesOfferedService {

    private PlacesOfferedRepository repository;

    @Autowired
    private void setRepository(PlacesOfferedRepository repository) {
        this.repository = repository;
    }


    @Override
    public Integer getMostRecentPlacesOffered() {
        return repository.findAllPlacesOfferedDescendingOrder()
                .stream()
                .findFirst()
                .orElse(new PlacesOffered())
                .getPlacesOffered();
    }

    @Override
    public PlacesOffered savePlacesOffered(PlacesOffered placesOffered) {
        return repository.save(placesOffered);
    }
}
