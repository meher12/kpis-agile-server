package tn.altercall.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import tn.altercall.exception.ApiResourceNotFoundException;
import tn.altercall.entities.Project;
import tn.altercall.utils.DataTaskBugChart;
import tn.altercall.utils.PairArrays;

public interface ProjectService {
    
    
    Project addProjet(Project projet) throws ApiResourceNotFoundException;

    ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone, ArrayList<String> moresp);

    ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints, ArrayList<String> tabFromdb);

    PairArrays listTaskByStatus(String projetref);

    PairArrays efficacityByStartDateTask(String referenceProject, Map<Date, Date> requestMap) throws ParseException;

    DataTaskBugChart getTasksBugs(String referenceProject, Map<Date, Date> requestMap);

}
