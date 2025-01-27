package es.bitsandchips.beans;

public class LineasPedidos {
    private short id;
    private Pedido pedido;
    private Producto producto;
    private short cantidad;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
}
