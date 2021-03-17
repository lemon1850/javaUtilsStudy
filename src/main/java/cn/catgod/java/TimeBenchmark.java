package cn.catgod.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/1/13
 */
public class TimeBenchmark {

    @Benchmark
    @Fork(value = 1, warmups = 2)
    public void initDate(){
        new Date();
    }

    @Benchmark
    @Fork(value = 1, warmups = 2)
    public void initLocalDateTime(){
        LocalDateTime.now();
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);;
        String a = "2021-02-01T19:00:22";
        System.out.println(LocalDateTime.parse(a));
    }
}
