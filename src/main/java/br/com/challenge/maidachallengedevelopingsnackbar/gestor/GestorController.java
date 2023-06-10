/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor;

import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorDto;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorListDto;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/snack-bar")
public class GestorController {

  @Autowired
  private GestorService service;

  @GetMapping("/manager")
  @ResponseBody
  public ResponseEntity<GestorListDto> getGestor() {
    return new ResponseEntity<>(this.service.getGestor(), HttpStatus.OK );
  }
  @PostMapping("/new")
  @ResponseBody
  public ResponseEntity<GestorListDto> addGestor(@Valid @RequestBody GestorDto dto) {

    final GestorListDto gestorObj = this.service.addGestor(dto);
    return new ResponseEntity<>(gestorObj, HttpStatus.CREATED);
  }
  @PutMapping("/update")
  @ResponseBody
  public ResponseEntity<GestorListDto> updateGestor(@Valid @RequestBody GestorDto dto) {
    return new ResponseEntity<>(this.service.updateGestor(dto), HttpStatus.OK);
  }

}
