/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.LastVisitedDirectory;

import java.io.File;

/**
 *
 * @author lbsilva
 */
public abstract class SecurityPane extends VBox {

    public final static int GENERAL_SPACING = 30;
    public final static int GENERAL_PADDING = 30;

    protected TextField pathTextField;
    protected Button selectButton;
    protected Button doButton;

    protected Stage primaryStage;

    public SecurityPane(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
        createPathTextField();
        createSelectButton();
        createDoButton();
        createWindow();
    }

    protected abstract void createDoButton();

    private void createSelectButton() {
        selectButton = new Button("Select...");
        selectButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select file");
            addExtensionFilters(fileChooser.getExtensionFilters());
            if (LastVisitedDirectory.getInstance().wasVisited()) {
                fileChooser.setInitialDirectory(new File(LastVisitedDirectory.getInstance().getPath()));
            }
            try {
                String newPath = fileChooser.showOpenDialog(primaryStage).getAbsolutePath();
                LastVisitedDirectory.getInstance().setLastVisitedDirectory(newPath);
                pathTextField.setText(newPath);
                pathTextField.positionCaret(pathTextField.getLength());
            } catch (NullPointerException e) {
                System.out.println("Canceled");
            }
        });
    }

    protected void addExtensionFilters(ObservableList<FileChooser.ExtensionFilter> extensionFilters) {
    }

    private void createWindow() {
        HBox path = new HBox();
        path.getChildren().addAll(pathTextField, selectButton);
        path.setSpacing(GENERAL_SPACING);
        getChildren().addAll(path, doButton);
        setSpacing(GENERAL_SPACING);
        setPadding(new Insets(GENERAL_PADDING));
    }

    private void createPathTextField() {
        pathTextField = new TextField();
        pathTextField.setPrefWidth(250);
        pathTextField.setPromptText("File path...");
    }
}
