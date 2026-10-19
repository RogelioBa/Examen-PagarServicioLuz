package controlador;

import modelo.Cliente;
import modelo.IFachada;

public class PagoController implements IControlador {

    private IFachada fachada;

    public PagoController(IFachada fachada) {
        this.fachada = fachada;
    }

    @Override
    public void buscarClientes(String texto) {
        fachada.buscarClientes(texto);
    }

    @Override
    public void seleccionarCliente(Cliente c) {
        fachada.seleccionarCliente(c);
    }

    @Override
    public void pagar(String numeroTarjeta) {
        fachada.pagar(numeroTarjeta);
    }
}
