package com.rookie.stack.im.auth.common.validate;

import com.rookie.stack.im.auth.domain.model.req.UpdatePasswordReq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author eumenides
 * @description
 * @date 2024/9/27
 */
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UpdatePasswordReq> {
    private String message;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }
    @Override
    public boolean isValid(UpdatePasswordReq req, ConstraintValidatorContext context) {
        if (req == null) {
            // TODO
            return true;
        }
        boolean isValid = req.getNewPassword().equals(req.getRetryPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    // 指定错误节点
                    .addPropertyNode("retryPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
