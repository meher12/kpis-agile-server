package com.mdev.springboot.services;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;

@Service
public class ProjectServiceImp implements ProjectService{
    
    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    TaskRepository taskRepository;
    
    @Autowired
    SprintRepository sprintRepository;
    
    @Override
    public  ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone, ArrayList<String> moresp) {
        
        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();
        projetRepository.totalSpInProject();
        taskRepository.tasktimeUpdate();
        sprintRepository.updateMoreSp();
        storyRepository.updatePlusSp();


        ArrayList<String> totalspCommitment = new ArrayList<String>();
//        int spwrked[] = {10, 10, 10, 10, 10, 10, 10};
//        int morework[] =   {0, 0, 0, 3, 0, 0, 0};

        int spwrked[] = new int[spDone.size()];
        int morework[] = new int[moresp.size()];

        for (int index = 0; index < spwrked.length; index++) {

            spwrked[index] = Integer.parseInt(spDone.get(index));
        }

        System.out.println("**Done**" + Arrays.toString(spwrked));

        for (int index = 0; index < morework.length; index++) {

            morework[index] = Integer.parseInt(moresp.get(index));
        }

        System.out.println("*MORE***" + Arrays.toString(morework));

        // System.out.println(z));

        int SP = 231;
        int newtask = 0;
        totalspCommitment.add(String.valueOf(SP));
        for (int j = 0; j < spwrked.length; j++) {
            if (morework[j] == 0) {
                newtask = SP - spwrked[j];
                SP = newtask;
            } else {
                newtask = (SP + morework[j]) - spwrked[j];
                SP = newtask;
                // System.out.println(newtask);
            }
            totalspCommitment.add(String.valueOf(SP));
        }

        // System.out.println("completed"+spDone);
        // System.out.println("more"+moresp);
        System.out.println("Commitement" + totalspCommitment);
        return totalspCommitment;
        
    }

}
