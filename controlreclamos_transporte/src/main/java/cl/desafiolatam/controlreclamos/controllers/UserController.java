package cl.desafiolatam.controlreclamos.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.desafiolatam.controlreclamos.entities.User;
import cl.desafiolatam.controlreclamos.services.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	UserService us;
	
	@GetMapping("/registration")
	public ModelAndView showRegistrationForm() {
		return new ModelAndView("registrationPage")
				.addObject("user", new User());//es necesario para poder registrar un usuario
	}
	
	@PostMapping("/registration")
	public ModelAndView registerUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
	    if (result.hasErrors()) {//si aparece otro error(si se viona alguna restricción indicada en la clase Usuario)
	        return new ModelAndView("registrationPage");
	    }
	    if (user.getPassword().equals(user.getPasswordConfirmation()) == false) {//si las contraseñas no coinciden
	        result.rejectValue("passwordConfirmation", "error.user", "Las contraseñas no coinciden.");
	        return new ModelAndView("registrationPage");
	    }//si no hay error, se registra el usuario
	    us.saveWithUserRole(user);
	    return new ModelAndView("loginPage");
	}
	
	
	@GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        
        ModelAndView modelAndView = new ModelAndView("loginPage");

        if (error != null) {//si hay error de inicio de sesión, este es el mensaje que se debe mostrar
            modelAndView.addObject("errorMessage", "Credenciales inválidas, intenta nuevamente.");
        }
        
        if (logout != null) {//si se cerró la sesión, este es el mensaje que se debe mostrar
            modelAndView.addObject("logoutMessage", "Ha cerrado sesión exitosamente.");
        }
        
        return modelAndView;
    }
	 
	 @GetMapping("/home")
	 public ModelAndView home(Principal principal) {
		 String username = principal.getName();//La interfaz Principal proporciona información sobre el usuario autenticado
		 
		 return new ModelAndView("home")
				 .addObject("currentUser", us.findByUsername(username));
	 }
}
