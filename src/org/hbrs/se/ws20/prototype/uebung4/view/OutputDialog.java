package org.hbrs.se.ws20.prototype.uebung4.view;

import org.hbrs.se.ws20.prototype.uebung4.model.UserStory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OutputDialog {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_WHITE = "\u001B[37m";

    private List<UserStory> list = null;

    public OutputDialog(List<UserStory> list){
        this.list = list;
    }

    public void dump(){

        if(list.isEmpty()){
            System.out.println(TEXT_RED + "No UserStories available!" + TEXT_CYAN);
            return;
        }

        System.out.println(TEXT_RED + "UserStories overview (sorted by highest Prio): \n" + TEXT_CYAN);
        Collections.sort( this.list);
        Collections.reverse(this.list);

        System.out.printf(TEXT_RED + "%-20S %-40S %-20S %-20S %-20S %-20S %-20S " + TEXT_CYAN,"ID","Title", "Added value", "Penalty", "Effort", "Risk", "Prio");

        for (UserStory userStory : list) {
            System.out.println(TEXT_WHITE + "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------" + TEXT_CYAN);
            System.out.printf("%-20d %-40s %-20d %-20d %-20d %-20d %-20.2f", userStory.getId(), userStory.getTitel(), userStory.getMehrwert(), userStory.getStrafe(), userStory.getAufwand(), userStory.getRisk(), userStory.getPrio());
        }
        System.out.println(TEXT_WHITE + "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + TEXT_CYAN);
    }

    public void dumpEffort(int effort){


        System.out.println(TEXT_RED + "UserStories overview (Effort above " + effort + "): \n" + TEXT_CYAN);
        System.out.printf(TEXT_RED + "%-20S %-40S %-20S %-20S %-20S %-20S %-20S " + TEXT_CYAN,"ID","Title", "Added value", "Penalty", "Effort", "Risk", "Prio");

        list.stream().filter( userStory -> userStory.getAufwand() > effort  )
                .sorted(Comparator.comparingDouble(UserStory::getAufwand).reversed())
                .forEach( userStory ->  System.out.printf(TEXT_WHITE + "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------" + TEXT_CYAN +
                        "\n%-20d %-40s %-20d %-20d %-20d %-20d %-20.2f\n", userStory.getId(), userStory.getTitel(), userStory.getMehrwert(), userStory.getStrafe(), userStory.getAufwand(), userStory.getRisk(), userStory.getPrio()));
        System.out.println(TEXT_WHITE + "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------\n" + TEXT_CYAN);
    }
}
