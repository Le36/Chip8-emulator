package com.chip8.ui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Launcher extends Stage {

    Launcher() {
        UiElements uiElements = new UiElements();
        this.setTitle("Chip8 Launcher");
        Button extended = uiElements.makeButton("Extended");
        Button normal = uiElements.makeButton("Normal");
        extended.setPrefSize(80, 40);
        normal.setPrefSize(80, 40);
        HBox hBoxButtons = new HBox(5, extended, normal);
        hBoxButtons.setAlignment(Pos.CENTER);
        Label top = uiElements.makeLabel("Select the mode you want to launch the emulator in", LabelType.TOOLBAR);
        HBox hBoxTop = new HBox(2, top);
        hBoxTop.setAlignment(Pos.CENTER);
        BorderPane launcherRoot = new BorderPane();
        Background bg = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
        launcherRoot.setBackground(bg);
        launcherRoot.setTop(hBoxTop);
        launcherRoot.setCenter(hBoxButtons);

        Slider resolution = uiElements.makeSlider(1, 60, 15);
        resolution.setSnapToTicks(true);
        resolution.setMajorTickUnit(1.0);
        resolution.setMinorTickCount(1);
        Label gameRes = uiElements.makeLabel("Game resolution:", LabelType.TOOLBAR);
        Label selectedRes = uiElements.makeLabel("0x0", LabelType.TOOLBAR);
        Button launchNormal = uiElements.makeButton("Launch");
        VBox normalMode = new VBox(5, resolution, gameRes, selectedRes, launchNormal);
        normalMode.setAlignment(Pos.CENTER);
        this.setScene(new Scene(launcherRoot, 400, 200));
        this.show();

        extended.setOnAction(e -> {
            this.close();
            new EmulatorUi(true, 10);
        });

        normal.setOnAction(e -> {
            top.setText("Select your resolution");
            launcherRoot.setCenter(normalMode);
        });

        launchNormal.setOnAction(e -> {
            this.close();
            new EmulatorUi(false, (int) resolution.getValue());
        });

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                int val = (int) resolution.getValue();
                selectedRes.setText(val * 64 + "x" + val * 32);
            }
        }.start();
    }
}
