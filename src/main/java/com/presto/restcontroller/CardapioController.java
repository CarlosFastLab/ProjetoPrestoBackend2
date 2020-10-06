package com.presto.restcontroller;

import com.presto.model.Cardapio;
import com.presto.model.Produto;
import com.presto.repository.CardapioRepository;
import com.presto.service.CardapioServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("cardapio")
public class CardapioController {
    @Autowired
    CardapioRepository cardapioRepository;


    @Autowired 
    CardapioServiceImp cardapioServiceImp;

    @GetMapping("/getfiltro/{nome}/{tipo}")
    public ResponseEntity<?> getfiltro(@PathVariable("nome") String nome, @PathVariable("tipo") String tipo) {
        ResponseEntity cardapio1 = cardapioServiceImp.filtraProdutos(tipo, nome);
        if (cardapio1.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(cardapio1.getBody(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cardapios")
    public ResponseEntity<List<Cardapio>> getAllCardapios() {
        try {
            List<Cardapio> cardapios = new ArrayList<Cardapio>();
            cardapioRepository.findAll().forEach(cardapios::add);

            if (cardapios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cardapios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Cardapio> getCardapioById(@PathVariable("id") long id) {
        Optional<Cardapio> cardapioData = cardapioRepository.findById(id);

        if (cardapioData.isPresent()) {
            return new ResponseEntity<>(cardapioData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getnome/{nome}")
    public ResponseEntity<Cardapio> getCardapioByNome(@PathVariable("nome") String nome) {
        Optional<Cardapio> cardapioData = cardapioRepository.findByNomeContaining(nome);

        if (cardapioData.isPresent()) {
            return new ResponseEntity<>(cardapioData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cardapio> createCardapio(@RequestBody Cardapio cardapio) {
        try {
            Cardapio _cardapio = cardapioRepository.save(cardapio);
            return new ResponseEntity<>(_cardapio, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Cardapio> updateCardapio(@PathVariable("id") long id, @RequestBody Cardapio cardapio) {
        Optional<Cardapio> cardapioData = cardapioRepository.findById(id);

        if (cardapioData.isPresent()) {
            Cardapio _cardapio = cardapio;
           _cardapio.setNome(cardapio.getNome());
            cardapioRepository.save(_cardapio);
            return new ResponseEntity<>(_cardapio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addproduto/{nome}")
    public ResponseEntity<?> updateCardapioPorNome(@PathVariable("nome") String nome, @RequestBody Produto produto) {
        try {
            Optional<Cardapio> cardapioData = cardapioRepository.findByNomeContaining(nome);

            if (cardapioData.isPresent()) {

                cardapioData.get().setProdutos(produto);
                produto.setCardapios(cardapioData.get());
                cardapioRepository.save(cardapioData.get());
                return new ResponseEntity<>(cardapioData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/remove/{nome}")
    public ResponseEntity<?> removeProduto(@PathVariable("nome") String nome, @RequestBody Produto produto) {
        try {
            Optional<Cardapio> cardapioData = cardapioRepository.findByNomeContaining(nome);

            if (cardapioData.isPresent()) {

                cardapioData.get().setProdutos(produto);
                produto.setCardapios(cardapioData.get());
                cardapioRepository.save(cardapioData.get());
                return new ResponseEntity<>(cardapioData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCardapio(@PathVariable("id") long id) {
        try {
           cardapioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllCardapios() {
        try {
            cardapioRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }


}
