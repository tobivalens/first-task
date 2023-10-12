package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task implements Comparable<Task>, Cloneable{
    
    private String name;
    private String description;
    private int key;
    private Calendar limitDate;
    private PriorityLevel priorityLevel;
    
    public Task(String name, String description, int key, Calendar lim, PriorityLevel x) {
        this.name = name;
        this.description = description;
        this.key = key;
        this.limitDate = lim;
        this.priorityLevel = x;
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

    public String getLimitDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String stringDate = sdf.format(limitDate.getTime());
        return stringDate;
    }

    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    @Override
    public int compareTo(Task otherTask) {
        return otherTask.limitDate.compareTo(this.limitDate);
    }       

    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Limit Date: " + getLimitDateString() + ", Priority Level: "
                + priorityLevel + "\n";
    }

    public int getKey(){
        return key;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}