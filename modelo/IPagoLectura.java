package modelo;

import java.util.List;

/** vista de solo lectura del modelo, la vista unicamente puede consultar*/
public interface IPagoLectura {

    List<Cliente> getClientesFiltrados();

    Cliente getClienteSeleccionado();

    Consumo getConsumoActual();

    Recibo getReciboActual();
}
