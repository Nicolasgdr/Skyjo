package be.he2b.atlr5.skyjo.business;

import be.he2b.atlr5.skyjo.db.UserDB;
import be.he2b.atlr5.skyjo.dbdto.UserDto;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.dbspecification.UserSpecification;
import be.he2b.atlr5.skyjo.exception.DtoException;
import java.util.Collection;
import java.util.List;
/**
 *
 * @author mad8
 */
public class UserBusinessLogic {
    
    /**
     * Insert an user in the database. Returns the user's id.
     *
     * @param mail user's mail.
     * @return the user's id.
     * @throws SQLException if the query failed.
     */
    static int add(String userMail) throws DBException, DtoException {
        //return UserDB.insert(userMail);
        var userdb = new UserDB();
       return userdb.insert(new UserDto(Integer.SIZE, userMail));
    }

    /**
     * Removes the given user.
     *
     * @param user user to delete.
     * @throws SQLException if the query failed.
     */
    static void delete(int id) throws DBException {
        //UserDB.delete(id);
        var userdb = new UserDB();
        userdb.delete(id);
    }

    /**
     * Updates the given user.
     *
     * @param user user to update.
     * @throws SQLException if the query failed.
     */
    static void update(UserDto user) throws DBException {
       // UserDB.update(user);
       var userdb = new UserDB();
       userdb.update(user);
    }

    /**
     * Returns the unique user with the given mail.
     *
     * @param id user's mail
     * @return the unique user with the given mail.
     * @throws SQLException if the query failed.
     */
static UserDto findByMail(String mail) throws DBException {
        UserSpecification sel = new UserSpecification(mail);
        Collection<UserDto> col = UserDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * Returns the unique user with the given id.
     *
     * @param id user's id.
     * @return the unique user with the given id.
     * @throws SQLException if the query failed.
     */
    static UserDto findById(int id) throws DBException {
        UserSpecification sel = new UserSpecification(id);
        Collection<UserDto> col = UserDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * Returns a list of users with the given specifications.
     *
     * @param sel specifications (where clause)
     * @return a list of users with the given specifications.
     * @throws BusinessException if the query failed.
     */
    static List<UserDto> findBySel(UserSpecification sel) throws DBException {
        List<UserDto> col = UserDB.getCollection(sel);
        return col;
    }

    /**
     * Returns a list of all users.
     *
     * @return a list of all users.
     * @throws BusinessException if the query failed.
     */
    static List<UserDto> findAll() throws DBException {
        UserSpecification sel = new UserSpecification(0);
        List<UserDto> col = UserDB.getCollection(sel);
        return col;
    }

    //ajouter les methodes avec une logique métier
}
