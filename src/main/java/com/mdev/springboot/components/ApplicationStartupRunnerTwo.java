package com.mdev.springboot.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mdev.springboot.services.ProjectServiceImp;
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

    @Autowired
    ProjectServiceImp projectServiceImp;

   

    @Override
    public void run(String... args) throws Exception {

       
        
//        for (String keys : map.keySet()) {
//            System.out.println(keys + ":" + map.get(keys));
//        }
        
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
       // System.out.println(map);

//        ArrayList<String> tabFromdb = sprintRepository.getListSpCompleted();
//        
//        ArrayList<String> percentageTab = new ArrayList<>();
//        
//        double sum = 209;
//        float percentage;
//        List<Double> numbers = new ArrayList<>();
//        
//       for (String item : tabFromdb) {
//           numbers.add(Double.valueOf(item));
//       }
// 
//        for (int i = 0; i < numbers.size(); i++) {
//            percentage = (float) ((numbers.get(i) * 100) / sum);
//           
//            percentageTab.add(String.format("%.2f", percentage));
//        }
//        System.out.println(percentageTab); 

        // projectServiceImp.releaseBurndownChart(231);

//        storyRepository.StoryPointUpdate();
//        sprintRepository.sprintStoryPointUpdate();
//        projetRepository.totalSpInProject();
//        taskRepository.tasktimeUpdate();
//        sprintRepository.updateMoreSp();
//        storyRepository.updatePlusSp();

        // Story points completed in project
//        ArrayList<String> spDone = sprintRepository.getListSpCompleted();

        // More table for brundown relases
        // ArrayList<String> moresp = sprintRepository.getListMoreSp();

//        System.out.println("completed"+spDone);
//        System.out.println("more"+moresp);

//        ArrayList<Integer> totalspCommitment = new ArrayList<Integer>();
//        int spwrked[] = {10, 10, 10, 10, 10, 10, 10};
//        int morework[] =   {0, 0, 0, 3, 0, 0, 0};

//        int spwrked[] = new int[spDone.size()];
//        int morework[] = new int[moresp.size()];
//
//        for (int index = 0; index < spwrked.length; index++) {
//
//            spwrked[index] = Integer.parseInt(spDone.get(index));
//        }
//
//        System.out.println("**Done**" + Arrays.toString(spwrked));
//
//        for (int index = 0; index < morework.length; index++) {
//
//            morework[index] = Integer.parseInt(moresp.get(index));
//        }
//
//        System.out.println("*MORE***" + Arrays.toString(morework));
//
//        // System.out.println(z));
//
//        int SP = 231;
//        int newtask = 0;
//        totalspCommitment.add(SP);
//        for (int j = 0; j < spwrked.length; j++) {
//            if (morework[j] == 0) {
//                newtask = SP - spwrked[j];
//                SP = newtask;
//            } else {
//                newtask = (SP + morework[j]) - spwrked[j];
//                SP = newtask;
//                // System.out.println(newtask);
//            }
//            totalspCommitment.add(SP);
//        }

        // System.out.println("completed"+spDone);
        // System.out.println("more"+moresp);
        // System.out.println("Commitement" + totalspCommitment);

//         sprint.setArrayOfDays(sprintServiceImp.numberOfDaysInSprint(str_date, end_date));
//         sprint.getArrayOfDays();
        // sprintRepository.sprintArrayDays(sprint.getArrayOfDays());

//        sprint.setArrayOfDays(sprintServiceImp.numberOfDaysInSprint(str_date, end_date));
//        System.out.println("************************"+ sprint.getArrayOfDays());

        // sprintRepository.sprintStoryPointUpdate();
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
