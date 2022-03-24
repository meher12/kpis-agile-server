package com.mdev.springboot.components;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.services.SprintServiceImp;
//
//@Order(value = 2)
//@Component
//public class ApplicationStartupRunnerTwo implements CommandLineRunner {

   // private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunnerTwo.class);

//    @Autowired
//    SprintRepository sprintRepository;
//    @Autowired
//    SprintServiceImp sprintServiceImp;
//    
//
//
//    @Override
//    public void run(String... args) throws Exception {
//       
//       //Long id = 55;
//        Sprint sprints = sprintRepository.findById((long) 53)
//             .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with id = " ));
//        
//
//          sprints.setDaysarray(sprintServiceImp.numberOfDaysInSprint(sprints.getSdateDebut(), sprints.getSdateFin()));
//        List<String> result =  sprints.getDaysarray();
//       System.out.println("************************"+ result);
//        
//       
//        sprintRepository.sprintArrayDays(58, result);
        
       
        
//         sprint.setArrayOfDays(sprintServiceImp.numberOfDaysInSprint(str_date, end_date));
//         sprint.getArrayOfDays();
        //sprintRepository.sprintArrayDays(sprint.getArrayOfDays());
        
//        sprint.setArrayOfDays(sprintServiceImp.numberOfDaysInSprint(str_date, end_date));
//        System.out.println("************************"+ sprint.getArrayOfDays());

        //sprintRepository.sprintStoryPointUpdate();
//
//        ArrayList<Integer> workCommitmentList = new ArrayList<>();
//        ArrayList<Integer> workCompletedList = new ArrayList<>();
//
//        List<Sprint> ss = sprintRepository.findAll();
//        HashMap<Integer, Integer> sprintTab = new HashMap<Integer, Integer>();
//
//
//        sprintRepository.findAll().forEach((sprints) -> {
//            workCommitmentList.add(sprints.getWorkCommitment());
//            workCompletedList.add(sprints.getWorkCompleted());
//            sprintTab.put(sprints.getWorkCommitment(), sprints.getWorkCompleted());
//
//            // logger.info("{}", sprints.getWorkCommitment());
//        });
//        logger.info("{}", workCommitmentList);
//        logger.info("{}", workCompletedList);
//
//        logger.info("{}", sprintTab);

//    }
//}
