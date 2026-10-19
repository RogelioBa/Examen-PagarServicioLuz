package vista;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import controlador.IControlador;
import modelo.Cliente;
import modelo.Consumo;
import modelo.IPagoLectura;
import modelo.ModeloObserver;
import modelo.Recibo;

/**
 * vista en consola. observa el modelo (modeloobserver), lee su estado por
 * ipagolectura y manda los eventos del usuario a icontrolador.
 */
public class VentanaPago implements ModeloObserver {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private IPagoLectura lectura;
    private IControlador controlador;
    private Scanner teclado;

    public VentanaPago(IPagoLectura lectura, IControlador controlador) {
        this.lectura = lectura;
        this.controlador = controlador;
        this.teclado = new Scanner(System.in);
    }

    /** recorre el flujo basico del caso de uso. */
    public void iniciar() {
        System.out.println("==========================================");
        System.out.println(" PAGAR SERVICIO DE LUZ");
        System.out.println("==========================================");

        // pasos 1 a 4: solicitar numero de servicio y seleccionar cliente
        while (lectura.getClienteSeleccionado() == null) {
            String texto = leer("\nNúmero de servicio: ");
            if (texto == null) {
                return;
            }
            controlador.buscarClientes(texto);
            List<Cliente> lista = lectura.getClientesFiltrados();
            if (lista.isEmpty()) {
                continue;
            }
            String opcion = leer("Elige un cliente (1-" + lista.size() + ") o Enter para seguir tecleando: ");
            if (opcion == null) {
                return;
            }
            int indice = convertir(opcion);
            if (indice >= 1 && indice <= lista.size()) {
                controlador.seleccionarCliente(lista.get(indice - 1));
            }
        }

        // paso 6 y 7: solicitar tarjeta y pagar
        String tarjeta = "";
        while (tarjeta.trim().isEmpty()) {
            tarjeta = leer("\nNúmero de tarjeta de pago: ");
            if (tarjeta == null) {
                return;
            }
        }
        controlador.pagar(tarjeta);
    }

    /** observer: el modelo cambio, se vuelve a pintar segun su estado. */
    @Override
    public void modeloActualizado() {
        if (lectura.getReciboActual() != null) {
            mostrarRecibo(lectura.getReciboActual());
        } else if (lectura.getConsumoActual() != null) {
            mostrarConsumo(lectura.getConsumoActual());
        } else {
            mostrarListaClientes(lectura.getClientesFiltrados());
        }
    }

    private void mostrarListaClientes(List<Cliente> lista) {
        System.out.println("\n--- CLIENTES ---");
        if (lista.isEmpty()) {
            System.out.println("(sin coincidencias)");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private void mostrarConsumo(Consumo c) {
        Cliente cliente = lectura.getClienteSeleccionado();
        System.out.println("\n--- DATOS DE CONSUMO ---");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Dirección: " + cliente.getDireccion());
        System.out.println("Periodo: " + c.getPeriodo());
        System.out.println("Consumo: " + c.getKWh() + " kWh");
        System.out.println("Tarifa: $" + dinero(c.getTarifa()) + "/kWh");
        System.out.println("Importe: $" + dinero(c.getImporte()));
        System.out.println("Fecha límite: " + c.getFechaLimite().format(FORMATO_FECHA));
    }

    private void mostrarRecibo(Recibo r) {
        System.out.println("\n==========================================");
        System.out.println(" RECIBO DE PAGO");
        System.out.println("==========================================");
        System.out.println("Folio: " + r.getFolio());
        System.out.println("Fecha: " + r.getFecha().format(FORMATO_FECHA));
        System.out.println("Servicio: " + r.getNumeroServicio());
        System.out.println("Cliente: " + r.getNombreCliente());
        System.out.println("Periodo: " + r.getPeriodo());
        System.out.println("Importe pagado: $" + dinero(r.getImportePagado()));
        System.out.println("Tarjeta: " + r.getTarjetaEnmascarada());
        System.out.println("PAGO EXITOSO");
        System.out.println("==========================================");
    }

    private String leer(String mensaje) {
        System.out.print(mensaje);
        if (!teclado.hasNextLine()) {
            return null;
        }
        return teclado.nextLine().trim();
    }

    private int convertir(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String dinero(double valor) {
        return String.format(Locale.US, "%.2f", valor);
    }
}