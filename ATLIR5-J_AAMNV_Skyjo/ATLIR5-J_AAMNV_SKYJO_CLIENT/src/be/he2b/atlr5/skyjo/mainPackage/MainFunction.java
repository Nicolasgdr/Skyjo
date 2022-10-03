package be.he2b.atlr5.skyjo.mainPackage;

import be.he2b.atlr5.skyjo.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainFunction extends Application{
    Controller controller ;
    @Override
    public void start(Stage primaryStage) throws Exception {
         controller = new Controller();
        controller.start(primaryStage);
    }
    
    @Override
    public void stop(){
        controller.closeConnexion();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
