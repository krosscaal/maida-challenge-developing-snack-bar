/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.domain.DomainOrderStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.service.ClienteService;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDto;
import br.com.challenge.maidachallengedevelopingsnackbar.service.ProdutoService;
import br.com.challenge.maidachallengedevelopingsnackbar.service.PedidoService;
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

  @Transactional
  @PostMapping("/new")
  public ResponseEntity<PedidoDtoStatus> addPedido(@Valid @RequestBody PedidoDto dto) {

    return new ResponseEntity<>(this.service.addPedido(dto), HttpStatus.CREATED);
  }

  @Transactional
  @PutMapping("/update/{id}")
  public ResponseEntity<PedidoDtoStatus> updatePedido(@PathVariable("id") Long id, @Valid @RequestBody PedidoDto dto) {
    return new ResponseEntity<>(this.service.updatePedido(id, dto), HttpStatus.OK);
  }
  @GetMapping("/status/{id}")
  public ResponseEntity<PedidoDtoStatus> getPedidoStatus(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.getPedido(id), HttpStatus.OK);
  }
  @GetMapping("/finished-orders/{id}")
  public ResponseEntity<List<PedidoDtoStatus>> listPedidosFinalizadosCliente(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.ListPedidoFinalizadosPorCliente(id), HttpStatus.OK);
  }
  @Transactional
  @PutMapping("/update-status/{id}")
  public ResponseEntity<PedidoDtoStatus> updateStatusDoPedido(@PathVariable("id") Long id, DomainOrderStatus status) {
    return new ResponseEntity<>(this.service.updateStatusPedido(id, status), HttpStatus.OK);
  }
  @Transactional
  @PutMapping("/order-cancel/{id}")
  public ResponseEntity<PedidoDtoStatus> updateStatusPedidoParaCancelado(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.updateStatusPedidoParaCancelado(id), HttpStatus.OK);
  }
}
