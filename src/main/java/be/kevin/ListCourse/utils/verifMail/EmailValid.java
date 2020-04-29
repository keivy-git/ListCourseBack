package be.kevin.ListCourse.utils.verifMail;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface EmailValid {
    String message() default "Email invalide";
    Class<?>[]  groups() default{};
    Class<? extends Payload>[] payload() default{};

}
