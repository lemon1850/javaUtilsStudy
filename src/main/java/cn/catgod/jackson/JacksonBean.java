package cn.catgod.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/15
 */
@NoArgsConstructor
@Data
public class JacksonBean {

    private String name;

    private Map<String, String> properties;

    /**
     * 把map里面独立出来一个个field:value
     * <pre>{@code
     *     {
     *         "attr1":"val1",
     *         "attr2":"val2"
     *     }
     * }</pre>
     **/
    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
