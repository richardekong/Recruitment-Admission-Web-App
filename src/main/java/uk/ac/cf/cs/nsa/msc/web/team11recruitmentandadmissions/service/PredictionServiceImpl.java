package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.HistoricalDataRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.PlacesOfferedRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    private HistoricalDataRepository repository;

    private PlacesOfferedService placesOfferedService;

    @Autowired
    public void setRepository(HistoricalDataRepository repository){
        this.repository = repository;
    }

    @Autowired
    public void setPlacesOfferedService(PlacesOfferedService service){
        this.placesOfferedService = service;
    }
    @Override
    public double eliminateOutliers() {
        //sort ratios from historical data repository
        List<Double> ratios = repository.selectRatiosFromHistorical();
        Collections.sort(ratios);
        List<Double> filteredRatios = eliminateOutliers(ratios);
        return getAverage(filteredRatios);
    }

    @Override
    public Integer offersRecommended() {
        return (int)(placesOfferedService.getMostRecentPlacesOffered()/eliminateOutliers());
    }

    private List<Double> eliminateOutliers(List<Double> ratios){
        List<Double> output = new ArrayList<>();
        List<Double> data1;
        List<Double> data2;
        if (!ratios.isEmpty()){
            if (ratios.size() % 2 == 0) {
                data1 = ratios.subList(0, ratios.size() / 2);
                data2 = ratios.subList(ratios.size() / 2, ratios.size());
            } else {
                data1 = ratios.subList(0, ratios.size() / 2);
                data2 = ratios.subList(ratios.size() / 2 + 1, ratios.size());
            }
            double q1 = getMedian(data1);
            double q3 = getMedian(data2);

            for (Double ratio : ratios) {
                if (ratio > q1 && ratio < q3)
                    output.add(ratio);
            }
        }
        return output;
    }

    public double getAverage(List<Double> ratios) {
        double sum = 0.0;
        double average = 0;
        if (!ratios.isEmpty()) {
            for (Double ratio : ratios) {
                sum += ratio;
            }
            average = sum / ratios.size();
        }
        return average;
    }

    private double getMedian(List<Double> data) {
        if (!data.isEmpty() && data.size() % 2 == 0) {
            return (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2;
        }else {
            return data.get(data.size() / 2);
        }
    }
}
