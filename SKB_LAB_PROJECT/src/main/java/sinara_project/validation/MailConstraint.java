package sinara_project.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MailValidator.class)
@Documented
public @interface MailConstraint {
    String message() default "{Invalid input, try again}";

    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};
}
