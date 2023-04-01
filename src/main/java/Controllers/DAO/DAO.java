package Controllers.DAO;

import java.util.List;

public interface DAO<T>{
    public void addElement(T e);
    public void modifyElement(T e);
    public void deleteElement(T e);
    public List<T> getElements();
    public T getElement(T e);

}