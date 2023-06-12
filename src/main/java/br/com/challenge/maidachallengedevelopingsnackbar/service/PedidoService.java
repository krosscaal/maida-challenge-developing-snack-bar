/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.service;

import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_CANNOT_BE_MODIFIED;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_CANNOT_MODIFY_STATUS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_DOES_NOT_BELONG_TO_THE_CUSTOMER;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_IS_NOT_IN_REQUESTED_STATUS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.QUANTITY_ERROR;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.ClienteEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.itempedido.ItemPedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.itempedido.dto.ItemPedidoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoProdutoQuantidade;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatusUpdate;
import br.com.challenge.maidachallengedevelopingsnackbar.repository.PedidoRepository;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.ProdutoEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
  @Autowired
  private PedidoRepository repository;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ProdutoService produtoService;
  @Autowired
  private GestorService gestorService;
  @Autowired
  private EntityManager entityManager;

  public PedidoDtoStatus addPedido(final PedidoDto dto) {
    this.gestorService.findGestor();
    final PedidoEntity novoPedidoEntity = this.validarPedidoDto(dto);
    final PedidoEntity savedPedidoEntity = this.repository.save(novoPedidoEntity);

    final List<ItemPedidoDto> listItemPedidoDto =
        this.produtoService.listProdutosEmPedido(savedPedidoEntity.getItens());

    PedidoDtoStatus pedidoDtoStatus =
        new PedidoDtoStatus(
            savedPedidoEntity.getId(),
            listItemPedidoDto,
            savedPedidoEntity.getValor(),
            savedPedidoEntity.getStatus());
    return pedidoDtoStatus;
  }

//  public PedidoDtoStatus updatePedido(final Long pedido_id, final PedidoDto dto) {
//    this.gestorService.findGestor();
//
//    final Optional<PedidoEntity> optionalPedidoEntityPersisted = this.findPedido(pedido_id);
//    final ClienteEntity clienteEntityObj = this.clienteService.findCliente(dto.getCliente_id()).get();
//
//    if (!optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.REQUESTED)) {
//      throw new BusinessException(ORDER_CANNOT_BE_MODIFIED);
//    }
//    if (!optionalPedidoEntityPersisted.get().getCliente().equals(clienteEntityObj)) {
//      throw new BusinessException(ORDER_DOES_NOT_BELONG_TO_THE_CUSTOMER);
//    }
//    final PedidoEntity pedidoEntityUpdate = this.validarPedidoDtoparaUpdate(optionalPedidoEntityPersisted.get(), dto);
//
//    pedidoEntityUpdate.setId(optionalPedidoEntityPersisted.get().getId());
//    pedidoEntityUpdate.setDataPedido(optionalPedidoEntityPersisted.get().getDataPedido());
//    pedidoEntityUpdate.setCreatedAt(optionalPedidoEntityPersisted.get().getCreatedAt());
//    pedidoEntityUpdate.setStatus(optionalPedidoEntityPersisted.get().getStatus());
//    this.repository.save(pedidoEntityUpdate);
//
//    final List<ItemPedidoDto> listItemPedidoDto =
//        this.produtoService.listProdutosEmPedido(pedidoEntityUpdate.getItens());
//
//    PedidoDtoStatus pedidoDtoStatus =
//        new PedidoDtoStatus(
//            pedidoEntityUpdate.getId(),
//            listItemPedidoDto,
//            pedidoEntityUpdate.getValor(),
//            pedidoEntityUpdate.getStatus());
//
//    return pedidoDtoStatus;
//  }

  public PedidoDtoStatus getPedido(final Long id) {
    this.gestorService.findGestor();
    final PedidoEntity pedidoEntityPersisted = this.findPedido(id).get();
    final List<ItemPedidoDto> listItemPedidoDto =
        this.produtoService.listProdutosEmPedido(pedidoEntityPersisted.getItens());
    PedidoDtoStatus pedidoDtoStatus =
        new PedidoDtoStatus(
            id,
            listItemPedidoDto,
            pedidoEntityPersisted.getValor(),
            pedidoEntityPersisted.getStatus());
    return pedidoDtoStatus;
  }
  public List<PedidoDtoStatus> ListPedidoFinalizadosPorCliente(final Long cliente_id) {
    this.gestorService.findGestor();
    this.clienteService.findCliente(cliente_id);
    final List<PedidoEntity> listPedidoStatusFinalizados =
        this.entityManager
        .createQuery("FROM PedidoEntity p WHERE "
            + "p.status =:statusDelivered "
            + "or p.status =:statusCanceled "
            + "or p.status =:statusRecused")
            .setParameter("statusDelivered", DomainOrderStatus.DELIVERED)
            .setParameter("statusCanceled", DomainOrderStatus.CANCELED)
            .setParameter("statusRecused", DomainOrderStatus.RECUSED)
            .getResultList();

    List<PedidoDtoStatus> listaPedidoDtoStatus = new ArrayList<>();
    listPedidoStatusFinalizados
        .forEach(pedido->{
          final List<ItemPedidoDto> listItemPedidoDto =
              this.produtoService.listProdutosEmPedido(pedido.getItens());

          PedidoDtoStatus pedidoDtoStatus =
              new PedidoDtoStatus(pedido.getId(), listItemPedidoDto, pedido.getValor(), pedido.getStatus());
          listaPedidoDtoStatus.add(pedidoDtoStatus);
        });

    return listaPedidoDtoStatus;

  }
  public PedidoDtoStatus updateStatusPedido(final Long id, PedidoDtoStatusUpdate status) {
    this.gestorService.findGestor();
    final Optional<PedidoEntity> optionalPedidoEntityPersisted = this.findPedido(id);

    if (optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.CANCELED)
        || optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.RECUSED)
        || optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.DELIVERED)) {
      throw new BusinessException(ORDER_CANNOT_MODIFY_STATUS);
    }
    final PedidoDtoStatus pedidoDtoStatus =
        this.updateStatus(optionalPedidoEntityPersisted.get(), status.getStatus());
    return pedidoDtoStatus;
  }

  public PedidoDtoStatus updateStatusPedidoParaCancelado(final Long id) {
    this.gestorService.findGestor();
    final Optional<PedidoEntity> optionalPedidoEntityPersisted = this.findPedido(id);
    if (!optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.REQUESTED)) {
      throw new BusinessException(ORDER_IS_NOT_IN_REQUESTED_STATUS);
    }
    final PedidoDtoStatus pedidoDtoStatus =
        this.updateStatus(optionalPedidoEntityPersisted.get(), DomainOrderStatus.CANCELED);
    return pedidoDtoStatus;

  }

  private PedidoEntity validarPedidoDto(PedidoDto dto) {

    ClienteEntity clienteEntityPersisted =
        this.clienteService.findCliente(dto.getCliente_id()).get();

    PedidoEntity pedidoEntity = new PedidoEntity(clienteEntityPersisted);

    dto.getProdutos()
        .forEach(
            pedidoDtoProdutoQuantidade->{
              final ProdutoEntity produtoEntity =
                  produtoService.findProduto(pedidoDtoProdutoQuantidade.getProduto_id()).get();
              final Integer quantidade = pedidoDtoProdutoQuantidade.getQuantidade();
              if(quantidade < 1) {
                throw new BusinessException(QUANTITY_ERROR);
              }
              pedidoEntity.adicionarItem(new ItemPedidoEntity(quantidade, pedidoEntity, produtoEntity));
            });
    pedidoEntity.setDataPedido(dto.getDataPedido());
    pedidoEntity.setStatus(DomainOrderStatus.REQUESTED);
    return pedidoEntity;
  }
  private PedidoEntity validarPedidoDtoparaUpdate(final PedidoEntity pedidoEntity, final PedidoDto dto) {
    ClienteEntity clienteEntityPersisted =
        this.clienteService.findCliente(dto.getCliente_id()).get();

    final List<ItemPedidoEntity> itens = pedidoEntity.getItens();

    List<PedidoDtoProdutoQuantidade> listaProdutoQuantidadeNovos = new ArrayList<>();
    List<ItemPedidoEntity> itensParaRemover = new ArrayList<>();
    /* cria lista itens para remover*/
    itens.forEach(itemPedidoEntity -> {
      final Long itemPedidoEntity_Id = itemPedidoEntity.getId();
      final Long produto_id = itemPedidoEntity.getProduto().getId();
      boolean notfound = false;
      for (PedidoDtoProdutoQuantidade pedidoDtoProdutoQuantidade : dto.getProdutos()) {
        if(!pedidoDtoProdutoQuantidade.getProduto_id().equals(produto_id)) {
          notfound = true;
        }
      }
      if(notfound) {
        itensParaRemover.add(itemPedidoEntity);
      }
    });

    /* atualizando quantidade se o item existe*/
    dto.getProdutos()
          .forEach(pedidoDtoProdutoQuantidade -> {
            final Long produtoId = pedidoDtoProdutoQuantidade.getProduto_id();
            final Integer quantidade = pedidoDtoProdutoQuantidade.getQuantidade();
            final ProdutoEntity produtoEntity = this.produtoService.findProduto(produtoId).get();
            boolean produto_idExists = false;
            for (ItemPedidoEntity item: itens) {
              if(item.getProduto().getId().equals(produtoId)) {
                item.setQuantidade(quantidade);
                produto_idExists = true;
                break;
              }
            }
            if (!produto_idExists) {
              listaProdutoQuantidadeNovos.add(pedidoDtoProdutoQuantidade);
            }
          });
    /* adicionando novos itens */
    listaProdutoQuantidadeNovos
        .forEach(pedidoDtoProdutoQuantidade -> {
          final Long produtoId = pedidoDtoProdutoQuantidade.getProduto_id();
          final Integer quantidade = pedidoDtoProdutoQuantidade.getQuantidade();
          final ProdutoEntity produtoEntity = this.produtoService.findProduto(produtoId).get();
          pedidoEntity.adicionarItem(new ItemPedidoEntity(quantidade, pedidoEntity, produtoEntity));
        });


    /* valor total*/
    final List<ItemPedidoEntity> itensFinais = pedidoEntity.getItens();

    Double novoValorTotal = 0.0;
    for (ItemPedidoEntity itemPedido : itensFinais){
      final double valor = itemPedido.getQuantidade() * itemPedido.getPrecoUnitario().doubleValue();
      novoValorTotal+=valor;
    }
    pedidoEntity.setValor(new BigDecimal(novoValorTotal));

    return pedidoEntity;
  }


  private Optional<PedidoEntity> findPedido(final Long id) {
    final Optional<PedidoEntity> optionalObj = this.repository.findById(id);
    if(optionalObj.isEmpty()) {
      throw new BusinessException(ORDER_NOT_FOUND);
    }
    return optionalObj;
  }

  private PedidoDtoStatus updateStatus(PedidoEntity entity, DomainOrderStatus status) {
    entity.setStatus(status);
    this.repository.save(entity);
    PedidoDtoStatus pedidoDtoStatus = modelMapper.map(entity, PedidoDtoStatus.class);
    return pedidoDtoStatus;
  }
}
