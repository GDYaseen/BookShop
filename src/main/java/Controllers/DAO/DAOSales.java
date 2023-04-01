package Controllers.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Sale;
import Controllers.DBConnection.Connect;

public class DAOSales implements DAO<Sale>{
    Connection con = Connect.getInstance();
    @Override
    public void addElement(Sale e) {
        try {
            PreparedStatement stm = con.prepareStatement("INSERT INTO ventes VALUES (?,?,?)");
            stm.setInt(1, e.getId_client());
            stm.setInt(2,e.getId_livre());
            stm.setString(3,e.getDate_vente());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteElement(Sale e) {
        try {
            PreparedStatement stm = con.prepareStatement("DELETE FROM ventes WHERE id_client=? AND id_Livre=? AND date_vente=?");
            stm.setInt(1, e.getId_client());
            stm.setInt(2,e.getId_livre());
            stm.setString(3, e.getDate_vente());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public Sale getElement(Sale e) {
    	Sale s=null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM ventes WHERE id_client=? OR id_Livre=?");
            stm.setInt(1, e.getId_client());
            stm.setInt(2,e.getId_livre());
            ResultSet rs= stm.executeQuery();
            s = new Sale(rs.getInt(1),rs.getInt(2),rs.getString(3));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Sale> getElements() {
        ArrayList<Sale> list= new ArrayList<Sale>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM ventes");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
                list.add(new Sale(rs.getInt(1),rs.getInt(2),rs.getString(3)));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
    
    @Override
    public void modifyElement(Sale e) {
        try {
            PreparedStatement stm = con.prepareStatement("UPDATE ventes SET Id_client=?, Id_livre=?, Date_vente=? WHERE id_client=? AND id_livre=?)");
            stm.setInt(1,e.getId_client());
            stm.setInt(2,e.getId_livre());
            stm.setString(3,e.getDate_vente());
            stm.setInt(4,e.getId_client());
            stm.setInt(5,e.getId_livre());
            stm.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public List<Sale> getDetailedSales(String req){
    	ArrayList<Sale> list= new ArrayList<Sale>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT id,id_client,titre,auteur,categories,prix,count(*),(count(*)*prix),date_vente from books,ventes where id=id_livre AND "+req+" group by id,date_vente;");
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
            	Sale s = new Sale(rs.getInt(1), rs.getInt(2)
            			, rs.getInt(7)
            			, rs.getString(3)
            			, rs.getString(4)
            			, rs.getString(5)
            			, rs.getFloat(6)
            			,rs.getFloat(8)
            			,rs.getString(9));
                list.add(s);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
    public List<Sale> getDetailedSales(int client_id){
    	ArrayList<Sale> list= new ArrayList<Sale>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT id,id_client,titre,auteur,categories,prix,count(*),(count(*)*prix),date_vente from books,ventes where id=id_livre AND id_client=? group by id,date_vente;");
            stm.setInt(1, client_id);
            ResultSet rs= stm.executeQuery();
            while(rs.next()){
            	Sale s = new Sale(rs.getInt(1), rs.getInt(2)
            			, rs.getInt(7)
            			, rs.getString(3)
            			, rs.getString(4)
            			, rs.getString(5)
            			, rs.getFloat(6)
            			,rs.getFloat(8)
            			,rs.getString(9));
                list.add(s);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return list;
    }
}
