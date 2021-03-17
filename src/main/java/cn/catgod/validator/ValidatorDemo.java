package cn.catgod.validator;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/10
 */
@Log4j2
public class ValidatorDemo {

    @DisplayName("validate Bean")
    @Test
    public void nomarl_validator_bean(){

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = new User();
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        user.setMoney(123456.12);
        user.setCreateTime(LocalDateTime.now().plusDays(1));
        user.setFinishTime(LocalDateTime.now().plusDays(-1));
        user.setHobbies(List.of("f", ""));
        user.setIncome(Optional.of(BigDecimal.TEN));

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            log.error(violation.getMessage());
        }

    }
}
