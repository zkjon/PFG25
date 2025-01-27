package es.bitsandchips.beans;

import java.util.Date;
import java.util.List;

public class Pedido {
    public enum Estado{
        c, f
    }
    private short id;
    private Date fecha;
    private Estado estado;
    private Usuario usuario;
    private double importe;
    private double iva;
    private List<LineasPedidos> lineasPedidos;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getImporte() {
        double totalImporte = 0;
        for (LineasPedidos lineaPedido : lineasPedidos) {
            double precioProducto = lineaPedido.getProducto().getPrecio();
            int cantidad = lineaPedido.getCantidad();
            totalImporte += precioProducto * cantidad;
        }
        return totalImporte;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public List<LineasPedidos> getLineasPedidos() {
        return lineasPedidos;
    }

    public void setLineasPedidos(List<LineasPedidos> lineasPedidos) {
        this.lineasPedidos = lineasPedidos;
    }





}
