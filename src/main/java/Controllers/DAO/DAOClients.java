package Controllers.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Client;
import Controllers.DBConnection.Connect;

public class DAOClients implements DAO<Client>{
    Connection con = Connect.getInstance();
    @Override
    public void addElement(Client e) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Clients (`nom complet`,`email`) VALUES (?,?)");
            stm.setString(1, e.getNom());
            stm.setString(2,e.getEmail());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Client e) {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM Clients WHERE id=?");
            stm.setInt(1, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Client getElement(Client e) {
    	Client b=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Clients WHERE id=?");
            stm.setInt(1, e.getId());
            ResultSet rs= stm.executeQuery();
            b = new Client(rs.getInt(1),rs.getString(2),rs.getString(3));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Client> getElements() {
        ArrayList<Client> list= new ArrayList<Client>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM Clients");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new Client(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }

    @Override
    public void modifyElement(Client e) {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE Clients SET `nom complet`=?,`email`=? WHERE id=?");
            stm.setString(1, e.getNom());
            stm.setString(2,e.getEmail());
            stm.setInt(3, e.getId());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
}
