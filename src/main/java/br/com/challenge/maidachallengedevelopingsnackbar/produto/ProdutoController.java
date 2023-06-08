/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */
 
package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/products")
public class ProdutoController {

  private ProdutoInterface produtoInterface;

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<ProdutoEntity>> listProdutos() {

    final List<ProdutoEntity> listaProdutos = this.produtoInterface.listProdutos();
    return ResponseEntity.ok().body(listaProdutos);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<ProdutoEntity> getProduto(@PathVariable("id") Long id) {

    return new ResponseEntity<>(this.produtoInterface.getProduto(id), HttpStatus.OK);
  }

  @PostMapping("/new")
  @ResponseBody
  public ResponseEntity<ProdutoEntity> addProduto(@Valid @RequestBody ProdutoDto dto) {

    final ProdutoEntity obj = this.produtoInterface.addProduto(dto);
    return new ResponseEntity<>(obj,HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<ProdutoEntity> updateProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoDto dto) {

    return new ResponseEntity<>(this.produtoInterface.updateProduto(id, dto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeProduto(@PathVariable("id") Long id) {
    this.produtoInterface.deleteProduto(id);
    return ResponseEntity.noContent().build();
  }
}