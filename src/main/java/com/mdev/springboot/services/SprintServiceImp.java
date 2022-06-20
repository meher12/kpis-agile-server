package com.mdev.springboot.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.SprintRepository;

@Service
public class SprintServiceImp implements SprintService {

    @Autowired
    SprintRepository sprintRepository;

    // get and convert date to day "dd-MM-yyyy" in brundown chart axe x
    @Override
    public List<String> numberOfDaysInSprint(Date str_date, Date end_date) throws Exception {

        List<Date> dates = new ArrayList<Date>();
        List<String> datesS = new ArrayList<String>();

//str_date ="01-02-2022";
//end_date ="02-02-2022";

        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("dd-MM-yyyy");
//         Date startDate = (Date) formatter.parse(str_date);
//         Date endDate = (Date) formatter.parse(end_date);

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

        // bug idealline horizontal when change type int
        // int idealLine[] = new int[nbrOfDay];
        float idealLine[] = new float[nbrOfDay];

        // bug idealline horizontal when change type int
        // int SP = workCommitment;
        float SP = workCommitment;

        // bug idealline horizontal when change type int
        // int inc = SP / nbrOfDay;
        float inc = SP / nbrOfDay;

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

    // Number sprint average_velocity capacity_story_points_in_next_sprint method
    @Override
    public Map<String, Float> nbrSprintByvelocity(List<Integer> diffSprint, List<Integer> commitmentSprintTab,
            Long projectId) {

        // Nbr of sprint in project par rapport au velocity:
        int sumDiff = 0;
        int sumCommit = 0;

        int sizeTab = diffSprint.size();

        for (int number : diffSprint) {
            sumDiff += number;
        }

        // System.out.println(sumDiff);
        float avgVelocity = sumDiff / sizeTab;
        // System.out.printf("average Velocity: %d \n", avgVelocity);

        for (int number : commitmentSprintTab) {
            sumCommit += number;
        }

        float nbr_sprint = (sumCommit / avgVelocity);

        // Capacity story points in sprint par rapport au velocity:
        ArrayList<Integer> nbrDayArray = new ArrayList<Integer>();

        List<Sprint> sprints = this.sprintRepository.findByProjetId(projectId);
        for (Sprint sprint : sprints) {
            long nbrDays = calculDaysDiff(sprint.getSdateDebut(), sprint.getSdateFin());
            int nbrOfDay = (int) nbrDays;
            nbrDayArray.add(nbrOfDay);
        }

        float sumCapacity = 0;
        float sumDaysSprint = 0;
        int jourSprint = 17;

        for (int numberCapacity : commitmentSprintTab) {
            sumCapacity += numberCapacity;
        }

        for (Integer day : nbrDayArray) {
            sumDaysSprint += day;
        }

        // System.out.printf(" average
        // Velocity---------------------------------------------------:s %f d %d j %d
        // \n", sumCapacity, sumDaysSprint, jourSprint);
        float capacity_story_points_in_next_sprint = ((sumCapacity / sumDaysSprint) * jourSprint);

        // create List of variables:
//        float nbr_sprintFloat = (float) nbr_sprint;
//        float avgVelocityFloat = (float) avgVelocity;

        Map<String, Float> resultSprint = new HashMap<>();

        resultSprint.put("number_sprint", nbr_sprint);
        resultSprint.put("average_velocity", avgVelocity);
        resultSprint.put("capacity_story_points_in_next_sprint", capacity_story_points_in_next_sprint);
        return resultSprint;
    }

//  @Override
//  public List<String> getIdealLine(Date str_date, Date end_date, int workCommitment) {
    // Set values for both dates
//      String join = "01-01-2022";   
//      String leave  = "8-01-2022";   
    // Calling find() method for getting difference bwtween dates
    // long nbrDays = calculDaysDiff(str_date, end_date);

//      List<String> negative = new ArrayList<>();
//      List<String> positive = new ArrayList<>();

//      List<Integer> negative2 = new ArrayList<>();
//      List<Integer> positive2 = new ArrayList<>();

//      int nbrOfDay = (int) nbrDays;
//
//      int idealLine[] = new int[nbrOfDay];
//      int SP = workCommitment;
//
//      int inc = Math.round(SP / nbrOfDay);
//
//      for (int i = 0; i < nbrOfDay; i++) {
//          idealLine[i] = SP;
//          SP = idealLine[i] - inc;
//      }
//
//      for (int i = 0; i < idealLine.length; i++) {
//          if (idealLine[i] < 0) {
//              negative.add(String.valueOf(idealLine[i]));
//          } else {
//              positive.add(String.valueOf(idealLine[i]));
//          }
//      }

//      for (int i = 0; i < idealLine.length; i++) {
//          if (idealLine[i] < 0) {
//              negative.add(""+idealLine[i]);
//          } else {
//              positive.add(""+idealLine[i]);
//          }
//      }

    /* ******* */
    // int lastNum = 10;
//      for (int t= 0; t < positive.size(); t++) {
//      if (positive.get(positive.size() - 1) != 0) {
//          int lastNum = positive.get(positive.size() - 1);
//
//          int zero = lastNum - inc;
//          positive.add(zero);
//      }
//      }
//      System.out.println("One one one: "+positive);
//
//      for (int i = 0; i < positive.size(); i++) {
//          if (positive.get(i) < 0) {
//              negative2.add(positive.get(i));
//          } else {
//              positive2.add(positive.get(i));
//          }
//      }

    // System.out.println("Tow tow tow: "+positive2);
//       pure Java:
//       List<String> sNumbers = new ArrayList<String>();
//       for (Integer i: positive2) {
//           sNumbers.add(String.valueOf(i));
//       }

    // Java 8 - map:
    // List<String> sNumbers = positive2.stream().map((n) ->
    // n.toString()).collect(Collectors.toList());
    // System.out.println(sNumbers);
//      return positive;
//  }

}
