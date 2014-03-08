package com.tm.gui;

import com.tm.core.AppConfigurator;
import com.tm.exception.BusinessLogicException;
import com.tm.exception.RemoteAccessException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Runner extends Application {
	
	private Group root;
	
	public static void main(String[] args) {
    	AppConfigurator.configureDefault();
        launch(args);
    }
	
	@Override
    public void start(Stage primaryStage) {
    	init(primaryStage);
        primaryStage.show();
    }
	
	private void init(Stage primaryStage) {

		root = new Group();
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        
        MainPane mainPane = new MainPane();
        try {
			MainPaneController controller = new MainPaneController(mainPane);
		} catch (RemoteAccessException | BusinessLogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
        add(mainPane);
        
    }
	
	private void add(Node e) {
		root.getChildren().add(e);
	}
    
}
