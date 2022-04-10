package com.mdev.springboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;
import com.mdev.springboot.utils.Efficacity;
import com.mdev.springboot.utils.PairArrays;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SprintRepository sprintRepository;

    @Override
    public ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone,
            ArrayList<String> moresp) {

        ArrayList<String> totalspCommitment = new ArrayList<String>();

        int spwrked[] = new int[spDone.size()];
        int morework[] = new int[moresp.size()];

        for (int index = 0; index < spwrked.length; index++) {

            spwrked[index] = Integer.parseInt(spDone.get(index));
        }

        for (int index = 0; index < morework.length; index++) {

            morework[index] = Integer.parseInt(moresp.get(index));
        }

        int SP = sumStorypoints;
        int newtask = 0;
        totalspCommitment.add(String.valueOf(SP));
        for (int j = 0; j < spwrked.length; j++) {
            if (morework[j] == 0) {
                newtask = Math.abs(SP - spwrked[j]);
                SP = newtask;
            } else {
                newtask = Math.abs(SP + morework[j] - spwrked[j]);
                SP = newtask;
            }
            totalspCommitment.add(String.valueOf(SP));
        }

        // System.out.println("Commitement" + totalspCommitment);
        return totalspCommitment;

    }

    @Override
    public ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints, ArrayList<String> tabFromdb) {

        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();

        ArrayList<String> percentageTab = new ArrayList<>();

        double sum = sumStorypoints;
        float percentage;
        List<Double> numbers = new ArrayList<>();

        for (String item : tabFromdb) {
            numbers.add(Double.valueOf(item));
        }

        for (int i = 0; i < numbers.size(); i++) {
            percentage = (float) ((numbers.get(i) * 100) / sum);

            percentageTab.add(String.format("%.2f", percentage));
        }
        // System.out.println(percentageTab);
        return percentageTab;
    }

    // List status task by project ref
    @Override
    public PairArrays listTaskByStatus(String p_reference) {

        List<String[]> list = projetRepository.getListStatusTasks(p_reference);

        ArrayList<String> tabkey = new ArrayList<>();
        ArrayList<String> tabvalue = new ArrayList<>();

        Map<String, String> map = new HashMap<String, String>();
        for (String[] ob : list) {
            String key = (String) ob[0];
            String value = (String) ob[1];
            map.put(key, value);
        }

        map.forEach((status, number) -> {
            // System.out.println("Status: " + status + ", Number: " + number);
            tabkey.add(status);
            tabvalue.add(number);
        });

        PairArrays pair = new PairArrays();
        pair.KeyArr = tabkey;
        pair.ValueArr = tabvalue;

        return pair;
    }

    @Override
    public PairArrays efficacityByStartDateTask(String referenceProject, Map<Date, Date> requestMap)
            throws ParseException {

        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
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
//
//        Map<Date, Date> mapDate = new HashMap<Date, Date>();
//
//        mapDate.put(date1, date2);
//        mapDate.put(date3, date4);
//        mapDate.put(date5, date6);
//        mapDate.put(date7, date8);

        Set<Date> listDates = requestMap.keySet();
        ArrayList<String> dateArray = new ArrayList<String>();

        ArrayList<Float> efficacityArray = new ArrayList<Float>();

        Efficacity efficacityObj = new Efficacity();
        ArrayList<Efficacity> efficacityList = new ArrayList<>();
        for (Date datestart : listDates) {

            Date dateend = requestMap.get(datestart);

            // System.out.println("datekey1: " + datestart + " ==> dateend: " + dateend);
            float scheduledcount = projetRepository.getCountStatusScheduled(referenceProject, datestart, dateend);
            // System.out.printf("-------------- scheduledcount %f : \n", scheduledcount);

            float InprogressCount = projetRepository.getCountStatusInprogress(referenceProject, datestart, dateend);
            // System.out.printf("-------------- InprogressCount %f : \n", InprogressCount);

            float efficacity = InprogressCount / (scheduledcount + InprogressCount) * 100;
//            boolean res2 = Float.isNaN(efficacity);
//            if(res2) {
//                efficacity = -1;
//            }
//            System.out.println("-------------- date : "+ dateend);
//           System.out.printf("-------------- efficacity %f : \n", efficacity);

            efficacityObj.setEndDate(dateend);
            efficacityObj.setEfficacity(efficacity);

            efficacityList.add(new Efficacity(efficacityObj.getEndDate(), efficacityObj.getEfficacity()));

            // Remove NaN efficacity value:
            efficacityList.removeIf(item -> Float.isNaN(item.getEfficacity()));

            // Sort in assending order
            Collections.sort(efficacityList, new Comparator<Efficacity>() {
                public int compare(Efficacity e1, Efficacity e2) {
                    return Long.valueOf(e1.getEndDate().getTime()).compareTo(e2.getEndDate().getTime());
                }
            });

            // Sort in dessending order
//            Collections.sort(efficacityList, new Comparator<Efficacity>() {
//                public int compare(Efficacity e1, Efficacity e2) {
//                    return Long.valueOf(e2.getDateEnd().getTime()).compareTo(e1.getDateEnd().getTime());
//                }
//            });
        }

        for (Efficacity eff : efficacityList) {
            // System.out.println("Efficacity list: ");
            String dateString = obj.format(eff.getEndDate());
            // System.out.println(datSSS);
            dateArray.add(dateString);
            // System.out.println(eff.getEfficacity());
            efficacityArray.add(eff.getEfficacity());
        }
        // System.out.println(dateArray);
        // System.out.println(efficacityArray);

        PairArrays pairArrays = new PairArrays();
        pairArrays.KeyArr = dateArray;
        pairArrays.FloatArr = efficacityArray;

        return pairArrays;
    }

}
