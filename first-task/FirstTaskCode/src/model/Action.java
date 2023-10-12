package model;

public class Action{
    
    private ActionType actionType;
    private Task task;
    private Task originalTask;

    public Action(ActionType actionType, Task task) {
        this.actionType = actionType;
        this.task = task;
    }

    public Action(ActionType actionType, Task task, Task originalTask) {
        this.actionType = actionType;
        this.task = task;
        this.originalTask = originalTask;
    }

    public ActionType getActionType() {
        return actionType;
    }
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }

    public Task getOriginalTask() {
        return originalTask;
    }

    public void setOriginalTask(Task originalTask) {
        this.originalTask = originalTask;
    }
}