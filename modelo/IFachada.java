package modelo;

/** punto de entrada unico al modelo para el controlador*/
public interface IFachada {

    void buscarClientes(String texto);

    void seleccionarCliente(Cliente c);

    void pagar(String numeroTarjeta);

    void agregarObservador(ModeloObserver o);
}
