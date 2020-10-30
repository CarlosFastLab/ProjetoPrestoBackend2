package com.presto.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.presto.model.Usuario;
import com.presto.repository.UsuarioRepository;

import com.presto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  UsuarioRepository usuarioRepository;
  @Autowired
  UsuarioService usuarioService;

  @GetMapping("/usuarios")
  public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String email) {
    try {
      List<Usuario> usuarios = new ArrayList<Usuario>();

      if (email == null) {
        usuarioRepository.findAll().forEach(usuarios::add);
      }
      
      else
        usuarioRepository.findByEmailContaining(email);
      if (usuarios.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(usuarios, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/getById/{id}")
  public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id) {
    Optional<Usuario> usuarioData = usuarioRepository.findById(id);

    if (usuarioData.isPresent()) {
      return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/getemail/{email}")
  public ResponseEntity<Usuario> getUsuarioByEmail(@PathVariable("email") String email) {
    Optional<Usuario> usuarioData = usuarioRepository.findByEmailContaining(email);

    if (usuarioData.isPresent()) {
      return new ResponseEntity<>(usuarioData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
    try {
      ResponseEntity respostaDoServico = usuarioService.validaEmail(usuario.getEmail());
      if(respostaDoServico.getStatusCode()== HttpStatus.OK) {
//        Usuario _usuario = new Usuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getDataNascimento()) ;

        usuarioRepository.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
      }
      return new ResponseEntity<>(respostaDoServico.getBody(), respostaDoServico.getStatusCode());
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Usuario> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
    Optional<Usuario> usuarioData = usuarioRepository.findById(id);

    if (usuarioData.isPresent()) {
      Usuario _usuario = usuarioData.get();
      _usuario.setEmail(usuario.getEmail());
      _usuario.setSenha(usuario.getSenha());
      _usuario.setNome(usuario.getNome());
      _usuario.setDataNascimento(usuario.getDataNascimento());
      return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/redefinirsenha/{email}")
  public ResponseEntity<?> redefinirSenha(@PathVariable("email") String email){
    Optional<Usuario> usuario = usuarioRepository.findByEmailContaining(email);
    if (usuario.isPresent()) {
      ResponseEntity retorno = usuarioService.enviaSenhaPorEmail(email);
      System.out.println("Usuário localizado.");
      return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    System.out.println("Usuário não localizado.");
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") long id) {
    try {
      usuarioRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<HttpStatus> deleteAllUsuarios() {
    try {
      usuarioRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

  }
}