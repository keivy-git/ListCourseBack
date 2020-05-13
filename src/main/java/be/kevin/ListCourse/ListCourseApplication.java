package be.kevin.ListCourse;

import be.kevin.ListCourse.entities.*;
import be.kevin.ListCourse.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ListCourseApplication {


	public static void main(String[] args) {
		SpringApplication.run(ListCourseApplication.class, args);
	}

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private CouponRepository couponRepository;
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private PasswordEncoder passwordEncoder;

	public ListCourseApplication(UserRepository userRepository, RoleRepository roleRepository,
								 CouponRepository couponRepository, PasswordEncoder passwordEncoder,
								 ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.couponRepository = couponRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/** l'initialisation des données doit se faire dans cet ordre précis pour les liaison entre elle*/
	@EventListener(ApplicationReadyEvent.class)
	public void insertDb() {
		addRoles();
		addCoupon();
		addCategory();
		addProduct();
		addUsers();
	}

	private void addCategory(){

		Category fruit = new Category();
		fruit.setName("fruit");

		Category legume = new Category();
		legume.setName("legume");

		Category surgeler = new Category();
		surgeler.setName("surgeler");

		Category conserve = new Category();
		conserve.setName("conserve");

		categoryRepository.save(fruit);
		categoryRepository.save(legume);
		categoryRepository.save(surgeler);
		categoryRepository.save(conserve);
	}

	private void addProduct() {
		Product product_banane = new Product();
		product_banane.setName("Banane");
		product_banane.setQuantity(1);
		product_banane.setPoids(1000);
//		product_banane.getCategories().add(categoryRepository.findByName("fruit"));
//		product_banane.getCategories().add(categoryRepository.findByName("surgeler"));


		Product product_brocoli = new Product();
		product_brocoli.setName("Brocoli");
		product_brocoli.setQuantity(1);
//		product_brocoli.getCategories().add(categoryRepository.findByName("legume"));
//		product_brocoli.getCategories().add(categoryRepository.findByName("surgeler"));
//		product_brocoli.getCategories().add(categoryRepository.findByName("conserve"));

		productRepository.save(product_banane);
		productRepository.save(product_brocoli);
	}

	private void addCoupon(){
		Coupon reduct_banane = new Coupon();
		reduct_banane.setName("-50% sur les bananes");
		reduct_banane.setDateBegin(LocalDate.of(2020, 4, 30));
		reduct_banane.setDateEnd(LocalDate.of(2020, 5, 10));
		reduct_banane.setDescription("Acheter pour 2 kg de banane et obtenez une réduction de 50 %");


		Coupon reduct_pomme = new Coupon();
		reduct_pomme.setName("-50% sur les pommes");
		reduct_pomme.setDateBegin(LocalDate.of(2020, 4, 30));
		reduct_pomme.setDateEnd(LocalDate.of(2020, 4, 20));
		reduct_pomme.setDescription("Acheter pour 1 kg de pommes et obtenez une réduction de 50 %");


		couponRepository.save(reduct_banane);
		couponRepository.save(reduct_pomme);
	}

	private void addRoles(){
		Role adminRole = new Role();
		adminRole.setName("Administrateur");
		adminRole.setDescriptionRole("Role qui a tout les droits sur l'application");

		Role memberRole = new Role();
		memberRole.setName("Membre");
		memberRole.setDescriptionRole("Membre qui a les droits sur leurs profils, la création d'une liste produit");

		roleRepository.save(adminRole);
		roleRepository.save(memberRole);
	}
	private void addUsers() {
		User kev = new User();
		kev.setFirstName("Kev");
		kev.setName("Jo");
		kev.setPassword(passwordEncoder.encode("kev"));
		kev.setEmail("kev@gmail.com");
		kev.getRoles().add(roleRepository.findByName("Administrateur"));
		kev.getRoles().add(roleRepository.findByName("Membre"));


		User erwin = new User();
		erwin.setFirstName("Erwin");
		erwin.setName("Richard");
		erwin.setPassword(passwordEncoder.encode("erwin"));
		erwin.setEmail("richard@gmail.com");
		erwin.getRoles().add(roleRepository.findByName("Membre"));

		userRepository.save(kev);
		userRepository.save(erwin);

	}

}
