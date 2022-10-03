/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.business;

import be.he2b.atlr5.skyjo.db.PauseDb;
import be.he2b.atlr5.skyjo.dbdto.PauseDto;
import be.he2b.atlr5.skyjo.dbspecification.PauseSpecification;
import be.he2b.atlr5.skyjo.exception.DBException;
import be.he2b.atlr5.skyjo.exception.DtoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author le-ni
 */
public class PauseBusiness {
    
    static int verifPause (int user_id , int id_game){
        
        try {
            return PauseDb.selectall(new PauseSpecification(id_game, user_id)).size();
        } catch (DBException ex) {
            Logger.getLogger(PauseBusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DtoException ex) {
            Logger.getLogger(PauseBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    static void inserPause(int user_id , int id_game) throws DtoException{
        
       
               //afficher a l'utilisateur taille 2
        
        try {
             if(verifPause(user_id, id_game) >2){
            PauseDb db = new PauseDb();
            db.update(new PauseDto(user_id, id_game,1));
             }
        } catch (DBException ex) {
            Logger.getLogger(PauseBusiness.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       }
    
}
