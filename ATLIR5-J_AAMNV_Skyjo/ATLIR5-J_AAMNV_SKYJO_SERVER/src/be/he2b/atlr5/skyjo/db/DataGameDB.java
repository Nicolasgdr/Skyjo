package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.DataGameDto;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.exception.DtoException;
import be.he2b.atlr5.skyjo.dbspecification.DataGameSpecification;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data game db.
 *
 * @author mad8
 */
public class DataGameDB implements Dao<Integer, DataGameDto>  {
    private static final String recordName = "DATA_GAME";

    /**
     * Gets all game data.
     *
     * @return all game data
     * @throws DtoException the dto exception
     * @throws DBException  the db exception
     */
    public static List<DataGameDto> getAllGameData() throws DtoException, DBException {
        List<DataGameDto> users = getCollection(new DataGameSpecification(0));
        return users;
    }

    /**
     * Gets collection.
     *
     * @param sel the sel
     * @return collection
     * @throws DBException the db exception
     */
    public static List<DataGameDto> getCollection(DataGameSpecification sel) throws DBException {
        List<DataGameDto> al = new ArrayList<>();
        try {
            String query = "Select (\"ID_GAME\"),(\"ID_USER\"),(\"SCORE\") FROM \"main\".\"DATA_GAME\"";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            String where = "";
            if (sel.getIdGame()!= 0) {
                where = where + " (\"ID_GAME\") = ? ";
            }
            if (sel.getIdUser()!= 0) {
                if (!where.isEmpty()) {
                    where = where + " AND ";
                }
                where = where + " (\"ID_USER\") = ? ";
            } else {
            }

            if (where.length() != 0) {
                where = " where " + where + " order by (\"ID_GAME\")";
                query = query + where;
                stmt = connexion.prepareStatement(query);
                int i = 1;
                if (sel.getIdGame()!= 0) {
                    stmt.setInt(i, sel.getIdGame());
                    i++;

                }
                if (sel.getIdUser()!= 0) {
                    stmt.setString(i, sel.getIdUser() + "%");
                    i++;
                }
            } else {
                query = query + " Order by (\"ID_GAME\")";
                stmt = connexion.prepareStatement(query);
            }
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataGameDto(rs.getInt("\"ID_GAME\""),rs.getInt("\"ID_USER\""),rs.getInt("\"SCORE\"")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    /**
     * Insert db int.
     *
     * @return int
     * @throws DBException the db exception
     */

    @Override
    public Integer insert(DataGameDto item) throws DBException {
        try {
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement insert;
            insert = connexion.prepareStatement( "INSERT INTO main.DATA_GAME VALUES (?,?,?);");
            insert.setInt(1, item.getIdGame());            
            insert.setInt(2, item.getIdUser());
            insert.setInt(3, item.getScore());
            insert.executeUpdate();
            return item.getIdGame();
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName+": ajout impossible\n" + ex.getMessage());
    }
    }
    @Override
    public void delete(Integer key) throws DBException {
             try {
            java.sql.PreparedStatement stmt = (java.sql.PreparedStatement) DBManager.getConnection().createStatement();
            stmt.execute("delete from \"main\".\"DATA_GAME\" where (\"id_game\")= (?)" + key);
        } catch (DBException | SQLException ex) {
            throw new DBException(recordName + ": suppression impossible\n" + ex.getMessage());
        }
    }

    @Override
    public void update(DataGameDto item) throws DBException {
        // la je sais pas quoi faire comme update !
    }

    @Override
    public List<DataGameDto> selectAll() throws DBException {
         List<DataGameDto> al = new ArrayList<>();
        try {
            String query = "Select (\"ID_GAME\"),(\"ID_USER\"),(\"SCORE\") FROM \"main\".\"DATA_GAME\" Order by (\"ID_GAME\")";
            java.sql.Connection connexion = DBManager.getConnection();
            java.sql.PreparedStatement stmt;
            stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                al.add(new DataGameDto(rs.getInt("\"ID_GAME\""),rs.getInt("\"ID_USER\""),rs.getInt("\"SCORE\"")));
            }
        } catch (DtoException ex) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nDTOException: " + ex.getMessage());
        } catch (java.sql.SQLException eSQL) {
            throw new DBException("Instanciation de "+recordName+" impossible:\nSQLException: " + eSQL.getMessage());
        }
        return al;
    }

    @Override
    public DataGameDto select(Integer key) throws DBException {
        List<DataGameDto> ah = new ArrayList<>();
        try {
             String query = "Select (?) FROM \"main\".\"DATA_GAME\"";
         java.sql.Connection connexion = DBManager.getConnection();
         java.sql.PreparedStatement stmt;
         stmt = connexion.prepareStatement(query);
            java.sql.ResultSet rs = stmt.executeQuery();
              while (rs.next()) {
                ah.add(new DataGameDto(rs.getInt("\"ID_GAME\""),rs.getInt("\"ID_USER\""),rs.getInt("\"SCORE\"")));
            }
        } catch (Exception e) {
            
        }
      return  ah.get(0);
    }
    
}
