/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juliomorosama;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import javafx.scene.control.TextInputDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class Productos {
    
    // Declaramos las variables que vamos a usar en esta clase.
    private String codProducto,nombre,cantidad,descripcion;
    final private String RUTA = ".\\productos.txt";
    
    // Creamos los constructores, 1 vacio 1 con todas las variables.

    public Productos() {
    }

    public Productos(String codProducto, String nombre, String cantidad, String descripcion) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    
    // Getter y Setter.
    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    // Creamos el metodo altas.
    public String altas(){
        File fichero = new File(RUTA);
        try{
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero, true));
            dos.writeUTF(codProducto);
            dos.writeUTF(nombre);
            dos.writeUTF(cantidad);
            dos.writeUTF(descripcion);
            dos.close(); // Siempre cerrar el flujo de datos con el fichero.
        return "Grabado.";
        } catch(Exception e){
            return "Error al grabar " + e.getMessage();
        }
    }
    
    
    // Creamos el metodo listar.
    
    public String listar(){
        File fichero = new File(RUTA);
        String lista = ""; // Tenemos que iniciar esta variable, si no, daría un error.
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(fichero));
            codProducto = dis.readUTF();
            while(codProducto!=null){
                nombre = dis.readUTF();
                cantidad = dis.readUTF();
                descripcion = dis.readUTF();
                lista = lista + System.lineSeparator()  + codProducto + " " + nombre + " " + cantidad + " " + descripcion;
                codProducto = dis.readUTF();
            }
        } catch (Exception e) {
            e.getMessage(); // Capturamos el error.
        }return "Todos los productos." + System.lineSeparator()  + lista;
    }

    
    // creamos el metodo buscar.
    public String buscar(){ // Es como el metodo listar, pero añadimos 2 if, 1 para añadir la cadena que queremos que devuelva y otro para comprobar si encontró lo que buscamos.
        
        String entrada = JOptionPane.showInputDialog("Introduce un nombre.");
        File fichero = new File(RUTA);
        String buscado = "";
        try(DataInputStream dis = new DataInputStream(new FileInputStream(fichero));) { // Lo pongo de otra manera tambien valida.
            codProducto = dis.readUTF();
            boolean encontrado = false;
            while(codProducto!=null){
                nombre = dis.readUTF();
                cantidad = dis.readUTF();
                descripcion = dis.readUTF();
                if (entrada.toLowerCase().equals(nombre.toLowerCase())){ // Así comparamos las 2 cadenas sin mayusculas.
                    buscado = codProducto + " " + nombre + " " + cantidad + " " + descripcion;
                    encontrado = true; // y con esta variable comprobamos si encontro o no encontro el producto.
                }
                codProducto = dis.readUTF(); // con esto sigue el bucle hasta que llegue a null.
                if(!encontrado){
                    buscado = "Producto no encontrado.";
                }
            }
        } catch (Exception e) {
            e.getMessage(); // Capturamos el error.
        }return buscado;
    }
            
    
}
