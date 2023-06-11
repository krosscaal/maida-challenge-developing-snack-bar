/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.service;

import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_EXISTS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_NAME_ERROR;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_PRICE_ERROR;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_QUANTITY_ERROR;

import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoInterface;
import br.com.challenge.maidachallengedevelopingsnackbar.repository.ProdutoRepository;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaCliente;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaGestor;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProdutoService implements ProdutoInterface {

  private ProdutoRepository repository;

  private EntityManager manager;

  private ModelMapper modelMapper;

  @Override
  public List<ProdutoDtoParaGestor> listProdutosParaGestor() {

    return this.manager
        .createQuery("FROM ProdutoEntity p order by p.nome", ProdutoEntity.class)
        .getResultList().stream().map(produtoEntity -> modelMapper.map(produtoEntity, ProdutoDtoParaGestor.class)).collect(
            Collectors.toList());
  }

  @Override
  public Optional<ProdutoDtoParaGestor> getProduto(Long id) {

    final Optional<ProdutoEntity> optionalObjPersisted = this.findProduto(id);
    final Optional<ProdutoDtoParaGestor> optionalprodutoDtoParaGestor =
        optionalObjPersisted
            .map(optionalObj -> modelMapper.map(optionalObj, ProdutoDtoParaGestor.class));
    return optionalprodutoDtoParaGestor;
  }

  @Transactional
  @Override
  public ProdutoDtoParaGestor addProduto(final ProdutoDto dto) {
    this.validarProdutoDto(dto);
    if(this.nomeExists(dto)) {
      throw new BusinessException(PRODUCT_EXISTS);
    }
    ProdutoEntity newObj = modelMapper.map(dto, ProdutoEntity.class);
    this.repository.save(newObj);
    ProdutoDtoParaGestor produtoDtoParaGestorObj = modelMapper.map(newObj, ProdutoDtoParaGestor.class);
    return produtoDtoParaGestorObj;
  }

  @Transactional
  @Override
  public ProdutoDtoParaGestor updateProduto(final Long id, final ProdutoDto dto) {

    this.validarProdutoDto(dto);
    final Optional<ProdutoEntity> optionalObj = this.findProduto(id);
    final Optional<ProdutoEntity> optionalByNome =
        this.repository.findByNome(dto.getNome());

    if(optionalByNome.isPresent()
        && !optionalObj.get().getId().equals(optionalByNome.get().getId())) {
      throw new BusinessException(PRODUCT_EXISTS);
    }

    ProdutoEntity updateObj = modelMapper.map(dto, ProdutoEntity.class);
    updateObj.setId(id);
    updateObj.setCreatedAt(optionalObj.get().getCreatedAt());
    this.repository.save(updateObj);
    ProdutoDtoParaGestor produtoDtoParaGestorObj = modelMapper.map(updateObj, ProdutoDtoParaGestor.class);
    return produtoDtoParaGestorObj;
  }

  @Transactional
  @Override
  public void deleteProduto(final Long id) {
    this.findProduto(id);
    this.repository.deleteById(id);
  }

  @Override
  public List<ProdutoDtoParaCliente> listProdutosParaCliente() {

    return repository
        .findAll(Sort.by("nome"))
        .stream()
        .map(produtoEntity -> modelMapper.map(produtoEntity, ProdutoDtoParaCliente.class))
        .collect(Collectors.toList());
  }

  @Override
  public ProdutoDtoParaCliente getProdutoParaCliente(final Long id) {

    final Optional<ProdutoEntity> produtoObjPersisted = this.findProduto(id);
    ProdutoDtoParaCliente produtoDtoParaCliente =
        modelMapper.map(produtoObjPersisted.get(), ProdutoDtoParaCliente.class);
    return produtoDtoParaCliente;
  }
  protected List<ProdutoDtoParaCliente> listProdutosEmPedido(List<ProdutoEntity> lista) {
    return lista
        .stream()
        .map(produtoEntity -> modelMapper.map(produtoEntity, ProdutoDtoParaCliente.class))
        .collect(Collectors.toList());
  }

  protected Optional<ProdutoEntity> findProduto(final Long id) {
    final Optional<ProdutoEntity> optionalObj = this.repository.findById(id);
    if(optionalObj.isEmpty()) {
      throw new BusinessException(PRODUCT_NOT_FOUND);
    }
    return optionalObj;
  }

  private boolean nomeExists(ProdutoDto dto) {
    return this.repository
        .findByNome(dto.getNome())
        .stream()
        .anyMatch(nomeExiste -> !nomeExiste.equals(dto));
  }
  private void validarProdutoDto(final ProdutoDto dto) {
    dto.setNome(dto.getNome().toUpperCase(Locale.ROOT));
    dto.setDescricao(dto.getDescricao().toUpperCase(Locale.ROOT));
    final String nomeProduto = dto.getNome();

    final boolean nomeIsAlfanumerico = nomeProduto.matches("[A-Z0-9 ]+");

    if(!nomeIsAlfanumerico) {
      throw new BusinessException(PRODUCT_NAME_ERROR);
    }
    if(dto.getNome().length() == 5 && dto.getNome().contains(" ")) {
      throw new BusinessException(PRODUCT_NAME_ERROR);
    }
    if(dto.getQuantidade() < 3) {
      throw new BusinessException(PRODUCT_QUANTITY_ERROR);
    }
    if(dto.getPreco().doubleValue() <= 0 ) {
      throw new BusinessException(PRODUCT_PRICE_ERROR);
    }
  }

}
