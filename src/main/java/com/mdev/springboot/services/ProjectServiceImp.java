package com.mdev.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;

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

        System.out.println("Commitement" + totalspCommitment);
        return totalspCommitment;

    }

    @Override
    public ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints) {
        
       
       
        
        ArrayList<String> tabFromdb = sprintRepository.getListSpCompleted();

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

}
