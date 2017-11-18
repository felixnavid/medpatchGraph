package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public LineChart lineChart;
    public Button healthButton;
    public Button aquaButton;
    public Button medicationButton;
    XYChart.Series series = new XYChart.Series();
    int time = 0;


    public void clickyClacka(ActionEvent actionEvent) {
        ImageView imageView = new ImageView("res\\23758346_1779997648699773_1902865384_n.png");
            healthButton.setGraphic(imageView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ImageView imageView = new ImageView("res\\23758346_1779997648699773_1902865384_n.png");
        healthButton.setGraphic(imageView);

        lineChart.getData().add(series);
        series.getData().add(new XYChart.Data<Number,Number>(1,2));


        Gson gson = new GsonBuilder().create();
        SerialPort serialPort= SerialPort.getCommPorts()[0];
        serialPort.openPort();
        System.out.println("opened");

        Task task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                while(true) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(serialPort.bytesAvailable()>0) {
                                    byte[] newData = new byte[serialPort.bytesAvailable()];
                                    int num = serialPort.readBytes(newData, newData.length);
                                    String str = new String(newData, StandardCharsets.UTF_8);
                                    System.out.println(str);
                                    try {
                                        PatchPacket patchPacket = gson.fromJson(str, PatchPacket.class);
                                        patchPacket.t++;
                                        patchPacket.T++;
                                        series.getData().add(new XYChart.Data<Number,Number>(patchPacket.t, patchPacket.T));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Thread.sleep(1000);
                }

            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
