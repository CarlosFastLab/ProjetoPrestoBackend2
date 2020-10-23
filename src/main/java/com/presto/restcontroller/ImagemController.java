package com.presto.restcontroller;

import com.presto.model.Imagem;
import com.presto.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("imagem")
public class ImagemController {
    @Autowired
    ImagemRepository imagemRepository;

    @PostMapping("/create")
    ResponseEntity<Imagem> createImagem(@RequestBody Imagem imagem) {
        try {
            imagemRepository.save(imagem);
            return new ResponseEntity<>(imagem, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/imagens")
    ResponseEntity<List<Imagem>> getAllImagens() {
        try {
            List<Imagem> imagens = new ArrayList<Imagem>();
            imagemRepository.findAll().forEach(imagens::add);

            if (imagens.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(imagens, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getid/{id}")
    ResponseEntity<Imagem> getImagemById(@PathVariable("id") long id) {
        try {
            Imagem imagem = imagemRepository.findById(id).get();
            if (imagem == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(imagem, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Imagem> updateImagem(@PathVariable("id") long id, @RequestBody Imagem imagem) {
        try {
            Imagem imagemData = imagemRepository.findById(id).get();
            if (imagemData != null) {
                if (imagemData.getBytes() != null){imagemData.setBytes(imagem.getBytes());}
                if (imagemData.getProduto() != null){imagemData.setProduto(imagem.getProduto());}
                return new ResponseEntity<>(imagemRepository.save(imagemData), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
