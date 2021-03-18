package cn.catgod.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * csv解析
 *
 * @author weiqiang.lin
 * @version 1.0
 * @date 2020/2/26
 */
public class CSVUtils {

    static public <T> List<T> getCsvData(String filePath, Class<T> clazz) throws FileNotFoundException, UnsupportedEncodingException {
        InputStreamReader in = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8);

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
                .withQuoteChar('\'')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

}
