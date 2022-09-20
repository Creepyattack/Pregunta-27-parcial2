/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ppbet
 */
public class estudiante extends persona{
    private int id;
    private String carnet;
    Conexion cn;
    
    public estudiante(){
        
    }
    
    public estudiante(int id, String carnet, String nombres, String apellidos, String direccion, String telefono, String genero, String email, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, genero, email, fecha_nacimiento);
        this.id = id;
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
     public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT id_estudiante as id ,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento FROM estudiantes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"Id","Carnet","Nombres","Apellido","Direccion","Telefono","Genero","Email","Nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[9];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("Carnet");
                datos[2] = consulta.getString("Nombres");
                datos[3] = consulta.getString("Apellidos");
                datos[4] = consulta.getString("Direccion");
                datos[5] = consulta.getString("Telefono");
                datos[6] = consulta.getString("Genero");
                datos[7] = consulta.getString("Email");
                datos[8] = consulta.getString("Fecha_Nacimiento");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error....... " + ex.getMessage());
        }
        return tabla;
    }
     
     
    @Override
     public void agregar(){
         try{
            PreparedStatement parametro;
            String query ="INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento) VALUES(?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getCarnet());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getGenero());
            parametro.setString(7, this.getEmail());
            parametro.setString(8, this.getFecha_nacimiento());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,"Registro Ingresado: " + Integer.toString(executar),"Agregar",JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error....."+ ex.getMessage());
        }
     }
     
    @Override
     public void actualizar(){
         try{
            PreparedStatement parametro;
            String query ="UPDATE estudiantes SET carnet = ?,nombres= ?,apellidos= ?,direccion= ?,telefono= ?,genero= ?,email= ?,fecha_nacimiento= ?" + "WHERE id_estudiante= ?;";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, this.getCarnet());
            parametro.setString(2, this.getNombres());
            parametro.setString(3, this.getApellidos());
            parametro.setString(4, this.getDireccion());
            parametro.setString(5, this.getTelefono());
            parametro.setString(6, this.getGenero());
            parametro.setString(7, this.getEmail());
            parametro.setString(8, this.getFecha_nacimiento());
            parametro.setInt(9, this.getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,"Registro Actualizado: " + Integer.toString(executar),"Actualizado",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error....."+ ex.getMessage());
        }
     }
     
    @Override
     public void eliminar(){
        try{
            PreparedStatement parametro;
            String query ="DELETE FROM estudiantes WHERE id_estudiante = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,"Registro Eliminado: " + Integer.toString(executar),"Eliminado",JOptionPane.INFORMATION_MESSAGE);
            
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error....."+ ex.getMessage());
        }  
     }
}
