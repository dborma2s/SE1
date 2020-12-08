package org.hbrs.se.ws20.prototype.uebung4.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Container {

	private List<UserStory> list = null;
	private static Container instance = null;
	private PersistenceStrategy strategy = null;

	private Container(){
		list = new ArrayList<>();
	}

	public synchronized static Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}

	public void setStrategy(PersistenceStrategy strategy) {
		this.strategy = strategy;
	}

	public PersistenceStrategy getStrategy() {
		return strategy;
	}

	public void load() throws IOException, PersistenceException, ClassNotFoundException {
		try{
			this.list = strategy.load();
		} catch (NullPointerException e) {
			System.out.println("Strategy not set!");
		}
	}

	public List<UserStory> mergeload() throws IOException, PersistenceException, ClassNotFoundException {
		try{
			return strategy.load();
		} catch (NullPointerException e) {
			System.out.println("Strategy not set!");
		}

		return null;
	}

	public void store() throws ContainerException, PersistenceException {
		try{
			this.strategy.save(this.list);
		} catch (NullPointerException e) {
			System.out.println("Strategy not set!");
		}

	}

	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r)) {
			throw new ContainerException("ID already in use!");
		}
		list.add(r);
	}

	private boolean contains(UserStory r) {
		int ID = r.getId();
		for ( UserStory rec : list) {
			if ( rec.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	public int size(){
		return list.size();
	}

	public List<UserStory> getCurrentList() {
		return this.list;
	}

	public UserStory getUserStory(int id) {
		for ( UserStory rec : list) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}

	public void deleteUserStory(int id){
		this.list.remove(getUserStory(id));
	}



}
