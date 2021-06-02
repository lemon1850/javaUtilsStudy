package cn.catgod.mysql;

import cn.hutool.json.JSONUtil;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/5/27
 */
public class MysqlDemo {

//    @SneakyThrows
//    @Test
//    public void a(){
//        File binlogFile = new File("/Users/xmly/Downloads/binlog.a");
//        EventDeserializer eventDeserializer = new EventDeserializer();
//        eventDeserializer.setCompatibilityMode(
//                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
//                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
//
//        );
//        BinaryLogFileReader reader = new BinaryLogFileReader(binlogFile, eventDeserializer);
//        try {
//            for (Event event; (event = reader.readEvent()) != null; ) {
//                EventData data = event.getData();
//                System.out.println(data.toString());
////                if (data != null && data.getClass().isAssignableFrom(RowsQueryEventData.class)) {
////                    RowsQueryEventData dmlData = (RowsQueryEventData) data;
////                    System.out.println(dmlData.getQuery());
////                }
//            }
//        } finally {
//            reader.close();
//        }
//    }

    @SneakyThrows
    @Test
    public void b() {
        BinaryLogClient client = new BinaryLogClient("10.20.255.9", 3306, "root", "password!");
//        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "930320");
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(new BinaryLogClient.EventListener() {

            @Override
            public void onEvent(Event event) {
                EventData data = event.getData();
                if (data != null && data.getClass().isAssignableFrom(RowsQueryEventData.class)) {
                    RowsQueryEventData dmlData = (RowsQueryEventData) data;
                    System.out.println(dmlData.getQuery());
                }
                if (data != null && data.getClass().isAssignableFrom(UpdateRowsEventData.class)) {
                    UpdateRowsEventData dmlData = (UpdateRowsEventData) data;
                    System.out.println(JSONUtil.toJsonStr(dmlData.getRows()));

                }
                if (data != null && data.getClass().isAssignableFrom(WriteRowsEventData.class)) {
                    WriteRowsEventData dmlData = (WriteRowsEventData) data;
                    System.out.println(JSONUtil.toJsonStr(dmlData.getRows()));

                }
//                System.out.println(event.toString());
            }
        });
        client.connect();
    }

}
