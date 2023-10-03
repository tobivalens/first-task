package model;

import java.util.Calendar;

public class Task {
    
    String name;
    String description;
    Category category;
    Calendar limitDate;
    
    public Task(String name, String description, Category category, Calendar limitDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.limitDate = limitDate;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Calendar getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Calendar limitDate) {
        this.limitDate = limitDate;
    }
}