/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatusUpdate;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snack-bar/orders")
public class PedidoController {

  @Autowired
  private PedidoService service;

  @ApiOperation(value = "ADICIONAR PEDIDO")
  @Transactional
  @PostMapping("/new")
  public ResponseEntity<PedidoDtoStatus> addPedido(@Valid @RequestBody PedidoDto dto) {

    return new ResponseEntity<>(this.service.addPedido(dto), HttpStatus.CREATED);
  }
  @ApiOperation(value = "BUSCA PEDIDO PARA CLIENTE")
  @GetMapping("/status/{id}")
  public ResponseEntity<PedidoDtoStatus> getPedidoStatus(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.getPedido(id), HttpStatus.OK);
  }
  @ApiOperation(value = "LISTAR PEDIDOS FINALIZADOS PARA CLIENTE")
  @GetMapping("/finished-orders/{cliente_id}")
  public ResponseEntity<List<PedidoDtoStatus>> listPedidosFinalizadosCliente(@PathVariable("cliente_id") Long id) {
    return new ResponseEntity<>(this.service.listPedidoFinalizadosPorCliente(id), HttpStatus.OK);
  }

  @ApiOperation(value = "ATUALIZAR STATUS DO PEDIDO(PELO GESTOR)")
  @Transactional
  @PutMapping("/update-status/{id}")
  public ResponseEntity<PedidoDtoStatus> updateStatusDoPedido(@PathVariable("id") Long id, @Valid @RequestBody PedidoDtoStatusUpdate status) {
    return new ResponseEntity<>(this.service.updateStatusPedido(id, status), HttpStatus.OK);
  }
  @ApiOperation(value = "CANCELAR PEDIDO(PELO CLIENTE)")
  @Transactional
  @PutMapping("/order-cancel/{id}")
  public ResponseEntity<PedidoDtoStatus> updateStatusPedidoParaCancelado(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.updateStatusPedidoParaCancelado(id), HttpStatus.OK);
  }
}
