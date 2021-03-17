package cn.catgod.java;

import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/1/13
 */
public class BenchmarkRunner {

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
    }
}
