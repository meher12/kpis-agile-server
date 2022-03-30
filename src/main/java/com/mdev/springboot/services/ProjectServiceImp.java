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
                newtask = SP - spwrked[j];
                SP = newtask;
            } else {
                newtask = (SP + morework[j]) - spwrked[j];
                SP = newtask;
            }
            totalspCommitment.add(String.valueOf(SP));
        }

        System.out.println("Commitement" + totalspCommitment);
        return totalspCommitment;
        
    }

}
