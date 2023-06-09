/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDto;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoDadosPublicos;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoDadosParaGestor;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/costumer")
public class ClienteController {

  @Autowired
  private ClienteService service;

  @Transactional
  @PostMapping("/new")
  @ResponseBody
  public ResponseEntity<ClienteDtoDadosPublicos> addCliente(
      @Valid @RequestBody ClienteDto dto) {

    final ClienteDtoDadosPublicos clienteDtoDadosPublicosObj =
        this.service.addCliente(dto);
    return new ResponseEntity<>(clienteDtoDadosPublicosObj, HttpStatus.CREATED);
  }
  @Transactional
  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<ClienteDtoDadosPublicos> updateCliente(
      @PathVariable("id") Long id, @Valid @RequestBody ClienteDto dto) {
    return new ResponseEntity<>(
        this.service.updateCliente(id, dto),
        HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<ClienteDtoDadosParaGestor> getClienteParaGestor(
      @PathVariable("id") long id) {
    return new ResponseEntity<>(this.service.getClienteParaGestor(id), HttpStatus.OK);
  }
  @GetMapping("/v2/{id}")
  @ResponseBody
  public ResponseEntity<ClienteDtoDadosPublicos> getClienteParaCliente(
      @PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.getClienteParaCliente(id), HttpStatus.OK);
  }

  @GetMapping("/list")
  @ResponseBody
  public ResponseEntity<List<ClienteDtoDadosParaGestor>> listClientesParaGestor() {

    final List<ClienteDtoDadosParaGestor> listaClientesParaGestor =
        this.service.listClientesParaGestor();
    return ResponseEntity.ok().body(listaClientesParaGestor);
  }
}
