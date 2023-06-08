/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor;

import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snack_bar_manager")
public class GestorController {

  @Autowired
  private GestorService service;

  @GetMapping
  @ResponseBody
  public ResponseEntity<GestorEntity> getGestor(@PathVariable("id") Long id) {
    return new ResponseEntity<>(this.service.getGestor(id), HttpStatus.OK );
  }
  @PostMapping("/new")
  @ResponseBody
  public ResponseEntity<GestorEntity> addGestor(@Valid @RequestBody GestorDto dto) {

    final GestorEntity gestorObj = this.service.addGestor(dto);
    return new ResponseEntity<>(gestorObj, HttpStatus.CREATED);
  }
  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<GestorEntity> updateGestor(@PathVariable("id") Long id, @Valid @RequestBody GestorDto dto) {
    return new ResponseEntity<>(this.service.updateGestor(id, dto), HttpStatus.OK);
  }

}
