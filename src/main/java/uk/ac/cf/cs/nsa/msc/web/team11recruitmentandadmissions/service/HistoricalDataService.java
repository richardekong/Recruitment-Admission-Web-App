package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

import java.util.List;

public interface HistoricalDataService {

    HistoricalData save (HistoricalData historicalData);

    List<Double> selectRatiosFromHistoricalData();

}