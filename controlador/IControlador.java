package controlador;

import modelo.Cliente;

public interface IControlador {

    void buscarClientes(String texto);

    void seleccionarCliente(Cliente c);

    void pagar(String numeroTarjeta);
}
