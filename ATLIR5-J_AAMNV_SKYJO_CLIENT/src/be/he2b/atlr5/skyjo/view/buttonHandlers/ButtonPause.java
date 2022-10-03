package be.he2b.atlr5.skyjo.view.buttonHandlers;
import be.he2b.atlr5.skyjo.controller.Controller;
import be.he2b.atlr5.skyjo.view.viewJfx.SkyjoView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author le-ni
 */
public class ButtonPause  implements EventHandler<MouseEvent>{
      private final SkyjoView view;
    private final Controller controller;
    private boolean pause;

    public ButtonPause(SkyjoView view, Controller controller) {
        this.view = view;
        this.controller = controller;
        this.pause = false;
        
    }
    
    public void sendbutton (){
        this.view.pause().addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        
    }
    
      @Override
    public void handle(MouseEvent mouse){
          System.out.println("avant handler");
        this.controller.setPause();
     
          System.out.println("avant sendPause");
        this.controller.sendPause();
    }

   
    
}
