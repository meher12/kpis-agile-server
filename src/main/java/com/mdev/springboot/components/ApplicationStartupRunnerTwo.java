package com.mdev.springboot.components;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;
import com.mdev.springboot.services.SprintServiceImp;

@Order(value = 2)
@Component
public class ApplicationStartupRunnerTwo implements CommandLineRunner {

   private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupRunnerTwo.class);

    @Autowired
    SprintRepository sprintRepository;
    @Autowired
    SprintServiceImp sprintServiceImp;
    
    @Autowired
    ProjetRepository projetRepository;
    
    @Autowired
    StoryRepository storyRepository;
    
    @Autowired
    TaskRepository taskRepository;
    


    @Override
    public void run(String... args) throws Exception {
      
//        storyRepository.StoryPointUpdate();
//        sprintRepository.sprintStoryPointUpdate();
//        projetRepository.totalSpInProject();
//        taskRepository.tasktimeUpdate();
        
        //More table for  brundown relases
        List<String> moresp = taskRepository.getspMoretasks();
        System.out.println(moresp);
        
        //Story points completed in project
        List<String> spDone = sprintRepository.getLisSpCompleted();
        System.out.println(spDone);
        
        ArrayList<Integer> totalspCommitment = new ArrayList<Integer>();
        int spwrked[] = {10, 10, 10, 10, 10, 10, 10};
        int morework[] =   {0, 0, 0, 3, 0, 0, 0};
        
        int SP = 100;
        int newtask = 0;
        totalspCommitment.add(SP);
              for (int j = 0; j <spwrked.length; j++) {
                  if(morework[j] == 0){
                   newtask =   SP -   spwrked[j];
                   SP =  newtask;
                  }
                  else{
                       newtask =   (SP+morework[j]) -   spwrked[j];
                       SP =  newtask;
                      // System.out.println(newtask);
                  }
                  totalspCommitment.add(SP);
              }
              // System.out.println(sptb);
        
       
       
        
       
        
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

   }
}
