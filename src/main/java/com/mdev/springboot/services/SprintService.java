package com.mdev.springboot.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SprintService {

    List<String> numberOfDaysInSprint(Date startDate, Date endDate) throws Exception;

    long calculDaysDiff(Date join_date, Date leave_date);

    List<String> getIdealLine(Date str_date, Date end_date, int workCommitment);
    
    Map<String, Integer> nbrSprintByvelocity(List<Integer> diffSprint, List<Integer> commitmentSprintTab);

}
