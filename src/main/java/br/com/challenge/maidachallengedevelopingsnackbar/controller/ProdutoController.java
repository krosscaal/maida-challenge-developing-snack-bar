/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */
 
package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoInterface;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaCliente;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaGestor;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RestController
@RequestMapping("/snack-bar/products")
public class ProdutoController {

  private ProdutoInterface produtoInterface;

  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<List<ProdutoDtoParaGestor>> listProdutos() {

    final List<ProdutoDtoParaGestor> listaProdutos = this.produtoInterface.listProdutosParaGestor();
    return ResponseEntity.ok().body(listaProdutos);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<ProdutoDtoParaGestor> getProduto(@PathVariable("id") Long id) {

    return new ResponseEntity<>(this.produtoInterface.getProduto(id).get(), HttpStatus.OK);
  }

  @Transactional
  @PostMapping("/new")
  @ResponseBody
  public ResponseEntity<ProdutoDtoParaGestor> addProduto(@Valid @RequestBody ProdutoDto dto) {

    return new ResponseEntity<>(this.produtoInterface.addProduto(dto), HttpStatus.CREATED);
  }

  @Transactional
  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<ProdutoDtoParaGestor> updateProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoDto dto) {

    return new ResponseEntity<>(this.produtoInterface.updateProduto(id, dto), HttpStatus.OK);
  }

  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeProduto(@PathVariable("id") Long id) {
    this.produtoInterface.deleteProduto(id);
    return ResponseEntity.noContent().build();
  }
  @GetMapping("/list-v2")
  public ResponseEntity<List<ProdutoDtoParaCliente>> listProdutosParaCliente() {
    final List<ProdutoDtoParaCliente> listaProdutosParaCliente = this.produtoInterface.listProdutosParaCliente();
    return ResponseEntity.ok().body(listaProdutosParaCliente);
  }

  @GetMapping("/v2/{id}")
  public ResponseEntity<ProdutoDtoParaCliente> getProdutoParaCliente(@PathVariable("id") final Long id) {

    final ProdutoDtoParaCliente produtoParaClienteObj =
        this.produtoInterface.getProdutoParaCliente(id);
    return ResponseEntity.ok().body(produtoParaClienteObj);
  }
}
