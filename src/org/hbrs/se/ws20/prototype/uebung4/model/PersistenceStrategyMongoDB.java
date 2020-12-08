package org.hbrs.se.ws20.prototype.uebung4.model;

import java.util.List;

public class PersistenceStrategyMongoDB<UserStory> implements PersistenceStrategy<UserStory> {
    @Override
    public void openConnection() throws PersistenceException {
        try{
            throw new UnsupportedOperationException("Not implemented!");
        } catch (UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented!");
        }

    }

    @Override
    public void closeConnection() throws PersistenceException {
        try{
            throw new UnsupportedOperationException("Not implemented!");
        } catch (UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented!");
        }
    }

    @Override
    public void save(List<UserStory> story) throws PersistenceException {
        try{
            throw new UnsupportedOperationException("Not implemented!");
        } catch (UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented!");
        }

    }

    @Override
    public List<UserStory> load() throws PersistenceException {
        try{
            throw new UnsupportedOperationException("Not implemented!");
        } catch (UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented!");
        }
    }

    public String getName(){
        return "MongoDB";
    }
}
