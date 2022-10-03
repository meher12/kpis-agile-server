package tn.altercall.utils;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import tn.altercall.entities.ERole;
import tn.altercall.entities.Role;
import tn.altercall.repository.RoleRepository;
import tn.altercall.services.FilesStorageServiceImpl;

@Order(value=1)
@Component
public class ApplicationStartupRunnerOne implements CommandLineRunner{
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunnerOne.class);
    
    @Autowired
    RoleRepository roleRepository;
    
    @Resource
    FilesStorageServiceImpl storageService;
    
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
        
        // Upload file Config
        //storageService.deleteAll();
        storageService.init();
    }
}
