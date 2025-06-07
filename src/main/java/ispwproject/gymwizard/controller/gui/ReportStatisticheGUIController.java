package ispwproject.gymwizard.controller.gui;

import ispwproject.gymwizard.util.DAO.StatisticaDAO;
import ispwproject.gymwizard.util.singleton.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.sql.SQLException;

public class ReportStatisticheGUIController extends AbstractGUIController{

    String homePage = (String) SessionManager.getInstance().getAttributo("homePage");

    @FXML
    private Label utentiAttiviLabel, prenotazioniLabel, attivitaLabel;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize() {
        anchorPane.setBackground(new Background(this.background()));

        try {
            StatisticaDAO dao = new StatisticaDAO();
            utentiAttiviLabel.setText(String.valueOf(dao.getTotaleClienti()));
            prenotazioniLabel.setText(String.valueOf(dao.getTotalePrenotazioni()));
            attivitaLabel.setText(String.valueOf(dao.getTotaleAttivita()));

            // Facoltativo: popolamento dinamico bar chart (placeholder statico per ora)
            xAxis.setLabel("Giorno");
            yAxis.setLabel("Prenotazioni");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Prenotazioni Settimanali");

            // Sostituibile con query settimanale reale in seguito
            series.getData().add(new XYChart.Data<>("Lunedì", 50));
            series.getData().add(new XYChart.Data<>("Martedì", 75));
            series.getData().add(new XYChart.Data<>("Mercoledì", 90));
            series.getData().add(new XYChart.Data<>("Giovedì", 60));
            series.getData().add(new XYChart.Data<>("Venerdì", 80));
            series.getData().add(new XYChart.Data<>("Sabato", 95));
            series.getData().add(new XYChart.Data<>("Domenica", 40));

            barChart.getData().add(series);

        } catch (SQLException e) {
            this.showError("Errore Statistiche", e.getMessage());
        }
    }

    @FXML
    public void onBackClick(ActionEvent event) {
        System.out.println("BACK button clicked.");
        this.switchScene(homePage,event);
    }

    @FXML
    public void onHelpClick() {
        System.out.println("HELP button clicked.");
        this.showPopup("Guida Inerfaccia", "Report e Statistiche", """
                Questa schermata mostra:
                - Numero totale di utenti attivi
                - Numero complessivo di prenotazioni
                - Attività disponibili
                - Un grafico delle prenotazioni per giorno della settimana

                Usa ↩ per tornare alla dashboard, ⌂ per fare logout.
                """);
    }

    @FXML
    public void onHomeClick(ActionEvent event) {
        System.out.println("HOME button clicked.");
        this.switchScene(homePage, event);
    }
}
