package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;

public interface PlacesOfferedService {
    Integer getMostRecentPlacesOffered();

    PlacesOffered savePlacesOffered(PlacesOffered placesOffered);
}
