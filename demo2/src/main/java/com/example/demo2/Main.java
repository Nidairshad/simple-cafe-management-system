//main class-java fx 2017 verison used

package com.example.demo2;//declares the package all the classes in this package

import javafx.application.Application;//javafx.application is package and Application class from javaFX
import javafx.fxml.FXMLLoader;//loads the fxml file
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;//import the parent class from the java fx scene package
import javafx.scene.image.Image;//ImageView displays the image on the screen

public class Main extends Application {//inherits from Application

    @Override
    //start method overrided from application class ->runtime polymorphsim
    public void start(Stage stage) throws Exception {
        try {
            //get class is methof object class it get the current class and get resource is class method
           //getresource()to locate a file from the resources folder  and return a URL pointing to it.

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo2/logincontroller.fxml"));
            //creates instance that can now be used to load the FXML layout into a JavaFX scene or controller.
            //getClass:this finds the file named logincontroller.fxml in the resource path of your project (usually under src/main/resources in a Maven/Gradle project).


            //root object of Parent class ,  top level ui container the root will hold all the nodes loaded from fxml file
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);

            //for icon
            //Loads the image using that string path --> toexternal form changes url to string,relative path
            Image icon = new Image(getClass().getResource("/com/example/demo2/CafeLogo.png").toExternalForm());

//getIcons() is a getter method that simply returns the list of icons associated with the Stage
            stage.getIcons().add(icon);

            //for title on the menu bar ig:
            stage.setTitle("Cafe management system");


            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //main function launch method inside to launch obv the stage
    public static void main(String[] args) {
        //static method of javafx application class
        launch();
    }
}

