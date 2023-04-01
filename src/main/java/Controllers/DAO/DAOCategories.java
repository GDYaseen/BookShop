package Controllers.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Category;
import Controllers.DBConnection.Connect;

public class DAOCategories implements DAO<Category>{
    Connection con = Connect.getInstance();
    @Override
    public void addElement(Category e) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Categories VALUES (?,?)");
            stm.setInt(1, e.getId());
            stm.setString(2,e.getCatg());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Category e) {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Categories WHERE id=?");
            stm.setInt(1, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Category getElement(Category e) {
    	Category b=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Categories WHERE id=?");
            stm.setInt(1, e.getId());
            ResultSet rs= stm.executeQuery();
            b = new Category(rs.getInt(1),rs.getString(2));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Category> getElements() {
        ArrayList<Category> list= new ArrayList<Category>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Categories");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new Category(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public void modifyElement(Category e) {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Categories SET Category=? WHERE id=?)");
            stm.setString(1,e.getCatg());
            stm.setInt(2, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
}
