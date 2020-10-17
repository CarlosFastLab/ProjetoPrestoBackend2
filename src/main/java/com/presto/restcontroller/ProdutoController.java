package com.presto.restcontroller;

import com.presto.model.Produto;
import com.presto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/create")
    ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        try{
            produtoRepository.save(produto);
            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/produtos")
    ResponseEntity<List<Produto>> getAllProdutos(){
        try{
            List<Produto> produtos = new ArrayList<Produto>();
            produtoRepository.findAll().forEach(produtos::add);

            if(produtos.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gettipo/{tipo}")
    ResponseEntity<List<Produto>> getProdutosByTipo(@PathVariable ("tipo") String tipo){
        try{
            List<Produto> produtos = new ArrayList<Produto>();
            produtoRepository.findByTipoContaining(tipo).forEach(produtos::add);

            if(produtos.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getnome/{nome}")
    ResponseEntity<Produto> getProdutoByNome(@PathVariable("nome") String nome){
        try {
            Produto produto = produtoRepository.findByNomeContaining(nome).get();
            if(produto == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   // @PutMapping("/addcadapio/")

    @PutMapping("/update/{nome}")
    ResponseEntity<Produto> updateProduto( @PathVariable("nome") String nome, @RequestBody Produto produto){
        try{
            Produto produtoData = produtoRepository.findByNomeContaining(nome).get();
            if (produtoData != null){
                if(produto.getNome() != null){produtoData.setNome(produto.getNome());}
                if (produto.getTipo() != null){produtoData.setTipo(produto.getTipo());}
                if (produto.getTempo() != null){ produtoData.setTempo(produto.getTempo().toString());}
                if (produto.getValor() == 0.0){produtoData.setValor(produto.getValor());}
                if (produto.getImagem() != null){produtoData.setImagem(produto.getImagem());}
                if (produto.getDescricao() != null){produtoData.setDescricao(produto.getDescricao());}
                return new ResponseEntity<>(produtoRepository.save(produtoData), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteProduto( @PathVariable("id") Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return new ResponseEntity<>(produto, HttpStatus.GONE);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
