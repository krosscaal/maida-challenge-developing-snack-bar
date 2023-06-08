/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.produto;

import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDto;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProdutoService implements ProdutoInterface{

  final static String PRODUCT_EXISTS = "Já existe um produto cadastrado com esse nome!";
  final static String PRODUCT_NOT_FOUND = "Produto não encontrado!";

  private ProdutoRepository repository;

  private EntityManager manager;

  private ModelMapper modelMapper;

  @Override
  public List<ProdutoEntity> listProdutos() {

    return this.manager
        .createQuery("FROM ProdutoEntity p order by p.nome", ProdutoEntity.class)
        .getResultList();
  }

  @Override
  public ProdutoEntity getProduto(Long id) {

    final Optional<ProdutoEntity> optionalObjPersisted = this.findProduto(id);
    return optionalObjPersisted.get();
  }

  @Transactional
  @Override
  public ProdutoEntity addProduto(final ProdutoDto dto) {
    if(this.nomeExists(dto)) {
      throw new BusinessException(PRODUCT_EXISTS);
    }
    ProdutoEntity newObj = modelMapper.map(dto, ProdutoEntity.class);
    return this.repository.save(newObj);
  }

  @Transactional
  @Override
  public ProdutoEntity updateProduto(final Long id, final ProdutoDto dto) {

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
    return this.repository.save(updateObj);
  }

  @Transactional
  @Override
  public void deleteProduto(final Long id) {
    this.findProduto(id);
    this.repository.deleteById(id);
  }

  private Optional<ProdutoEntity> findProduto(final Long id) {
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
}
