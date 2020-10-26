package br.com.rafael.dsbootcampchallenger.resources;


import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafael.dsbootcampchallenger.dto.ClientDTO;
import br.com.rafael.dsbootcampchallenger.entities.Client;
import br.com.rafael.dsbootcampchallenger.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clienteService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction
			){
	
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		Page<ClientDTO> list = clienteService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		ClientDTO c = clienteService.findById(id);
		
		return ResponseEntity.ok().body(c);
		
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert( @RequestBody ClientDTO clientDto){
		clientDto = clienteService.insert(clientDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(clientDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(clientDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity <ClientDTO> update(@PathVariable Long id ,@RequestBody ClientDTO client){
		client = clienteService.update(id, client);
		
		return ResponseEntity.ok().body(client);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
		 clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
