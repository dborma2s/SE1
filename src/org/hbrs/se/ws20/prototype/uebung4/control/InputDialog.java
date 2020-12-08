package org.hbrs.se.ws20.prototype.uebung4.control;

import org.hbrs.se.ws20.prototype.uebung4.model.*;
import org.hbrs.se.ws20.prototype.uebung4.view.OutputDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputDialog {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";

    private Container container = null;

    public InputDialog() {
        this.container = Container.getInstance();
    }

    public void startEingabe() throws ContainerException, PersistenceException, IOException, ClassNotFoundException {
        Console console = Console.getInstance();
        String strInput = null;

        Scanner scanner = new Scanner( System.in );

        System.out.println(TEXT_RED + "Prio-Tool V1.5" + TEXT_CYAN);
        while ( true ) {
            System.out.print( TEXT_RED + "> " + TEXT_CYAN);
            strInput = scanner.nextLine();
            String[] strings = strInput.split(" ");
            switch(strings[0]) {

                case "enter":
                    int id = console.readLineInt("UserStory ID: ");
                    String title = console.readLine("Title: ");
                    int addedValue = console.readLineInt("Added value (1-5): ");
                    int penalty = console.readLineInt("Penalty (1-5): ");
                    int effort = console.readLineInt("Effort (should not be higher than 100): ");
                    int risk = console.readLineInt("Risk (1-5): ");
                    double prio = ((double) (addedValue + penalty)/ (double)(effort + risk));
                    System.out.println("UserStory Prio: " + prio);

                    try {
                        Container.getInstance().addUserStory(new UserStory(id, title, addedValue, penalty, effort, risk, prio));
                    } catch (ContainerException e) {
                        System.out.print(TEXT_RED);
                        e.printStackTrace();
                        break;
                    }
                    System.out.println(TEXT_RED + "UserStory added" + TEXT_CYAN);
                    break;

                case "store":
                    if(container.getStrategy() == null) {
                        System.out.println(TEXT_CYAN + "Where would you like to save your UserStories?");
                        String storage = console.readLine("Enter " + TEXT_PURPLE + "hdd " + TEXT_CYAN + "or " + TEXT_PURPLE +  "mongodb" + TEXT_CYAN  +": ");
                        if (storage.contains("hdd")) {
                            container.setStrategy(new PersistenceStrategyStream());
                        } else if(storage.contains("mongodb")) {
                            container.setStrategy(new PersistenceStrategyMongoDB());
                        } else {
                            System.out.println(TEXT_RED + "Wrong input! Nothing set. Try again..." + TEXT_CYAN);
                        }
                    } else if(container.getStrategy() != null) {
                        System.out.println("Storage location is already set to " + container.getStrategy().getName() +  ".");
                        if(console.readLine("Would you like to change it? Type (y) or press any key ").contains("y")) {
                            String storage = console.readLine("Enter " + TEXT_PURPLE + "hdd " + TEXT_CYAN + "or " + TEXT_PURPLE + "mongodb" + TEXT_CYAN + ": ");
                            if (storage.contains("hdd")) {
                                container.setStrategy(new PersistenceStrategyStream());
                            } else if (storage.contains("mongodb")) {
                                container.setStrategy(new PersistenceStrategyMongoDB());
                            } else {
                                System.out.println(TEXT_RED + "Wrong input! Nothing changed." + TEXT_CYAN);
                            }
                        }
                    }
                    container.store();
                    break;

                case "load":
                    if(container.getStrategy() == null) {
                        System.out.println(TEXT_CYAN + "From where would you like to load your UserStories?");
                        String storage = console.readLine("Enter " + TEXT_PURPLE + "hdd " + TEXT_CYAN + "or " + TEXT_PURPLE +  "mongodb" + TEXT_CYAN  +": ");
                        if (storage.contains("hdd")) {
                            container.setStrategy(new PersistenceStrategyStream());
                        } else if(storage.contains("mongodb")) {
                            container.setStrategy(new PersistenceStrategyMongoDB());
                        } else {
                            System.out.println(TEXT_RED + "Wrong input! Nothing set. Try again..." + TEXT_CYAN);
                        }
                    } else if(container.getStrategy() != null) {
                        System.out.println("Load location is already set to " + container.getStrategy().getName() + ".");
                        if(console.readLine("Would you like to change it? Type (y) or press any key ").contains("y")) {
                            String storage = console.readLine("Enter " + TEXT_PURPLE + "hdd " + TEXT_CYAN + "or " + TEXT_PURPLE + "mongodb" + TEXT_CYAN + ": ");
                            if (storage.contains("hdd")) {
                                container.setStrategy(new PersistenceStrategyStream());
                            } else if (storage.contains("mongodb")) {
                                container.setStrategy(new PersistenceStrategyMongoDB());
                            } else {
                                System.out.println(TEXT_RED + "Wrong input! Nothing changed." + TEXT_CYAN);
                            }
                        }
                    }
                    System.out.println("Do you want to " + TEXT_PURPLE + "merge " + TEXT_CYAN + "or " + TEXT_PURPLE + "force " + TEXT_CYAN + "the UserStories?");
                    String output = console.readLine("Enter " + TEXT_PURPLE + "merge " + TEXT_CYAN + "or " + TEXT_PURPLE + "force" + TEXT_CYAN + ": ");
                    if(output.contains("force")) {
                        container.load();
                    } else if(output.contains("merge")) {

                        List<UserStory> loadedList = container.mergeload();

                        for(UserStory userStory : loadedList) {
                            try {
                                container.addUserStory(userStory);
                            } catch (ContainerException e) {
                                System.out.println(TEXT_RED + "ATTENTION! MERGE CONFLICT!" + TEXT_CYAN);

                                System.out.println(TEXT_RED + "\nUserStory tmp: " + TEXT_CYAN);
                                new OutputDialog(Arrays.asList(container.getUserStory(userStory.getId()))).dump();


                                System.out.println(TEXT_RED + "\nUserStory hdd: " + TEXT_CYAN);
                                new OutputDialog(Arrays.asList(userStory)).dump();

                                String mergeconflict = console.readLine("Would you like to add the hdd UserStory or keep the temporary UserStory? Enter (hdd/tmp): ");
                                if(mergeconflict.contains("hdd")) {
                                    container.deleteUserStory(userStory.getId());
                                    container.addUserStory(userStory);
                                } else if(mergeconflict.contains("tmp")) {
                                    continue;
                                }
                            }

                        }
                    } else {
                        System.out.println(TEXT_RED + "Wrong input! Nothing changed." + TEXT_CYAN);
                    }
                    break;

                case "dump":
                    OutputDialog outputDialog = new OutputDialog(container.getCurrentList());
                    String dump = console.readLine("Choose between (default/effort): ");
                    if(dump.contains("default")){
                        outputDialog.dump();
                        break;
                    } else if(dump.contains("effort")) {
                        outputDialog.dumpEffort(console.readLineInt("Choose a positive number you want to filter: "));
                    }
                    break;

                case "help":
                    System.out.println(TEXT_RED + "\nFollowing commands are available:" +
                            TEXT_CYAN + "\nenter" + TEXT_GREEN + " - insert a UserStory " +
                            TEXT_CYAN + "\nstore" + TEXT_GREEN +  " - store all temporary UserStories to hdd" +
                            TEXT_CYAN + "\nload" + TEXT_GREEN + " - load UserStories from hdd" +
                            TEXT_CYAN + "\n\tmerge " + TEXT_GREEN + " - merge temporary UserStories together with the hdd/mongodb" +
                            TEXT_CYAN + "\n\tforce " + TEXT_GREEN + " - override all temporary UserStories" +
                            TEXT_CYAN + "\ndump" + TEXT_GREEN + " - display all available UserStories " +
                            TEXT_CYAN + "\nexit" + TEXT_GREEN + " - will end the program\n");
                    break;

                case "exit":
                    System.exit(0);
            }
        }
    }
}
