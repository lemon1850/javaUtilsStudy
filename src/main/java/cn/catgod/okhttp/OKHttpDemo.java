package cn.catgod.okhttp;

import lombok.SneakyThrows;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/18
 */
public class OKHttpDemo {

    private final OkHttpClient client = new OkHttpClient();

    @SneakyThrows
    @Test
    public void a(){

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        }

    }
}
