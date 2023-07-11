/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;
public class CtrlLogin implements ActionListener {
   private ConsultasSQL sql ;
    private Login login;
    private Productos pr;
    private Modelo mod;
    private Principal principal;
    private boolean ban;
    public CtrlLogin(){
        
    }
    public CtrlLogin(ConsultasSQL sql,Login login,Productos pr,Modelo mod,Principal principal){
        this.login=login;
        this.sql=sql;
        this.pr=pr;
        this.mod=mod;
        login.btn_ingresarPrincipal.addActionListener(this);
        principal.m_item_salir.addActionListener(this);
      
        
    }
    public void iniciar(){
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==login.btn_ingresarPrincipal) {
            sql.setSQLMostrar("select*from vendedor");
          try   {
                  pr.setUsuario(login.txt_usuario.getText());
        pr.setContraseña(String.valueOf(login.txt_password.getPassword()));
        pr.setRol(login.cbx_admin.getSelectedItem().toString());
              if (login.txt_password.equals("")) {
                  System.out.println("passs");
              }
              if(pr.getRol().equalsIgnoreCase("administrador")){
                  ban=false;
              }else{
                  ban=true;
              }
              if (mod.buscar(pr)==true) {
                  login.setVisible(false);
                  new Principal(ban).setVisible(true);
                  
              }else{
                  JOptionPane.showMessageDialog(null, "acceso denegado","mensaje error",JOptionPane.ERROR_MESSAGE);
              }
             
          }catch(NullPointerException ev){
              JOptionPane.showMessageDialog(null,"no puede dejar campos vacios ");
                      
          }
         }
      
       
    }
}
