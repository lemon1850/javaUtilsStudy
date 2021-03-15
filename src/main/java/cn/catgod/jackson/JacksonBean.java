package cn.catgod.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/15
 */
@NoArgsConstructor
@Data
/**
 * @see SerializationFeature.WRAP_ROOT_VALUE
 */
@JsonRootName("user")
public class JacksonBean {

    private String name;

    private Map<String, String> properties;

    private String address;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class )
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime lastUpdateTime;

    /**
     * serialize a property to json object
     * <pre>{@code
     *     {
     *         "context":"{\"k2\":\"v2\",\"k1\":\"v1\"}", //before
     *         "context":{   //after
     *           "k2":"v2",
     *           "k1":"v1"
     *         }
     *     }
     * }</pre>
     * */
    @JsonRawValue(value = true)
    private String context;

    /**
     * 把map field as standard properties
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

    /**
     * 替换默认的getter
     * @return
     */
    @JsonGetter("address")
    public String getAddressSecondWay() {
        return address + ":second Way";
    }


    public String getAddress() {
        return address;
    }

    /**
     * 序列化真个对象的唯一调用方法
     * @return
     */
//    @JsonValue
    public String getBySingleMethod(){
        return "nameBySingleMethod";
    }
}
