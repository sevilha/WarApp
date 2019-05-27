package com.mycompany.warapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

public class MainApp extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) {

        //Construct new browser
        final Browser browser = new Browser();

        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(browser, Color.web("#666970"));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();

        //Add event handler so that when close close window stop webapp-runner
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                browser._p.destroy();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    static final String WAR_PROJECT = "project.war";
    static final String WEB_RUNNER = "name.version.jar";

    Process _p;

    public Browser() {

        //apply the styles
        getStyleClass().add("browser");
        // load the web page

        _p = null;

        //Start webapp-runner and load war, add to project lib/webapp-runner and war/project.war 
        try {
            _p = Runtime.getRuntime().exec("java -jar lib/" + WEB_RUNNER + " --port 18789 war/" + WAR_PROJECT);
            System.out.println("Running web-runner");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        webEngine.load("http://localhost:18789/");
        //add the web view to the scene
        getChildren().add(browser);

    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
