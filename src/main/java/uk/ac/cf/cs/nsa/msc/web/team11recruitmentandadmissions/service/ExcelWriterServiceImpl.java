package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.ExcelWriterUtils.writeData;

@Service
public class ExcelWriterServiceImpl implements ExcelWriterService {
    @Override
    public <T> boolean createExcelSheet(HttpServletResponse response, List<T> data) throws IOException {
        return writeData(response, data);
    }

}
