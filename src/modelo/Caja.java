/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author julio
 */
public class Caja {
    int disponible=0;
    String codigo="";

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(String codigo,int disponible) {
        this.disponible = disponible;
        this.codigo=codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
}
