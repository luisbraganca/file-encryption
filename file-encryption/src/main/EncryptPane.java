/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import security.FileEncryption;

/**
 *
 * @author lbsilva
 */
public class EncryptPane extends SecurityPane {

    public EncryptPane(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    protected void createDoButton() {
        doButton = new Button();
        doButton.setText("Encrypt...");
        doButton.setOnAction(event -> new PasswordWindow(pathTextField.getText(), FileEncryption.ENCRYPT_MODE));
    }
}
