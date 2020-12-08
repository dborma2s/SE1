package org.hbrs.se.ws20.prototype.uebung4.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceStrategyStream<UserStory> implements PersistenceStrategy<UserStory> {

    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private List<UserStory> list = null;
    final static String LOCATION = "userstories.ser";

    @Override
    public void openConnection() throws PersistenceException, IOException {}

    @Override
    public void closeConnection() throws PersistenceException {}

    public void save(List<UserStory> list) throws ContainerException {

        ObjectOutputStream oos = null;

        try {

            oos = new ObjectOutputStream(new FileOutputStream(PersistenceStrategyStream.LOCATION));

            oos.writeObject(list);
            System.out.println(list.size() + " User Stories saved successfully!");
            oos.close();
        } catch (IOException e) {
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new ContainerException("error while loading!");
        }
    }

    public List<UserStory> load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            this.list = new ArrayList<>();
            ois = new ObjectInputStream(new FileInputStream(PersistenceStrategyStream.LOCATION));

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                this.list = (List) obj;

            }
            System.out.println(this.list.size() + " User Stories successfully loaded!");
        } catch (IOException e) {
            System.out.println("LOG: file not found!");
        } catch (ClassNotFoundException e) {
            System.out.println("LOG: list couldn't be extracted (ClassNotFound)!");
        } finally {
            if (ois != null) try {
                ois.close();
            } catch (IOException e) {}
            if (fis != null) try {
                fis.close();
            } catch (IOException e) {}
        }

        return list;
    }

    public String getName(){
        return "hdd";
    }

}
