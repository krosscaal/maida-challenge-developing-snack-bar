/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/costumer")
public class ClienteController {

  @Autowired
  private ClienteService service;

  @GetMapping("/new")
  @ResponseBody
  public ResponseEntity<ClienteEntity> addCliente(@Valid @RequestBody ClienteDto dto) {

    final ClienteEntity clienteObj = this.service.addCliente(dto);
    return new ResponseEntity<>(clienteObj, HttpStatus.CREATED);
  }

}
