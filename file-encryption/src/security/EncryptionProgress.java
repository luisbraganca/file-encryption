/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author lbsilva
 */
public class EncryptionProgress extends Stage implements Runnable {

    public final static int GENERAL_SPACING = 15;
    public final static int GENERAL_PADDING = 15;

    private FileEncryption fe;
    private ProgressIndicator progressIndicator;
    private Label label;
    private Button okButton;
    private boolean finish;

    public EncryptionProgress(FileEncryption fe) {
        this.fe = fe;
        finish = false;
        createOkButton();
        getIcons().add(new Image(getClass().getResourceAsStream(".." + File.separator + "images" + File.separator + "logo.png")));
        setScene(createScene());
        createWindow();
    }

    private void createWindow() {
        setResizable(false);
        setIconified(false);
        setAlwaysOnTop(true);
        initModality(Modality.APPLICATION_MODAL);
        setOnCloseRequest(event -> {
            if (!finish) {
                fe.abort();
                finish = true;
                event.consume();
            }
        });
    }

    private Scene createScene() {

        setTitle("FileEncryption - Working...");
        progressIndicator = new ProgressIndicator();
        label = new Label();
        updateLabel(0);
        VBox vb = new VBox();
        vb.setSpacing(GENERAL_SPACING);
        vb.setPadding(new Insets(GENERAL_PADDING));
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(new Label("Close this window to cancel"), progressIndicator, label, okButton);
        Scene scene = new Scene(vb, 275, 200);
        scene.getStylesheets().add(getClass().getResource(".." + File.separator + "styles" + File.separator + "Style.css").toExternalForm());
        scene.setRoot(vb);
        return scene;
    }

    public void start() {
        show();
        startGUI();
        startEncryption();
    }

    private void startEncryption() {
        new Thread(() -> fe.start()).start();
    }

    private void startGUI() {
        new Thread(() -> {
            while (!finish) {
                sleepSomeTime(); // Just some sleep so the program doesn't crash
                final double progress = fe.getProgress();
                if (progress >= 1) {
                    finish = true;
                }
                Platform.runLater(() -> { // GUI changes
                    // You can also update the progress indicator but its design changes and becomes a bit ugly
                    // I like it when it's on "loading" so that's why I never update it, only when finished instantly to 100%
                    // progressIndicator.setProgress(progress);
                    updateLabel(progress);
                });
            }
            Platform.runLater(() -> { // More GUI changes
                progressIndicator.setProgress(1);
                String status = fe.getStatus();
                label.setText(fe.getStatus());
                if (!status.contains("Successfully")) {
                    label.setTextFill(Color.DARKRED);
                } else {
                    label.setTextFill(Color.DARKGREEN);
                }
                okButton.setDisable(false);
            });
        }).start();
    }

    private void sleepSomeTime() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException ignored) {
        }
    }

    private void createOkButton() {
        okButton = new Button("Close");
        okButton.setDisable(true);
        okButton.setOnAction(event -> close());
    }

    private void updateLabel(double progress) {
        label.setText(String.format("%.0f", (progress * 100)) + " %");
    }

    @Override
    public void run() {
        fe.start();
    }
}
