
package vista;

import java.awt.Component;
import java.awt.List;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import modelo.*;
public class Clientes extends javax.swing.JFrame {

  Conexion con= new Conexion();
  Statement st;
  ResultSet rs;
  Connection cnx;
  DefaultTableModel model;
  String sql="";
    
    public Clientes() {
        initComponents();
         this.jTabbedPane2.setSelectedIndex(1);
    }
     private String code_prov(){
        Connection cnxr=con.conectar();
        String cadena="";
        try{
         st=cnxr.createStatement();
         rs=st.executeQuery("select identificacion from clientes where identificacion=");
        String number[]= new String[1];
        while(rs.next()) {
          number[0]=rs.getString("identificacion");
         
         limpiarcliente();
        }
        cnxr.close();
        }catch(Exception e){
           
        }
        
        
        return cadena;
    }
      public void empty(){
       
         if(this.txt_telefono.getText().isEmpty()){
            this.txt_telefono.setText("N/A");
        }
        
            if(this.area_obs.getText().isEmpty()){
            this.area_obs.setText("N/A");
        }
             if(this.txt_direccion.getText().isEmpty()){
            this.txt_direccion.setText("N/A");
        }
             
           
            
             
              
    }
      public void llenaTable(){
            if (this.radio_id.isSelected()) {
           String cons="select id_cliente,nombre,identificacion,direccion,telefono,observaciones from clientes where id_cliente="+this.txt_seleccion.getText();
            radioClientesId(cons);
            
        }else if(this.radio_nombre.isSelected()){
             String cons="select id_cliente,nombre,identificacion,direccion,telefono,observaciones from clientes where nombre='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
        }else if(this.radio_identificador.isSelected()){
             String cons="select id_cliente,nombre,identificacion,direccion,telefono,observaciones from clientes where identificacion="+this.txt_seleccion.getText();
            radioClientesId(cons);
        }else{
            String cons="select id_cliente,nombre,identificacion,direccion,telefono,observaciones from clientes ;";
            radioClientesId(cons);
        }
      }
public void actualizaCliente(){
    Connection cnx=con.conectar();
    try{
      PreparedStatement pst=cnx.prepareStatement("update clientes set nombre=?,direccion=?,telefono=?,"
              + "observaciones=? where id_cliente=?");
      
      pst.setString(1, this.txt_nombre.getText());
        
          pst.setString(2, this.txt_direccion.getText());
            pst.setString(3, this.txt_telefono.getText());
              pst.setString(4, this.area_obs.getText());
                pst.setInt(5, Integer.parseInt(this.txt_id.getText()));
                
                pst.execute();
                JOptionPane.showMessageDialog(null,"datos  actualizados");
                 
    }catch(NumberFormatException ex){
        JOptionPane.showMessageDialog(null,"ingrese como identificador un numero");
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        
    }
    
    
    
}
public int identificador(){
    int code=0;
    cnx=con.conectar();
    try{
        PreparedStatement pst=cnx.prepareStatement("select identificacion from clientes order by identificacion desc limit 1;");
        rs=pst.executeQuery();
        if(rs.next()){
            code=rs.getInt("identificacion");
        }
        cnx.close();
        code++;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
               try {
                   cnx.close();
               } catch (SQLException ex) {
                   Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    return code;
}
public String cliente(){
    String code="";
    cnx=con.conectar();
    try{
        PreparedStatement pst=cnx.prepareStatement("select nombre from clientes where nombre ='"+this.txt_nombre.getText()+"'");
        rs=pst.executeQuery();
        if(rs.next()){
            code=rs.getString("nombre");
        }
        cnx.close();
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
               try {
                   cnx.close();
               } catch (SQLException ex) {
                   Logger.getLogger(FRProducto.class.getName()).log(Level.SEVERE, null, ex);
               }
    }
    return code;
}
    public  void radioClientesId(String consulta){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
        model= new DefaultTableModel();
        String titulos[]={"id_cliente","nombre","identificacion","direccion","telefono","observaciones"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[6];
            while (rs.next()) {
               num[0]= rs.getString("id_cliente");
                num[1]=rs.getString("nombre");
                num[2]=rs.getString("identificacion");
                num[3]=rs.getString("direccion");
                num[4]=rs.getString("telefono");
                num[5]=rs.getString("observaciones");
                model.addRow(num);
            }
            this.jTable2.setModel(model);
            cnx.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
    }
    public void limpiarcliente(){
        this.txt_id.setText(null);
        this.txt_direccion.setText(null);
     this.txt_seleccion.setText("");
        this.txt_nombre.setText("");
        this.txt_telefono.setText("");
        this.area_obs.setText("");
        
        
    }
    public  boolean validarEmail(String email){
        
        Pattern patron =  Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher  matcher=patron.matcher(email);
        return matcher.find();
    }
           public void insertarCliente(){
             sql="insert into clientes(id_cliente,nombre,identificacion,direccion ,telefono,observaciones)values(?,?,?,?,?,?)";
                cnx=con.conectar();
                
               try{
               PreparedStatement pst=cnx.prepareStatement(sql);
               empty();
               pst.setString(1, this.txt_id.getText());
               pst.setString(2,this.txt_nombre.getText());
               pst.setInt(3,identificador());
               pst.setString(4,this.txt_direccion.getText());
               pst.setString(5, this.txt_telefono.getText());
               pst.setString(6, this.area_obs.getText());
                if(this.txt_nombre.getText().isEmpty()){
                         
                        JOptionPane.showMessageDialog(null,"  indique un cliente");
                   
                    }else if(cliente().equalsIgnoreCase(this.txt_nombre.getText())){
                        JOptionPane.showMessageDialog(null,"el cliente ya existe en la base de datos ");
                    }else{
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"datos ingresado correctamente");
                         limpiarcliente();
                   
                    }
                        
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                   System.out.println(e.toString());
               }
           }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_obs = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_seleccion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        radio_id = new javax.swing.JRadioButton();
        radio_nombre = new javax.swing.JRadioButton();
        radio_identificador = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));

        btn_nuevo.setBackground(new java.awt.Color(255, 255, 204));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/nuevo.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_guardar.setBackground(new java.awt.Color(255, 255, 204));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/GuardarTodo.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(255, 255, 204));
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/report.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 204));
        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/eliminar.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_salir.setBackground(new java.awt.Color(255, 255, 204));
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/salir_1.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setText("ID");

        jLabel2.setText("Nombre o Razon Social");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono ");

        jLabel6.setText("Observaciones");

        txt_id.setEditable(false);
        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_idMouseEntered(evt);
            }
        });

        txt_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_nombreFocusLost(evt);
            }
        });

        area_obs.setColumns(20);
        area_obs.setRows(5);
        jScrollPane1.setViewportView(area_obs);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(txt_telefono)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(60, 60, 60)))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_direccion)
                                        .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("nuevo/modificar", jPanel8);

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));

        txt_seleccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_seleccionKeyTyped(evt);
            }
        });

        jButton1.setText("Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        grupo1.add(radio_id);
        radio_id.setText("Id Cliente");
        radio_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_idActionPerformed(evt);
            }
        });

        grupo1.add(radio_nombre);
        radio_nombre.setText("Nombre o Razon social");

        grupo1.add(radio_identificador);
        radio_identificador.setText("identificador");
        radio_identificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_identificadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(radio_id)
                        .addGap(18, 18, 18)
                        .addComponent(radio_nombre)
                        .addGap(18, 18, 18)
                        .addComponent(radio_identificador)))
                .addGap(49, 49, 49)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_id)
                            .addComponent(radio_nombre)
                            .addComponent(radio_identificador))
                        .addGap(18, 18, 18)
                        .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTable2 =new javax.swing.JTable(){
            public boolean isCellEditable(int r,int c){
                return false;
            }
        };
        jTable2.setFocusable(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("buscar", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
       insertarCliente();
       limpiarcliente();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void radio_identificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_identificadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_identificadorActionPerformed

    private void radio_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_idActionPerformed
      
    }//GEN-LAST:event_radio_idActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      llenaTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseEntered
            int in=0;
           ArrayList<Integer> numeros= new ArrayList<>();
            try {
            Connection conexion =con.conectar();
            PreparedStatement pst1 =conexion.prepareStatement("select id_cliente from clientes");
            ResultSet rs1 =pst1.executeQuery();
           
               while (rs1.next()) {
                   numeros.add(Integer.parseInt(rs1.getString("id_cliente")));
                   
                }
                
                 in =numeros.size()+1;
                
           
        } catch (Exception ex) {
           
        }
      
               
              
                String identificador=String.valueOf(in);
                 this.txt_id.setText(identificador);
                this.txt_id.setEditable(false);
    }//GEN-LAST:event_txt_idMouseEntered

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpiarcliente();
        
           
        model.getDataVector().removeAllElements();
        this.jTable2.updateUI();
        
     

// Recorre todos los componentes dentro del contenedor principal
for (Component component : this.getComponents()) {
    // Verifica si es un JRadioButton
    if (component instanceof JRadioButton) {
        // Desmarca el JRadioButton
        JRadioButton radioButton = (JRadioButton) component;
        radioButton.setSelected(false);
    }
}
        
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
       int codigo = this.jTable2.getSelectedRow();
      String id=this.jTable2.getValueAt(codigo,0).toString();
      String nombre=this.jTable2.getValueAt(codigo,1).toString();
      
      String direccion=this.jTable2.getValueAt(codigo,3).toString();
      String telefono=this.jTable2.getValueAt(codigo,4).toString();
    
      String fabricacion=this.jTable2.getValueAt(codigo,5).toString();
      
      String identificacion=this.jTable2.getValueAt(codigo,2).toString();
      
        this.txt_id.setText(id);
        this.txt_nombre.setText(nombre);
        this.txt_direccion.setText(direccion);
        this.txt_telefono.setText(telefono);
        
        this.area_obs.setText(fabricacion);
        
      this.jTabbedPane2.setSelectedIndex(0);
        
        
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        this.btn_guardar.setEnabled(true);
       this.btn_modificar.setEnabled(true);
       this.jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        actualizaCliente();
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_nombreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreFocusLost

    private void txt_seleccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seleccionKeyTyped
         if(evt.getKeyChar()==VK_ENTER){
       
       llenaTable();
     }
    }//GEN-LAST:event_txt_seleccionKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_obs;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton radio_id;
    private javax.swing.JRadioButton radio_identificador;
    private javax.swing.JRadioButton radio_nombre;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_seleccion;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
