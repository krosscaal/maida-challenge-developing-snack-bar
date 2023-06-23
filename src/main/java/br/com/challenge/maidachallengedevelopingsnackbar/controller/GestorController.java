/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.controller;

import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorDto;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorListDto;
import br.com.challenge.maidachallengedevelopingsnackbar.service.GestorService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snack-bar")
public class GestorController {

  @Autowired
  private GestorService service;

  @ApiOperation(value = "BUSCA GESTOR")
  @GetMapping("/manager")
  public ResponseEntity<GestorListDto> getGestor() {
    return new ResponseEntity<>(this.service.getGestor(), HttpStatus.OK );
  }
  @ApiOperation(value = "ADICIONAR GESTOR")
  @PostMapping("/new")
  public ResponseEntity<GestorListDto> addGestor(@Valid @RequestBody GestorDto dto) {

    final GestorListDto gestorObj = this.service.addGestor(dto);
    return new ResponseEntity<>(gestorObj, HttpStatus.CREATED);
  }
  @ApiOperation(value = "ATUALIZAR GESTOR")
  @PutMapping("/update")
  public ResponseEntity<GestorListDto> updateGestor(@Valid @RequestBody GestorDto dto) {
    return new ResponseEntity<>(this.service.updateGestor(dto), HttpStatus.OK);
  }

}
