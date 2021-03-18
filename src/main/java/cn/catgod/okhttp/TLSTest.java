package cn.catgod.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.Collections;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2020/6/1
 */
public class TLSTest {

    public static void main(String[] args) throws IOException {

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_3)
//                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                        //tls1.3
                        CipherSuite.TLS_AES_256_GCM_SHA384

                )
                .build();
//        public x coord: 96181472774402640133316553508452804000726002479739424968517707684453591412773
//        public y coord: 86028716436454085632717549164577586044942216470636603723783733789573654734654
//        40296682570790137282897943990552345653394616678328338727399857204013651053810388576134508224283151861843168354720700674543945360336538145367366662892995727730792039348123389946275835319801916416984192613702121658975790268865358581990527999511189288836802403518274002367600541424698847568395853509637564297909971

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();
//        Request request = new Request.Builder().url("https://www.taobao.com").build();
        Request request = new Request.Builder().url("https://www.sina.com.cn/").build();
        try (Response response = client.newCall(request).execute()) {
//            System.out.println(JSON.toJSONString(response));
        }
    }
}
