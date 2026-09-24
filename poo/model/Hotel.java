package co.edu.uniquindio.poo.model;
import java.util.ArrayList;
import java.util.Arrays;
public class Hotel {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private ArrayList<Huesped> listaHuespedes;
    private Habitacion[] habitaciones;
    private ArrayList<Reserva> reservas;
    private char [][] matrizOcupacion;

    public Hotel(String nombreComercial,String nit,String direccion,
                 String telefono, int numHabitaciones, byte numReservas){
        this.nombreComercial=nombreComercial;
        this.nit=nit;
        this.direccion=direccion;
        this.telefono=telefono;
        this.listaHuespedes=new ArrayList<>();
        this.habitaciones=new Habitacion [numHabitaciones];
        this.reservas= new ArrayList<>();
        this.matrizOcupacion=new char[numHabitaciones][7];
    }
    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Huesped> getListaHuespedes() {
        return listaHuespedes;
    }

    public void setListaHuespedes(ArrayList<Huesped> listaHuespedes) {
        this.listaHuespedes = listaHuespedes;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Habitacion[] habitaciones) {
        this.habitaciones = habitaciones;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }


    public char[][] getMatrizOcupacion() {
        return matrizOcupacion;
    }

    public void setMatrizOcupacion(char[][] matrizOcupacion) {
        this.matrizOcupacion = matrizOcupacion;
    }

    @Override
    public String toString() {
        return "Hotel" +
                "nombre Comercial='" + nombreComercial + '\'' +
                ", NIT='" + nit + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Lista de Huespedes=" + listaHuespedes +
                ", Habitaciones=" + Arrays.toString(habitaciones) +
                ", Reservas=" + reservas +
                ", Matriz de Ocupacion=" + Arrays.toString(matrizOcupacion);
    }
    public void registrarHabitacion(int numeroHabitacion, String tipoHabitacion, byte piso,
                                    byte capacidadMaxima, double precioNoche, String estado,int pos){

        Habitacion habitacion = new Habitacion(numeroHabitacion, tipoHabitacion,piso,capacidadMaxima,precioNoche,
                estado);
        habitaciones[pos]=habitacion;
    }
    public String registrarHuesped(String nombre, String documento, byte edad, String telefono, String ciudad ){
        String mensaje = "";
        Huesped huespedNuevo = new Huesped(nombre, documento, edad, telefono, ciudad);
        listaHuespedes.add(huespedNuevo);
        mensaje= "Huesped registrado con exito";
        return mensaje;
    }
    public Huesped consultarHuespedPorTelefono (String telefono){
        for(Huesped aux : listaHuespedes){
            if(aux.getTelefono().equals(telefono)){
                return aux;
            }
        }
        return null; // si retorna null es porque no encontro un huesped con ede telefono
    }
    public String registrarReserva(int codigo, String estado, String fecha, byte noches, byte cantHuespedes, Huesped huesped,
                                   String metodoPago, int numHabitacion){
        if (huesped == null) {
            return "No se puede registrar la reserva ya que el huésped no existe";
        }
        Habitacion habitacionSeleccionada = null;

        //Buscar si la habitación existe en el arreglo
        for (Habitacion aux : habitaciones) {
            if (aux != null && aux.getNumeroHabitacion() == numHabitacion) {
                habitacionSeleccionada = aux;
                break;
            }
        }
        //Si no se encontró ningún registro con ese número
        if (habitacionSeleccionada == null) {
            return "La habitación número " + numHabitacion + " no existe.";
        }
        //Si la habitación existe pero no está disponible
        if (!habitacionSeleccionada.estaDisponible()) {
            return "La habitación número " + numHabitacion + " existe pero no está disponible (Estado actual: "
                    + habitacionSeleccionada.getEstado() + ")";
        }
        //Registrar la reserva
        Reserva reservaNueva = new Reserva(codigo, estado, fecha, noches, cantHuespedes, huesped, metodoPago);
        reservaNueva.agregarHabitacion(habitacionSeleccionada);

        reservas.add(reservaNueva);
        huesped.agregarReserva(reservaNueva);

        return "Reserva registrada con éxito. Valor total: $" + reservaNueva.getValorTotal();
    }
    public Habitacion buscarHabitacionMayorPrecio() {
        Habitacion mayor = null;
        for (Habitacion aux : habitaciones) {
            if (aux != null && (mayor == null || aux.getPrecioNoche() > mayor.getPrecioNoche())) {
                mayor = aux;
            }
        }
        return mayor;
    }
    public Habitacion buscarHabitacionMenorPrecio() {
        Habitacion mayor = null;
        for (Habitacion aux : habitaciones) {
            if (aux != null && (mayor == null || aux.getPrecioNoche() < mayor.getPrecioNoche())) {
                mayor = aux;
            }
        }
        return mayor;
    }
    public int contarHabitacionesDisponibles() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux!=null && aux.getEstado().equalsIgnoreCase("Disponible")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }
    public int contarHabitacionesOcupadas() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux!=null && aux.getEstado().equalsIgnoreCase("Ocupada")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }
    public int contarHabitacionesMantenimiento() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux!=null && aux.getEstado().equalsIgnoreCase("Mantenimiento")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }
}


