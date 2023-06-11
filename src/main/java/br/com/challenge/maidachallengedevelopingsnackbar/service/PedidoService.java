/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.service;

import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_CANNOT_MODIFY_STATUS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_IS_NOT_IN_REQUESTED_STATUS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_NOT_FOUND;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.ClienteEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.produto.dto.ProdutoDtoParaCliente;
import br.com.challenge.maidachallengedevelopingsnackbar.repository.PedidoRepository;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoSolicitar;
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
  private EntityManager entityManager;

  public PedidoDtoStatus addPedido(final PedidoDtoSolicitar dto) {

    final PedidoEntity pedidoEntity = this.validarPedidoDto(dto);
    final PedidoEntity savedPedidoEntity = this.repository.save(pedidoEntity);

    final List<ProdutoDtoParaCliente> listProdutoDtoParaClientes =
        this.produtoService.listProdutosEmPedido(savedPedidoEntity.getProdutos());

    PedidoDtoStatus pedidoDtoStatus =
        new PedidoDtoStatus(
            savedPedidoEntity.getId(),
            listProdutoDtoParaClientes,
            savedPedidoEntity.getValor(),
            savedPedidoEntity.getStatus());
    return pedidoDtoStatus;
  }

  public PedidoDtoStatus getStatusPedido(final Long id) {
    final PedidoEntity pedidoEntityPersisted = this.findPedido(id).get();
    final List<ProdutoDtoParaCliente> listProdutoDtoParaClientes =
        this.produtoService.listProdutosEmPedido(pedidoEntityPersisted.getProdutos());
    PedidoDtoStatus pedidoDtoStatus =
        new PedidoDtoStatus(
            id,
            listProdutoDtoParaClientes,
            pedidoEntityPersisted.getValor(),
            pedidoEntityPersisted.getStatus());
    return pedidoDtoStatus;
  }
  public List<PedidoDtoStatus> ListPedidoFinalizadosPorCliente(final Long id) {
    this.clienteService.findCliente(id);
    final List<PedidoEntity> listPedidoStatusFinalizados =
        this.entityManager
        .createQuery("FROM PedidoEntity p WHERE p.status =:status")
        .setParameter("status", DomainOrderStatus.DELIVERED).getResultList();

    final List<PedidoDtoStatus> listaPedidoDtoStatus =
        listPedidoStatusFinalizados
            .stream()
            .map(pedido -> modelMapper.map(pedido, PedidoDtoStatus.class))
            .collect(Collectors.toList());

    List<PedidoEntity> listaPedidosEntregados =
        this.repository.findByStatus(DomainOrderStatus.DELIVERED);

    List<PedidoDtoStatus> listaPedidoDtoStatus2 = new ArrayList<>();
    listaPedidosEntregados
        .forEach(
            pedidoEntity -> {
              final PedidoDtoStatus pedidoDtoStatus = modelMapper.map(pedidoEntity,PedidoDtoStatus.class);
              listaPedidoDtoStatus2.add(pedidoDtoStatus);
            });
    return listaPedidoDtoStatus2;

  }
  public PedidoDtoStatus updateStatusPedido(final Long id, DomainOrderStatus status) {

    final Optional<PedidoEntity> optionalPedidoEntityPersisted = this.findPedido(id);

    if (optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.CANCELED)
        || optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.RECUSED)) {
      throw new BusinessException(ORDER_CANNOT_MODIFY_STATUS);
    }
    final PedidoDtoStatus pedidoDtoStatus =
        this.updateStatus(optionalPedidoEntityPersisted.get(), status);
    return pedidoDtoStatus;
  }

  public PedidoDtoStatus updateStatusPedidoParaCancelado(final Long id) {

    final Optional<PedidoEntity> optionalPedidoEntityPersisted = this.findPedido(id);
    if (!optionalPedidoEntityPersisted.get().getStatus().equals(DomainOrderStatus.REQUESTED)) {
      throw new BusinessException(ORDER_IS_NOT_IN_REQUESTED_STATUS);
    }
    final PedidoDtoStatus pedidoDtoStatus =
        this.updateStatus(optionalPedidoEntityPersisted.get(), DomainOrderStatus.CANCELED);
    return pedidoDtoStatus;

  }

  private PedidoEntity validarPedidoDto(PedidoDtoSolicitar dto) {

    ClienteEntity clienteEntityPersisted =
        this.clienteService.findCliente(dto.getCliente_id()).get();

    List<ProdutoEntity> listaDeProdutos = new ArrayList<>();

    Double valorTotalDouble = 0.0;
    dto.getProdutos()
        .forEach(
            produto_id->{
              final ProdutoEntity produtoEntity = produtoService.findProduto(produto_id).get();
              listaDeProdutos.add(produtoEntity);
            });

    for (ProdutoEntity produto: listaDeProdutos) {
      valorTotalDouble += produto.getPreco().doubleValue();
    }
    final BigDecimal valorTotal = new BigDecimal(valorTotalDouble);
    PedidoEntity pedidoEntity =
        new PedidoEntity(
            dto.getCliente_id(),
            clienteEntityPersisted,
            listaDeProdutos,
            dto.getDataPedido(),
            valorTotal,
            DomainOrderStatus.REQUESTED);

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
