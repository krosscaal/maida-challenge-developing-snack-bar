/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor;

import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.MANAGER_EXISTS;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.MANAGER_NAME_ERROR;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.MANAGER_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.MANAGER_TELEFONE_ERROR;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDto;
import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorDto;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorListDto;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GestorService {

  @Autowired
  private GestorRepository repository;
  @Autowired
  private ModelMapper modelMapper;

  public GestorListDto getGestor() {

    final Optional<GestorEntity> optionalGestorEntityPersisted = this.findGestor();
    GestorListDto gestorListDto = modelMapper.map(optionalGestorEntityPersisted.get(), GestorListDto.class);
    return gestorListDto;
  }

  @Transactional
  public GestorListDto addGestor(final GestorDto dto) {
    this.validarGestorDto(dto);
    if(this.repository.count() > 0) {
      throw new BusinessException(MANAGER_EXISTS);
    }
    GestorEntity newObj = modelMapper.map(dto, GestorEntity.class);

    this.repository.save(newObj);
    GestorListDto gestorListDto = modelMapper.map(newObj, GestorListDto.class);
    return gestorListDto;
  }
  @Transactional
  public GestorListDto updateGestor(final GestorDto dto) {

    this.validarGestorDto(dto);
    final Optional<GestorEntity> optinalObjPersisted = this.findGestor();

    GestorEntity updateObj = modelMapper.map(dto, GestorEntity.class);

    updateObj.setCreatedAt(optinalObjPersisted.get().getCreatedAt());
    updateObj.setId(optinalObjPersisted.get().getId());

    this.repository.save(updateObj);
    GestorListDto gestorListDto = modelMapper.map(updateObj, GestorListDto.class);
    return gestorListDto;
  }

  private Optional<GestorEntity> findGestor() {

    final Optional<GestorEntity> firstOptionalGestor = this.repository.findAll().stream().findFirst();
    if(firstOptionalGestor.isEmpty()){
      throw new BusinessException(MANAGER_NOT_FOUND);
    }
    return firstOptionalGestor;
  }
  private void validarGestorDto(final GestorDto dto) {
    dto.setNome(dto.getNome().toUpperCase(Locale.ROOT));
    dto.setEmail(dto.getEmail().trim());
    dto.setSenha(dto.getSenha().trim());
    dto.setNomeLanchonete(dto.getNomeLanchonete().toUpperCase(Locale.ROOT));

    final String nomeGestor = dto.getNome();
    final String telefoneGestor = dto.getTelefone();

    final boolean nomeIsLetras = nomeGestor.matches("[A-Z ]+");
    final boolean telefoneIsNumeros = telefoneGestor.matches("[0-9]+");

    if(!nomeIsLetras) {
      throw new BusinessException(MANAGER_NAME_ERROR);
    }
    if(dto.getNome().length() == 5 && dto.getNome().contains(" ")) {
      throw new BusinessException(MANAGER_NAME_ERROR);
    }
    if(!telefoneIsNumeros){
      throw new BusinessException(MANAGER_TELEFONE_ERROR);
    }
  }

}
