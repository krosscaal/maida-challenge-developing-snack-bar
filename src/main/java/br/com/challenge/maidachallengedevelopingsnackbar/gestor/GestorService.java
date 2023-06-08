/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.gestor;

import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.gestor.dto.GestorDto;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GestorService {

  final static String MANAGER_EXISTS ="Gestor já foi cadastrado, somente pode cadastrar um único Gestor!";

  final static String MANAGER_NOT_FOUND = "Gestor não encontrado!";
  @Autowired
  private GestorRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  public GestorEntity getGestor(final Long id) {

    final Optional<GestorEntity> optionalObjPersisted = this.findGestor(id);
    return optionalObjPersisted.get();
  }

  @Transactional
  public GestorEntity addGestor(final GestorDto dto) {

    if(this.repository.count() > 0) {
      throw new BusinessException(MANAGER_EXISTS);
    }
    GestorEntity obj = modelMapper.map(dto, GestorEntity.class);
    return this.repository.save(obj);
  }
  @Transactional
  public GestorEntity updateGestor(final Long id, final GestorDto dto) {

    final Optional<GestorEntity> optinalObjPersisted = this.findGestor(id);

    GestorEntity obj = modelMapper.map(dto, GestorEntity.class);

    obj.setCreatedAt(optinalObjPersisted.get().getCreatedAt());
    obj.setId(optinalObjPersisted.get().getId());
    return this.repository.save(obj);
  }

  private Optional<GestorEntity> findGestor(final Long id) {
    final Optional<GestorEntity> optionalObj = this.repository.findById(id);
    if(optionalObj.isEmpty()){
      throw new BusinessException(MANAGER_NOT_FOUND);
    }
    return optionalObj;
  }

}
