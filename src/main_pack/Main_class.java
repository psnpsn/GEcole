package main_pack;

import Models.Eleve;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main_class extends Application {

    // -1 = non connecter
    // 0 = administrateur
    // >0 = id de l'instituteur connecter
    public static int user_id = -1;

    public static double xOffset = 0;
    public static double yOffset = 0;
    private static BorderPane root = new BorderPane();
    public static final String MENU_UP = "../GUI/uppermenu.fxml";
    public static final String MENU_LOW = "../GUI/lowermenu.fxml";
    public static final String LOGIN = "../GUI/login.fxml";
    public static Stage stage;

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("GUI/image/icone.png"));
        stage = primaryStage;
        URL upper = getClass().getResource(MENU_UP);
        HBox barup = FXMLLoader.load(upper);
        URL lower = getClass().getResource(MENU_LOW);
        HBox barlow = FXMLLoader.load(lower);
        URL content = getClass().getResource(LOGIN);
        AnchorPane middle = FXMLLoader.load(content);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setStyle("-fx-border-style:solid solid solid solid");
        root.setStyle("-fx-border-color:grey");
        root.setTop(barup);
        root.setCenter(middle);
        root.setBottom(barlow);
        Scene scene = new Scene(root, 1200, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("     Gestion Ecole   ");
        primaryStage.show();
    }

    public static void main(String[] args) {
        p();
        //ODB.OracleDBSingleton.seConnecter("jdbc:oracle:thin:@localhost:1521:XE","oracle.jdbc.driver.OracleDriver","gecole","gecole");
        launch(args);
    }

    public static void p() {
        Eleve e = new Eleve();
        e.setId_e(26);
        e.setNom("CHAKARON");
        e.setPrenom("MAKARON");
        Print.print_eleve(e);
    }
}
