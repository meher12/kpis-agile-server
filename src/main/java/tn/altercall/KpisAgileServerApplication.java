package tn.altercall;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class KpisAgileServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(KpisAgileServerApplication.class, args);
        deleteDirectoryStream();

    }

    /*
  * the expression into separate components:
      0 - at second :00,
      0/5 - every 5 minutes starting at minute :00,
      * - every hour,
      * - every day,
      * - every month,
      ? - any day of the week.
  * */
    @Scheduled(cron = "*/30 * * * * ?")  // 0 */5 * * * ?
    private static void  deleteDirectoryStream() throws IOException, InterruptedException {
        Path path = Paths.get("uploads");
        File directory = new File(path + "");
        if (!FileUtils.isEmptyDirectory(directory)) {
            log.info("Directory not empty");
            Thread.sleep(3000);
            FileUtils.cleanDirectory(directory);
            log.info("File deleted");
        } else {
            log.info("Directory is empty");
        }
    }



    //@Scheduled(cron = "2 * * * * *")
   /* public void scheduleTaskUsingCronExpression() {
         Path path = Paths.get("uploads");
        long now = System.currentTimeMillis() / 1000;
        System.out.println( "schedule tasks using cron jobs - " + now);
        String fileName = path +"/*.xml";
        log.info("----------  {}", fileName);
        if(!fileName.isEmpty()) {
            try {
                Files.delete(Paths.get(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/





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
