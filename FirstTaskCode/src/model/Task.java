package model;

import java.util.Calendar;

public class Task {
    String title, description;
    Category category;
    Calendar limitDate;

    public Task(String title, String descrip, Category cat, Calendar date){
        this.title=title;
        this.description=descrip;
        this.category=cat;
        this.limitDate=date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
