package com.mdev.springboot.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
import com.mdev.springboot.utils.Efficacity;

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
//        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
//        Date date1 = obj.parse("15-03-2022"); // 2022-03-15 01-03-2022
//        Date date2 = obj.parse("28-05-2022"); // 2022-05-28 03-05-2022
//
//        Date date3 = obj.parse("06-04-2022"); // 2022-04-06 01-03-2022
//        Date date4 = obj.parse("17-06-2022"); // 2022-06-17 03-05-2022
//
//        Date date5 = obj.parse("12-04-2022"); // 2022-04-12 01-03-2022
//        Date date6 = obj.parse("11-05-2022"); // 2022-05-11 03-05-2022
//
//        Date date7 = obj.parse("01-05-2022"); // 2022-04-12 01-03-2022
//        Date date8 = obj.parse("10-05-2022"); // 2022-05-11 03-05-2022 2022-05-10

//        float scheduledcount = projetRepository.getCountStatusScheduled("PUID17B44", date1, date2);
//        System.out.printf("-------------- scheduledcount %f : \n", scheduledcount);
//        
//        float InprogressCount = projetRepository.getCountStatusInprogress("PUID17B44", date1, date2);
//        System.out.printf("-------------- InprogressCount %f : \n", InprogressCount);
//        
//        float efficacity = InprogressCount/(scheduledcount+InprogressCount)*100;
//        System.out.printf("-------------- efficacity %f : \n", efficacity);

//        Map<Date, Date> mapDate = new HashMap<Date, Date>();
//
//        mapDate.put(date1, date2);
//        mapDate.put(date3, date4);
//        mapDate.put(date5, date6);
//        mapDate.put(date7, date8);
//
//        Set<Date> listDates = mapDate.keySet();
//        ArrayList<String> dateArray = new ArrayList<String>();
//
//        ArrayList<Float> efficacityArray = new ArrayList<Float>();
//
//        Efficacity efficacityObj = new Efficacity();
//        ArrayList<Efficacity> efficacityList = new ArrayList<>();
//        for (Date datestart : listDates) {
//
//            Date dateend = mapDate.get(datestart);

            // System.out.println("datekey1: " + datestart + " ==> dateend: " + dateend);
//            float scheduledcount = projetRepository.getCountStatusScheduled("PUID17B44", datestart, dateend);
            // System.out.printf("-------------- scheduledcount %f : \n", scheduledcount);

 //           float InprogressCount = projetRepository.getCountStatusInprogress("PUID17B44", datestart, dateend);
            // System.out.printf("-------------- InprogressCount %f : \n", InprogressCount);

 //           float efficacity = InprogressCount / (scheduledcount + InprogressCount) * 100;
//            System.out.println("-------------- date : "+ dateend);
//            System.out.printf("-------------- efficacity %f : \n", efficacity);

//            efficacityObj.setDateEnd(dateend);
//            efficacityObj.setEfficacity(efficacity);
//
//            efficacityList.add(new Efficacity(efficacityObj.getDateEnd(), efficacityObj.getEfficacity()));

            // Sort in assending order
//            Collections.sort(efficacityList, new Comparator<Efficacity>() {
//                public int compare(Efficacity e1, Efficacity e2) {
//                    return Long.valueOf(e1.getDateEnd().getTime()).compareTo(e2.getDateEnd().getTime());
//                }
//            });

            // Sort in dessending order
//            Collections.sort(efficacityList, new Comparator<Efficacity>() {
//                public int compare(Efficacity e1, Efficacity e2) {
//                    return Long.valueOf(e2.getDateEnd().getTime()).compareTo(e1.getDateEnd().getTime());
//                }
//            });
 //       }

//        for (Efficacity eff : efficacityList) {
//            // System.out.println("Efficacity list: ");
//            String dateString = obj.format(eff.getDateEnd());
//            // System.out.println(datSSS);
//            dateArray.add(dateString);
//            // System.out.println(eff.getEfficacity());
//            efficacityArray.add(eff.getEfficacity());
//        }
//        System.out.println(dateArray);
//        System.out.println(efficacityArray);
//        res.keySet().stream().sorted().forEach(key -> {
//            Float value = res.get(key);
//
//            System.out.println("key: " + key);
//            System.out.println("value: " + value);
//        }
//    );

//        Set s = res.entrySet();
//        Iterator i = s.iterator();
//        while (i.hasNext()) {
//            Map.Entry m = (Map.Entry) i.next();
//            String key = (String) m.getKey();
//            float value = (float) m.getValue();
//
//            System.out.println("Key : " + key + "  value : " + value);
//        }
        // System.out.println(res);

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
