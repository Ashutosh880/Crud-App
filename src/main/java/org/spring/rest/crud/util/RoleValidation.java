package org.spring.rest.crud.util;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.spring.rest.crud.annotation.Role;
import org.spring.rest.crud.entity.Authorities;

import java.util.List;

public class RoleValidation implements ConstraintValidator<Role, List<Authorities>> {

    List<String> roleArray = List.of("User", "Admin");

    @Override
    public boolean isValid(List<Authorities> authoritiesList, ConstraintValidatorContext constraintValidatorContext) {
        return authoritiesList.stream().anyMatch(userRole-> roleArray.contains(userRole.getAuthority()));
    }
}
