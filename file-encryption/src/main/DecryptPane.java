/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import security.FileEncryption;

/**
 *
 * @author lbsilva
 */
public class DecryptPane extends SecurityPane {

    public DecryptPane(Stage primaryStage) {
        super(primaryStage);
    }

    @Override
    protected void createDoButton() {
        doButton = new Button();
        doButton.setText("Decrypt...");
        doButton.setOnAction(event -> new PasswordWindow(pathTextField.getText(), FileEncryption.DECRYPT_MODE));
    }

    @Override
    protected void addExtensionFilters(ObservableList<FileChooser.ExtensionFilter> extensionFilters) {
        extensionFilters.add(new FileChooser.ExtensionFilter("Encrypted files (*.enc)", "*.enc"));
    }
}
