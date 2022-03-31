package com.mdev.springboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SprintServiceImp implements SprintService {

    @Override
    public List<String> numberOfDaysInSprint(Date str_date, Date end_date) throws Exception {

        List<Date> dates = new ArrayList<Date>();
        List<String> datesS = new ArrayList<String>();

//str_date ="01-02-2022";
//end_date ="02-02-2022";

        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("dd-MM-yyyy");
        // Date startDate = (Date) formatter.parse(str_date);
        // Date endDate = (Date) formatter.parse(end_date);

        Date startDate = str_date;
        Date endDate = end_date;

        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        for (int i = 0; i < dates.size(); i++) {
            Date lDate = (Date) dates.get(i);
            String ds = formatter.format(lDate);
            datesS.add(ds);
        }

        return datesS;
    }

    @Override
    public long calculDaysDiff(Date join_date, Date leave_date) {
        // Create an instance of the SimpleDateFormat class
        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
        long days_difference = 1L;
        // In the try block, we will try to find the difference
        // Use parse method to get date object of both dates
//            Date date1 = obj.parse(join_date);   
//            Date date2 = obj.parse(leave_date);   

        Date date1 = join_date;
        Date date2 = leave_date;
        // Calucalte time difference in milliseconds
        long time_difference = date2.getTime() - date1.getTime();
        // Calucalte time difference in days
        days_difference = (time_difference / (1000 * 60 * 60 * 24)) % 365;

        return days_difference;

    }

    @Override
    public List<String> getIdealLine(Date str_date, Date end_date, int workCommitment) {
        // Set values for both dates
//        String join = "01-01-2022";   
//        String leave  = "8-01-2022";   
        // Calling find() method for getting difference bwtween dates
        long nbrDays = calculDaysDiff(str_date, end_date);

        List<String> negative = new ArrayList<>();
        List<String> positive = new ArrayList<>();

        int nbrOfDay = (int) nbrDays;

        int idealLine[] = new int[nbrOfDay];
        int SP = workCommitment;

        int inc = Math.round(SP / nbrOfDay);

        for (int i = 0; i < nbrOfDay; i++) {
            idealLine[i] = SP;
            SP = idealLine[i] - inc;
        }

        for (int i = 0; i < idealLine.length; i++) {
            if (idealLine[i] < 0) {
                negative.add(String.valueOf(idealLine[i]));
            } else {
                positive.add(String.valueOf(idealLine[i]));
            }
        }
        return positive;
    }

    // Nember sprint by velocity
    @Override
    public  List<Integer> nbrSprintByvelocity(List<Integer> diffSprint, List<Integer> commitmentSprintTab) {

        int sumDiff = 0;
        int sumCommit = 0;

        int sizeTab = diffSprint.size();

        for (int number : diffSprint) {
            sumDiff += number;
        }

        System.out.println(sumDiff);
        int avgVelocity = sumDiff / sizeTab;
        System.out.printf("average Velocity: %d  \n", avgVelocity);

        for (int number : commitmentSprintTab) {
            sumCommit += number;
        }

        int nbr_sprint = Math.round(sumCommit / avgVelocity);
        System.out.printf("Nomber de sprint est : %d  ", nbr_sprint);
        
        List<Integer> resultSprint = new ArrayList<Integer>();
        resultSprint.add(nbr_sprint);
        resultSprint.add(avgVelocity);
        return resultSprint;
    }

}
