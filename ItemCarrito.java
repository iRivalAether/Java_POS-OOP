package Proyecto_Integrador;

public class ItemCarrito extends Producto {
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        super(producto.getId(), producto.getNombre(), producto.getPrecio());
        this.cantidad = cantidad;
    }

    
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return getPrecio() * cantidad;
    }

    // Método para obtener los detalles del item
    public String obtenerDetalle() {
        return String.format("%6s | %-16s | $%6.2f | %8d | $%8.2f",//Estas madres son para darle formato a la cadena de datos
                getId(),
                getNombre(),
                getPrecio(),
                cantidad,
                calcularSubtotal());
    }
}
