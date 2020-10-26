package br.com.rafael.dsbootcampchallenger.services;



import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafael.dsbootcampchallenger.dto.ClientDTO;
import br.com.rafael.dsbootcampchallenger.entities.Client;
import br.com.rafael.dsbootcampchallenger.exception.DataBaseException;
import br.com.rafael.dsbootcampchallenger.exception.ResourceNotFoundException;
import br.com.rafael.dsbootcampchallenger.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public Page <ClientDTO> findAllPaged(PageRequest pageRequest){
		Page <Client> list = clienteRepository.findAll(pageRequest);
		
		return list.map(x->new ClientDTO(x));
		
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = clienteRepository.findById(id);
		Client entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not Found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO client) {
		try {
			Client entiClient = new Client();
			copyDtoToEntity(client, entiClient);
			entiClient = clienteRepository.save(entiClient);
			return new ClientDTO(entiClient);
		} catch (Exception e) {
			throw new DataBaseException("Erro ao salvar objeto no H2"+ e);
		}
		
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client entity=  clienteRepository.getOne(id);
			copyDtoToEntity(clientDTO, entity);
			entity = clienteRepository.save(entity);
			return new ClientDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id Not Found" + e);
		}
		
	}

	@Transactional
	public void delete(Long id) {
		try {
			clienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not Found" + e);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violition" + e);
		}
	}
	
	private void copyDtoToEntity(ClientDTO clientDTO , Client clientEntity) {
		clientEntity.setName(clientDTO.getName());
		clientEntity.setCpf(clientDTO.getCpf());
		clientEntity.setBirthDate(clientDTO.getBirthDate());
		clientEntity.setIncome(clientDTO.getIncome());
		clientEntity.setChildren(clientDTO.getChildren());
		
	}

	
	
	
	

	
	


}
