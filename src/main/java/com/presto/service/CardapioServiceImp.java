
package com.presto.service;

import com.presto.model.Cardapio;
import com.presto.model.Produto;
import com.presto.repository.CardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CardapioServiceImp implements CardapioService{
    @Autowired
    CardapioRepository cardapioRepository;

    @Override
    public ResponseEntity<?> filtraProdutos(String tipo, String nome) {

            Optional<Cardapio> cardapio = cardapioRepository.findByNomeContaining(nome);
            List<Produto> listaProdutosRetorno = new ArrayList<Produto>();


            if (cardapio.isPresent()) {
                for (Produto produto : cardapio.get().getProdutos()) {
                    String aux = produto.getTipo();
                    if(aux.equals(tipo)) {
                        listaProdutosRetorno.add(produto);
                    }

                }
                return new ResponseEntity<>(listaProdutosRetorno, HttpStatus.OK);
            }
            return new ResponseEntity<>("Cardápio não encontrado", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> removerProduto(String nome, Long id) {
        Optional<Cardapio> cardapio = cardapioRepository.findByNomeContaining(nome);

        if (cardapio.isPresent()) {
            List<Produto> listaDeProdutosAux= new ArrayList<>();

            for (Produto produto : cardapio.get().getProdutos()) {
                if (produto.getId() != id) {
                   listaDeProdutosAux.add(produto);
                }
            }
            cardapio.get().setProdutos(listaDeProdutosAux);
            cardapioRepository.save(cardapio.get());
            return new ResponseEntity<>(cardapio, HttpStatus.OK);
        }
        return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND);
    }
}