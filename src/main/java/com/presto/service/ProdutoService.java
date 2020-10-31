package com.presto.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProdutoService {
    ResponseEntity<?> salvarImagem(MultipartFile file);
}
