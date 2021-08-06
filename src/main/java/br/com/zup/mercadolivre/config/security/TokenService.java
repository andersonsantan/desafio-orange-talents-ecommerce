package br.com.zup.mercadolivre.config.security;


import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${mercadolivre.jwt.expiration}")
    private String expiration;

    @Value("${mercadolivre.jwt.secret}")
    private String secretKay;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date expirationDate = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Mercado Livre")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKay)
                .compact();

    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secretKay).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secretKay).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
