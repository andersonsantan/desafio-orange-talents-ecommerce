package br.com.zup.mercadolivre.config.validacao.annotation;

import br.com.zup.mercadolivre.produto.NovaCaracteristicaRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class CaracteristicasRepetidasValidator implements ConstraintValidator<CaracteristicasRepetidas, Set<NovaCaracteristicaRequest>> {

    @Override
    public void initialize(CaracteristicasRepetidas constraintAnnotation) {

    }

    @Override
    public boolean isValid(Set<NovaCaracteristicaRequest> novaCaracteristicaRequests, ConstraintValidatorContext constraintValidatorContext) {
        if (!novaCaracteristicaRequests.isEmpty()){
            return true;
        }
        for (NovaCaracteristicaRequest caracteristica : novaCaracteristicaRequests) {
            if (novaCaracteristicaRequests.contains(caracteristica.getNome())) {
                return true;
            }
        }

        return false;
    }
}
