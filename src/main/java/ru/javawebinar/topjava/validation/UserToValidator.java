package ru.javawebinar.topjava.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

@Component
public class UserToValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;

        User user = null;
        try {
            user = userService.getByEmail(userTo.getEmail());
        } catch (NotFoundException e) {
            return;
        }
        AuthorizedUser authorizedUser = SecurityUtil.safeGet();
        if (user != null && authorizedUser == null) {
            errors.rejectValue("email", "email","User with this email already exists");
        } else if (authorizedUser != null){
            if (user.getId() != authorizedUser.getId()) {
                errors.rejectValue("email", "email","User with this email already exists");
            }
        }
    }
}
