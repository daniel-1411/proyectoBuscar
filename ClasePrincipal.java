/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoBuscar;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.Event;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author FP Mañana A
 */
//public class ClasePrincipal extends Application implements ChangeListener{
public class ClasePrincipal extends Application{
static Label lb_lista;
TextField tf_buscar;
VBox v;
ProgressBar pb_barra;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        v= new VBox(20);
        lb_lista=new Label();
        tf_buscar=new TextField();
        pb_barra=new ProgressBar();
        EventHandler<KeyEvent> oyente_teclas=new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                //Barra
                
                MiTarea2 tarea2=new MiTarea2();
                Thread hilo2=new Thread(tarea2);
                hilo2.setDaemon(true);
                hilo2.start();
                pb_barra.progressProperty().bind(tarea2.progressProperty());
                //Buscador
                System.out.println("Pone: "+tf_buscar.getText());
                MiTarea tarea=new MiTarea(tf_buscar.getText());
                Thread hilo=new Thread(tarea);
                hilo.setDaemon(true);
                hilo.start();
                tarea.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent t) {
                        ArrayList<Contacto> lista=tarea.getValue();
                        rellenarLabel(lista);
                    }
                });
            }
        };
        tf_buscar.setOnKeyReleased(oyente_teclas);
        v.getChildren().addAll(tf_buscar,lb_lista,pb_barra);
        Scene scene = new Scene(v,250,300);
        stage.setScene(scene);
        stage.show();
    }
    private void rellenarLabel(ArrayList<Contacto> lista)
        {
            String texto_final="";
            for (Contacto contacto : lista) {
                texto_final=texto_final+"Su nombre es "+contacto.getNombre()+" y su edad es de "+contacto.getEdad()+" años \n";
            }
            
            if(tf_buscar.getText().isEmpty())
            {
                lb_lista.setText("");
            }
            else
                {
            lb_lista.setText(texto_final);
        }
        }
}