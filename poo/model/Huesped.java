package co.edu.uniquindio.poo.model;

import javax.swing.*;
import java.util.ArrayList;

public class Huesped {
    private String nombreCompleto;
    private String documentoIdentidad;
    private byte edad;
    private String telefono;
    private String ciudadProcedencia;
    private ArrayList<Reserva> reservas;

    public Huesped(String nombreCompleto, String documentoIdentidad, byte edad,
                   String telefono, String ciudadProcedencia) {
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.edad = edad;
        this.telefono = telefono;
        this.ciudadProcedencia = ciudadProcedencia;
        this.reservas = new ArrayList<>();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudadProcedencia() {
        return ciudadProcedencia;
    }

    public void setCiudadProcedencia(String ciudadProcedencia) {
        this.ciudadProcedencia = ciudadProcedencia;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "Huesped:" +
                " Nombre Completo='" + nombreCompleto + '\'' +
                ", Documento de Identidad='" + documentoIdentidad + '\'' +
                ", Edad=" + edad +
                ", Telefono='" + telefono + '\'' +
                ", Ciudad de Procedencia='" + ciudadProcedencia + '\'' +
                ", Reservas=" + reservas;
    }
    public void agregarReserva(Reserva reservaNueva){
        reservas.add(reservaNueva);
    }
    public String consultarReservas(){
        String textoReservas="";
        if (reservas == null) {
            return "El huésped " + nombreCompleto + " no tiene reservas registradas.";
        }
        textoReservas = "Reservas de " + nombreCompleto + ":\n";
        for (Reserva usuarioReservas : reservas) {
            textoReservas += "Reserva #" + usuarioReservas.getCodigoReserva() +
                    " - Estado: " + usuarioReservas.getEstado() +
                    " - Fecha: " + usuarioReservas.getFechaReserva() +
                    " - Valor total: $" + usuarioReservas.getValorTotal() + "\n";
        }

        return textoReservas;
    }
}

