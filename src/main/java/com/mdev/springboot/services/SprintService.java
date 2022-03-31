package com.mdev.springboot.services;

import java.util.Date;
import java.util.List;

public interface SprintService {

    List<String> numberOfDaysInSprint(Date startDate, Date endDate) throws Exception;

    long calculDaysDiff(Date join_date, Date leave_date);

    List<String> getIdealLine(Date str_date, Date end_date, int workCommitment);
    
    List<Integer> nbrSprintByvelocity(List<Integer> diffSprint, List<Integer> commitmentSprintTab);

}
