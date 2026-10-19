package modelo;

import java.time.LocalDate;

public class Recibo {

    private String folio;
    private LocalDate fecha;
    private String numeroServicio;
    private String nombreCliente;
    private String periodo;
    private double importePagado;
    private String tarjetaEnmascarada;

    public Recibo(String folio, LocalDate fecha, Cliente cliente, Consumo consumo, String numeroTarjeta) {
        this.folio = folio;
        this.fecha = fecha;
        this.numeroServicio = cliente.getNumeroServicio();
        this.nombreCliente = cliente.getNombre();
        this.periodo = consumo.getPeriodo();
        this.importePagado = consumo.getImporte();
        this.tarjetaEnmascarada = enmascarar(numeroTarjeta);
    }

    /** deja visibles solo los ultimos 4 dígitos de la tarjeta */
    private String enmascarar(String numeroTarjeta) {
        String digitos = numeroTarjeta.replaceAll("\\D", "");
        String ultimos = digitos.length() > 4
                ? digitos.substring(digitos.length() - 4)
                : digitos;
        return "**** **** **** " + ultimos;
    }

    public String getFolio() {
        return folio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public double getImportePagado() {
        return importePagado;
    }

    public String getTarjetaEnmascarada() {
        return tarjetaEnmascarada;
    }
}
