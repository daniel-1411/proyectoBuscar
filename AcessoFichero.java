/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoBuscar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FP Mañana A
 */
class AccesoFichero {
private static String ruta="C:\\Agenda\\agenda.csv";
    static ArrayList<Contacto> sacarContactos(String nombre_buscado) {
        ArrayList<Contacto> lista=new ArrayList<>();
    try {
        FileReader fr=new FileReader(ruta);
        BufferedReader br=new BufferedReader(fr);
        String linea=br.readLine();
        while (linea!=null) {
            String[] datos=linea.split(",");
            Contacto c=new Contacto();
            String nombre=datos[0];
            c.setNombre(nombre);
            int edad=Integer.parseInt(datos[1]);
            c.setEdad(edad);
            if(nombre.toUpperCase().contains(nombre_buscado.toUpperCase())){
                System.out.println("Se ha añadido a la lista "+c);
                lista.add(c);
            }
            linea=br.readLine();
        }
    } catch (FileNotFoundException ex) {
        Logger.getLogger(AccesoFichero.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(AccesoFichero.class.getName()).log(Level.SEVERE, null, ex);
    }
        return lista;
    }
}