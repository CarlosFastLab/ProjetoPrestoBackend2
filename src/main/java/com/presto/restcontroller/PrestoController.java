package com.presto.restcontroller;


import com.presto.model.Cardapio;
import com.presto.model.Produto;
import com.presto.model.Usuario;
import com.presto.repository.CardapioRepository;
import com.presto.repository.ProdutoRepository;
import com.presto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("presto")
@RestController
public class PrestoController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CardapioRepository cardapioRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("create")
    ResponseEntity<?> createEstrutura(){
        Usuario usuario = new Usuario("A", "A@A.com", "12345678","12/12/2000");
        usuarioRepository.save(usuario);

        Cardapio cardapio = new Cardapio("Geral");
        cardapioRepository.save(cardapio);

        Produto produto = new Produto("Coca-cola", "bebida", 1, "coca lata de 350ml","cocacolalata.png");
        produtoRepository.save(produto);

        return  new ResponseEntity<>("Presto iniciado com sucesso!", HttpStatus.OK);

    }

}
