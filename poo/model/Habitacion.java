package co.edu.uniquindio.poo.model;

public class Habitacion {
    private int numeroHabitacion;
    private String tipoHabitacion;   // Individual, Doble, Suite
    private byte piso;
    private byte capacidadMaxima;
    private double precioNoche;
    private String estado;           // Disponible, Reservada, Ocupada

    public Habitacion(int numeroHabitacion, String tipoHabitacion, byte piso,
                      byte capacidadMaxima, double precioNoche, String estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.piso = piso;
        this.capacidadMaxima = capacidadMaxima;
        this.precioNoche = precioNoche;
        this.estado = estado;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public byte getPiso() {
        return piso;
    }

    public void setPiso(byte piso) {
        this.piso = piso;
    }

    public byte getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(byte capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion: " +
                "Numero de Habitacion= " + numeroHabitacion +
                ", Tipo de habitacion= " + tipoHabitacion + '\'' +
                ", Piso= " + piso +
                ", Capacidad Maxima= " + capacidadMaxima +
                ", PrecioNoche= " + precioNoche +
                ", Estado= " + estado + '\'';
    }
    public boolean estaDisponible (){
        return estado.equals("Disponible");

    }

}
