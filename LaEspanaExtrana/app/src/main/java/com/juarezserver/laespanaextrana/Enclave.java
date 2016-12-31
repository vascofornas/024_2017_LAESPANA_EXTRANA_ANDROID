package com.juarezserver.laespanaextrana;

/**
 * Created by modes on 30/12/2016.
 */

public class Enclave {


    private String Comunidad_enclave;
    private String Descripcion_enclave;

    private String Imagen_Enclave;
    private String Latitud_enclave;
    private String Longitud_enclave;
    private String Nombre_enclave;
    private String Poblacion_enclave;
    private String Provincia_enclave;
    private String Tipo_enclave;

    public Enclave() {

    }

    public Enclave(String comunidad_enclave, String descripcion_enclave, String imagen_Enclave, String latitud_enclave, String longitud_enclave, String nombre_enclave, String poblacion_enclave, String provincia_enclave, String tipo_enclave) {
        Comunidad_enclave = comunidad_enclave;
        Descripcion_enclave = descripcion_enclave;
        Imagen_Enclave = imagen_Enclave;
        Latitud_enclave = latitud_enclave;
        Longitud_enclave = longitud_enclave;
        Nombre_enclave = nombre_enclave;
        Poblacion_enclave = poblacion_enclave;
        Provincia_enclave = provincia_enclave;
        Tipo_enclave = tipo_enclave;
    }


    public String getComunidad_enclave() {
        return Comunidad_enclave;
    }

    public void setComunidad_enclave(String comunidad_enclave) {
        Comunidad_enclave = comunidad_enclave;
    }

    public String getDescripcion_enclave() {
        return Descripcion_enclave;
    }

    public void setDescripcion_enclave(String descripcion_enclave) {
        Descripcion_enclave = descripcion_enclave;
    }

    public String getImagen_Enclave() {
        return Imagen_Enclave;
    }

    public void setImagen_Enclave(String imagen_Enclave) {
        Imagen_Enclave = imagen_Enclave;
    }

    public String getLatitud_enclave() {
        return Latitud_enclave;
    }

    public void setLatitud_enclave(String latitud_enclave) {
        Latitud_enclave = latitud_enclave;
    }

    public String getLongitud_enclave() {
        return Longitud_enclave;
    }

    public void setLongitud_enclave(String longitud_enclave) {
        Longitud_enclave = longitud_enclave;
    }

    public String getNombre_enclave() {
        return Nombre_enclave;
    }

    public void setNombre_enclave(String nombre_enclave) {
        Nombre_enclave = nombre_enclave;
    }

    public String getPoblacion_enclave() {
        return Poblacion_enclave;
    }

    public void setPoblacion_enclave(String poblacion_enclave) {
        Poblacion_enclave = poblacion_enclave;
    }

    public String getProvincia_enclave() {
        return Provincia_enclave;
    }

    public void setProvincia_enclave(String provincia_enclave) {
        Provincia_enclave = provincia_enclave;
    }

    public String getTipo_enclave() {
        return Tipo_enclave;
    }

    public void setTipo_enclave(String tipo_enclave) {
        Tipo_enclave = tipo_enclave;
    }
}
