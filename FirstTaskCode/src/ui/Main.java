package ui;
import java.util.Scanner;

import customExceptions.HashIsEmptyException;
import customExceptions.HeapFullException;
import customExceptions.NonExistentKeyException;
import customExceptions.ObjectNotFoundException;
import customExceptions.PriorityQueueIsEmptyException;
import customExceptions.QueueIsEmptyException;
import customExceptions.StackIsEmptyException;
import model.Controller;

public class Main {
    private Scanner sc;
    private Controller controller;
    
    public Main(){
        sc= new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args){
       
        Main main = new Main();
        int option= -1;
        do {
            option =main.showMenuAndGetOption();
            main.answerOption(option);
        }while (option !=0);
    }

    public void answerOption(int userOption) {
        switch(userOption) {
            case 0:
                System.out.println("¡Goodbye!");
                break;
            case 1:
                addTask();
                break;
            case 2:
                modifyTask();
                break;
            case 3:
                showAllTasks();
                break;
            case 4:
                managePriorityTask();
                break;
            case 5:
                manageNonPriorityTask();
                break;
            case 6:
                revertLastAction();
                break;
            case 9:
            testCases();
            break;
        }
    }

    public int showMenuAndGetOption() {
        int input;
        System.out.println("\nWelcome to the organization app!\n"+
                "(1) Add Task\n" +
                "(2) Modify Task\n" +
                "(3) Show list of all Tasks\n" +
                "(4) Manage priority task\n" +
                "(5) Manage non priority task\n" +
                "(6) Undo last action\n" +
                "(0) Exit\n"
        );
        input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public void addTask(){
        System.out.println("Please enter the information of the new task");
        System.out.println("Title of the task");
        String title= sc.nextLine();

        System.out.println("Brief description of the task");
        String description= sc.nextLine();

        System.out.println("Insert the limit date of the task (YYYY/MM/DD)");
        String date= sc.nextLine();

        System.out.println("Please indicate the type of priority of the task");
        System.out.println("(1) Priority");
        System.out.println("(2) Non-Priority");
        int state= sc.nextInt();

        System.out.println("Please enter an integer that will be the key for this task. Each task must have a different key");
        int key= sc.nextInt();

        try {
            controller.addTask(title, description, date, state, key);
        } catch (HeapFullException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifyTask(){

        System.out.println("Please enter the key of the task you'd like to modify");
        int key= sc.nextInt();
        sc.nextLine();

        System.out.println("Please enter the new information of the task");
        System.out.println("New title of the task");
        String title= sc.nextLine();

        System.out.println("New description of the task");
        String description= sc.nextLine();

        System.out.println("Insert the new limit date of the task (Strictly in format: YYYY/MM/DD)");
        String date= sc.nextLine();

        System.out.println("Please indicate the new type of task ");
        System.out.println("(1) Priority");
        System.out.println("(2) Non-Priority");
        int state= sc.nextInt();
        
        try {
            controller.modifyTask(key, title, description, date, state);
        } catch (HashIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NonExistentKeyException e) {
            System.out.println(e.getMessage());
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (HeapFullException e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The task was added successfully"); 
    }

    public void showAllTasks(){
        System.out.println(controller.showAllTasks());
    }

    public void managePriorityTask(){

        try {
            if(controller.showPrioritaryTasks().equals("") == false){
                if(controller.getHeapSize() != 0){
                    System.out.println("This is the priority task with the nearest due date");
                    System.out.println(controller.showFirstPrioritaryTask());

                    System.out.println("Would you like to mark this task as completed? \n(1) Yes \n(2) No\n");
                    int option = sc.nextInt();

                    if(option == 1){
                        
                        try {
                            controller.managePriorityTask();
                        } catch (ObjectNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (QueueIsEmptyException e) {
                            System.out.println(e.getMessage());
                        }
                        
                    }
                }
                else{
                    System.out.println("There aren't any pending prioritary tasks to manage");
                }
            }
            else{
                System.out.println("There aren't any pending prioritary tasks to manage");
            }
        } catch (PriorityQueueIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (HashIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NonExistentKeyException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The task was successfully completed");
    }

    public void manageNonPriorityTask(){
        
        try{
            if(controller.showNonPrioritaryTasks().equals("") == false){
                    System.out.println("This is the first non priority task registered");
                    System.out.println(controller.showFirstNonPrioritaryTask());

                    System.out.println("Would you like to mark this task as completed? \n(1) Yes \n(2) No\n");
                    int option = sc.nextInt();

                    if(option == 1){
                        try {
                            controller.manageNonPriorityTask();
                        } catch (HashIsEmptyException e) {
                            System.out.println(e.getMessage());
                        } catch (NonExistentKeyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                else{
                    System.out.println("There aren't any pending non prioritary tasks to manage");
                }
        }
        catch (QueueIsEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void revertLastAction(){
        try {
            controller.revertLastAction();
        } catch (StackIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (HashIsEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NonExistentKeyException e) {
            System.out.println(e.getMessage());
        } catch (ObjectNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (HeapFullException e) {
            System.out.println(e.getMessage());
        }
    }
    public void testCases(){
        try {
            controller.addTask("name", "null", "2022/11/01", 1, 12);
            controller.addTask("name2", "null", "2022/11/02", 1, 1);
            controller.addTask("name3", "null", "2022/11/03", 2, 15);
            controller.addTask("name4", "null", "2022/11/04", 2, 18);
        } catch (HeapFullException e) {
            // TODO Auto-generated catch block
            e.getMessage();
        }
    }
}