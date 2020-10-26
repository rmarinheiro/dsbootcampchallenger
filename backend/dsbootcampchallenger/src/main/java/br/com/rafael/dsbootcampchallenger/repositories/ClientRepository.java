package br.com.rafael.dsbootcampchallenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafael.dsbootcampchallenger.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
