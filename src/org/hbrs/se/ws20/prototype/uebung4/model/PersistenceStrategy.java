package org.hbrs.se.ws20.prototype.uebung4.model;

import java.io.IOException;
import java.util.List;

/**
 * Interface for defining basic methods for a persistence mechanism
 * Each concrete algorithm (i.e. strategy) must implement this method
 * This interface corresponds to the abstract strategy w.r.t. to the
 * Strategy Design Pattern (GoF).
 *
 * @param <E>
 */
public interface PersistenceStrategy<E> {
    public void openConnection() throws PersistenceException, IOException;
    public void closeConnection() throws PersistenceException;
    public void save(List<E> story) throws PersistenceException, ContainerException;
    public List<E> load() throws PersistenceException, IOException, ClassNotFoundException;
    public String getName();
}
