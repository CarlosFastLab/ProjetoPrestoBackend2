package com.presto.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ProdutoServiceImp implements ProdutoService {

    @Override
    public ResponseEntity<?> salvarImagem(MultipartFile file) {
//        String UPLOADER_FOLDER = "C:/Users/charlinho/Documents/UNIFOR/ADS/PA 2/ProjetoPresto Front/ProjetoPresto/Angular/Presto/src/assets/imagens/";
//        String UPLOADER_FOLDER = "C:/Users/clail/Documents/github/ProjetoPresto/Angular/Presto/src/assets/imagens";
       String UPLOADER_FOLDER = "C:\\Users/Ezequiel/Documents/Projetos/Versões Presto git/ProjetoPresto/Angular/Presto/src/assets/imagens";

        if (file.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADER_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return new ResponseEntity<>(file, HttpStatus.ACCEPTED);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
