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
import util.MaxHeap;
import util.Stack;
import util.Queue;

public class Controller {
    
    private HashTable<Integer, Task> hashTableTask;
    private Stack<Action> actions;
    private Queue<Task> queueTask;
    private MaxHeap<Task> heapTask;

    public Controller (){

        hashTableTask = new HashTable<Integer, Task>();
        actions = new Stack<Action>();
        queueTask = new Queue<Task>();
        heapTask = new MaxHeap<>(200);
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
            heapTask.insert(newTask);
        }
        else if(priority.equals(PriorityLevel.NON_PRIORITY)){
            queueTask.enQueue(newTask);
        }
        hashTableTask.insertElement(key, newTask);
        Action lastAction = new Action(ActionType.ADD, newTask);
        actions.push(lastAction);
    }

    public void modifyTask(int key, String newName, String newDescription, String newStrLimitDate, int newPriorityLevel) throws HashIsEmptyException, NonExistentKeyException, ObjectNotFoundException, HeapFullException, CloneNotSupportedException{

        Task task = hashTableTask.searchElement(key).getValue();
        Task originalTask = null;

        originalTask = (Task)task.clone();

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

        if(currentPriority.equals(newPriority) == false){
            if(currentPriority.equals(PriorityLevel.PRIORITY)){
                int index = heapTask.getIndexForAnObject(task);
                heapTask.remove(index);
                queueTask.enQueue(task);
            }
            else if(currentPriority.equals(PriorityLevel.NON_PRIORITY)){
                queueTask.getList().delete(task);
                heapTask.insert(task);
            }
        }
        Action lastAction = new Action(ActionType.MODIFY, task, originalTask);
        actions.push(lastAction);
    }

    public String showAllTasks(){

        return hashTableTask.print();
    }

    public String showPrioritaryTasks(){

        return heapTask.printHeap();
    }

    public String showNonPrioritaryTasks(){

        return queueTask.getList().toString();
    }

    public String showFirstPrioritaryTask() throws PriorityQueueIsEmptyException{

        return heapTask.getMax().toString();
    }

    public String showFirstNonPrioritaryTask() throws QueueIsEmptyException{

        return queueTask.front().toString();
    }

    public void managePriorityTask() throws PriorityQueueIsEmptyException, HashIsEmptyException, NonExistentKeyException, ObjectNotFoundException, QueueIsEmptyException{

        Task currentTask = heapTask.extractMax();
        int key = currentTask.getKey();
        
        HashNode hashNode = hashTableTask.searchElement(key);
        hashNode.setStatus(HashNodeStatus.DELETED);

        int index = heapTask.getIndexForAnObject(currentTask);

        if(index != -1){
            heapTask.remove(index);
            actions.push(new Action(ActionType.COMPLETE, currentTask));
        }
    }

    public void manageNonPriorityTask() throws QueueIsEmptyException, HashIsEmptyException, NonExistentKeyException{
        
        Task currentTask = queueTask.front().getValue();
        int key = currentTask.getKey();

        HashNode hashNode = hashTableTask.searchElement(key);
        hashNode.setStatus(HashNodeStatus.DELETED);
        queueTask.deQueue();
        actions.push(new Action(ActionType.COMPLETE, currentTask));
    }

    public void revertLastAction() throws StackIsEmptyException, HashIsEmptyException, NonExistentKeyException, ObjectNotFoundException, HeapFullException{

        Action lastAction = actions.top().getValue();
        Task task = lastAction.getTask();
        if(lastAction.getActionType().equals(ActionType.ADD)){
            if(task.getPriorityLevel().equals(PriorityLevel.PRIORITY)){
                int index = heapTask.getIndexForAnObject(task);
                if(index != -1){
                    heapTask.remove(index); 
                }             
            }
            else if(task.getPriorityLevel().equals(PriorityLevel.NON_PRIORITY)){
                queueTask.getList().delete(task);
            }
            hashTableTask.deleteElement(task.getKey());
        }    
        else if(lastAction.getActionType().equals(ActionType.MODIFY)){
            Task originalTask = lastAction.getOriginalTask();
            task.setName(originalTask.getName());
            task.setDescription(originalTask.getDescription());
            task.setLimitDate(originalTask.getLimitDate());
            task.setPriorityLevel(originalTask.getPriorityLevel());

        }
        else{
            if(task.getPriorityLevel().equals(PriorityLevel.PRIORITY)){
                heapTask.insert(task);
            }
            else if(task.getPriorityLevel().equals(PriorityLevel.NON_PRIORITY)){
                queueTask.enQueue(task);
            }
            hashTableTask.restoreElement(task.getKey(), task);
        }
    }
}