package br.com.willersantosdev.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.willersantosdev.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
            throws ServletException, IOException {

                var serveltPath = request.getServletPath();

                if (serveltPath.startsWith("/tasks/")) {
                     //Pegar a autenticação que é o meu usuário e senha.
                    var authorization = request.getHeader("Authorization");

                    var authEncoded = authorization.substring("Basic".length()).trim();

                    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

                    var authString = new String(authDecoded);

                    String[] credentials = authString.split(":");
                    String userName = credentials[0];
                    String password = credentials[1];

                    System.out.println("Authorization");
                    System.out.println(userName);
                    System.out.println(password);


                    //Validar o usuário
                    var user = this.userRepository.findByUsername(userName);

                    if (user == null) {
                        response.sendError(401);
                    } else {
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    
                        if (passwordVerify.verified) {
                    //Segue viagem
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);
                        } else {
                            response.sendError(401);
                        }
                    }

                } else {
                    filterChain.doFilter(request, response);
                }
    }    
}
