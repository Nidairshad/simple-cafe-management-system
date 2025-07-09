package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ShowOrder extends FileHandler {

    @FXML private Button next,back;
    @FXML private Label orderlabel;



    //public made so can be acessed by menucontroller class
    public void ordersummary(int latte, int crossiant , int cookie , int tea ){
        String itemName[] = new String[4];

        //storing in item name in same format as given in file;itemname[0]
        // tea,itemname[1] latte, itemname[2] cookie ,itemname[3] corsiiant
        String summary = "You ordered : \n";

        //System.out.println("order summary");
        if (tea > 0) {
            summary += tea + " Tea(s)\n";
            itemName[0] = "Tea";
        }
        if(latte>0){
            summary +=latte + "Latte(s)\n";
            itemName[1] = "Latte";
        }
        if (cookie > 0) {
            summary += cookie + " Cookie(s)\n";
            itemName[2] = "Cookie";
        }

        if (crossiant > 0) {
            summary += crossiant + " Croissant(s)\n";
            itemName[3] = "crossiant";
        }

        //if nothing orders so compared with the orgininal string

        if (summary.equals("You ordered : \n")) {
            summary += "Nothing yet.";
            orderlabel.setText(summary); //after appending
        } else {
            orderlabel.setText(summary); //after appending
        }
        updateStockAfterOrder("inventory.txt",itemName,tea,latte,cookie,crossiant);
    }


   //butttonss
    @FXML
    private void back(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menucontroller.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));

            Image icon = new Image(getClass().getResource("/com/example/demo2/CafeLogo.png").toExternalForm());
            stage.getIcons().add(icon);
            stage.setTitle("Fay Cafe - Menu");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    //next to inventory
    @FXML
    private void next() {
        try {
            // Correct path to load from resources , absolute path of file
            InputStream input = getClass().getResourceAsStream("/com/example/demo2/inventory.txt");

            if (input == null) {
                System.out.println("Error: inventory.txt not found in resources folder.");
                return;
            }
            //this gets the system temp folder path
            //java.io.tmpdir system.getproperty(java.io.tmpdir) gives path to os temp folder
            //new File(parent,child) where parent = parent path folder, and child = file name inside that folder

            File tempFile = new File(System.getProperty("java.io.tmpdir"), "inventory.txt");

            //StandardCopyOption.REPLACE_EXISTING if file already exits in the temp folder , over write it then
            Files.copy(input, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            input.close();

            if (!Desktop.isDesktopSupported()) {
                System.out.println("Your system doesn't support desktop actions.");
                return;
            }

            Desktop.getDesktop().open(tempFile);

        } catch (IOException e) {
            System.out.println("Error opening inventory.txt: " + e.getMessage());
        }
    }


    @FXML
    private void exit(ActionEvent event){
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
