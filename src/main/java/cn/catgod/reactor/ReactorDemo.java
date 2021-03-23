package cn.catgod.reactor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * reactor是push模型 无限元素 提供backpressure机制
 * stream是pull模型 有限元素
 *
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/23
 */
public class ReactorDemo {

    @DisplayName("初次见面")
    @Test
    void normalUse() {

        Flux<Integer> just1 = Flux.just(1, 2, 3, 4);
        Mono<Integer> just2 = Mono.just(1);

        List<Integer> elements = new ArrayList<>();
        just1.log().subscribe(elements::add);

        just1.log().subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                elements.add(integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @DisplayName("操作流")
    @Test
    void a() {
        Flux.just(1, 2, 3)
                .log()
                .map(i -> i + 1)
                .subscribe(System.out::println);

        //结合两个流
        Flux.just(1, 2, 3)
                .log()
                .map(i -> i + 1)
                .zipWith(Flux.range(0, Integer.MAX_VALUE),
                        (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(System.out::println);
    }

    @DisplayName("背压")
    @Test
    void backpressureUse() {

        Flux<Integer> just1 = Flux.just(1, 2, 3, 4);
        List<Integer> elements = new ArrayList<>();
        just1.log().subscribe(new Subscriber<Integer>() {

            int onNextNum;
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(2);
            }

            @Override
            public void onNext(Integer integer) {
                elements.add(integer);
                onNextNum++;
                if (onNextNum % 2 == 0) {
                    subscription.request(2);
                }

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @DisplayName("hotStream")
    @Test
    void b() {
        //publish可以让subscribe()立即消费处理
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
            while (true) {
                fluxSink.next(System.currentTimeMillis());
            }
        }).publish();

        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);

        publish.connect();
    }

    @DisplayName("concurrency")
    @Test
    void c() {
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);
    }
}
