package com.mdev.springboot.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.mdev.springboot.utils.DataTaskBugChart;
import com.mdev.springboot.utils.PairArrays;

public interface ProjectService {

    ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone, ArrayList<String> moresp);

    ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints, ArrayList<String> tabFromdb);
    
    PairArrays listTaskByStatus(String projetref);
    
    PairArrays efficacityByStartDateTask(String referenceProject, Map<Date, Date> requestMap) throws ParseException;
    
    DataTaskBugChart getTasksBugs(String referenceProject, Map<Date, Date> requestMap);
    

}
