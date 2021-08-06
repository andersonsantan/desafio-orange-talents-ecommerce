package br.com.zup.mercadolivre.config.validacao.annotation;

import br.com.zup.mercadolivre.produto.CaracteristicaProduto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinSizeListValidator implements ConstraintValidator<MinSizeList, List<CaracteristicaProduto>> {

    int valueField;

    @Override
    public void initialize(MinSizeList constraintAnnotation) {
        valueField = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(List<CaracteristicaProduto> list, ConstraintValidatorContext constraintValidatorContext) {
        return list.size() >= valueField;
    }
}
