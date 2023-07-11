/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papel;


import controlador.*;
import modelo.Calculator_num;

import modelo.ConsultasSQL;
import modelo.Productos;
import modelo.Modelo;
import vista.*;

/**
 *
 * @author julio
 */
public class Papel {

   
    public static void main(String[] args) {
   ConsultasSQL sql=new ConsultasSQL();
  Login login = new Login() ;
   Productos pr = new Productos();
   Modelo mod= new Modelo();
  Principal principal= new Principal();
  Calculator_num n= new Calculator_num();
  FrCalculadora cal = new FrCalculadora();
 
        
        /*   Modelo model= new Modelo();
       Oficina of= new Oficina();
       Productos pr= new Productos();
       
       Office ofi=new Office(model,pr,of);
       ofi.iniciar();
       of.setVisible(true);*/
  /* Principal paper = new Principal();
    paper.setVisible(true);
   paper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
  
   CtrlLogin vista =new CtrlLogin(sql,login,pr,mod,principal);
   vista.iniciar();
   login.setVisible(true);
 
/* Controladorcalc cntrl= new Controladorcalc(n,cal);
  cal.setVisible(true);*/


  /* CtrlProductos prod=new CtrlProductos( pr, fr, mod,sql);
 prod.iniciar();
    fr.setVisible(true);*/
  
    }
    
}
