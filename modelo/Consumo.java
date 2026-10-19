package modelo;

import java.time.LocalDate;

public class Consumo {

    private String periodo;
    private int kWh;
    private double tarifa;
    private LocalDate fechaLimite;
    private boolean pagado;

    public Consumo(String periodo, int kWh, double tarifa, LocalDate fechaLimite) {
        this.periodo = periodo;
        this.kWh = kWh;
        this.tarifa = tarifa;
        this.fechaLimite = fechaLimite;
        this.pagado = false;
    }

    public String getPeriodo() {
        return periodo;
    }

    public int getKWh() {
        return kWh;
    }

    public double getTarifa() {
        return tarifa;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public double getImporte() {
        return kWh * tarifa;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void marcarPagado() {
        this.pagado = true;
    }
}
