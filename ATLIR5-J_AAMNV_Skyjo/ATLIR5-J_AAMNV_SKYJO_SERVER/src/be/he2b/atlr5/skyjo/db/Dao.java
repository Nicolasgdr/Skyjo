package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.Dto;
import java.util.List;
import be.he2b.atlr5.skyjo.exception.DBException;
/**
 * Data access object of a resource (file, database, web service).
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Data_access_object"> Wikipedia</a>
 *
 * @author jlc
 * @param <K> key of an item.
 * @param <T> item of the resource.
 */
public interface Dao<K, T extends Dto<K>> {

    /**
     * Inserts an element into the resource.
     *
     * @param item item to insert.
     * @return the element's key, usefull when the key is auto-generated.
     * @throws RepositoryException if the resource can't be accessed.
     */
    K insert(T item) throws DBException;

    /**
     * Deletes the item of the specific key from the resource.
     *
     * @param key key of the element to delete.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void delete(K key) throws DBException;

    /**
     * Update an element of the resource. The search of the element is based on
     * its key.
     *
     * @param item item to update.
     * @throws RepositoryException if the resource can't be accessed.
     */
    void update(T item) throws DBException;

    /**
     * Returns all the elements of the resource. This method can be long.
     *
     * @return all the elements of the resource.
     * @throws RepositoryException if the resource can't be accessed.
     */
    List<T> selectAll() throws DBException;

    /**
     * Returns an element based on its key.
     *
     * @param key key of the element to select.
     * @return an element based on its key.
     * @throws RepositoryException if the resource can't be accessed.
     */
    T select(K key) throws DBException;
}
