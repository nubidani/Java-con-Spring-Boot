package cl.desafiolatam.controlreclamos.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



@Entity
@Table(name="usuarios")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "El nombre de usuario es obligatorio")//No pueden haber espacios en blanco
	private String username;
	
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String password;
	
	@NotBlank(message = "Debe confirmar la contraseña")//No pueden haber espacios en blanco
	@Transient
	private String passwordConfirmation;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	

	public User(Long id, String username, String password, String passwordConfirmation, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.roles = roles;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

}
