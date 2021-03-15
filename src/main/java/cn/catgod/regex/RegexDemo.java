package cn.catgod.regex;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/15
 */
public class RegexDemo {

    @Test
    public void readForRegix() throws IOException {
        Pattern pattern1 = compile("orderFrom");
        Pattern pattern2 = compile("fulfillOrderNo");
        Pattern pattern3 = compile("finishTime");
        Set<String> orderingMsgs = new HashSet<>();
        Set<String> fulfillMsgs = new HashSet<>();
        Set<String> finishMsgs = new HashSet<>();
        int num = 0;
        List<String> lines = FileUtils.readLines(new File("/Users/xmly/Downloads/fixTrackOrders.txt"), StandardCharsets.UTF_8);

        for (String line : lines) {
            String tradeOrderNo = JsonPath.parse(line).read("$.tradeOrderNo");

            if(pattern1.matcher(line).find()){
                orderingMsgs.add(tradeOrderNo);
//                System.out.println(line);
            }else if(pattern2.matcher(line).find()){
                fulfillMsgs.add(tradeOrderNo);
//                System.out.println(line);
            }else if(pattern3.matcher(line).find()){
                finishMsgs.add(tradeOrderNo);
//                System.out.println(line);
            }
        }
        //202101294001452002724267062单号不存在
        finishMsgs.removeAll(orderingMsgs);
        System.out.println(finishMsgs.size());
    }
    @Test
    public void normal_use(){
        Pattern pattern = compile("abc");
        Matcher matcher = pattern.matcher("abc");
        assertThat(matcher.find()).isTrue();
    }
}
