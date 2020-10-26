package br.com.rafael.dsbootcampchallenger.services;



import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafael.dsbootcampchallenger.entities.Client;
import br.com.rafael.dsbootcampchallenger.exception.DataBaseException;
import br.com.rafael.dsbootcampchallenger.exception.ResourceNotFoundException;
import br.com.rafael.dsbootcampchallenger.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public List<Client> findAll(){
		
		return clienteRepository.findAll();
		
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		Optional<Client> obj = clienteRepository.findById(id);
		Client entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity Not Found"));
		return entity;
	}

	@Transactional
	public Client insert(Client client) {
		Client entiClient = new Client();
		entiClient.setName(client.getName());
		entiClient.setCpf(client.getCpf());
		entiClient.setIncome(client.getIncome());
		entiClient.setChildren(client.getChildren());
		entiClient.setBirthDate(client.getBirthDate());
		clienteRepository.save(entiClient);
		return entiClient;
	}

	@Transactional
	public Client update(Long id, Client client) {
		try {
			Client entity=  clienteRepository.getOne(id);
			entity.setName(client.getName());
			entity.setCpf(client.getName());
			entity.setBirthDate(client.getBirthDate());
			entity.setChildren(client.getChildren());
			entity.setIncome(client.getIncome());
			entity = clienteRepository.save(entity);
			return entity;
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

	
	
	
	

	
	


}
