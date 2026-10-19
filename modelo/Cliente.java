package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String numeroServicio;
    private String nombre;
    private String direccion;
    private List<Consumo> consumos;

    public Cliente(String numeroServicio, String nombre, String direccion) {
        this.numeroServicio = numeroServicio;
        this.nombre = nombre;
        this.direccion = direccion;
        this.consumos = new ArrayList<>();
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void agregarConsumo(Consumo c) {
        consumos.add(c);
    }

    /** regresa el primer consumo sin pagar, o null si no hay adeudo */
    public Consumo getConsumoPendiente() {
        for (Consumo c : consumos) {
            if (!c.isPagado()) {
                return c;
            }
        }
        return null;
    }

    /** el cliente coincide si su número de servicio empieza con el texto tecleado */
    public boolean coincide(String texto) {
        return numeroServicio.startsWith(texto);
    }

    @Override
    public String toString() {
        return numeroServicio + " - " + nombre;
    }
}
