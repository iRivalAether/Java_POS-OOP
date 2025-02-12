package Proyecto_Integrador;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public List<ItemCarrito> getProductos() {
        return productos;
    }

    public void setProductos(List<ItemCarrito> productos) {
        this.productos = productos;
    }

    public void agregarProducto(ItemCarrito itemCarrito) {
        productos.add(itemCarrito);
    }

    public void eliminarProducto(int indice) {
        productos.remove(indice);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : productos) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public void mostrarCarrito() {
        System.out.println("| Código | Nombre           | Precio | Cantidad | Subtotal |");
        System.out.println("|--------|------------------|--------|----------|----------|");
        for (ItemCarrito item : productos) {
            System.out.println(item.obtenerDetalle());
        }
        System.out.println("|--------|------------------|--------|----------|----------|");
    }
}
