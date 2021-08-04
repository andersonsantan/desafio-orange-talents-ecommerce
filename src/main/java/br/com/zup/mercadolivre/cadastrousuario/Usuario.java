package br.com.zup.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    @Length(min = 6)
    private String senha;
    @NotNull
    private LocalDateTime instante = LocalDateTime.now();

    public Usuario() {
    }

    /**
     *
     * @param email, String no formato de email
     * @param senha, String em formato limpo
     */
    public Usuario(String email, SenhaLimpa senhaLimpa) {
        Assert.isTrue(StringUtils.hasLength(email),"Esse email não pode ser nulo");
        Assert.notNull(senhaLimpa,"Senha não pode ser em branco");

        this.email = email;
        this.senha = senhaLimpa.hash();
    }

}
