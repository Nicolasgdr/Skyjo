/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.dbdto;

import be.he2b.atlr5.skyjo.exception.DtoException;

/**
 *
 * @author le-ni
 */
public class PauseDto extends Dto<Integer>{
      private int id_game;
    private int id_user;
    private int pause;

    public PauseDto(int id_game, int id_user, int pause) throws DtoException{
        super(id_game);
        
        this.id_user = id_user;
        this.pause = pause;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
    
}
