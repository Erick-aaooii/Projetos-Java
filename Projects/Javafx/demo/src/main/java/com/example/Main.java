package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cria o WebView e carrega o HTML
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/index.html").toExternalForm());
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("javaFunctions", new JavaFunctions());

        // Define a cena
        StackPane root = new StackPane();
        root.getChildren().add(webView);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Editor de Código");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public class JavaFunctions {
        public void exibirMensagem(String msg) {
            System.out.println("Mensagem recebida do JavaScript: " + msg);
        }
    }
}
