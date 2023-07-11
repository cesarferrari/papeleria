
package controlador;

import modelo.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import vista.FrCalculadora;


public class Controladorcalc implements ActionListener{
  //  private Modelo mod;
    private Calculator_num pr;
    private FrCalculadora frm;
    private double contador,numSuma,numMultiplica,numResta,numDivide,numSiguiente,numResultado;
   private  String operacion ;
   public Controladorcalc(){
    
}
    public Controladorcalc (Calculator_num  pr,FrCalculadora frm){
       
        this.pr=pr;
        this.frm=frm;
        frm.btn_0.addActionListener(this);
        frm.btn_1.addActionListener(this);
        frm.btn_2.addActionListener(this);
    frm.btn_3.addActionListener(this);
    frm.btn_4.addActionListener(this);
    frm.btn_5.addActionListener(this);
    frm.btn_6.addActionListener(this);
    frm.btn_7.addActionListener(this);
    frm.btn_8.addActionListener(this);
    frm.btn_9.addActionListener(this);
    frm.btn_punto.addActionListener(this);
    frm.btn_c.addActionListener(this);
    frm.btn_multiplica.addActionListener(this);
    frm.btn_divide.addActionListener(this);
    frm.btn_menos.addActionListener(this);
    frm.btn_mas.addActionListener(this);
    frm.btn_res.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==frm.btn_0) {
            frm.txt_r.setText(frm.txt_r.getText()+"0");
        }
         if (e.getSource()==frm.btn_1) {
            frm.txt_r.setText(frm.txt_r.getText()+"1");
        }
          if (e.getSource()==frm.btn_2) {
            frm.txt_r.setText(frm.txt_r.getText()+"2");
        }
           if (e.getSource()==frm.btn_3) {
            frm.txt_r.setText(frm.txt_r.getText()+"3");
        }
            if (e.getSource()==frm.btn_4) {
            frm.txt_r.setText(frm.txt_r.getText()+"4");
        }
             if (e.getSource()==frm.btn_5) {
            frm.txt_r.setText(frm.txt_r.getText()+"5");
        }
              if (e.getSource()==frm.btn_6) {
            frm.txt_r.setText(frm.txt_r.getText()+"6");
        }
               if (e.getSource()==frm.btn_7) {
            frm.txt_r.setText(frm.txt_r.getText()+"7");
        }
                if (e.getSource()==frm.btn_8) {
            frm.txt_r.setText(frm.txt_r.getText()+"8");
        }
                 if (e.getSource()==frm.btn_9) {
            frm.txt_r.setText(frm.txt_r.getText()+"9");
        }
                       if (e.getSource()==frm.btn_punto) {
            frm.txt_r.setText(frm.txt_r.getText()+".");
        }
                  if (e.getSource()==frm.btn_c) {
            frm.txt_r.setText("");
        }
                   if (e.getSource()==frm.btn_mas) {
                contador++;
                operacion="sumar";
 
                cont();
        }
                   if (e.getSource()==frm.btn_menos) {
                 contador++;
                operacion="restar";
                       if (frm.txt_r.getText().equalsIgnoreCase("")) {
                           frm.txt_r.setText("-");
                           contador=0;
                       }else {
                           cont();
                       }
                
        }
                   if (e.getSource()==frm.btn_multiplica) {
                  contador++;
                  operacion="multiplicar";
 
                cont();
        }
                   if (e.getSource()==frm.btn_divide) {
                 contador++;
                 operacion="dividir";
 
                cont();
        }
                   if (e.getSource()==frm.btn_res) {
            contador++;
            resultado();
        }
    }
    public void iniciar(){
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
    }
     public void cont(){
        if (contador==1&&operacion.equalsIgnoreCase("multiplicar")) {
           // JOptionPane.showMessageDialog(null,"el contador llego a "+contador);
        numMultiplica=Double.parseDouble(frm.txt_r.getText());
        frm.txt_r.setText("");
      
        }else if(contador==1&&operacion.equalsIgnoreCase("sumar")){
             // JOptionPane.showMessageDialog(null,"el contador llego a "+contador);
        numSuma=Double.parseDouble(frm.txt_r.getText());
        frm.txt_r.setText("");
       }else if(contador==1&&operacion.equalsIgnoreCase("restar")){
             // JOptionPane.showMessageDialog(null,"el contador llego a "+contador);
        numResta=Double.parseDouble(frm.txt_r.getText());
        frm.txt_r.setText("");
       }else if(contador==1&&frm.txt_r.getText().equalsIgnoreCase("")&&operacion.equalsIgnoreCase("restar")){
             // JOptionPane.showMessageDialog(null,"el contador llego a "+contador);
            
      numResta=Double.parseDouble(frm.txt_r.getText());
        frm.txt_r.setText("");
       }else if(contador==1&&operacion.equalsIgnoreCase("dividir")){
             // JOptionPane.showMessageDialog(null,"el contador llego a "+contador);
        numDivide=Double.parseDouble(frm.txt_r.getText());
        frm.txt_r.setText("");
       }
        if (contador==2&&operacion.equalsIgnoreCase("sumar")) {
             JOptionPane.showMessageDialog(null, "ya ha sido guardado el num ingrese el siguiente ");
            contador=1;
           
        } if(contador==2&&operacion.equalsIgnoreCase("multiplicar")){
             JOptionPane.showMessageDialog(null, "ya ha sido guardado el num ingrese el siguiente ");
            contador=1;
            
        }
          if(contador==2&&operacion.equalsIgnoreCase("restar")){
             JOptionPane.showMessageDialog(null, "ya ha sido guardado el num ingrese el siguiente ");
            contador=1;
            
        }
           if(contador==2&&operacion.equalsIgnoreCase("dividir")){
             JOptionPane.showMessageDialog(null, "ya ha sido guardado el num ingrese el siguiente ");
            contador=1;
            
        }
    }
      public void resultado(){
        if (contador==2&&operacion.equalsIgnoreCase("multiplicar")) {
          numSiguiente=Integer.parseInt(frm.txt_r.getText());
          numResultado=numMultiplica*numSiguiente;
          frm.txt_r.setText("");
        }else if(contador==2&&operacion.equalsIgnoreCase("sumar")){
            numSiguiente=Integer.parseInt(frm.txt_r.getText());
          numResultado=numSuma+numSiguiente; 
          frm.txt_r.setText("");
        }else if(contador==2&&operacion.equalsIgnoreCase("restar")){
            numSiguiente=Integer.parseInt(frm.txt_r.getText());
          numResultado=numResta-numSiguiente; 
          frm.txt_r.setText("");
        }else if(contador==2&&operacion.equalsIgnoreCase("dividir")){
            numSiguiente=Integer.parseInt(frm.txt_r.getText());
          numResultado=numDivide/numSiguiente; 
          frm.txt_r.setText("");
        }
       
        frm.txt_r.setText(Double.toString(numResultado));
        contador=0;
    }
}
