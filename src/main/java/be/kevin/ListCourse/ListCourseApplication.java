package be.kevin.ListCourse;

import be.kevin.ListCourse.entities.Role;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.repository.RoleRepository;
import be.kevin.ListCourse.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableAutoConfiguration
@SpringBootApplication
public class ListCourseApplication {


	public static void main(String[] args) {
		SpringApplication.run(ListCourseApplication.class, args);
	}

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public ListCourseApplication(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void insertDb() {
		addUsers();
		addRoles();
	}
	private void addRoles(){
		Role Admin = new Role();
		Admin.setName("Administrateur");
		Admin.setDescriptionRole("Role qui a tout les droits sur l'application");

		Role Membre = new Role();
		Membre.setName("Membre");
		Membre.setDescriptionRole("Membre qui a les droits sur leurs profils, la création d'une liste utilisateur");

		roleRepository.save(Admin);
		roleRepository.save(Membre);
	}
	private void addUsers() {
		User kev = new User();
		kev.setFirstName("Kev");
		kev.setName("Jo");
		kev.setPassword(passwordEncoder.encode("kev"));
		kev.setEmail("kev@gmail.com");
		kev.getRoles().add(roleRepository.findByName("Administrateur"));
		kev.getRoles().add(roleRepository.findByName("Membre"));

		User Lambda = new User();
		Lambda.setFirstName("lol");
		Lambda.setName("Lambda");
		Lambda.setPassword(passwordEncoder.encode("Lambda"));
		Lambda.setEmail("Lamda@gmail.com");
		Lambda.getRoles().add(roleRepository.findByName("Membre"));

		userRepository.save(kev);
		userRepository.save(Lambda);

	}

}
