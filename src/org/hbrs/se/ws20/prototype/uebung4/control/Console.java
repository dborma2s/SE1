package org.hbrs.se.ws20.prototype.uebung4.control;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_CYAN = "\u001B[36m";
    private Scanner sc = null;
    private static Console console = null;

    private Console(){
        sc = new Scanner(System.in);
    }

    public static synchronized Console getInstance(){
        if(console == null){
            console = new Console();
        }
        return console;
    }

    public String readLine(String console) {

        String line = null;

        while (line == null) {
            System.out.print(console);
            try {
                line = sc.nextLine();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println(TEXT_RED + "Invalid input! Enter a string!" + TEXT_CYAN);
            }
        }
        return line;
    }

    public int readLineInt(String console){
        int line = -1;

        while(line < 0){
            System.out.print(console);
            try {
                line = sc.nextInt();
                sc.nextLine();
                if(line > 0 && (console.contains("Effort") || console.contains("UserStory"))) {
                    return line;
                } else if(line < 0){
                    throw new IllegalArgumentException("Number has to be positive!");
                } else if((line > 5 || line < 1) && (console.contains("Added value") || console.contains("Penalty") || console.contains("Risk"))){
                    throw new IllegalArgumentException("Number has to be between 1-5!");
                }
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println(TEXT_RED + "Invalid input! Enter a number!" + TEXT_CYAN);
            } catch (IllegalArgumentException e) {
                System.out.println(TEXT_RED + e.getMessage() + TEXT_CYAN);
                line = -1;
            }
        }
        return line;
    }
}
