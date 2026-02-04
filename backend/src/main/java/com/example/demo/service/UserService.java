package com.example.demo.service;

import com.example.demo.dto.UserResponse;
import com.example.demo.exception.RegraDeNegocioException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.security.SecureRandom;
import java.util.List;



@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService service;

    @Autowired
    private  MailService mailService;


    // mostra todos os usuário no formato UserResponse

    public List<UserResponse> getAllUsers(){
        List<UserResponse> response = repository.findAll().stream().map(UserResponse::new).toList();
        return response;
    }

    // mostram um usuário específico pelo email no formato UserResponse

    public UserResponse getUserById(Integer id){
        User usuario = repository.findById(id).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado!"));
        UserResponse response = new UserResponse(usuario);
        return  response;
    }

    // cria usuários e retorna um UserResponse e manda um email

    @Transactional
    public UserResponse createUser(User user){
        if(repository.existsByEmail(user.getEmail())){
            throw  new RegraDeNegocioException("Email já cadastrado!");
        }

        var novaSenha = passwordEncoder.encode(user.getPassword());
        user.setPassword(novaSenha);

        SecureRandom secureRandom = new SecureRandom();
        int code = secureRandom.nextInt(1000000);
        String codigo = String.format("%06d",code);

        user.setCode(codigo);
        repository.save(user);
        UserResponse response = new UserResponse(user);

        Context context = new Context();
        context.setVariable("nome",user.getNome());
        context.setVariable("codigoVerificacao",codigo);
        mailService.sendEmail(user.getEmail(),"Seu código de verificação: "+ codigo,context,"email-verificacao");

        return  response;
    }

    // deleta o usuário pelo id

    public String deleteUsuarios(Integer id) {
            repository.deleteById(id);
            return "Usuário deletado!";
    }

    //verifica email do usuario pelo codigo

    public void verificaUsuario(String code){
        User usuario = repository.findByCode(code).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));

        usuario.setStatus(true);
        usuario.setCode(null);

        repository.save(usuario);

    }
    // metodo padrão do UserDetaisService

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = repository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado!"));
        return  usuario;
    }
}
