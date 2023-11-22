package com.webwaves.api.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.webwaves.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.token.security.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Voll.med API") //emissor da chamada
                    .withSubject(usuario.getLogin()) //usuario que fez a request
                    .withExpiresAt(dataExpiracao()) //Tempo de duração do token
                    .sign(algorithm); //assinatura usada
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerao o token JWT", exception);
        }
    }

    public Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
