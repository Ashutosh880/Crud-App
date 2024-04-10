package org.spring.rest.crud.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.spring.rest.crud.util.RoleValidation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface Role {

    String message() default "{role should be user and admin}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
