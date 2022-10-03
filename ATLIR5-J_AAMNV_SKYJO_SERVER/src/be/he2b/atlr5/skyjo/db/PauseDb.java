/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.PauseDto;
import be.he2b.atlr5.skyjo.dbdto.UserDto;
import be.he2b.atlr5.skyjo.dbspecification.PauseSpecification;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.exception.DtoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author le-ni
 */
public class PauseDb implements Dao<Integer, PauseDto> {
      private static final String recordName = "PAUSE";

  
   

    @Override
    public void delete(Integer key) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void update(PauseDto sel) throws DBException {
       try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"PAUSE\" set "
                    + "(\"pause\")=?, "
                    + "where (\"user_id\")=? AND (\"game_id\")=? ";
            update = connexion.prepareStatement(sql);
            int pause = sel.getPause();
            pause++;
            update.setInt(1,pause);
            update.setInt(2, sel.getId_user());
             update.setInt(3, sel.getId_game());
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ", modification impossible:\n" + ex.getMessage());
        }
    }

   

    public static  List<PauseDto> selectall(PauseSpecification sel) throws DBException, DtoException {
         List<PauseDto> al = new ArrayList<>();
        try {
            String query = "Select (\"id_user\"),(\"id_game\") FROM \"main\".\"PAUSE\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId_game() != 0) {
                where = where + " (\"id_game\") = ? ";
            }
            if ( sel.getId_user() !=0) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " (\"id_user\") like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by (\"mail\")";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId_game() != 0) {
                    stmt.setInt(i, sel.getId_game());
                    i++;

                }
                if (sel.getId_user()!= 0) {
                    stmt.setString(i, sel.getId_user()+ "%");
                    i++;
                }
            } else {
                query = query + " Order by (\"mail\")";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new PauseDto(rs.getInt("user_id"), rs.getInt("user_game"),rs.getInt("pause")));
            }
        } catch (DBException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    

    @Override
    public Integer insert(PauseDto item) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  

    @Override
    public PauseDto select(Integer key) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PauseDto> selectAll() throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
