package ru.bakhuss.library.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bakhuss.library.desktop.controller.MainController;

import java.io.IOException;

public class ApplicationDesktop extends Application {
    private final Logger log = LoggerFactory.getLogger(ApplicationDesktop.class);
    private Stage primaryStage;

    public ApplicationDesktop() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        method();
        log.info("Start desktop library");
    }

    private void method() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ApplicationDesktop.class.getResource("/view/fxml/main.fxml"));
        AnchorPane mainView = loader.load();
        Scene scene = new Scene(mainView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library");
        primaryStage.show();

        MainController controller = loader.getController();
        controller.setApplicationDesktop(this);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
