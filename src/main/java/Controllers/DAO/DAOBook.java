package Controllers.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Book;
import Controllers.DBConnection.Connect;

public class DAOBook implements DAO<Book>{
    Connection con = Connect.getInstance();
    @Override
    public void addElement(Book e) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO books VALUES (?,?,?,?,?,?,?)");
            stm.setInt(1, e.getId());
            stm.setString(2,e.getTitre());
            stm.setString(3,e.getAuteur());
            stm.setString(4,e.getCategories());
            stm.setBytes(5, e.getCover());
            stm.setFloat(6,e.getPrix());
            stm.setInt(7, e.getStock());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Book e) {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM books WHERE id=?");
            stm.setInt(1, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Book getElement(Book e) {
        Book b=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM books WHERE id=?");
            stm.setInt(1, e.getId());
            ResultSet rs= stm.executeQuery();
            if(rs.next()!=false) {            	
            	b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(6),rs.getBytes(5),rs.getInt(7));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Book> getElements() {
        ArrayList<Book> list= new ArrayList<Book>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM books");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(6),rs.getBytes(5),rs.getInt(7)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
    public List<Book> getElements(String req) {
        ArrayList<Book> list= new ArrayList<Book>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM books WHERE "+req);
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getFloat(6),rs.getBytes(5),rs.getInt(7)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void modifyElement(Book e) {
        try {
        	PreparedStatement stm=null;
        	if(e.getCover()==null) {        		
        		stm = con.prepareStatement("UPDATE books SET Titre=?, Auteur=?, Categories=?, Prix=?,Stock=? WHERE id=?");
                stm.setInt(6, e.getId());        		
        	}else {
        		stm = con.prepareStatement("UPDATE books SET Titre=?, Auteur=?, Categories=?, Prix=?,Stock=?, Cover=? WHERE id=?");
                stm.setBytes(6, e.getCover());
                stm.setInt(7, e.getId());
        	}
            stm.setString(1,e.getTitre());
            stm.setString(2,e.getAuteur());
            stm.setString(3,e.getCategories());
            stm.setFloat(4, e.getPrix());
            stm.setInt(5, e.getStock());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
}
