package br.com.zup.mercadolivre.cadastrousuario;

import br.com.zup.mercadolivre.config.validacao.annotation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NovoUsuarioRequest {


    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email invalido")
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Esse email já existe")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    public NovoUsuarioRequest() {

    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoUsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(email, new SenhaLimpa(senha));
    }


}
