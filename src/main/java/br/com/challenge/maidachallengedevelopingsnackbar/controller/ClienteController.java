/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDto;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoDadosPublicos;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoDadosParaGestor;
import br.com.challenge.maidachallengedevelopingsnackbar.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/snack-bar/costumer", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

  @Autowired
  private ClienteService service;

  @ApiOperation(value = "CRIAR NOVO CLIENTE")
  @Transactional
  @PostMapping("/new")
  public ResponseEntity<ClienteDtoDadosPublicos> addCliente(@Valid @RequestBody ClienteDto dto) {

    final ClienteDtoDadosPublicos clienteDtoDadosPublicosObj =
        this.service.addCliente(dto);
    return new ResponseEntity<>(clienteDtoDadosPublicosObj, HttpStatus.CREATED);
  }
  @ApiOperation(value = "ATUALIZAR CLIENTE")
  @Transactional
  @PutMapping("/update/{id}")
  public ResponseEntity<ClienteDtoDadosPublicos> updateCliente(
      @PathVariable("id") Long id,
      @Valid @RequestBody ClienteDto dto) {
    return new ResponseEntity<>(
        this.service.updateCliente(id, dto),
        HttpStatus.OK);
  }

  @ApiOperation(value = "BUSCAR CLIENTE PELO ID PARA GESTOR")
  @GetMapping("/{id}")
  public ResponseEntity<ClienteDtoDadosParaGestor> getClienteParaGestor(@PathVariable("id") long id) {
    return new ResponseEntity<>(this.service.getClienteParaGestor(id), HttpStatus.OK);
  }
  @ApiOperation(value = "BUSCAR CLIENTE PELO ID PARA CLIENTE")
  @GetMapping("/v2/{id}")
  public ResponseEntity<ClienteDtoDadosPublicos> getClienteParaCliente(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.getClienteParaCliente(id), HttpStatus.OK);
  }

  @ApiOperation(value = "LISTA DE CLIENTES PARA GESTOR")
  @GetMapping("/list")
  public ResponseEntity<List<ClienteDtoDadosParaGestor>> listClientesParaGestor() {

    final List<ClienteDtoDadosParaGestor> listaClientesParaGestor =
        this.service.listClientesParaGestor();
    return ResponseEntity.ok().body(listaClientesParaGestor);
  }
}
