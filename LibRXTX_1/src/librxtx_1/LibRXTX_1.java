/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librxtx_1;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.swing.*;
import java.awt.*;

public class LibRXTX_1 extends JFrame{
    //inicializamos y decalramos variables

    CommPortIdentifier portId;
    Enumeration puertos;
    SerialPort serialport;
    static InputStream entrada = null;
    Thread t;
//creamos un constructor para realizar la conexion del puerto
    public static int aux=0,aux3=0;
    public static String  aux2="";
    public Graphics h;
    
    

    public LibRXTX_1() {
        super();              
        setTitle("Condensador");
        setSize(500,500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        puertos = CommPortIdentifier.getPortIdentifiers();
        t = new Thread(new LeerSerial());
        while (puertos.hasMoreElements()) { //para recorrer el numero de los puertos, y especificar con cual quiero trabajar
            //hasmorelements mientras tenga mas eleementos
            portId = (CommPortIdentifier) puertos.nextElement(); //next elemento recorreuno por uno
            //System.out.println(portId.getName()); //puertos disponbibles
            if (portId.getName().equalsIgnoreCase("/dev/ttyUSB0")) {
                try {
                    serialport = (SerialPort) portId.open("LecturaSerial", 1000);//tiempo en ms
                    entrada = serialport.getInputStream();//esta variable del tipo InputStream obtiene el dato serial
                    
                    t.start(); // inciamos el hilo para realizar nuestra accion deimprimir el dato serial

                } catch (Exception e) {
                }
            }
        }
    }
    //con este metodo del tipo thread relaizamos

    public static class LeerSerial implements Runnable {       

        public void run() {
            while (true) {
                try {
                    aux = entrada.read(); // aqui estamos obteniendo nuestro dato serial que es el ascii del numero 
                    aux2=aux2 + (char)aux; // aca se convierte y se concatena con para crear el numero completo                   
                    aux3=Integer.parseInt(aux2);                                                            
                    //System.out.println("Lectura string"+aux2);                    
                    Thread.sleep(100);
                    //if (aux > 0) {
                    //System.out.println(aux2);//imprimimos el dato serial
                    //cambio();
                      //arseInt(aux2);
                      System.out.println("Cambiado"+aux3);
                    //System.out.println("iNT"+aux);//imprimimos el dato serial                        
                    //}
                } catch (Exception e) {
                }
            }
        }
    }
    
    public static void cambio()
    {
        //aux3=Integer.parseInt(aux2);
        System.out.println("Cambiado"+aux3);
    }

    public  void paint(Graphics g)
    {
        g.drawLine(0,0,aux,aux);
    }
    public static void consulta()
    {
        
        try
        {
            System.out.println("-------");        
            //System.out.println("Char "+aux);
            //System.out.println("String "+aux2);
            System.out.println("Int "+aux3);
            Thread.sleep(10);
        } catch (Exception e) {
                }
        
    }
    
    public static void main(String[] args) {
        //Graphics h=null;
        LibRXTX_1 Con=new LibRXTX_1();
        
        //Con.paint(h);
        //Con.repaint();
    }
}
