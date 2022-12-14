package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.HistoricalDataRepository;

@Service
public class HistoricalDataServiceImpl implements HistoricalDataService {

    private HistoricalDataRepository repository;

    @Autowired
    public void setRepository(HistoricalDataRepository repository){
        this.repository = repository;
    }

    @Override
    public HistoricalData save(HistoricalData historicalData) {
        return repository.save(historicalData);
    }
}
