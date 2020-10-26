package br.com.rafael.dsbootcampchallenger.resources;


import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafael.dsbootcampchallenger.entities.Client;
import br.com.rafael.dsbootcampchallenger.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = clienteService.findAll();
		
		//list.add( new Client(1L, "Joao", "11274293707",650.00, Instant.now(), 1));
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client c = clienteService.findById(id);
		
		return ResponseEntity.ok().body(c);
		
	}
	
	@PostMapping
	public ResponseEntity<Client> insert( @RequestBody Client client){
		client = clienteService.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(client.getId()).toUri();
		
		return ResponseEntity.created(uri).body(client);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity <Client> update(@PathVariable Long id ,@RequestBody Client client){
		client = clienteService.update(id, client);
		
		return ResponseEntity.ok().body(client);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id){
		 clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
