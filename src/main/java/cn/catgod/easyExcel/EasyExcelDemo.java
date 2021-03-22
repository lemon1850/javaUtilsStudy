package cn.catgod.easyExcel;

import com.alibaba.excel.EasyExcel;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/15
 */
public class EasyExcelDemo {

    String path = "/Users/xmly/temp/";
    @Test
    public void writeExcel(){

        String filename = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(filename, DemoData.class).sheet("模板").doWrite(data());
    }

    private List<DemoData> data() {
        var list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
