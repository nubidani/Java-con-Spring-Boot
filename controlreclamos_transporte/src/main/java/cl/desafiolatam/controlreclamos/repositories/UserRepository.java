package cl.desafiolatam.controlreclamos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.desafiolatam.controlreclamos.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	public User findByUsername(String name); 
}
