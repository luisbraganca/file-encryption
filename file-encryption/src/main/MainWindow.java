/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author lbsilva
 */
public class MainWindow extends BorderPane {

    public MainWindow(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        Tab encryptTab = new Tab("Encrypt", new EncryptPane(primaryStage));
        encryptTab.setClosable(false);
        Tab decryptTab = new Tab("Decrypt", new DecryptPane(primaryStage));
        decryptTab.setClosable(false);
        Tab aboutTab = new Tab("About", new AboutPane(primaryStage));
        aboutTab.setClosable(false);
        tabPane.getTabs().addAll(encryptTab, decryptTab, aboutTab);
        setCenter(tabPane);
    }
}
