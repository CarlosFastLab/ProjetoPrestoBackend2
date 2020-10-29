package com.presto.service;

import com.presto.model.Produto;
import com.presto.model.ProdutoRetorno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProdutoService {
    ResponseEntity<?> salvarImagem(MultipartFile file);
}
