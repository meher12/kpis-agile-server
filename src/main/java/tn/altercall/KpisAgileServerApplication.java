package tn.altercall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KpisAgileServerApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(KpisAgileServerApplication.class, args);
        

    }

//    @Bean
//    public CommandLineRunner insertRole(RoleRepository roleRepository) {
//        return args -> {
//
//            // create and save new role
//            if (roleRepository.findByName(ERole.ROLE_PRODUCTOWNER).isEmpty()) {
//                roleRepository.save(new Role(ERole.ROLE_PRODUCTOWNER));
//            }
//           
//
//            if (roleRepository.findByName(ERole.ROLE_SCRUMMASTER).isEmpty()) {
//                roleRepository.save(new Role(ERole.ROLE_SCRUMMASTER));
//            }
//
//            if (roleRepository.findByName(ERole.ROLE_DEVELOPER).isEmpty()) {
//                roleRepository.save(new Role(ERole.ROLE_DEVELOPER));
//            }
//
//        };
//
//    }

}
