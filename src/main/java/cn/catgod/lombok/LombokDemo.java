package cn.catgod.lombok;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/9
 */
@Getter
@Setter
@NoArgsConstructor
public class LombokDemo {

    @Test
    public void cleanup_demo(){
        @Cleanup("close") InputStream is = this.getClass().getResourceAsStream("res.txt");
    }

    private @Setter(AccessLevel.PRIVATE) Long id;
    /**
     * toString需要通过getTransactions访问
     * 懒加载，通过AtomicReference控制并发
     */
    @Getter(lazy = true)
    private final Map<String, Long> transactions = getTransactions();

    /**
     * SneakyThrows 处理受检异常
     * @return
     */
    @SneakyThrows
    public String resourceAsString() {
        try (InputStream is = this.getClass().getResourceAsStream("sure_in_my_jar.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
    @Synchronized
    public void putValueInCache(String key, Object value){

    }
    private Map<String, Long> getTransactions() {

        final Map<String, Long> cache = new HashMap<>();
        List<String> txnRows = new ArrayList<>();

        txnRows.forEach(s -> {
            String[] txnIdValueTuple = s.split(",");
            cache.put(txnIdValueTuple[0], Long.parseLong(txnIdValueTuple[1]));
        });

        return cache;
    }


}
