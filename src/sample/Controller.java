package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller implements Initializable {


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
        Gson gson = new GsonBuilder().create();
        SerialPort serialPort= SerialPort.getCommPorts()[0];
        serialPort.openPort();
        System.out.println("opened");
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if(serialPortEvent.getEventType()!= SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
                if(serialPort.bytesAvailable()==0) return;
                byte[] newData = new byte[serialPort.bytesAvailable()];
                int num = serialPort.readBytes(newData, newData.length);
                String str = new String(newData, StandardCharsets.UTF_8);
                try {
                    PatchPacket patchPacket = gson.fromJson(str, PatchPacket.class);
                    System.out.print(patchPacket.T);
                    addToChart(patchPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        lineChart.getData().add(series);

    }
}
