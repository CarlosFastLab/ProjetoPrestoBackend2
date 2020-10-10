package com.presto.service;

import com.presto.model.Usuario;
import com.presto.repository.UsuarioRepository;
import com.presto.util.JavaMailApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioServiceImp implements  UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<?> validaEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailContaining(email);
        if(usuario.isPresent()){
            return new ResponseEntity<>("Usuario existente!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> enviaSenhaPorEmail(String email) {
       Optional<Usuario> usuario = usuarioRepository.findByEmailContaining(email);
       if(usuario.isPresent()) {
           JavaMailApp javamail = new JavaMailApp();

           //--------------------Gerador de senha----------------------------------
           String senha;
           Random gerador = new Random();
           char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
           StringBuffer sb = new StringBuffer();
           for (int i = 0; i < 8; i++) {
               int ch = gerador.nextInt(letras.length);
               sb.append(letras[ch]);
           }
           senha = sb.toString();
           usuario.get().setSenha(senha);
           usuarioRepository.save(usuario.get());

           javamail.enviarEmail(email, senha);

           return new ResponseEntity<>("Email enviado!", HttpStatus.OK);
       }
       return new ResponseEntity<>("Usuario inexistente!", HttpStatus.NOT_FOUND);

    }


}
