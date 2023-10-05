package ui;
import java.util.Scanner;

import customExceptions.HashIsEmptyException;
import customExceptions.HeapFullException;
import customExceptions.NonExistentKeyException;
import customExceptions.ObjectNotFoundException;
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
        }
    }

    public int showMenuAndGetOption() {
        int input;
        System.out.println("\n\n¡Welcome to the organization app!\n"+
                "(1) Add Task\n" +
                "(2) Modify Task\n" +
                "(3) Delete Task\n" +
                "(4) Display list of Tasks\n" +
                "(5) Undo an Action\n" +
                "(0) Exit\n"
        );
        input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public void addTask(){
        System.out.println("Please enter the information of the new task");
        System.out.println("Title of the task ");
        String title= sc.nextLine();

        System.out.println("Description of the task");
        String description= sc.nextLine();

        System.out.println("Insert the limit date of the task (YYYY/MM/DD)");
        String date= sc.nextLine();

        System.out.println("Please indicate the type of task ");
        System.out.println("(1) Priority");
        System.out.println("(2) Non-Priority");
        int state= sc.nextInt();

        System.out.println("Please enter an integer that will be the key for this task");
        int key= sc.nextInt();

        try {
            controller.addTask(title, description, date, state, key);
        } catch (HeapFullException e) {
            e.printStackTrace();
        }

        System.out.println(controller.showAllTasks());
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

        System.out.println("Insert the new limit date of the task (YYYY/MM/DD)");
        String date= sc.nextLine();

        System.out.println("Please indicate the new type of task ");
        System.out.println("(1) Priority");
        System.out.println("(2) Non-Priority");
        int state= sc.nextInt();

        try {
            controller.modifyTask(key, title, description, date, state);
        } catch (HashIsEmptyException | NonExistentKeyException | ObjectNotFoundException | HeapFullException e) {
            e.printStackTrace();
        }
        System.out.println(controller.showAllTasks());
    }
}