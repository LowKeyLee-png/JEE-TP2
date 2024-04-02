package ma.emsi.hospital;

import ma.emsi.hospital.entities.*;
import ma.emsi.hospital.repositories.*;
import ma.emsi.hospital.entities.Patient;
import ma.emsi.hospital.entities.RendezVous;
import ma.emsi.hospital.entities.StatusRDV;
import ma.emsi.hospital.repositories.MedecinRepository;
import ma.emsi.hospital.repositories.PatientRepository;
import ma.emsi.hospital.repositories.RendezVousRepository;
import ma.emsi.hospital.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {
	public static void main(String[] args) {

		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService) {
		return args -> {
			User u = new User();
			u.setUserName("user");
			u.setPassword("123456");
			userService.addNewUser(u);

			User u2 = new User();
			u2.setUserName("admin");
			u2.setPassword("123456");
			userService.addNewUser(u2);

			Stream.of("STUDENT", "USER", "ADMIN").forEach(r -> {
				Role role1 = new Role();
				role1.setRoleName(r);
				userService.addNewRole(role1);

			});

			userService.addRoleToUser("user", "STUDENT");
			userService.addRoleToUser("user", "USER");
			userService.addRoleToUser("admin", "USER");
			userService.addRoleToUser("admin", "ADMIN");

			try {
				User user = userService.authentificate("user", "123456");
				System.out.println(user.getUserId());
				System.out.println(user.getUserName());
				System.out.println("Role ==> ");
				user.getRoles().forEach(r -> {
					System.out.println(r.toString());
				});
			} catch (Exception e) {
				e.printStackTrace();

			}
		};
	}
}
