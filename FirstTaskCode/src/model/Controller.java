package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import customExceptions.HashIsEmptyException;
import customExceptions.HeapFullException;
import customExceptions.NonExistentKeyException;
import customExceptions.ObjectNotFoundException;
import customExceptions.PriorityQueueIsEmptyException;
import customExceptions.QueueIsEmptyException;
import customExceptions.StackIsEmptyException;
import util.HashNode;
import util.HashNodeStatus;
import util.HashTable;
import util.Stack;
import util.Queue;
import util.MaxPriorityQueue;

public class Controller {
    
    private HashTable<Integer, Task> hashTableTask;
    private Stack<Action> actions;
    private Queue<Task> queueTask;
    private MaxPriorityQueue<Task> priorityQueueTask;

    public Controller (){

        hashTableTask = new HashTable<Integer, Task>();
        actions = new Stack<Action>();
        queueTask = new Queue<Task>();
        priorityQueueTask = new MaxPriorityQueue<Task>(10);
    }

    public void addTask(String name, String description, String strLimitDate, int priorityLevel, int key) throws HeapFullException{

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

        Task newTask = new Task(name, description, key, limitDate, priority);

        if(priority.equals(PriorityLevel.PRIORITY)){
            priorityQueueTask.insert(newTask);
        }
        else if(priority.equals(PriorityLevel.NON_PRIORITY)){
            queueTask.enQueue(newTask);
        }
        hashTableTask.insertElement(key, newTask);
        Action lastAction = new Action(ActionType.ADD, newTask);
    }

    public void modifyTask(int key, String newName, String newDescription, String newStrLimitDate, int newPriorityLevel) throws HashIsEmptyException, NonExistentKeyException, ObjectNotFoundException, HeapFullException{

        Task task = hashTableTask.searchElement(key).getValue();
        PriorityLevel currentPriority = task.getPriorityLevel();

        String[] parts = newStrLimitDate.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        Calendar newLimitDate = new GregorianCalendar(year, month-1, day);

        PriorityLevel newPriority = PriorityLevel.NON_PRIORITY;
        if(newPriorityLevel == 1){
            newPriority = PriorityLevel.PRIORITY;
        }
        else if(newPriorityLevel == 2){
            newPriority = PriorityLevel.NON_PRIORITY;
        }

        task.setName(newName);
        task.setDescription(newDescription);
        task.setLimitDate(newLimitDate);
        task.setPriorityLevel(newPriority);

        if(!currentPriority.equals(newPriority)){
            if(currentPriority.equals(PriorityLevel.PRIORITY)){
                int index = priorityQueueTask.getHeap().getIndexForAnObject(task);
                priorityQueueTask.getHeap().remove(index);
                queueTask.enQueue(task);
            }
            else if(currentPriority.equals(PriorityLevel.NON_PRIORITY)){
                queueTask.getList().delete(task);
                priorityQueueTask.insert(task);
            }
        }
        Action lastAction = new Action(ActionType.ADD, task);
    }

    public void deleteTask(int key) throws HashIsEmptyException, NonExistentKeyException, ObjectNotFoundException{

        Task task = hashTableTask.searchElement(key).getValue();
        if(task.getPriorityLevel().equals(PriorityLevel.PRIORITY)){
            int index = priorityQueueTask.getHeap().getIndexForAnObject(task);
            priorityQueueTask.getHeap().remove(index);
        }
        else if(task.getPriorityLevel().equals(PriorityLevel.NON_PRIORITY)){
            queueTask.getList().delete(task);
        }
        hashTableTask.deleteElement(key);
    }

    public String showAllTasks(){

        return hashTableTask.print();
    }

    public String showPrioritaryTasks(){

        return priorityQueueTask.printHeap();
    }

    public String showNonPrioritaryTasks(){

        return queueTask.getList().toString();
    }

    public String showFirstPrioritaryTask() throws PriorityQueueIsEmptyException{

        return priorityQueueTask.maximum().toString();
    }

    public String showFirstNonPrioritaryTask() throws QueueIsEmptyException{

        return queueTask.front().toString();
    }

    public void managePriorityTask() throws PriorityQueueIsEmptyException, HashIsEmptyException, NonExistentKeyException{

        Task currentTask = priorityQueueTask.extractMax();
        int key = currentTask.getKey();

        HashNode hashNode = hashTableTask.searchElement(key);
        hashNode.setStatus(HashNodeStatus.DELETED);
    }

    public void manageNonPriorityTask() throws QueueIsEmptyException, HashIsEmptyException, NonExistentKeyException{
        
        Task currentTask = queueTask.front().getValue();
        int key = currentTask.getKey();

        HashNode hashNode = hashTableTask.searchElement(key);
        hashNode.setStatus(HashNodeStatus.DELETED);
        queueTask.deQueue();
    }

    public void revertLastAction() throws StackIsEmptyException{

        Action lastAction = actions.top().getValue();

    }
}