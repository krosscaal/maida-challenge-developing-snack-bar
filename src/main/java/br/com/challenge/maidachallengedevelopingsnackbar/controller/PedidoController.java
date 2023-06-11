/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.pedido.PedidoEntity;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoStatus;
import br.com.challenge.maidachallengedevelopingsnackbar.service.ClienteService;
import br.com.challenge.maidachallengedevelopingsnackbar.pedido.dto.PedidoDtoSolicitar;
import br.com.challenge.maidachallengedevelopingsnackbar.service.ProdutoService;
import br.com.challenge.maidachallengedevelopingsnackbar.service.PedidoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snack-bar/orders")
public class PedidoController {

  @Autowired
  private PedidoService service;
  @Autowired
  private ClienteService clienteService;
  @Autowired
  private ProdutoService produtoService;

  @Transactional
  @PostMapping("/new")
  public ResponseEntity<PedidoDtoStatus> addPedido(@Valid @RequestBody PedidoDtoSolicitar dto) {

    return new ResponseEntity<>(this.service.addPedido(dto), HttpStatus.CREATED);
  }

}
