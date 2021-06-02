package cn.catgod.okhttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;
import okhttp3.FormBody.Builder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/18
 */
public class OKHttpDemo {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @SneakyThrows
    @Test
    public void www_form_urlencoded() {

        String clubs = "7747,3270,3303,30688,7732,30303,3301,8211,28054,24068,3296,30294,5462,27532,31028,3307,3323,31063,31062,3350,6062,5987,31098,19836,213317548";
        String[] split = clubs.split(",");

        String key = "params";
        String queryParamTemplate = "{\"group\":\"business-vip-club\",\"scope\":\"default\",\"url\":\"\",\"iface\":\"com.ximalaya.business.vip.club.thrift.api.ThriftVipClubService$Iface\",\"artifactId\":\"business-vip-club-thrift-api\",\"method\":\"getClubByClubId\",\"authAapplication\":\"\",\"params\":\"{\\n    \\\"clubId\\\": %d\\n}\"}";
        String requestParam = String.format(queryParamTemplate, 5462);
        FormBody formBody = new Builder().add(key, requestParam).build();
        String url = "http://cms.9nali.com/thriftTester/v3/invoke.htm";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", "JSESSIONID=E766EF7B58E441E12E62CE9B63DC7A09")
                .addHeader("Content-Type", "application/x-www-form-urlencoded;")
                .post(formBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            JsonNode jsonNode = objectMapper.readTree(response.body().string());
            JsonNode content = objectMapper.readTree(jsonNode.get("content").asText());
            int clubId = content.get("clubId").intValue(); //53
        }
    }
}
