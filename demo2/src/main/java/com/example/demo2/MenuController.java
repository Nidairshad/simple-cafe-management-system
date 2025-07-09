package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MenuController {

    private Stage loginStage;
    private Stage menuStage;

    private int LatteCount = 0;
    private int TeaCount = 0;
    private int CrossiantCount = 0;
    private int CookieCount = 0;


    @FXML private Button addLatte, addTea, addCrossiant, addCookies;
    @FXML private Button deleteLatte, deleteTea, deleteCrossiant, deleteCookies;
    @FXML private Button next, back;


    public void setLoginStage(Stage loginStage){
        this.loginStage = loginStage;
    }

    public void setMenuStage(Stage menuStage){
        this.menuStage = menuStage;
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addLatte() {
        LatteCount++;
        addLatte.setText("Add Latte (" + LatteCount + ")");
    }

    @FXML
    private void deleteLatte() {
        if (LatteCount > 0) {
            LatteCount--;
            addLatte.setText("Add Latte (" + LatteCount + ")");
        }
    }

    @FXML
    private void addTea() {
        TeaCount++;
        addTea.setText("Add Tea (" + TeaCount + ")");
    }

    @FXML
    private void deleteTea() {
        if (TeaCount > 0) {
            TeaCount--;
            addTea.setText("Add Tea (" + TeaCount + ")");
        }
    }

    @FXML
    private void addCrossiant() {
        CrossiantCount++;
        addCrossiant.setText("Add Crossiant (" + CrossiantCount + ")");
    }

    @FXML
    private void deleteCrossiant() {
        if (CrossiantCount > 0) {
            CrossiantCount--;
            addCrossiant.setText("Add Crossiant (" + CrossiantCount + ")");
        }
    }

    @FXML
    private void addCookies() {
        CookieCount++;
        addCookies.setText("Add Cookies (" + CookieCount + ")");
    }

    @FXML
    private void deleteCookies() {
        if (CookieCount > 0) {
            CookieCount--;
            addCookies.setText("Add Cookies (" + CookieCount + ")");
        }
    }
    @FXML
    private void next() {
        int totalItems = LatteCount + TeaCount + CrossiantCount + CookieCount;

        if (totalItems == 0) {
            // show confirmation alert if no items are selected
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("No items selected");
            confirmAlert.setHeaderText("You have not added any items.");
            confirmAlert.setContentText("Are you sure you want to proceed without placing an order?");

            // wait for users response
            confirmAlert.showAndWait().ifPresent(response -> {
                //Shows the alert (confirmation dialog) and waits until the user closes it (clicks OK or Cancel).
                //
                //Returns:
                //An Optional<ButtonType> — because the user might click
                // a button or close the dialog without clicking.
                if (response == javafx.scene.control.ButtonType.OK) {
                    System.out.println("User chose to proceed with zero items.");
                    // Proceed to next screen or logic
                    showOrderStage();
                } else {
                    System.out.println("User cancelled and will stay on menu.");
                    //else do nth. stay on current screen
                }
            });

        } else {
            //proceed normally when items are selected
            System.out.println("Proceeding with order: " + totalItems + " items.");
            showOrderStage();

        }
    }

    //for next stage (Show order)
    private void showOrderStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showorder.fxml"));
            Parent root = loader.load();

            // Get controller and call method
            ShowOrder controller = loader.getController();
            controller.ordersummary(LatteCount, CrossiantCount, CookieCount, TeaCount);

            Stage stage = new Stage();
            stage.setTitle("Fay Cafe - Order Summary");

            // Set the icon
            Image icon = new Image(getClass().getResource("/com/example/demo2/CafeLogo.png").toExternalForm());
            stage.getIcons().add(icon);

            stage.setScene(new Scene(root));
            stage.show();

            // Close current stage
            Stage currentStage = (Stage) next.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) back.getScene().getWindow();
        currentStage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logincontroller.fxml"));
        Parent root = loader.load();

        Stage loginStage = new Stage();
        loginStage.setTitle("Fay Cafe Management System");

        Image icon = new Image(getClass().getResource("/com/example/demo2/CafeLogo.png").toExternalForm());
        loginStage.getIcons().add(icon);

        loginStage.setScene(new Scene(root));
        loginStage.show();
    }
}
