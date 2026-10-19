package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PagoModel implements IPagoLectura {

    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados;
    private Cliente clienteSeleccionado;
    private Consumo consumoActual;
    private Recibo reciboActual;
    private List<ModeloObserver> observadores;
    private int contadorFolios;

    public PagoModel() {
        clientes = new ArrayList<>();
        clientesFiltrados = new ArrayList<>();
        observadores = new ArrayList<>();
        contadorFolios = 0;
        cargarClientesDeEjemplo();
    }

    private void cargarClientesDeEjemplo() {
        LocalDate limite = LocalDate.of(2026, 10, 25);
        double tarifa = 1.85;

        Cliente c1 = new Cliente("100234", "Juan Pérez López", "Calle Sonora 120");
        c1.agregarConsumo(new Consumo("Sep-Oct 2026", 320, tarifa, limite));

        Cliente c2 = new Cliente("100235", "María Gómez Ruiz", "Av. Hidalgo 455");
        c2.agregarConsumo(new Consumo("Sep-Oct 2026", 410, tarifa, limite));

        Cliente c3 = new Cliente("100301", "Carlos Ramírez Soto", "Calle Allende 78");
        c3.agregarConsumo(new Consumo("Sep-Oct 2026", 275, tarifa, limite));

        Cliente c4 = new Cliente("205120", "Ana Torres Vega", "Blvd. Kino 900");
        c4.agregarConsumo(new Consumo("Sep-Oct 2026", 380, tarifa, limite));

        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
        clientes.add(c4);
    }

    public void agregarObservador(ModeloObserver o) {
        observadores.add(o);
    }

    /** filtra los clientes cuyo numero de servicio empieza con el texto */
    public void buscarClientes(String texto) {
        clientesFiltrados = new ArrayList<>();
        clienteSeleccionado = null;
        consumoActual = null;
        reciboActual = null;

        if (texto != null && !texto.trim().isEmpty()) {
            String criterio = texto.trim();
            for (Cliente c : clientes) {
                if (c.coincide(criterio)) {
                    clientesFiltrados.add(c);
                }
            }
        }
        notificar();
    }

    public void seleccionarCliente(Cliente c) {
        clienteSeleccionado = c;
        consumoActual = c.getConsumoPendiente();
        reciboActual = null;
        notificar();
    }

    public void pagar(String numeroTarjeta) {
        if (clienteSeleccionado == null || consumoActual == null) {
            return;
        }
        consumoActual.marcarPagado();
        contadorFolios++;
        String folio = String.format("R-%04d", contadorFolios);
        reciboActual = new Recibo(folio, LocalDate.now(), clienteSeleccionado, consumoActual, numeroTarjeta);
        notificar();
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public Consumo getConsumoActual() {
        return consumoActual;
    }

    public Recibo getReciboActual() {
        return reciboActual;
    }

    private void notificar() {
        for (ModeloObserver o : observadores) {
            o.modeloActualizado();
        }
    }
}
