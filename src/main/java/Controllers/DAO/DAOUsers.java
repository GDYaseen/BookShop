package Controllers.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.User;
import Controllers.DBConnection.Connect;

public class DAOUsers implements DAO<User>{
    Connection con = Connect.getInstance();
    @Override
    public void addElement(User e) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Users (username,password,isAdmin) VALUES (?,?,?)");
            stm.setString(1, e.getUsername());
            stm.setString(2,e.getPassword());
            stm.setBoolean(3, e.isAdmin());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteElement(User e) {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Users WHERE id=?");
            stm.setInt(1, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public User getElement(User e) {
    	User b=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Users WHERE id=?");
            stm.setInt(1, e.getId());
            ResultSet rs= stm.executeQuery();
            b = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return b;
    }

    @Override
    public List<User> getElements() {
        ArrayList<User> list= new ArrayList<User>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Users");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public void modifyElement(User e) {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Users SET username=?,password=?,isAdmin=? WHERE id=?");
            stm.setString(1,e.getUsername());
            stm.setString(2,e.getPassword());
            stm.setBoolean(3,e.isAdmin());
            stm.setInt(4, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public User matchUser(String username,String password) {
    	User b=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs= stm.executeQuery();
            while(rs.next()) {
            	b = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return b;
    }
}
