package cn.catgod.lombok;

import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * value object
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/17
 */
@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
@ToString
@EqualsAndHashCode()
public class DtoDemo {

    private final @NotNull Long userId;
    private final @NotNull String name;

}
