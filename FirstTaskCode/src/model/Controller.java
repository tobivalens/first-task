package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import util.HashTable;
import util.Stack;
import util.Queue;
import util.MaxPriorityQueue;

public class Controller {
    
    private HashTable<Integer, Task> hashTableTask;
    private Stack<Task> actions;
    private Queue<Task> queueTask;
    private MaxPriorityQueue<Task> priorityQueueTask;

    public Controller (){

        hashTableTask = new HashTable<Integer, Task>();
        actions = new Stack<Task>();
        queueTask = new Queue<Task>();
        priorityQueueTask = new MaxPriorityQueue<Task>(10);
    }

    public void addTask(String name, String description, String strLimitDate, int priorityLevel, int key){

        String[] parts = strLimitDate.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        Calendar limitDate = new GregorianCalendar(year, month-1, day);

        PriorityLevel priority = PriorityLevel.NON_PRIORITY;
        if(priorityLevel == 1){
            priority = PriorityLevel.PRIORITY;
        }
        else if(priorityLevel == 2){
            priority = PriorityLevel.NON_PRIORITY;
        }

        Task newTask = new Task(name, description, limitDate, priority);
        hashTableTask.insertElement(key, newTask);

    
        if(priority.equals(PriorityLevel.PRIORITY)){
            priorityQueueTask.insert(newTask);
        }
        else if(priority.equals(PriorityLevel.NON_PRIORITY)){
            queueTask.enQueue(newTask);
        }
    }
}