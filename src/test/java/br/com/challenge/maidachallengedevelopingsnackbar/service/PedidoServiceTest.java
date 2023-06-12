/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.service;

import br.com.challenge.maidachallengedevelopingsnackbar.gestor.GestorEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoProdutoQuantidade;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.repository.PedidoRepository;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
  @InjectMocks
  private PedidoRepository pedidoRepository;
  @InjectMocks
  private ClienteService clienteService;
  @InjectMocks
  private ProdutoService produtoService;
  @InjectMocks
  private GestorService gestorService;
  @InjectMocks
  private EntityManager entityManager;
  @InjectMocks
  private ModelMapper modelMapper;
  @InjectMocks
  private PedidoService pedidoService;

  @BeforeEach
  public void setup() {

    GestorEntity gestor = new GestorEntity();
    PedidoEntity pedidoEntity = new PedidoEntity();
    Optional<GestorEntity> optionalGestor = Optional.of(gestor);
    Mockito.when(gestorService.findGestor()).thenReturn(optionalGestor);
  }


  public void addPedidoTest() {
    final OffsetDateTime datapedido = OffsetDateTime.now();
    final PedidoDtoProdutoQuantidade pedidoDtoProdutoQuantidade = new PedidoDtoProdutoQuantidade(1l,1);
    List<PedidoDtoProdutoQuantidade> prdutos = new ArrayList<>();
    prdutos.add(pedidoDtoProdutoQuantidade);
    PedidoDto pedidoDto = new PedidoDto(1L,prdutos, datapedido);


  }

}
