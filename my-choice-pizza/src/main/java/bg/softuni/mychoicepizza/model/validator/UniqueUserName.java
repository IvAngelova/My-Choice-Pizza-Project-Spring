package bg.softuni.mychoicepizza.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUserName {
    String message() default "Потребителското име вече е заето.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
