package me.dellamite.appcompras;

import java.util.ArrayList;

public class Lista {
    private String emailAutor, nombreLista;
    private ArrayList<String> producto;
    private float precioC, precioW, precioS;

    public Lista(){

    }

    public float getPrecioC() {
        return precioC;
    }

    public void setPrecioC(float precioC) {
        this.precioC = precioC;
    }

    public float getPrecioW() {
        return precioW;
    }

    public void setPrecioW(float precioW) {
        this.precioW = precioW;
    }

    public float getPrecioS() {
        return precioS;
    }

    public void setPrecioS(float precioS) {
        this.precioS = precioS;
    }

    public String getEmailAutor() {
        return emailAutor;
    }

    public ArrayList<String> getProducto() {
        return producto;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setEmailAutor(String emailAutor) {
        this.emailAutor = emailAutor;
    }

    public void setProducto(ArrayList<String> producto) {
        this.producto = producto;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }
}
