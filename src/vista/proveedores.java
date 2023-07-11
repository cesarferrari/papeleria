/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.List;
import java.sql.CallableStatement;
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
import javax.swing.table.DefaultTableModel;
import modelo.*;
public class proveedores extends javax.swing.JFrame {

  Conexion con= new Conexion();
  Statement st;
  ResultSet rs;
  Connection cnx;
  DefaultTableModel model;
  String sql="";
    
    public proveedores() {
        initComponents();
        
    }
    public int identificador(){
    int code=0;
    cnx=con.conectar();
    try{
        PreparedStatement pst=cnx.prepareStatement("select identificacion from proveedores order by identificacion desc limit 1;");
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
        PreparedStatement pst=cnx.prepareStatement("select nombre from proveedores where nombre ='"+this.txt_nombre.getText()+"'");
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
        String titulos[]={"idproveedor","nombre","direccion","telefono","email","fabricaion","celular","cuenta1","cuenta2","estado","identificacion"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[11];
            while (rs.next()) {
               num[0]= rs.getString("idproveedor");
                num[1]=rs.getString("nombre");
                 num[2]= rs.getString("direccion");
                num[3]=rs.getString("telefono");
                 num[4]= rs.getString("email");
                num[5]=rs.getString("fabricacion");
                 num[6]= rs.getString("celular");
                num[7]=rs.getString("cuenta1");
                 num[8]= rs.getString("cuenta2");
                num[9]=rs.getString("estado");
                 num[10]= rs.getString("identificacion");
                
               
                model.addRow(num);
            }
            this.jTable2.setModel(model);
            cnx.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
           
        }
    }
    public  void radioClientes(String consulta){
     
      //  sql="select id_cliente,nombre,identificacion,direccion,telefono from clientes where id_cliente=1";
        model= new DefaultTableModel();
        String titulos[]={"idproveedor","nombre","direccion","telefono","email","fabricaion","celular","cuenta1","cuenta2","estado","identificacion"};
        model.setColumnIdentifiers(titulos);
        cnx=con.conectar();
        try{
       // st=cnx.createStatement();
       PreparedStatement pst= cnx.prepareStatement(consulta);
        rs=pst.executeQuery();
        String num[]=new String[11];
            while (rs.next()) {
               num[0]= rs.getString("idproveedor");
                num[1]=rs.getString("nombre");
                 num[2]= rs.getString("direccion");
                num[3]=rs.getString("telefono");
                 num[4]= rs.getString("email");
                num[5]=rs.getString("fabricacion");
                 num[6]= rs.getString("celular");
                num[7]=rs.getString("cuenta1");
                 num[8]= rs.getString("cuenta2");
                num[9]=rs.getString("estado");
                 num[10]= rs.getString("identificacion");
                
               
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
      this.txt_telefono.setText(null);
       this.txt_nombre.setText(null);
       this.txt_celular.setText("");
       this.txt_cuenta1.setText(null);
       this.txt_cuenta2.setText(null);
       this.txt_direccion.setText(null);
      
       this.txt_email.setText("");
       this.area_obs.setText(null);
        
        
    }
    public void actualizar_proveedor(){
        Connection cn=con.conectar();
       String sqlupdate="update proveedores set nombre=?,direccion=?,telefono=?,email=?,fabricacion=?,celular=?,"
               + "cuenta1=?,cuenta2=?,estado=? where idProveedor=?";
        String radio="";
        try{
           PreparedStatement cst=cn.prepareStatement(sqlupdate);
            cst.setString(1,this.txt_nombre.getText());
            cst.setString(2,this.txt_direccion.getText());
            cst.setString(3,this.txt_telefono.getText());
            cst.setString(4,this.txt_email.getText());
            cst.setString(5,this.area_obs.getText());
            cst.setString(6,this.txt_celular.getText());
            cst.setInt(7,Integer.parseInt(this.txt_cuenta1.getText()));
         cst.setInt(8,Integer.parseInt(this.txt_cuenta2.getText()));
          
            if (radio_activo.isSelected()) {
                radio="activo";
            }else if(radio_inactivo.isSelected()){
                radio="inactivo";
            }
              cst.setString(9,radio);
               cst.setInt(10,Integer.parseInt(this.txt_id.getText()));
                    cst.execute();
                    JOptionPane.showMessageDialog(null,"datos actualizados");
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"ingrese en formato de numeros la cuenta o cuentas ");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    } 
 
     /*  private String code_prov(){
        Connection cnxr=con.conectar();
        String cadena="";
        try{
         st=cnxr.createStatement();
         rs=st.executeQuery("select identificacion from proveedores where identificacion="+this.txt_identificador.getText());
        String number[]= new String[1];
        while(rs.next()) {
          number[0]=rs.getString("identificacion");
         JOptionPane.showMessageDialog(null,"el id ya es¿xiste en la base de datos ");
         limpiarcliente();
        }
        cnxr.close();
        }catch(Exception e){
           
        }
        
        
        return cadena;
    }*/
    public void empty(){
        if(this.txt_celular.getText().isEmpty()){
            this.txt_celular.setText("N/A");
        }
         if(this.txt_telefono.getText().isEmpty()){
            this.txt_telefono.setText("N/A");
        }
         if(this.txt_email.getText().isEmpty()){
            this.txt_celular.setText("N/A");
        }
          if(this.txt_cuenta1.getText().isEmpty()){
         this.txt_cuenta1.setText("0");
        }
           if(this.txt_cuenta2.getText().isEmpty()){
         this.txt_cuenta2.setText("0");
        }
            if(this.area_obs.getText().isEmpty()){
            this.area_obs.setText("N/A");
        }
             if(this.txt_direccion.getText().isEmpty()){
            this.txt_direccion.setText("N/A");
        }
              if(this.txt_email.getText().isEmpty()){
            this.txt_email.setText("N/A");
        }
              
             
              
    }
    public  boolean validarEmail(String email){
        
        Pattern patron =  Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher  matcher=patron.matcher(email);
        return matcher.find();
    }
    
           public void insertarProveedor(){
             sql="insert into proveedores(idproveedor,nombre,identificacion,direccion ,telefono,celular,email,estado,cuenta1,cuenta2,fabricacion)values"
                     + "(?,?,?,?,?,?,?,?,?,?,?)";
             String radio="";
                cnx=con.conectar();
               try{
               PreparedStatement pst=cnx.prepareStatement(sql);
               empty();
               pst.setString(1, this.txt_id.getText());
               pst.setString(2,this.txt_nombre.getText());
                 pst.setInt(3,identificador());
                      pst.setString(4,this.txt_direccion.getText());
                   pst.setString(5, this.txt_telefono.getText());
                pst.setString(6, this.txt_celular.getText());
                pst.setString(7, this.txt_email.getText());
                   if (this.radio_activo.isSelected()) {
                       radio="activo";
                   }else if(this.radio_inactivo.isSelected()){
                         radio="inactivo";
                   }else if(radio.equalsIgnoreCase("")){
                       radio="activo";
                   }
                   pst.setString(8, radio);
                 pst.setString(9,this.txt_cuenta1.getText());
               pst.setString(10, this.txt_cuenta2.getText());
               pst.setString(11, this.area_obs.getText());
                   
                    if(cliente().equalsIgnoreCase(this.txt_nombre.getText())){
                         // limpiarcliente();
                        JOptionPane.showMessageDialog(null,"  proveedor  ya existente");
                    } else{
                        
                   pst.executeUpdate();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_seleccion = new javax.swing.JTextField();
        btn_reporte = new javax.swing.JButton();
        radio_id = new javax.swing.JRadioButton();
        radio_nombre = new javax.swing.JRadioButton();
        radio_identificador = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
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
        jLabel7 = new javax.swing.JLabel();
        txt_celular = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        radio_activo = new javax.swing.JRadioButton();
        radio_inactivo = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_cuenta1 = new javax.swing.JTextField();
        txt_cuenta2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Proveedores");
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
        btn_guardar.setEnabled(false);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(255, 255, 204));
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/report.png"))); // NOI18N
        btn_modificar.setText("Modificar");
        btn_modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_modificar.setEnabled(false);
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
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));

        btn_reporte.setText("Reporte");
        btn_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporteActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_id);
        radio_id.setText("Id Proveedor");
        radio_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_idActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_nombre);
        radio_nombre.setText("Nombre o Razon social");

        buttonGroup1.add(radio_identificador);
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
                        .addGap(28, 28, 28)
                        .addComponent(radio_id)
                        .addGap(18, 18, 18)
                        .addComponent(radio_nombre)
                        .addGap(18, 18, 18)
                        .addComponent(radio_identificador))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(600, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_id)
                            .addComponent(radio_nombre)
                            .addComponent(radio_identificador))
                        .addGap(18, 18, 18)
                        .addComponent(txt_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTable2 = new javax.swing.JTable(){
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
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("buscar", jPanel7);

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setText("ID");

        jLabel2.setText("Nombre o Razon Social");

        jLabel4.setText("Direccion");

        jLabel5.setText("Telefono ");

        jLabel6.setText("Observaciones");

        txt_id.setEditable(false);
        txt_id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_idMouseEntered(evt);
            }
        });

        txt_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txt_direccion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txt_telefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        area_obs.setColumns(20);
        area_obs.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        area_obs.setRows(5);
        jScrollPane1.setViewportView(area_obs);

        jLabel7.setText("celular");

        txt_celular.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jLabel8.setText("Email");

        txt_email.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });

        jLabel9.setText("Estado");

        grupo1.add(radio_activo);
        radio_activo.setText("activo");

        grupo1.add(radio_inactivo);
        radio_inactivo.setText("inactivo");

        jLabel10.setText("no.cuenta");

        jLabel11.setText("no. cuenta");

        txt_cuenta1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txt_cuenta2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_email)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_celular, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(radio_activo)
                                        .addGap(43, 43, 43)
                                        .addComponent(radio_inactivo))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txt_cuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txt_cuenta2)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_direccion)))))
                .addContainerGap(557, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(77, 77, 77)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radio_activo)
                    .addComponent(radio_inactivo))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txt_cuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cuenta2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane2.addTab("nuevo/modificar", jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1155, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
       insertarProveedor();
       limpiarcliente();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void radio_identificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_identificadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_identificadorActionPerformed

    private void radio_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_idActionPerformed
      
    }//GEN-LAST:event_radio_idActionPerformed

    private void btn_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporteActionPerformed
        if (this.radio_id.isSelected()) {
           String cons="select *from proveedores where idproveedor="+this.txt_seleccion.getText();
            radioClientesId(cons);
            this.txt_seleccion.setText("");
            
        }else if(this.radio_nombre.isSelected()){
             String cons="select * from proveedores where nombre='"+this.txt_seleccion.getText()+"'";
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else if(this.radio_identificador.isSelected()){
             String cons="select * from proveedores where identificacion="+this.txt_seleccion.getText();
            radioClientesId(cons);
            this.txt_seleccion.setText("");
        }else {
             String cons="select * from proveedores ";
            radioClientes(cons);
            this.txt_seleccion.setText("");
        }
    }//GEN-LAST:event_btn_reporteActionPerformed

    private void txt_idMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseEntered
            int in=0;
           ArrayList<Integer> numeros= new ArrayList<>();
            try {
            Connection conexion =con.conectar();
            PreparedStatement pst1 =conexion.prepareStatement("select idproveedor from proveedores");
            ResultSet rs1 =pst1.executeQuery();
           
               while (rs1.next()) {
                   numeros.add(Integer.parseInt(rs1.getString("idproveedor")));
                   
                }
                
                 in =numeros.size()+1;
                
           
        } catch (Exception ex) {
           
        }
      
               
              
                String identificador=String.valueOf(in);
                 this.txt_id.setText(identificador);
                this.txt_id.setEditable(false);
    }//GEN-LAST:event_txt_idMouseEntered

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
       this.btn_guardar.setEnabled(true);
       this.btn_modificar.setEnabled(true);
       this.jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
       limpiarcliente();
          this.jTabbedPane2.setSelectedIndex(1);
          grupo1.clearSelection();
          buttonGroup1.clearSelection();
            model.getDataVector().removeAllElements();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
      dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
       int codigo = this.jTable2.getSelectedRow();
      String id=this.jTable2.getValueAt(codigo,0).toString();
      String nombre=this.jTable2.getValueAt(codigo,1).toString();
      String direccion=this.jTable2.getValueAt(codigo,2).toString();
      String telefono=this.jTable2.getValueAt(codigo,3).toString();
      String email=this.jTable2.getValueAt(codigo,4).toString();
      String fabricacion=this.jTable2.getValueAt(codigo,5).toString();
      String celular=this.jTable2.getValueAt(codigo,6).toString();
      String cuenta1=this.jTable2.getValueAt(codigo,7).toString();
      String cuenta2=this.jTable2.getValueAt(codigo,8).toString();
       String estado=this.jTable2.getValueAt(codigo, 9).toString();
      String identificacion=this.jTable2.getValueAt(codigo,10).toString();
      
        this.txt_id.setText(id);
        this.txt_nombre.setText(nombre);
        this.txt_direccion.setText(direccion);
        this.txt_telefono.setText(telefono);
        this.txt_email.setText(email);
        this.area_obs.setText(fabricacion);
        this.txt_celular.setText(celular);
        this.txt_cuenta1.setText(cuenta1);
        this.txt_cuenta2.setText(cuenta2);
        
         this.jTabbedPane2.setSelectedIndex(1);
        
        
        
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
actualizar_proveedor();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
         if (validarEmail(this.txt_email.getText())) {
            
        }else{
             JOptionPane.showMessageDialog(null, "el formato de correo electronico es incorrecto");
             this.txt_email.setText("");
         }
    }//GEN-LAST:event_txt_emailFocusLost

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
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_obs;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JButton btn_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup grupo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton radio_activo;
    private javax.swing.JRadioButton radio_id;
    private javax.swing.JRadioButton radio_identificador;
    private javax.swing.JRadioButton radio_inactivo;
    private javax.swing.JRadioButton radio_nombre;
    private javax.swing.JTextField txt_celular;
    private javax.swing.JTextField txt_cuenta1;
    private javax.swing.JTextField txt_cuenta2;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_seleccion;
    private javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables
}
