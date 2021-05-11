package cn.catgod.guava;

import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/5/7
 */
public class ReflectionDemo {

    @Test
    public void a(){
        TypeToken<String> stringTypeToken = TypeToken.of(String.class);

        Type type = stringTypeToken.getType();
        Class<? super String> rawType = stringTypeToken.getRawType();

        TypeToken<Map<String, Long>> mapTypeToken = new TypeToken<Map<String, Long>>() {
        };
        TypeToken<List<String>> listTypeToken = new TypeToken<List<String>>() {};
        Type type1 = listTypeToken.getType();
        Class<? super List<String>> rawType1 = listTypeToken.getRawType();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
    }
}
