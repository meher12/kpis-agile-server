package com.mdev.springboot.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdev.springboot.models.ERole;
import com.mdev.springboot.models.Role;
import com.mdev.springboot.repository.RoleRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{
    

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    
    @Autowired
    RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
       
        // create and save new role
        if (roleRepository.findByName(ERole.ROLE_PRODUCTOWNER).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_PRODUCTOWNER));
        }
       

        if (roleRepository.findByName(ERole.ROLE_SCRUMMASTER).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_SCRUMMASTER));
        }

        if (roleRepository.findByName(ERole.ROLE_DEVELOPER).isEmpty()) {
            roleRepository.save(new Role(ERole.ROLE_DEVELOPER));
        }
        
        roleRepository.findAll().forEach((roles) -> {
            logger.info("{}", roles.getName());
        });
        
    }

}
