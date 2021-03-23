package cn.catgod.reactor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * <pre>
 *     concat, concatWith, merge, zip, combineLatest,
 * </pre>
 *
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/23
 */
public class CombinePublisherDemo {

    int min = 1;
    int max = 5;
    Flux<Integer> evenNumbers;
    Flux<Integer> oddNumbers;

    @BeforeEach
    void init() {
        evenNumbers = Flux.range(min, max)
                .filter(x -> x % 2 == 0);
        oddNumbers = Flux.range(min, max)
                .filter(x -> x % 2 == 1);

    }

    /**
     * 类似于字符串的连接
     */
    @DisplayName("concat, contactWith")
    @Test
    void a() {
        Flux<Integer> concat = Flux.concat(evenNumbers, oddNumbers);
        Flux<Integer> concat2 = evenNumbers.concatWith(oddNumbers);

        StepVerifier.create(concat)
                .expectNext(2)
                .expectNext(4)
                .expectNext(1)
                .expectNext(3)
                .expectNext(5)
                .expectComplete()
                .verify();
    }

    @DisplayName("")
    @Test
    void b() {
//        Flux.combineLatest(evenNumbers, oddNumbers, (a,))
    }
}
