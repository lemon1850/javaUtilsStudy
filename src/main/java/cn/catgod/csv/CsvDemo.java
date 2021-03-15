package cn.catgod.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/15
 */
public class CsvDemo {

    public static void a(){
        File file = new File("a.csv");
        CsvSchema.Builder schema = CsvSchema.builder()
                .addColumn("firstName")
                .addColumn("lastName")
                .addColumn("age", CsvSchema.ColumnType.NUMBER);

       CsvMapper csvMapper = new CsvMapper();


    }
}
