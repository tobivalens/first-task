package model;

import java.util.Calendar;

public class Task implements Comparable<Task>{
    
    String name;
    String description;
    Calendar limitDate;
    PriorityLevel priorityLevel;
    
    public Task(String name, String description, Calendar limitDate, PriorityLevel priorityLevel) {
        this.name = name;
        this.description = description;
        this.limitDate = limitDate;
        this.priorityLevel = priorityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Calendar limitDate) {
        this.limitDate = limitDate;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public int compareTo(Task otherTask) {
        return this.limitDate.compareTo(otherTask.limitDate);
    }
}