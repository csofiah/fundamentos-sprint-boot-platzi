package com.fundamentosplatzi.sprintboot.fundamentos;

import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.sprintboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.pojo.UserProperties;
import com.fundamentosplatzi.sprintboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.sprintboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserProperties userProperties;
	private UserRepository userRepository;
	private UserService userService;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency,
								  MyBean myBean, MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties, UserProperties userProperties,
								  UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userProperties = userProperties;
		this.userRepository = userRepository;
		this.userService = userService;
	}


	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {

		//saveUsersInDb();
		//ejemploClasesAnteriores();
		saveUsersInDataBase();
		//getInformationFromJPQL();
		saveWithErrorTransactional();

	}

	private void saveWithErrorTransactional(){
		User test1 = new User("Test1Transactional1", "testTransactional1@domain.com", LocalDate.now());
		User test2 = new User("Test2Transactional1", "testTransactional2@domain.com", LocalDate.now());
		User test3 = new User("Test3Transactional1", "testTransactional1@domain.com", LocalDate.now());//mismo correo q test1
		User test4 = new User("Test4Transactional1", "testTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);
		}catch(Exception e){
			LOGGER.error("esto es un error del metodo transaccional " + e.getMessage());
		}

		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transactional " + user));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("Luis", "luis@gmail.com", LocalDate.of(2021,03,20));
		User user2 = new User("jhon", "jhon@gmail.com", LocalDate.of(2021,04,21));
		User user3 = new User("dani", "daniela@gmail.com", LocalDate.of(2021,07,21));
		User user4 = new User("jhon4", "jhon4@gmail.com", LocalDate.of(2021,05,21));
		User user5 = new User("jhon5", "jhon5@gmail.com", LocalDate.of(2021,04,21));
		User user6 = new User("jhon6", "jhon6@gmail.com", LocalDate.of(2021,07,01));
		User user7 = new User("jhon7", "jhon7@gmail.com", LocalDate.of(2021,04,21));
		User user8 = new User("jhon8", "jhon8@gmail.com", LocalDate.of(2021,04,11));
		User user9 = new User("jhon9", "jhon9@gmail.com", LocalDate.of(2021,04,10));
		User user10 = new User("jhon10", "jhon10@gmail.com", LocalDate.of(2021,06,21));
		User user11 = new User("jhon11", "jhon11@gmail.com", LocalDate.of(2021,07,22));
		User user12 = new User("jhon12", "jhon12@gmail.com", LocalDate.of(2021,04,21));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}

	private void saveUsersInDb() {
		User user1 = new User("John", "john@domain.com", LocalDate.now());
		User user2 = new User("Julie", "julie@domain.com", LocalDate.now());
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.now());
		User user4 = new User("Oscar", "oscar@domain.com", LocalDate.now());
		User user5 = new User("Test1", "Test1@domain.com", LocalDate.now());
		User user6 = new User("Test2", "Test2@domain.com", LocalDate.now());
		User user7 = new User("Test3", "Test3@domain.com", LocalDate.now());
		User user8 = new User("Test4", "Test4@domain.com", LocalDate.now());
		User user9 = new User("Test5", "Test5@domain.com", LocalDate.now());
		User user10 = new User("Test6", "Test6@domain.com", LocalDate.now());
		User user11 = new User("Test7", "Test7@domain.com", LocalDate.now());
		User user12 = new User("Test8", "Test8@domain.com", LocalDate.now());
		User user13 = new User("Test9", "Test9@domain.com", LocalDate.now());
		List<User> list = Arrays.asList(user4, user1, user3, user2, user5, user6, user7, user8, user9, user10, user11, user12, user13);
		list.stream().forEach(userRepository::save);
	//	postRepository.save(new Posts("Mi post test1", user12));
	//	postRepository.save(new Posts("Mi post test2", user13));
	//	postRepository.save(new Posts("Mi post test3", user13));

	}

	private void getInformationFromJPQL(){
		LOGGER.info("usuario con el metodo findByUserEmail "+
				userRepository.findMyUserByEmail("jhon@gmail.com")
						.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));


		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("User with methodo "+ user));

		userRepository.findByName("Luis")
		.stream()
		.forEach(user -> LOGGER.info("Usuario con query method " + user));

		LOGGER.info("Usuario con Query Method " + userRepository.findByEmailAndName("daniela@gmail.com","dani" )
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

		userRepository.findByNameLike("%user%")
				.stream()
				.forEach(user -> LOGGER.info("usuario con findByNameLike  "+ user));

		userRepository.findByNameOrEmail(null,"jhon10@gmail.com")
				.stream()
				.forEach(user -> LOGGER.info("usuario con findByNameOrEmail  "+ user));

		userRepository.findByBirthDateBetween( LocalDate.of(2021,03,01),  LocalDate.of(2021,04,11))
				.stream()
				.forEach(user -> LOGGER.info("usuario con findByBirthDateBetween  "+ user));

		userRepository.findByNameLikeOrderByIdDesc("%jhon%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con findByNameLikeOrderByIdDesc " + user));

		userRepository.findByNameContainingOrderByIdDesc("jhon")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con findByNameLikeOrderByIdDesc " + user));

		LOGGER.info("El usuario a partir del named parameter es " + userRepository.getAllByBirthDateAndEmail( LocalDate.of(2021,07,21),"daniela@gmail.com")
				.orElseThrow(() -> new RuntimeException("No se encontro el usuario a partir del named parameter")));
	}

	private void ejemploClasesAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userProperties.getEmail() + "-" + userProperties.getPassword());
		LOGGER.error("esto es un error del app");
	}
}
