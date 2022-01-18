package br.dev.multicode.api.http.validators.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import br.dev.multicode.api.http.validators.ValueOfEnumValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface ValueOfEnum {

  /**
   * @return class containing enum values to which this String should match
   */
  Class<? extends Enum<?>> enumClass();

  /**
   * @return the error message template
   */
  String message()  default "must be any of enum {enumClass}";

  /**
   * @return the group the constraint belongs to
   */
  Class<?>[] groups() default {};

  /**
   * @return the payload associated to the constraint
   */
  Class<? extends Payload>[] payload() default {};

}
