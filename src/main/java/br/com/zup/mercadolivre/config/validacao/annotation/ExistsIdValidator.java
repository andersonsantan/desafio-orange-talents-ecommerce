package br.com.zup.mercadolivre.config.validacao.annotation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    String fieldName;
    Class<?> dClass;


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initialize(ExistsId constraint) {
        fieldName = constraint.fieldName();
        dClass = constraint.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) {
            return true;
        }
        Query query = entityManager.createQuery("select 1 from "+dClass.getName()+" where "+fieldName+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <=1, "Existe mais de um "+dClass+" com o atributo "+fieldName+" e valor = "+value);
        return !list.isEmpty();
    }
}
