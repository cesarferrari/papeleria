/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_0;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.*;


/**
 *
 * @author julio
 */
public class CompraX extends javax.swing.JFrame {
Conexion con = new Conexion();
Modelo m= new Modelo();
public static String user="";
public static int cliente=1;

   DefaultTableModel mod;
   double resultado;
   Caja bx;
   ArrayList<Integer>number;
     ArrayList<String>chain;
     LinkedList<String>products;
     int filas =0;
    public CompraX() {
        initComponents();
         mod= new DefaultTableModel();
        String titulos[]={"codigo producto","cantidad","producto","precio producto"};
        mod.setColumnIdentifiers(titulos);
       String numeros[]=new String[4];
       numeros[0]=txt_nombre.getText();
       numeros[1]=txt_total.getText();
       numeros[2]=txt_cantidad.getText();
       numeros[3]=txt_codigo.getText();
       
      
       this.Tabla.setModel(mod);
        
    }
  
    public String code(){
        String sql="select* from productos1";
        String valido="";
        String producto="";
        String stock="";
        String precio="";
        Connection cnx=con.conectar();
       
 try{
     Statement st=(Statement) cnx.createStatement();
     ResultSet rs=st.executeQuery("select  codigo,nombre,stock,precioCompra from productos1 where codigo="+this.txt_codigo.getText());
     String numero[]= new String[4];    
      
     while(rs.next()){
         valido= numero[0]=rs.getString("codigo");
         producto=numero[1]=rs.getString("nombre");
                 stock= numero[2]=rs.getString("stock");
                  precio=numero[3]=rs.getString("precioCompra");
          this.txt_nombre.setText("");
        this.txt_stock.setText(stock);
              this.txt_precio.setText(precio);
                this.txt_nombre.setText(producto);
                
                this.txt_nombre.setEditable(false);
                 this.txt_precio.setEditable(false);
            
                   this.txt_stock.setEditable(false);
     }
 }catch(Exception e){
     
 }
 return valido;
    }
    public void genera_venta(){
        
           int in=0;
           ArrayList<Integer> numeros= new ArrayList<>();
           
            try {
            Connection conexion =con.conectar();
            PreparedStatement pst1 =conexion.prepareStatement("select codigo from compra order by  codigo  desc limit 1;");
            ResultSet rs1 =pst1.executeQuery();
           
               while (rs1.next()) {
                   numeros.add(Integer.parseInt(rs1.getString("codigo")));
                    }
                in =numeros.get(numeros.size()-1)+1;
                 }catch (Exception ex) {
           }
       String identificador=String.valueOf(in);
                 this.txt_noVenta.setText(identificador);
                this.txt_noVenta.setEditable(false);
    }
      public void insertaDetalleCompra(){
        Conexion cn = new Conexion();
        Connection cnx=cn.conectar();
        for (int i = 0; i < this.Tabla.getRowCount(); i++) {
                try{
            PreparedStatement pst=cnx.prepareStatement("insert into detalle_compra(codigo_producto,cantidad,producto,precio,fecha)values(?,?,?,?,?)");
            pst.setString(1, Tabla.getValueAt(i, 0).toString());
              pst.setString(2, Tabla.getValueAt(i, 1).toString());
                pst.setString(3, Tabla.getValueAt(i, 2).toString());
                  pst.setString(4, Tabla.getValueAt(i, 3).toString());
                    pst.setString(5, this.txt_fecha.getText());
                    pst.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
    try {
        cnx.close();
    } catch (SQLException ex) {
        Logger.getLogger(CompraX.class.getName()).log(Level.SEVERE, null, ex);
    }
           
    }
    public String vta(){
        String vta="";
        int select=this.Tabla.getRowCount();
        ArrayList<String> lista=new ArrayList<>();
        for (int i = 0; i < select; i++) {
            lista.add("/"+this.Tabla.getValueAt(i,0).toString()+"/"+this.Tabla.getValueAt(i,1).toString()+"/"+this.Tabla.getValueAt(i,2).toString()+"/"+this.Tabla.getValueAt(i,3).toString()+"!");
     // vta="/"+this.txt_codigo.getText()+"/"+prog+"/"+this.txt_nombre.getText()+"/"+this.txt_precio.getText()+"!";
        }
        for (int i = 0; i < select; i++) {
           
             vta=vta+" "+lista.get(i);
        }
      
        return vta;
    }
public int codigoCompra(){
    int code=0;
    try{
        Connection cnx=con.conectar();
        PreparedStatement pst=cnx.prepareStatement("select codigo from compra order by  codigo  desc limit 1;");
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            code=Integer.parseInt(rs.getString("codigo"));
        }
        code++;
    }catch(Exception e){
        
    }
    return code;
}
   public void  insertaVenta(){
       Connection cnx=con.conectar();
       String sql="insert into compra(id_proveedor,empleado,productos,total,codigo,fecha)values(?,?,?,?,?,?)";
       try{
           PreparedStatement pst=cnx.prepareStatement(sql);
           pst.setInt(1,cliente);
            pst.setString(2, this.txt_serie.getText());
             pst.setString(3, vta());
              pst.setString(4, this.txt_subtotal.getText());
               pst.setString(5, this.txt_noVenta.getText());
                pst.setString(6, this.txt_fecha.getText());
                
                int val=pst.executeUpdate();
                if(val>0){
                    
                }else{
                    JOptionPane.showMessageDialog(null,"no tienes artuculos para regitrar");
                }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       
       
   }
   public void clean(){
         this.txt_subtotal.setText("");
        this.txt_total.setText("");
        this.txt_noVenta.setText("");
        this.txt_importe.setText("");
        this.txt_cambio.setText("");
        this.txt_valor_venta.setText("");
        this.txt_total_aPagar.setText("");
        this.txt_descuento.setText("");
        this.btn_importe.setEnabled(false);
        this.btn_recibo.setEnabled(false);
        this.btn_generar_venta.setEnabled(false);
        this.btn_quitar.setEnabled(false);
        this.btn_restar.setEnabled(false);
        this.btn_cancelar.setEnabled(false);
       
        this.btn_agregar.setEnabled(false);
        this.btn_cliente.setEnabled(false);
        this.btn_descuento.setEnabled(false);
   }
   public String  agrega_id(){
       
     
       Connection c=con.conectar();
       String id="";
       String query="select id, codigo from productos1 where codigo='"+this.txt_codigo.getText()+"'";
       try{
       Statement st=c.createStatement();
       ResultSet rs=st.executeQuery(query);
           while (rs.next()) {
              id= rs.getString("id");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
       return id;
   }
   public void agrega_articulo(){
         double resultado=0;
         int sobrante=0;
       double Dprecio=0.0;
       
      try{
               String cant=txt_cantidad.getText();
       String precio=txt_precioProveedor.getText();
       String stock=txt_stock.getText();
       double Dstock=Double.parseDouble(stock);
       double  Dcant=Double.parseDouble(cant);
        Dprecio=Double.parseDouble(precio);
     
       sobrante=(int) (Dstock+Dcant);
            
                Connection cn=con.conectar();
                PreparedStatement pst=cn.prepareStatement("update productos1 set stock=? where codigo=?");
                pst.setInt(1, sobrante );
                pst.setString(2,this.txt_codigo.getText() );
                pst.execute();
                
               
                
                resultado=Dcant*Dprecio;
                this.txt_total.setText(""+resultado);
                String numeros[]= new String[5];
                
                numeros[0]=this.txt_codigo.getText();
                numeros[1]=cant;
                numeros[2]=txt_nombre.getText();
                numeros[3]=txt_total.getText();
             
                mod.addRow(numeros);
                this.txt_codigo.setText("");
                this.txt_nombre.setText("");
                this.txt_precio.setText("");
                 this.txt_precioProveedor.setText("");
                this.txt_stock.setText("");
                this.txt_cantidad.setText("");
                 actualiza_precio();
                }catch(NumberFormatException e){
                 JOptionPane.showMessageDialog(null,"ingrese un precio proveedor");
             }catch(Exception e){
                 
             }
             
      this.btn_cancelar.setEnabled(true);
      this.btn_importe.setEnabled(true);
      this.btn_generar_venta.setEnabled(true);
      this.btn_recibo.setEnabled(true);
      this.btn_descuento.setEnabled(true);
    genera_venta();
     this.txt_cambio.setText("");
        this.txt_importe.setText("");
        this.txt_descuento.setText("");
        
   }
   public void productoNuevo(){
          if (code().equalsIgnoreCase("")) {
         //   JOptionPane.showMessageDialog(null,"ingrese un codigo de producto existente");
            this.txt_codigo.setText("");
              int jp= JOptionPane.showConfirmDialog(this,"ingrese un codigo existente","confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (jp==JOptionPane.YES_OPTION) {
       FRProducto1 fr= new FRProducto1();
       fr.setVisible(true);
        }else if(jp==JOptionPane.NO_OPTION){
             JOptionPane.showMessageDialog(null, "ok  continuar");
        }
     
        }else{
           
        }
   }
   public void restaura(){
        int tot=this.Tabla.getRowCount();
        int rest=0;
        try{
        
         Connection cn=con.conectar();
                PreparedStatement pst=cn.prepareStatement("update productos1 set stock=? where codigo=?");
            
               for (int i = 0; i < tot; i++) {
                 rest=resto(Integer.parseInt(this.Tabla.getValueAt(i, 0).toString()))-Integer.parseInt(this.Tabla.getValueAt(i, 1).toString());
                 pst.setInt(1,rest );
                pst.setString(2,  this.Tabla.getValueAt(i, 0).toString() );
                pst.execute();
            }
         
       
       
    }catch(ArrayIndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(null,"debe de seleccionar un producto de la tabla a eliminar ");
    }catch(Exception e){
        
    }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btn_cliente = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JTextField();
        cbx_documento = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        txt_precioProveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_nuevoProducto = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        btn_agregar = new javax.swing.JButton();
        btn_restar = new javax.swing.JButton();
        btn_quitar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();
        txt_noVenta = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_generar_venta = new javax.swing.JButton();
        btn_importe = new javax.swing.JButton();
        btn_recibo = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_descuento = new javax.swing.JButton();
        txt_subtotal = new javax.swing.JTextField();
        txt_total_aPagar = new javax.swing.JTextField();
        txt_descuento = new javax.swing.JTextField();
        txt_valor_venta = new javax.swing.JTextField();
        txt_cambio = new javax.swing.JTextField();
        txt_importe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(51, 255, 204));

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setAutoscrolls(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 51));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Detalle de compra");
        jLabel8.setAutoscrolls(true);

        txt_proveedor.setEditable(false);
        txt_proveedor.setText("Proveedor general");
        txt_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_proveedorActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Proveedor");
        jLabel9.setAutoscrolls(true);

        btn_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/lupa.png"))); // NOI18N
        btn_cliente.setEnabled(false);
        btn_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clienteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("documento");
        jLabel10.setAutoscrolls(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("fecha");
        jLabel11.setAutoscrolls(true);

        txt_fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        cbx_documento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cbx_documento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "boleta", "factura", " " }));
        cbx_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_documentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)))
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(79, 79, 79))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(3, 3, 3)
                                .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        Tabla= new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        Tabla.setFocusable(false);
        Tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla);

        jPanel5.setBackground(new java.awt.Color(255, 255, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Detalle producto");
        jLabel12.setAutoscrolls(true);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("codigo");
        jLabel13.setAutoscrolls(true);

        txt_codigo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_codigoFocusLost(evt);
            }
        });
        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });
        txt_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codigoKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("nombre");
        jLabel14.setAutoscrolls(true);

        txt_nombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("stock");
        jLabel15.setAutoscrolls(true);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("precio");
        jLabel16.setAutoscrolls(true);

        txt_stock.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt_precio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jCheckBox1.setText("automatic");

        txt_precioProveedor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Precio proveedor");

        btn_nuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/lupa.png"))); // NOI18N
        btn_nuevoProducto.setEnabled(false);
        btn_nuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(89, 89, 89)
                .addComponent(jLabel16)
                .addGap(85, 85, 85))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_nuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12)
                                .addGap(52, 52, 52)
                                .addComponent(jCheckBox1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel14))
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_precio, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addComponent(txt_precioProveedor)))
                .addGap(26, 26, 26))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jCheckBox1))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel13))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_nuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_precioProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("cantidad");
        jLabel17.setAutoscrolls(true);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("total");
        jLabel18.setAutoscrolls(true);

        txt_cantidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt_total.setEditable(false);
        txt_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/nuevo.png"))); // NOI18N
        btn_agregar.setEnabled(false);
        btn_agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_agregarMouseEntered(evt);
            }
        });
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_restar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btn_restar.setText("-");
        btn_restar.setEnabled(false);
        btn_restar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_restarActionPerformed(evt);
            }
        });

        btn_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/eliminar.png"))); // NOI18N
        btn_quitar.setEnabled(false);
        btn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_restar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_quitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_restar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("serie");
        jLabel19.setAutoscrolls(true);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("no. venta");
        jLabel20.setAutoscrolls(true);

        txt_serie.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txt_noVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_noVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_noVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addGap(31, 31, 31))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txt_serie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_noVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_noVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btn_nuevo.setBackground(new java.awt.Color(102, 255, 102));
        btn_nuevo.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_nuevo.setText("nuevo");
        btn_nuevo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_generar_venta.setBackground(new java.awt.Color(102, 255, 102));
        btn_generar_venta.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_generar_venta.setText("generar Compra");
        btn_generar_venta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_generar_venta.setEnabled(false);
        btn_generar_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_ventaActionPerformed(evt);
            }
        });

        btn_importe.setBackground(new java.awt.Color(102, 255, 102));
        btn_importe.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_importe.setText("importe");
        btn_importe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_importe.setEnabled(false);
        btn_importe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importeActionPerformed(evt);
            }
        });

        btn_recibo.setBackground(new java.awt.Color(102, 255, 102));
        btn_recibo.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_recibo.setText("recibo");
        btn_recibo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_recibo.setEnabled(false);

        btn_cancelar.setBackground(new java.awt.Color(102, 255, 102));
        btn_cancelar.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_cancelar.setText("cancelar");
        btn_cancelar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_cancelar.setEnabled(false);
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_salir.setBackground(new java.awt.Color(102, 255, 102));
        btn_salir.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_salir.setText("salir");
        btn_salir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_descuento.setBackground(new java.awt.Color(102, 255, 102));
        btn_descuento.setFont(new java.awt.Font("Rockwell Extra Bold", 1, 14)); // NOI18N
        btn_descuento.setText("Descuento");
        btn_descuento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_descuento.setEnabled(false);
        btn_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_descuentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_importe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_generar_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_recibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_descuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_generar_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btn_importe, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btn_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        txt_subtotal.setEditable(false);
        txt_subtotal.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N

        txt_total_aPagar.setEditable(false);
        txt_total_aPagar.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N
        txt_total_aPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_total_aPagarActionPerformed(evt);
            }
        });

        txt_descuento.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N
        txt_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuentoActionPerformed(evt);
            }
        });

        txt_valor_venta.setEditable(false);
        txt_valor_venta.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N

        txt_cambio.setEditable(false);
        txt_cambio.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N

        txt_importe.setFont(new java.awt.Font("Myanmar Text", 1, 24)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("valor venta");

        jLabel2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("sub total");

        jLabel3.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("descuento");

        jLabel5.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("total a pagar");

        jLabel6.setBackground(new java.awt.Color(255, 255, 102));
        jLabel6.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("importe");

        jLabel7.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("cambio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_importe, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel7)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txt_valor_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_subtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_total_aPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_total_aPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_valor_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_importe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_total_aPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_total_aPagarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_total_aPagarActionPerformed

    private void txt_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_proveedorActionPerformed

    private void btn_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clienteActionPerformed
       new Proveedores1().setVisible(true);
    }//GEN-LAST:event_btn_clienteActionPerformed

    private void cbx_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_documentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_documentoActionPerformed

    private void txt_codigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codigoKeyTyped
if(evt.getKeyChar()==VK_ENTER){
    this.txt_cantidad.setText("1");
    agrega_articulo();
}       
    }//GEN-LAST:event_txt_codigoKeyTyped

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
       
      agrega_articulo();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void txt_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codigoFocusLost
      productoNuevo();
    }//GEN-LAST:event_txt_codigoFocusLost

    private void btn_agregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_agregarMouseEntered
      if(this.txt_cantidad.getText().equalsIgnoreCase("")){
          this.txt_cantidad.setText("1");
      }
    }//GEN-LAST:event_btn_agregarMouseEntered

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
        this.txt_nombre.setText("");
        this.txt_stock.setText("");
        this.txt_precio.setText("");
        this.txt_codigo.setText("");
        this.txt_cantidad.setText("");
        this.txt_cambio.setText("");
        this.txt_importe.setText("");
         this.txt_total.setText("");
    }//GEN-LAST:event_btn_quitarActionPerformed

    private void btn_restarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_restarActionPerformed
  int select=this.Tabla.getSelectedRow();
        int tot=0;
   String aux= this.Tabla.getValueAt(select, 0).toString();
        try{
         
          tot=resto(Integer.parseInt(this.Tabla.getValueAt(select, 0).toString()))-Integer.parseInt(this.Tabla.getValueAt(select, 1).toString());
         Connection cn=con.conectar();
                PreparedStatement pst=cn.prepareStatement("update productos1 set stock=? where codigo=?");
                
                pst.setString(1,""+tot );
                pst.setString(2, aux);
                pst.executeUpdate();
         
        mod.removeRow(select);
        actualiza_precio();
    }catch(ArrayIndexOutOfBoundsException e){
        JOptionPane.showMessageDialog(null,"debe de seleccionar un producto de la tabla a eliminar ");
    }catch(Exception e){
        
    }
        this.txt_cambio.setText("");
        this.txt_importe.setText("");
        this.txt_descuento.setText("");
        
    }//GEN-LAST:event_btn_restarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
LocalDate fecha=  LocalDate.now();
this.txt_fecha.setText(""+fecha);
this.txt_fecha.setEditable(false);
this.btn_cliente.setEnabled(true);
this.btn_nuevoProducto.setEnabled(true);
this.btn_agregar.setEnabled(true);
this.btn_quitar.setEnabled(true);
this.btn_restar.setEnabled(true);
this.txt_serie.setText(user);
this.txt_serie.setEnabled(false);

    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       int jp= JOptionPane.showConfirmDialog(this,"desea salir de compras?","confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (jp==JOptionPane.YES_OPTION) {
          clean();
       restaura();
       limpia_tabla();
         dispose();
        }else if(jp==JOptionPane.NO_OPTION){
             JOptionPane.showMessageDialog(null, "ok  continuar");
        }
     
       
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuentoActionPerformed

    private void btn_importeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_importeActionPerformed
       double Dimporte=0;
       double Dsubtotal=0,tot=0;
      
       
       Dimporte=Double.parseDouble(this.txt_importe.getText());
       Dsubtotal=Double.parseDouble(this.txt_subtotal.getText());
    
       if(Dsubtotal>Dimporte){
           JOptionPane.showMessageDialog(null,"el importe es menor al total");
           
           this.txt_importe.setText("");
           
       }else{
      tot=Dimporte-Dsubtotal;
       }
       
       this.txt_cambio.setText(""+tot);
      
       
    }//GEN-LAST:event_btn_importeActionPerformed

    private void btn_generar_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_ventaActionPerformed
        insertaVenta();
        insertaDetalleCompra();
        limpia_tabla();
        clean();
      
    }//GEN-LAST:event_btn_generar_ventaActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
   int jp= JOptionPane.showConfirmDialog(this,"desea cancelar la venta","confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (jp==JOptionPane.YES_OPTION) {
          clean();
       restaura();
       limpia_tabla();
        
        }else if(jp==JOptionPane.NO_OPTION){
             JOptionPane.showMessageDialog(null, "ok  continuar");
        }
     
   
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_descuentoActionPerformed
       try{
            int seleccion=this.Tabla.getSelectedRow();
            
        double descuento=Double.parseDouble(this.txt_descuento.getText());
        if(descuento<0){
            JOptionPane.showMessageDialog(null,"use numeros positivos");
            this.txt_descuento.setText("");
        }else if(descuento>=100){
            JOptionPane.showMessageDialog(null,"descuento no valido");
            this.txt_descuento.setText("");
        }else{
              double resultado=(Double.parseDouble(this.Tabla.getValueAt(seleccion, 3).toString())*descuento)/100;
         double nuevo_precio=Double.parseDouble(this.Tabla.getValueAt(seleccion, 3).toString())-resultado;
      //  this.Tabla.setValueAt(this, seleccion,(int)nuevo_precio);
        JOptionPane.showMessageDialog(null,"nuevo precio con descuento del "+descuento+" % es" +nuevo_precio);
        String[] info= new String[4];
        info[0]=Tabla.getValueAt(seleccion, 0).toString();
        info[1]=Tabla.getValueAt(seleccion, 1).toString();
        info[2]=Tabla.getValueAt(seleccion, 2).toString();
        info[3]=""+nuevo_precio;
           for (int i = 0; i < Tabla.getColumnCount(); i++) {
               mod.setValueAt(info[i], seleccion, i);
           }
        
        }
       
      
       }catch(NumberFormatException e){
           JOptionPane.showMessageDialog(null, e);
       }catch(Exception ex){
              JOptionPane.showMessageDialog(null, ex);
       }
    }//GEN-LAST:event_btn_descuentoActionPerformed

    private void btn_nuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoProductoActionPerformed
productoNuevo();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevoProductoActionPerformed

    private void txt_noVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_noVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_noVentaActionPerformed

    
    public int resto(int r){
        int resto=0;
    try {
        Connection cn = con.conectar();
        Statement st=cn.createStatement();
        ResultSet rs=st.executeQuery("select stock from productos1 where codigo="+r);
        while(rs.next()){
            resto=rs.getInt("stock");
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(CompraX.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resto;
    }
    public void limpia_tabla(){
        
        mod.getDataVector().removeAllElements();
        this.Tabla.updateUI();
    }
   public void actualiza_precio(){
       
       int sel=this.Tabla.getRowCount();
       resultado=0.0;
       
       for (int i = 0; i < sel; i++) {
           resultado=resultado+Double.parseDouble(this.Tabla.getValueAt(i, 3).toString());
           
       }
       String res=String.valueOf(resultado);
       this.txt_subtotal.setText(res);
   }
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
            java.util.logging.Logger.getLogger(CompraX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompraX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompraX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompraX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompraX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cliente;
    private javax.swing.JButton btn_descuento;
    private javax.swing.JButton btn_generar_venta;
    private javax.swing.JButton btn_importe;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_nuevoProducto;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_recibo;
    private javax.swing.JButton btn_restar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cbx_documento;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_cambio;
    public static javax.swing.JTextField txt_cantidad;
    public static javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descuento;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_importe;
    private javax.swing.JTextField txt_noVenta;
    public static javax.swing.JTextField txt_nombre;
    public static javax.swing.JTextField txt_precio;
    public static javax.swing.JTextField txt_precioProveedor;
    public static javax.swing.JTextField txt_proveedor;
    public static javax.swing.JTextField txt_serie;
    public static javax.swing.JTextField txt_stock;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_total_aPagar;
    private javax.swing.JTextField txt_valor_venta;
    // End of variables declaration//GEN-END:variables
}
