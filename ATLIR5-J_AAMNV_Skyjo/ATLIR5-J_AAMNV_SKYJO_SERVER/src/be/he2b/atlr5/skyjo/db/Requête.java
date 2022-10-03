/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.db;

import be.he2b.atlr5.skyjo.dbdto.DataGameDto;
import be.he2b.atlr5.skyjo.dbdto.DataUserDto;
import be.he2b.atlr5.skyjo.exception.DBException;
import java.sql.SQLException;
import java.sql.*;


/**
 *
 * @author g54317
 */
public class Requête   {
    //requete sql de la table Game
    public static int insertGameDB(DataGameDto record) throws DBException{
        try{
            Connection connection = DBManager.getConnection();
         java.sql.PreparedStatement insert;
         insert = connection.prepareStatement( "INSERT INTO main.DATA_GAME VALUES (?,?,?);");
            insert.setInt(1, record.getIdGame());            
            insert.setInt(2, record.getIdUser());
            insert.setInt(3, record.getScore());
            insert.executeUpdate();
            return record.getIdGame();
        } catch (SQLException ex) {
            throw new DBException("DATA_GAME"+": ajout impossible\n" + ex.getMessage());
        }
    }
    // requete sql de la table User
    public static int insertUserDB(DataUserDto record) throws DBException {
         try {
            
             Connection connection = DBManager.getConnection();
                     java.sql.PreparedStatement insert;
            insert = connection.prepareStatement( "INSERT INTO main.DATA_USER VALUES (?,?,?,?);");
            insert.setString(1, Integer.toString(record.getIdUser()));
            insert.setString(2, Integer.toString(record.getNbrWins()));
            insert.setString(3, Integer.toString(record.getNbrGames()));
            insert.setString(4, Integer.toString(record.getLowestScore()));
            insert.executeUpdate();
            return record.getIdUser();
        } catch (SQLException ex) {
            throw new DBException("DATA_USER"+": ajout impossible\n" + ex.getMessage());
        }
        }
    }

