package modelo;

public class Fachada implements IFachada {

    private PagoModel modelo;

    public Fachada(PagoModel modelo) {
        this.modelo = modelo;
    }

    @Override
    public void buscarClientes(String texto) {
        modelo.buscarClientes(texto);
    }

    @Override
    public void seleccionarCliente(Cliente c) {
        modelo.seleccionarCliente(c);
    }

    @Override
    public void pagar(String numeroTarjeta) {
        modelo.pagar(numeroTarjeta);
    }

    @Override
    public void agregarObservador(ModeloObserver o) {
        modelo.agregarObservador(o);
    }
}
