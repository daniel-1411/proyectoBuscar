/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoBuscar;

import javafx.concurrent.Task;

/**
 *
 * @author FP Mañana A
 */
class MiTarea2 extends Task{
int i=0;
    @Override
    protected Object call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(100);
            updateProgress(i, 100);
            if(i==100){
                i=0;
            }
        }
        return null;
    }
    
}
