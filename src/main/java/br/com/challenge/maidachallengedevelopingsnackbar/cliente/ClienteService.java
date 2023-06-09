/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.cliente;

import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDto;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoDadosPublicos;
import br.com.challenge.maidachallengedevelopingsnackbar.cliente.dto.ClienteDtoVerDadosPeloGestor;
import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
  final static String COSTUMER_EMAIL_EXISTS ="Já existe um cliente cadastrado com esse e-mail!";
  final static String COSTUMER_NOT_FOUND = "Cliente não encontrado!";

  @Autowired
  private ClienteRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  @Transactional
  public ClienteDtoDadosPublicos addCliente(final ClienteDto dto) {
    if(this.emailExists(dto)) {
      throw new BusinessException(COSTUMER_EMAIL_EXISTS);
    }
    ClienteEntity clienteObj = modelMapper.map(dto, ClienteEntity.class);
    this.repository.save(clienteObj);
    ClienteDtoDadosPublicos clienteDtoDadosPublicos = modelMapper.map(clienteObj, ClienteDtoDadosPublicos.class);
    return clienteDtoDadosPublicos;
  }

  @Transactional
  public ClienteDtoDadosPublicos updateCliente(final Long id, final ClienteDto dto) {

    final Optional<ClienteEntity> optionalObjPersisted = this.findCliente(id);
    this.emailExists(dto);
    ClienteEntity updateObj = modelMapper.map(dto, ClienteEntity.class);

    updateObj.setId(id);
    updateObj.setCreatedAt(optionalObjPersisted.get().getCreatedAt());
    this.repository.save(updateObj);

    ClienteDtoDadosPublicos clienteDtoDadosPublicos = modelMapper.map(updateObj, ClienteDtoDadosPublicos.class);
    return clienteDtoDadosPublicos;
  }

  public ClienteDtoVerDadosPeloGestor getClienteParaGestor(final Long id) {
    final Optional<ClienteEntity> clienteObjPersisted = this.findCliente(id);
    ClienteDtoVerDadosPeloGestor clienteDtoVerDadosPeloGestor =
        modelMapper.map(clienteObjPersisted.get(), ClienteDtoVerDadosPeloGestor.class);
    return clienteDtoVerDadosPeloGestor;
  }

  public ClienteDtoDadosPublicos getClienteParaCliente(final Long id) {
    final Optional<ClienteEntity> clienteObjPersisted = this.findCliente(id);
    ClienteDtoDadosPublicos clienteDtoDadosPublicos =
        modelMapper.map(clienteObjPersisted.get(), ClienteDtoDadosPublicos.class);
    return clienteDtoDadosPublicos;
  }

  public List<ClienteDtoVerDadosPeloGestor> listClientesParaGestor() {
    return this.repository.findAll(Sort.by("nome"))
        .stream().map(cliente -> modelMapper.map(cliente, ClienteDtoVerDadosPeloGestor.class))
        .collect(Collectors.toList());
  }


  private Optional<ClienteEntity> findCliente(final Long id) {
    final Optional optionalObj = this.repository.findById(id);
    if(optionalObj.isEmpty()) {
      throw new BusinessException(COSTUMER_NOT_FOUND);
    }
    return optionalObj;
  }

  private boolean emailExists(ClienteDto dto) {
    return this.repository
        .findByEmail(dto.email)
        .stream()
        .anyMatch(emailExiste-> !emailExiste.equals(dto.email));
  }
}
