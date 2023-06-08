/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import java.util.List;
import java.util.Optional;

public interface ProdutoInterface {

  List<ProdutoEntity> listProdutosParaGestor();
  ProdutoEntity getProduto(Long id);
  ProdutoEntity addProduto(ProdutoDto dto);
  ProdutoEntity updateProduto(Long id, ProdutoDto dto);
  void deleteProduto(Long id);
  List<ProdutoDto> listProdutosParaCliente();

}
