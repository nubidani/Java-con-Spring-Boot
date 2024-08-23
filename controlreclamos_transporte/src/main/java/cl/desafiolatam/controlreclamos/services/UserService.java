package cl.desafiolatam.controlreclamos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.desafiolatam.controlreclamos.entities.User;
import cl.desafiolatam.controlreclamos.repositories.RoleRepository;
import cl.desafiolatam.controlreclamos.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPEncoder;
	
	 public void saveWithUserRole(User user) {
		  user.setPassword(bCryptPEncoder.encode(user.getPassword()));
		  user.setRoles(roleRepo.findByName("ROLE_USER"));
		  
		  userRepo.save(user); 
		  }
	 public void saveUserWithAdminRole(User user) {
		  user.setPassword(bCryptPEncoder.encode(user.getPassword()));
		  user.setRoles(roleRepo.findByName("ROLE_ADMIN"));
		  
		  userRepo.save(user); 
		  }
	 public User findByUsername(String username) {
			return userRepo.findByUsername(username);
		}
}
