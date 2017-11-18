package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField textField;
    public Button addButton;
    public LineChart lineChart;
    XYChart.Series series = new XYChart.Series();
    int time = 0;

    public void addToChart(PatchPacket patchPacket) {
        series.getData().add(new XYChart.Data(patchPacket.t, patchPacket.T));
    }
    public void clickyClacka(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=1;i<=10;i++) {
            PatchPacket p = new PatchPacket();
            p.t = i*2;
            p.T = i*3+2;
            addToChart(p);
            //lineChart.getData().add(p.t, p.T);
        }
        lineChart.getData().add(series);

    }
}
