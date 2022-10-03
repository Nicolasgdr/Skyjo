/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.he2b.atlr5.skyjo.view.buttonHandlers;

import be.he2b.atlr5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author le-ni
 */
public class ButtonPlay  implements EventHandler<MouseEvent>{
SkyjoView view;
Controller controller;
boolean play;
    @Override
    public void handle(MouseEvent t) {
    //   if(!this.controller.getClient().isIsPause()){
            this.controller.setPlay();
            this.controller.setPauseFalse();
        this.controller.sendPlay();
    //   }  
    }
      public ButtonPlay(SkyjoView view, Controller controller) {
        this.view = view;
        this.controller = controller;
        this.play = false;
      }
      public void GestionButton(){
          this.view.play().addEventHandler(MouseEvent.MOUSE_CLICKED, this);
      }
      
      
}
