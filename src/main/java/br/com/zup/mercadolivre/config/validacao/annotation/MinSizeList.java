package br.com.zup.mercadolivre.config.validacao.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = MinSizeListValidator.class)
public @interface MinSizeList {
    String message() default "Não tem a quantidade mínima";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    int value() default 0;
}
