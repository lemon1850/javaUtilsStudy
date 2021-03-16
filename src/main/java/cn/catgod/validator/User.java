package cn.catgod.validator;
import lombok.Data;
import org.hibernate.validator.constraints.CodePointLength;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.time.DurationMax;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
public class User {

    @NotNull(message = "Name cannot be null")
    private String name;

    @AssertTrue
    private boolean working;

    /**
     * alidates that the annotated property value has a size between the attributes min and max;
     * can be applied to String, Collection, Map, and array properties.
     */
    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    private String email;

    @Digits(integer = 5, fraction = 2, message = "money精度有问题")
    private Double money;

    /**
     * validates that the property is not null or empty;
     * can be applied to String, Collection, Map or Array values
     */
    @NotEmpty(message = "tag不能为空")
    private List<String> tags;
    /**
     * can be applied only to text values
     * and validates that the property is not null or whitespace.
     */
    @NotBlank(message = "comment不能为空")
    private String comment;

    @PastOrPresent(message = "createTime需要是一个过去或现在的时间")
    private LocalDateTime createTime;

    @FutureOrPresent(message = "finishTime完成时间应该是一个现在或者将来的时间")
    private LocalDateTime finishTime;

    @NotEmpty(message = "hobbies不能为空")
    private List<@NotBlank(message = "hobby不能为空") String> hobbies;

    private Optional<@Min(value = 10, message = "income不能少于10块") BigDecimal> income;

    @PositiveOrZero()
    private BigDecimal cost;

    @NegativeOrZero()
    private BigDecimal debate;

    /**
     * hibernate自定义约束标签
     */
    @Range
    private BigDecimal moneyForBuy;

    @Length(min = 1, max = 3, message = "firstName长度不满足")
    private String firstName;

    @CodePointLength(min = 1, max = 3, message = "lastName长度不满足")
    private String lastName;
}
