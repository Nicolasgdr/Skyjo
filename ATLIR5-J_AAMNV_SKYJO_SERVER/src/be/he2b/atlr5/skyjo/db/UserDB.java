package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.UserDto;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.exception.DtoException;
import be.he2b.atlr5.skyjo.dbspecification.UserSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mad8
 */
public class UserDB  implements Dao<Integer, UserDto> {

    private static final String recordName = "USER";

    /**
     *
     * @return @throws be.he2b.atlr5.skyjo.exception.DtoException
     * @throws be.he2b.atlr5.skyjo.exception.DBException
     */
    public static List<UserDto> getAllUsers() throws DtoException, DBException {
        List<UserDto> users = getCollection(new UserSpecification(0));
        return users;
    }

    /**
     *
     * @param sel
     * @return
     * @throws be.he2b.atlr5.skyjo.exception.DBException
     */
    public static List<UserDto> getCollection(UserSpecification sel) throws DBException {
        List<UserDto> al = new ArrayList<>();
        try {
            String query = "Select (\"id\"),(\"mail\") FROM \"main\".\"USERS\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getId() != 0) {
                where = where + " (\"id\") = ? ";
            }
            if (sel.getMail() != null && !sel.getMail().isEmpty()) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " (\"mail\") like ? ";
            }

            if (where.length() != 0) {
                where = " where " + where + " order by (\"mail\")";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getId() != 0) {
                    stmt.setInt(i, sel.getId());
                    i++;

                }
                if (sel.getMail() != null && !sel.getMail().isEmpty()) {
                    stmt.setString(i, sel.getMail() + "%");
                    i++;
                }
            } else {
                query = query + " Order by (\"mail\")";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new UserDto(rs.getInt("id"), rs.getString("mail")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }
 
    @Override
    public Integer insert(UserDto item) throws DBException {
        try {
            //int num = SequenceDB.getNextNum(SequenceDB.USERS);
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement("INSERT INTO main.USERS (MAIL) VALUES (?)");
            insert.setString(1, item.getMail());
            insert.executeUpdate();
            List<UserDto> users = getCollection(new UserSpecification(item.getMail()));
            if(users.size()==1)return users.get(0).getKey();
            else return 0;
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ": ajout impossible\n" + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer key) throws DBException {
               try {
            java.sql.Statement stmt = DBManager.getConnection().createStatement();
            stmt.execute("delete from \"main\".\"USERS\" where (\"id\")=" + key);
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ": suppression impossible\n" + ex.getMessage());
        }
    }

    @Override
    public void update(UserDto item) throws DBException {
           try {
            java.sql.Connection connexion = DBManager.getConnection();

            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"USERS\" set "
                    + "(\"MAIL\")=?, "
                    + "where (\"id\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, item.getMail());
            update.setInt(2, item.getKey());
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ", modification impossible:\n" + ex.getMessage());
        }
    }

    @Override
    public List<UserDto> selectAll() throws DBException {
List<UserDto> al = new ArrayList<>();
        try {
      String query = "Select (\"id\"),(\"mail\") FROM \"main\".\"USERS\"";
      java.sql.Connection connexion = DBManager.getConnection();
      java.sql.PreparedStatement stmt;
      stmt = connexion.prepareStatement(query);
      java.sql.ResultSet rs = stmt.executeQuery();
       while (rs.next()) {
                al.add(new UserDto(rs.getInt("\"ID\""),rs.getString("\"MAIL")));
            }
       } catch (SQLException | DtoException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
     return al;       
    }
        
   
    

    @Override
    public UserDto select(Integer key) throws DBException {
            List<UserDto> ah = new ArrayList<>();
        try {
             String query = "Select (?) FROM \"main\".\"USER\"";
         java.sql.Connection connexion = DBManager.getConnection();
         java.sql.PreparedStatement stmt;
         stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
              while (rs.next()) {
                ah.add(new UserDto(rs.getInt("\"ID\""),rs.getString("\"MAIL\"")));
            }
        } catch (DBException | DtoException | SQLException e) {
            
        }
      return  ah.get(0);
    }

  
}
