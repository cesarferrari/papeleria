/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author julio
 */
public class Conexion {
      Connection conectar=null;
      public Connection conectar(){
      /*  final String url="jdbc:mysql://192.168.1.73:3306/ecatepec_fisica1";
        final String root="victoria";
        final String password="1234";*/
       final String url="jdbc:mysql://localhost/ecatepec?serverTimezone=UTC";
        final String root="root";
        final String password="";
       try{
          Class.forName("com.mysql.cj.jdbc.Driver");
           conectar=DriverManager.getConnection(url, root, password);
          // JOptionPane.showMessageDialog(null,"exito");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
        
        return conectar;
    }
}
