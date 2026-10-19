import controlador.PagoController;
import modelo.Fachada;
import modelo.PagoModel;
import vista.VentanaPago;

public class Main {

    public static void main(String[] args) {
        PagoModel modelo = new PagoModel();
        Fachada fachada = new Fachada(modelo);
        PagoController controlador = new PagoController(fachada);
        VentanaPago ventana = new VentanaPago(modelo, controlador);

        fachada.agregarObservador(ventana);

        ventana.iniciar();
    }
}
