package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;


    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        //ifff the user enters "admin" for the username and
        // "1234" for the password, the system will print Login successful
        if ("admin".equals(username) && "1234".equals(password)) { //.equals compares the value
            //sout statements i  have written for myself to check if its workin
            System.out.println("successful!");
            showMenuStage();

        } else {
            System.out.println("invalid");
            showAlert();

        }
    }

@FXML
private void exit(javafx.event.ActionEvent event) {
        //event.getSource() gets the ui element (Node) that happened when button was clicke
    //downcasting event.getSource() returns an object(a generic type) java doesnt know what kind of ui element it is
    //so (javafx.scene.Node) event.getSource() cast the generic object to Node
    //because onlyNodes have .getScene() method
    //.getWindow is actuallly the current stage here
    //javafx.stage.Stage cast generic window obj to a Stage so you can use stage specific methods
    javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
    stage.close();
}

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);//Alerttype :is enum list of constants in Alert class
        alert.setTitle("Login failed.");
        alert.setHeaderText(null);//if not used so in header ERROR would be shown
        alert.setContentText("Invalid username or password entered .please try again");
        alert.showAndWait();

    }

    private void showMenuStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo2/menucontroller.fxml"));
            Parent root = loader.load();

            // get current stage (Login window)
            Stage loginStage = (Stage) loginButton.getScene().getWindow();

            // Create a new Stage for Menu
            Stage menuStage = new Stage();
            menuStage.setScene(new Scene(root));
            menuStage.setTitle("Fay Cafe - Menu");

            //for icon
            Image icon = new Image(getClass().getResource("/com/example/demo2/CafeLogo.png").toExternalForm());
            menuStage.getIcons().add(icon);

            menuStage.show();

            // Hide the login window
            loginStage.hide();

            // Pass both stages to the menu controller
            MenuController menuController = loader.getController();
            menuController.setLoginStage(loginStage);
            menuController.setMenuStage(menuStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
