package cn.catgod.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/12
 */
@Slf4j
public class JacksonDemo {

    public static ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule()) //zhichi
            .build();
    static {
        /**
         * WRAP_ROOT_VALUE 指定key of wrapper entity
         */
//        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);


    }
    @Test
    public void marshalrl() throws JsonProcessingException {
        var jacksonBean = new JacksonBean();
        jacksonBean.setName("weiqiang");
//        jacksonBean.setProperties(Map.of("age", "27", "sex","male"));
        jacksonBean.setAddress("广兰路");
        jacksonBean.setCreateTime(LocalDateTime.now());
        jacksonBean.setLastUpdateTime(LocalDateTime.now());
//        jacksonBean.setContext(mapper.writeValueAsString(Map.of("k1", "v1", "k2","v2")));
        System.out.println(mapper.writeValueAsString(jacksonBean));
    }

    @SneakyThrows
    @Test
    public void unmarshal() {
        String jacksonBeanStr = "{\"name\":\"weiqiang\",\"address\":\"广兰路:second Way\",\"createTime\":\"2021-03-19 12:42:29\",\"lastUpdateTime\":\"2021-03-19 12:42:29\",\"context\":null,\"bySingleMethod\":\"nameBySingleMethod\"}\n";
        JacksonBean jacksonBean = mapper.readValue(jacksonBeanStr, JacksonBean.class);
        System.out.println(jacksonBean);
    }
}
