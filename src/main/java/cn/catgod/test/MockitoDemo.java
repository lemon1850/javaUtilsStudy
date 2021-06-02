package cn.catgod.test;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/5/29
 */
public class MockitoDemo {

    @Test
    void name() {

        List mockList = mock(List.class);
        when(mockList.get(anyInt())).thenReturn("aaa");
        //用于验证发送顺序之类的
        InOrder inOrder = inOrder(mockList);

        verifyNoInteractions(mockList, mockList);
    }
}
