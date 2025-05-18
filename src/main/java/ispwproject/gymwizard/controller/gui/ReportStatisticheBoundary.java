package ispwproject.gymwizard.controller.gui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Optional;

public class ReportStatisticheBoundary {

    @FXML
    private Label backIcon, helpIcon, homeIcon;

    @FXML
    private Label utentiAttiviLabel, prenotazioniLabel, attivitaLabel;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize() {
        backIcon.setOnMouseClicked(event -> switchScene("/views/DashboardAdminView.fxml"));
        helpIcon.setOnMouseClicked(event -> handleHelpClick());

        homeIcon.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Vuoi effettuare il logout?");
            alert.setContentText("Verrai riportato alla schermata iniziale.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                switchScene("/views/HomeView.fxml");
            }
        });

        // Dati fittizi (esempio)
        utentiAttiviLabel.setText("120");
        prenotazioniLabel.setText("450");
        attivitaLabel.setText("15");

        xAxis.setLabel("Giorno");
        yAxis.setLabel("Prenotazioni");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Prenotazioni Settimanali");
        series.getData().add(new XYChart.Data<>("Lunedì", 50));
        series.getData().add(new XYChart.Data<>("Martedì", 75));
        series.getData().add(new XYChart.Data<>("Mercoledì", 90));
        series.getData().add(new XYChart.Data<>("Giovedì", 60));
        series.getData().add(new XYChart.Data<>("Venerdì", 80));
        series.getData().add(new XYChart.Data<>("Sabato", 95));
        series.getData().add(new XYChart.Data<>("Domenica", 40));

        barChart.getData().add(series);
    }

    private void handleHelpClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guida Interfaccia");
        alert.setHeaderText("Report e Statistiche");
        alert.setContentText("""
                Questa schermata mostra:
                - Numero totale di utenti attivi
                - Numero complessivo di prenotazioni
                - Attività disponibili
                - Un grafico delle prenotazioni per giorno della settimana

                Usa ↩ per tornare alla dashboard, ⌂ per fare logout.
                """);
        alert.showAndWait();
    }

    private void switchScene(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = loader.load();
            Stage stage = (Stage) backIcon.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600); // dimensione fissa
            stage.setScene(scene);
            stage.setResizable(false);              // blocca resize
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
