package cn.catgod.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/12
 */
@Slf4j
public class JacksonDemo {

    public static ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void marshalrl() throws JsonProcessingException {
        var jacksonBean = new JacksonBean();
        jacksonBean.setName("weiqiang");
        jacksonBean.setProperties(Map.of("age", "27", "sex","male"));
        System.out.println(objectMapper.writeValueAsString(jacksonBean));
    }
}
