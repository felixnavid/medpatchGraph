package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("AAAAd");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*Gson gson = new GsonBuilder().create();
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
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        */
        launch(args);
    }
}
