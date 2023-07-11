/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.*;
/**
 *
 * @author julio
 */
public class Principal extends javax.swing.JFrame {
    Productos pr= new Productos();
    public static String user;
   fondo fr= new fondo();
    public Principal() {
    
        initComponents();
        setLocationRelativeTo(null);
        setTitle("principal");
        
         
        
         
    }
     public Principal(boolean bool) {
    
        initComponents();
        setLocationRelativeTo(null);
        setTitle("principal");
        if(bool){
            this.item_producto.setEnabled(false);
            this.item_categoria.setEnabled(false);
            this.item_cancelaCompra.setEnabled(false);
            this.item_cancelaVenta.setEnabled(false);
            this.item_proveedor.setEnabled(false);
            this.item_empleado.setEnabled(false);
            this.item_inventario.setEnabled(false);
            this.item_compra.setEnabled(false);
            this.item_cliente.setEnabled(false);
        }
           
        
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_archivo = new javax.swing.JMenu();
        m_item_salir = new javax.swing.JMenuItem();
        menu_almacen = new javax.swing.JMenu();
        item_producto = new javax.swing.JMenuItem();
        item_categoria = new javax.swing.JMenuItem();
        menu_compras = new javax.swing.JMenu();
        item_compra = new javax.swing.JMenuItem();
        item_proveedor = new javax.swing.JMenuItem();
        menu_ventas = new javax.swing.JMenu();
        item_venta = new javax.swing.JMenuItem();
        item_verificarProducto = new javax.swing.JMenuItem();
        item_cliente = new javax.swing.JMenuItem();
        menu_consultas = new javax.swing.JMenu();
        item_ventasRealizadas = new javax.swing.JMenuItem();
        item_ventasDetalladas = new javax.swing.JMenuItem();
        item_comprasRealizadas = new javax.swing.JMenuItem();
        item_comprasDetalladas = new javax.swing.JMenuItem();
        menu_mantenimiento = new javax.swing.JMenu();
        item_empleado = new javax.swing.JMenuItem();
        item_inventario = new javax.swing.JMenuItem();
        menu_cancelaciones = new javax.swing.JMenu();
        item_cancelaCompra = new javax.swing.JMenuItem();
        item_cancelaVenta = new javax.swing.JMenuItem();
        menu_herramientas = new javax.swing.JMenu();
        item_contraseña = new javax.swing.JMenuItem();
        item_calculadora = new javax.swing.JMenuItem();
        item_ventasvendedor = new javax.swing.JMenuItem();
        menu_ayuda = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        menu_archivo.setText("Archivo");
        menu_archivo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        m_item_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        m_item_salir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        m_item_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/eliminar.png"))); // NOI18N
        m_item_salir.setText("cerrar aplicacion");
        m_item_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_item_salirActionPerformed(evt);
            }
        });
        menu_archivo.add(m_item_salir);

        jMenuBar1.add(menu_archivo);

        menu_almacen.setText("Almacen");
        menu_almacen.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_producto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        item_producto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/producto.png"))); // NOI18N
        item_producto.setText("producto");
        item_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_productoActionPerformed(evt);
            }
        });
        menu_almacen.add(item_producto);

        item_categoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        item_categoria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/lupa.png"))); // NOI18N
        item_categoria.setText("categoria");
        item_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_categoriaActionPerformed(evt);
            }
        });
        menu_almacen.add(item_categoria);

        jMenuBar1.add(menu_almacen);

        menu_compras.setText("Compras");
        menu_compras.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_compra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        item_compra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_compra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Carrito-de-compras.png"))); // NOI18N
        item_compra.setText("compra");
        item_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_compraActionPerformed(evt);
            }
        });
        menu_compras.add(item_compra);

        item_proveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        item_proveedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/proveedor.png"))); // NOI18N
        item_proveedor.setText("proveedor");
        item_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_proveedorActionPerformed(evt);
            }
        });
        menu_compras.add(item_proveedor);

        jMenuBar1.add(menu_compras);

        menu_ventas.setText("Ventas");
        menu_ventas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_venta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_CONTROL, 0));
        item_venta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Nventa.png"))); // NOI18N
        item_venta.setText("venta");
        item_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ventaActionPerformed(evt);
            }
        });
        menu_ventas.add(item_venta);

        item_verificarProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        item_verificarProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_verificarProducto.setText("verificar producto");
        item_verificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_verificarProductoActionPerformed(evt);
            }
        });
        menu_ventas.add(item_verificarProducto);

        item_cliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        item_cliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Clientes.png"))); // NOI18N
        item_cliente.setText("cliente");
        item_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_clienteActionPerformed(evt);
            }
        });
        menu_ventas.add(item_cliente);

        jMenuBar1.add(menu_ventas);

        menu_consultas.setText("Consultas");
        menu_consultas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_ventasRealizadas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        item_ventasRealizadas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_ventasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/money.png"))); // NOI18N
        item_ventasRealizadas.setText("ventas realizadas");
        item_ventasRealizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ventasRealizadasActionPerformed(evt);
            }
        });
        menu_consultas.add(item_ventasRealizadas);

        item_ventasDetalladas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        item_ventasDetalladas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_ventasDetalladas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/report.png"))); // NOI18N
        item_ventasDetalladas.setText("ventas detalladas");
        item_ventasDetalladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ventasDetalladasActionPerformed(evt);
            }
        });
        menu_consultas.add(item_ventasDetalladas);

        item_comprasRealizadas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        item_comprasRealizadas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_comprasRealizadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/compras.png"))); // NOI18N
        item_comprasRealizadas.setText("compras realizadas");
        item_comprasRealizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_comprasRealizadasActionPerformed(evt);
            }
        });
        menu_consultas.add(item_comprasRealizadas);

        item_comprasDetalladas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9, java.awt.event.InputEvent.CTRL_MASK));
        item_comprasDetalladas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_comprasDetalladas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Carrito-de-compras.png"))); // NOI18N
        item_comprasDetalladas.setText("compras detalladas ");
        item_comprasDetalladas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_comprasDetalladasActionPerformed(evt);
            }
        });
        menu_consultas.add(item_comprasDetalladas);

        jMenuBar1.add(menu_consultas);

        menu_mantenimiento.setText("Mantenimiento");
        menu_mantenimiento.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_empleado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        item_empleado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Clientes.png"))); // NOI18N
        item_empleado.setText("empleado");
        item_empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_empleadoActionPerformed(evt);
            }
        });
        menu_mantenimiento.add(item_empleado);

        item_inventario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        item_inventario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/excel.png"))); // NOI18N
        item_inventario.setText("Inventario");
        item_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_inventarioActionPerformed(evt);
            }
        });
        menu_mantenimiento.add(item_inventario);

        jMenuBar1.add(menu_mantenimiento);

        menu_cancelaciones.setText("Cancelaciones");
        menu_cancelaciones.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_cancelaCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_cancelaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/borrar.png"))); // NOI18N
        item_cancelaCompra.setText("cancelar compra");
        item_cancelaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cancelaCompraActionPerformed(evt);
            }
        });
        menu_cancelaciones.add(item_cancelaCompra);

        item_cancelaVenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_cancelaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/eliminar.png"))); // NOI18N
        item_cancelaVenta.setText("cancelar venta");
        item_cancelaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cancelaVentaActionPerformed(evt);
            }
        });
        menu_cancelaciones.add(item_cancelaVenta);

        jMenuBar1.add(menu_cancelaciones);

        menu_herramientas.setText("Herramientas");
        menu_herramientas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        item_contraseña.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_contraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/Actualizar (2).png"))); // NOI18N
        item_contraseña.setText("cambiar contraseña");
        item_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_contraseñaActionPerformed(evt);
            }
        });
        menu_herramientas.add(item_contraseña);

        item_calculadora.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_calculadora.setText("calculadora");
        item_calculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_calculadoraActionPerformed(evt);
            }
        });
        menu_herramientas.add(item_calculadora);

        item_ventasvendedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        item_ventasvendedor.setText("ventas vendedor");
        item_ventasvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ventasvendedorActionPerformed(evt);
            }
        });
        menu_herramientas.add(item_ventasvendedor);

        jMenuBar1.add(menu_herramientas);

        menu_ayuda.setText("Ayuda");
        menu_ayuda.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Img/ayuda.png"))); // NOI18N
        menu_ayuda.add(jMenu3);

        jMenuBar1.add(menu_ayuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item_calculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_calculadoraActionPerformed
FRMcalculadora calcu= new FRMcalculadora();   
calcu.setVisible(true);
    }//GEN-LAST:event_item_calculadoraActionPerformed

    private void m_item_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_item_salirActionPerformed
       System.exit(0);
    }//GEN-LAST:event_m_item_salirActionPerformed

    private void item_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ventaActionPerformed
     Ventas vta= new Ventas();
     vta.setVisible(true);
       
    
    }//GEN-LAST:event_item_ventaActionPerformed

    private void item_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_contraseñaActionPerformed
     Contraseña contr= new Contraseña();
     contr.setVisible(true);
      
    
    }//GEN-LAST:event_item_contraseñaActionPerformed

    private void item_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_productoActionPerformed
       FRProducto product= new FRProducto();
       product.setVisible(true);
    //   CtrlProductos pro= new CtrlProductos(Producto pr,FrmProducto fPr,Modelo mod);
    }//GEN-LAST:event_item_productoActionPerformed

    private void item_empleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_empleadoActionPerformed
   
        new Usuarios().setVisible(true);
       
    }//GEN-LAST:event_item_empleadoActionPerformed

    private void item_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_compraActionPerformed
new Compras().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_item_compraActionPerformed

    private void item_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_clienteActionPerformed
        new Clientes().setVisible(true);
    }//GEN-LAST:event_item_clienteActionPerformed

    private void item_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_proveedorActionPerformed
        new proveedores().setVisible(true);
    }//GEN-LAST:event_item_proveedorActionPerformed

    private void item_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_categoriaActionPerformed
                new Categoria().setVisible(true);                                                                 //categoria
                                                                                 
    }//GEN-LAST:event_item_categoriaActionPerformed

    private void item_verificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_verificarProductoActionPerformed
       new Verificar_producto().setVisible(true);
    }//GEN-LAST:event_item_verificarProductoActionPerformed

    private void item_ventasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ventasRealizadasActionPerformed
       new  ventas_realizadas1().setVisible(true);
    }//GEN-LAST:event_item_ventasRealizadasActionPerformed

    private void item_ventasDetalladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ventasDetalladasActionPerformed
       new  ventas_realizadas().setVisible(true);
    }//GEN-LAST:event_item_ventasDetalladasActionPerformed

    private void item_cancelaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cancelaVentaActionPerformed
       new Cancelar_venta().setVisible(true);
    }//GEN-LAST:event_item_cancelaVentaActionPerformed

    private void item_comprasRealizadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_comprasRealizadasActionPerformed
       
         new compras_realizadas1().setVisible(true);
    }//GEN-LAST:event_item_comprasRealizadasActionPerformed

    private void item_comprasDetalladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_comprasDetalladasActionPerformed
          new compras_realizadas().setVisible(true);
    }//GEN-LAST:event_item_comprasDetalladasActionPerformed

    private void item_cancelaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cancelaCompraActionPerformed
        new cancelar_compra().setVisible(true);
    }//GEN-LAST:event_item_cancelaCompraActionPerformed

    private void item_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_inventarioActionPerformed
      new Inventario().setVisible(true);
    }//GEN-LAST:event_item_inventarioActionPerformed

    private void item_ventasvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ventasvendedorActionPerformed
        new ventas_vendedor().setVisible(true
        );
    }//GEN-LAST:event_item_ventasvendedorActionPerformed
public void respaldo(){
        try {
            Process p;
            p=Runtime.getRuntime().exec("mysqldump -u root -p ecatepec_fisica");
            InputStream is=p.getInputStream();
            FileOutputStream fos=new FileOutputStream("resp_sql/backup_papel.sql");
            byte[]buffer =new byte[1000];
            int leido=is.read(buffer);
            while (leido>0) {
                fos.write(buffer,0,leido);
                leido=is.read(buffer);
            }
            fos.close();
            
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void restaura(){
    
}
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem item_calculadora;
    public javax.swing.JMenuItem item_cancelaCompra;
    public javax.swing.JMenuItem item_cancelaVenta;
    public javax.swing.JMenuItem item_categoria;
    public javax.swing.JMenuItem item_cliente;
    public javax.swing.JMenuItem item_compra;
    public javax.swing.JMenuItem item_comprasDetalladas;
    public javax.swing.JMenuItem item_comprasRealizadas;
    public javax.swing.JMenuItem item_contraseña;
    public javax.swing.JMenuItem item_empleado;
    public javax.swing.JMenuItem item_inventario;
    public javax.swing.JMenuItem item_producto;
    public javax.swing.JMenuItem item_proveedor;
    public javax.swing.JMenuItem item_venta;
    public javax.swing.JMenuItem item_ventasDetalladas;
    public javax.swing.JMenuItem item_ventasRealizadas;
    private javax.swing.JMenuItem item_ventasvendedor;
    public javax.swing.JMenuItem item_verificarProducto;
    public javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JMenuItem m_item_salir;
    public javax.swing.JMenu menu_almacen;
    public javax.swing.JMenu menu_archivo;
    public javax.swing.JMenu menu_ayuda;
    public javax.swing.JMenu menu_cancelaciones;
    public javax.swing.JMenu menu_compras;
    public javax.swing.JMenu menu_consultas;
    public javax.swing.JMenu menu_herramientas;
    public javax.swing.JMenu menu_mantenimiento;
    public javax.swing.JMenu menu_ventas;
    // End of variables declaration//GEN-END:variables
class fondo extends JPanel{
    Image imagen;
    @Override
    public void paint(Graphics g){
        imagen=new ImageIcon(getClass().getResource("imagen/fongo.png")).getImage();
        g.drawImage(imagen, 0, 0,getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
        
    }
}
}
