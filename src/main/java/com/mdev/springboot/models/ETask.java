package com.mdev.springboot.models;

public enum ETask {
    Scheduled, In_progress, Cancelled, Failed, Completed, Succeeded;
}

/*
 * Custom Workflow 1: Scheduled – in Progress – Cancelled – Failed – Completed –
 * Succeeded
 * 
 * This custom workflow is based on the following task statuses:
 * 
 * Scheduled Task status "Scheduled" shows that the task is ready for execution
 * but is not started yet or it has Start and/or Finish date only.
 * 
 * In Progress Task status "In Progress" shows that the task is in process of
 * accomplishment.
 * 
 * Cancelled Task status "Cancelled" means that the task was cancelled to some
 * reasons and wasn’t completed.
 * 
 * Failed Task status "Failed" shows that the task wasn’t completed because of
 * some issues.
 * 
 * Completed Task status "Completed" was completed with some errors or issues.
 * 
 * Succeeded Task status "Succeeded" means that the task was successfully
 * completed and all the goals of these tasks are achieved.
 * 
 */