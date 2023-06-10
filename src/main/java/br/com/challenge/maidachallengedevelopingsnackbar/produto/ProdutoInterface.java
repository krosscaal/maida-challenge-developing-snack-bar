/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaCliente;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaGestor;
import java.util.List;
import java.util.Optional;

public interface ProdutoInterface {

  List<ProdutoDtoParaGestor> listProdutosParaGestor();
  Optional<ProdutoDtoParaGestor> getProduto(Long id);
  ProdutoDtoParaGestor addProduto(ProdutoDto dto);
  ProdutoDtoParaGestor updateProduto(Long id, ProdutoDto dto);
  void deleteProduto(Long id);
  List<ProdutoDtoParaCliente> listProdutosParaCliente();
  ProdutoDtoParaCliente getProdutoParaCliente(Long id);

}
