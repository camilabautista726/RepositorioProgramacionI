package co.edu.uniquindio.poo.model;


import java.util.ArrayList;

public class Reserva {
    private int codigoReserva;
    private String estado;          // Pendiente, Confirmada, Finalizada
    private String fechaReserva;
    private byte numeroNoches;
    private byte cantidadHuespedes;
    private Huesped huesped;
    private String metodoPago;      // Efectivo, Tarjeta, Transferencia bancaria
    private double valorTotal;
    private ArrayList<Habitacion> habitaciones; //preguntar

    public Reserva(int codigoReserva, String estado, String fechaReserva, byte numeroNoches,
                   byte cantidadHuespedes, Huesped huesped, String metodoPago) {
        this.codigoReserva = codigoReserva;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
        this.numeroNoches = numeroNoches;
        this.cantidadHuespedes = cantidadHuespedes;
        this.huesped = huesped;
        this.metodoPago = metodoPago;
        this.valorTotal = 0.0;
        this.habitaciones = new ArrayList<>();
    }

    public int getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public byte getNumeroNoches() {
        return numeroNoches;
    }

    public void setNumeroNoches(byte numeroNoches) {
        this.numeroNoches = numeroNoches;
    }

    public byte getCantidadHuespedes() {
        return cantidadHuespedes;
    }

    public void setCantidadHuespedes(byte cantidadHuespedes) {
        this.cantidadHuespedes = cantidadHuespedes;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigoReserva='" + codigoReserva + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaReserva='" + fechaReserva + '\'' +
                ", numeroNoches=" + numeroNoches +
                ", cantidadHuespedes=" + cantidadHuespedes +
                ", huesped=" + huesped +
                ", metodoPago='" + metodoPago + '\'' +
                ", valor Total=" + valorTotal +
                ", habitaciones=" + habitaciones +
                '}';
    }
    public void agregarHabitacion (Habitacion habitacionSeleccionada) {
        habitaciones.add(habitacionSeleccionada);
        this.valorTotal += habitacionSeleccionada.getPrecioNoche() * this.numeroNoches;
        habitacionSeleccionada.setEstado("Ocupada");
    }

}