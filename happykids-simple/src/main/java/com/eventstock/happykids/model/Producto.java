package com.eventstock.happykids.model;

import java.math.BigDecimal;

/**
 * Modelo de Producto — sin base de datos por ahora.
 * Datos de ejemplo tomados directamente del SQL de la BD happykids.
 */
public class Producto {

    private Integer codproducto;
    private String nombreproducto;
    private String dproducto;
    private BigDecimal precio_venta;
    private Integer stock_p;
    private String serial_p;
    private String imagen_url;
    private String categoria;

    public Producto(Integer codproducto, String nombreproducto, String dproducto,
                    BigDecimal precio_venta, Integer stock_p, String serial_p,
                    String imagen_url, String categoria) {
        this.codproducto    = codproducto;
        this.nombreproducto = nombreproducto;
        this.dproducto      = dproducto;
        this.precio_venta   = precio_venta;
        this.stock_p        = stock_p;
        this.serial_p       = serial_p;
        this.imagen_url     = imagen_url;
        this.categoria      = categoria;
    }

    // Getters
    public Integer getCodproducto()    { return codproducto; }
    public String getNombreproducto()  { return nombreproducto; }
    public String getDproducto()       { return dproducto; }
    public BigDecimal getPrecio_venta(){ return precio_venta; }
    public Integer getStock_p()        { return stock_p; }
    public String getSerial_p()        { return serial_p; }
    public String getImagen_url()      { return imagen_url; }
    public String getCategoria()       { return categoria; }
}
