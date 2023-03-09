package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.HistoricalDataRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricalDataServiceImpl implements HistoricalDataService {

    private HistoricalDataRepository repository;

    @Autowired
    public void setRepository(HistoricalDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public HistoricalData save(HistoricalData historicalData) {
        return repository.save(historicalData);
    }

    @Override
    public List<Double> selectRatiosFromHistoricalData() {
        return repository.selectRatiosFromHistorical();
    }

    @Override
    public List<HistoricalData> saveAll(LinkedList<HistoricalData> historicalData) {
        List<HistoricalData> unsavedHistoricalData = historicalData
                .stream()
                .filter(data -> !repository.existsById(data.getAcademicYear()))
                .collect(Collectors.toList());
        return repository.saveAll(unsavedHistoricalData);
    }

    @Override
    public List<HistoricalData> findAll() {
        return repository.findAll();
    }
}
