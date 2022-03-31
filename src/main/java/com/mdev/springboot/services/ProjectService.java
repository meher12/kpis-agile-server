package com.mdev.springboot.services;

import java.util.ArrayList;

public interface ProjectService {

    ArrayList<String> releaseBurndownChart(int sumStorypoints, ArrayList<String> spDone, ArrayList<String> moresp);

    ArrayList<String> pourcentageStoryPointsCompleted(int sumStorypoints);

}
