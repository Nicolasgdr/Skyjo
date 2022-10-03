package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.DataUserDto;
import be.he2b.atlr5.skyjo.dbspecification.DataUserSpecification;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.exception.DtoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data user db.
 *
 * @author mad8
 */
public class DataUserDB implements Dao<Integer, DataUserDto> {

    private static final String recordName = "DATA_USER";

    /**
     * Gets all users.
     *
     * @return @throws be.he2b.atlr5.skyjo.exception.DtoException
     * @throws DBException  the db exception
     */
    public static List<DataUserDto> getAllUsers() throws DtoException, DBException {
        List<DataUserDto> users = getCollection(new DataUserSpecification(0));
        return users;
    }


    /**
     * Gets collection.
     *
     * @param sel the sel
     * @return collection
     * @throws DBException the db exception
     */
    public static List<DataUserDto> getCollection(DataUserSpecification sel) throws DBException {
        List<DataUserDto> al = new ArrayList();
        try {
            String query = "Select * FROM \"main\".\"DATA_USER\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = " ";
            if (sel.getIdUser()!= 0) {
                where = where + " id_User = (?) ";
            }
            if (where.length() != 0) {
                where = " where " + where + " order by id_User";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdUser() != 0) {
                    stmt.setString(i, Integer.toString(sel.getIdUser()));
                    i++;
                }
            } else {
                query = query + " Order by id_User";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataUserDto(rs.getInt("id_User"), rs.getInt("nbr_games"), rs.getInt("lowest_score"), rs.getInt("nbr_wins")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de " + recordName + " impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     * Update win player.
     *
     * @param item
     * @throws DBException the db exception
     */
    public static void updateWinPlayer(DataUserDto item) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"DATA_USER\" set(\"nbr_Wins\")=(\"nbr_Wins\")+1 where (\"id_USER\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(item.getIdUser()));
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ", modification impossible:\n" + ex.getMessage());
        }
    }

    /**
     * Update played games.
     *
     * @throws be.he2b.atlr5.skyjo.exception.DBException
     */
   

    @Override
    public Integer insert(DataUserDto item) throws DBException {
           try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement( "INSERT INTO main.DATA_USER VALUES (?,?,?,?);");
            insert.setString(1, Integer.toString(item.getIdUser()));
            insert.setString(2, Integer.toString(item.getNbrWins()));
            insert.setString(3, Integer.toString(item.getNbrGames()));
            insert.setString(4, Integer.toString(item.getLowestScore()));
            insert.executeUpdate();
            return item.getIdUser();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName+": ajout impossible\n" + ex.getMessage());
        }
    }

    @Override
    public void delete(Integer key) throws DBException {
           try {
            java.sql.PreparedStatement stmt = (java.sql.PreparedStatement) DBManager.getConnection().createStatement();
            stmt.execute("delete from \"main\".\"DATA_USERS\" where (\"id_user\")= (?)" + key);
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ": suppression impossible\n" + ex.getMessage());
        }
    }

    @Override
    public void update(DataUserDto item) throws DBException {
         try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement update;
            String sql = "Update \"main\".\"DATA_USER\" set (\"nbr_games\")=(\"nbr_games\")+1 where (\"id_USER\")=?";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(item.getIdUser()));
            update.executeUpdate();
            sql = "Update \"main\".\"DATA_USER\" set (\"lowest_Score\")=(?) where (\"id_USER\")=? AND (\"lowest_Score\")>(?)";
            update = connexion.prepareStatement(sql);
            update.setString(1, Integer.toString(item.getNbrGames()));
            update.setString(2, Integer.toString(item.getLowestScore()));
            update.setString(3, Integer.toString(item.getNbrWins()));
            update.executeUpdate();
        } catch (DBException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<DataUserDto> selectAll() throws DBException {
         List<DataUserDto> al = new ArrayList<>();
        try {
            String query = "Select (\"ID_USER\"),(\"NBR_WINS\"),(\"NBR_GAMES\"),(\"LOWEST_SCORE\") FROM \"main\".\"DATA_USER\" Order by (\"ID_GAME\")";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataUserDto(rs.getInt("\"ID_USER\""),
                        rs.getInt("\"NBR_WINS\""),rs.getInt("\"NBR_GAMES\""),rs.getInt("\"LOWEST_SCORE\"")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    @Override
    public DataUserDto select(Integer key) throws DBException {
             List<DataUserDto> ah = new ArrayList<>();
        try {
             String query = "Select (?) FROM \"main\".\"DATA_USER\"";
         java.sql.Connection connexion = DBManager.getConnection();
         java.sql.PreparedStatement stmt;
         stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
              while (rs.next()) {
                ah.add(new DataUserDto(rs.getInt("\"ID_USER\""),rs.getInt("\"NBR_WINS\"")
                        ,rs.getInt("\"NBR_GAMES\""),rs.getInt("\"LOWEST_SCORE\"")));
            }
        } catch (DBException | DtoException | SQLException e) {
            
        }
      return  ah.get(0);
    }

 
}
