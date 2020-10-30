package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage window) {
        window.setMaximized(true);
        window.setFullScreen(true);

        //menu
        MenuItem menuLigne = new MenuItem("Lignes");
        MenuItem menuRegion = new MenuItem("Régions");
        MenuItem menuBarre = new MenuItem("Barres");
        Menu importMenu = new Menu("Importer");
        Menu exportMenu = new Menu("Exporter");
        importMenu.getItems().addAll(menuLigne, menuRegion, menuBarre);
        MenuBar barreMenu = new MenuBar(importMenu, exportMenu);

        //créer les axes
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setAnimated(false);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température (•C)");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionnez un fichier DAT");

        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Où voulez-vous enregistrer l'image?");

        //créer graphique
        Group group = new Group();
        menuLigne.setOnAction(event -> {
            final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
            lineChart.setTitle("Température moyenne en fonction du mois");
            group.getChildren().clear();
            group.getChildren().add(lineChart);
            File fiche = fileChooser.showOpenDialog(window);
            System.out.print(fiche);
            XYChart.Series serieLigne = new XYChart.Series();
            serieLigne.setName("hheheheheh");

            Path path = Paths.get(fiche.getPath());
            try {
                List <String> liste = Files.readAllLines(path);
                String[] mois = liste.get(0).trim().split(", ");
                String[] temp = liste.get(1).trim().split(", ");
                for (int x = 0; x < mois.length; x++) {
                    int vraiTemp = Integer.parseInt(temp[x]);
                    System.out.println(mois[x] + ", " + vraiTemp);
                    serieLigne.getData().add(new XYChart.Data(mois[x], vraiTemp));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            lineChart.getData().addAll(serieLigne);

        });

        menuRegion.setOnAction(event -> {
            final AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
            areaChart.setTitle("Température moyenne en fonction du mois");
            group.getChildren().clear();
            group.getChildren().add(areaChart);
            File fiche = fileChooser.showOpenDialog(window);
            System.out.print(fiche);

            XYChart.Series serieLigne = new XYChart.Series();
            serieLigne.setName("hheheheheh");

            Path path = Paths.get(fiche.getPath());
            try {
                List <String> liste = Files.readAllLines(path);
                String[] mois = liste.get(0).trim().split(", ");
                String[] temp = liste.get(1).trim().split(", ");
                for (int x = 0; x < mois.length; x++) {
                    int vraiTemp = Integer.parseInt(temp[x]);
                    System.out.println(mois[x] + ", " + vraiTemp);
                    serieLigne.getData().add(new XYChart.Data(mois[x], vraiTemp));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            areaChart.getData().addAll(serieLigne);

        });

        menuBarre.setOnAction(event -> {
            final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Température moyenne en fonction du mois");
            group.getChildren().clear();
            group.getChildren().add(barChart);
            File fiche = fileChooser.showOpenDialog(window);
            System.out.print(fiche);

            XYChart.Series serieLigne = new XYChart.Series();
            serieLigne.setName("hheheheheh");

            Path path = Paths.get(fiche.getPath());
            try {
                List <String> liste = Files.readAllLines(path);
                String[] mois = liste.get(0).trim().split(", ");
                String[] temp = liste.get(1).trim().split(", ");
                for (int x = 0; x < mois.length; x++) {
                    int vraiTemp = Integer.parseInt(temp[x]);
                    System.out.println(mois[x] + ", " + vraiTemp);
                    serieLigne.getData().add(new XYChart.Data(mois[x], vraiTemp));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            barChart.getData().addAll(serieLigne);

        });

        BorderPane borderPane = new BorderPane(group);
        borderPane.setTop(barreMenu);
        borderPane.setCenter(group);

        Scene root = new Scene(borderPane);
        window.setScene(root);
        window.show();

        exportMenu.setOnAction(event -> {
            File file = fileChooser1.showOpenDialog(window);
            System.out.print(file);
            //Image image = null;
            //BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            //try {
            //    ImageIO.write(bImage, "png", file);
            //} catch (IOException e) {
            //    throw new RuntimeException(e)
            //}
            //System.out.println("Image Saved");

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}