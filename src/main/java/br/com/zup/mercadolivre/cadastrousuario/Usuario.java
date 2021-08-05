package br.com.zup.mercadolivre.cadastrousuario;

import br.com.zup.mercadolivre.config.security.Perfil;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
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

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
