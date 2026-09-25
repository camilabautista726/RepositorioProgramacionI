package co.edu.uniquindio.poo.model;
import javax.swing.*;
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
    private char[][] matrizOcupacion;

    public Hotel(String nombreComercial, String nit, String direccion,
                 String telefono, int numHabitaciones, byte numReservas) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaHuespedes = new ArrayList<>();
        this.habitaciones = new Habitacion[numHabitaciones];
        this.reservas = new ArrayList<>();
        this.matrizOcupacion = new char[numHabitaciones][7];
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
                                    byte capacidadMaxima, double precioNoche, String estado, int pos) {

        Habitacion habitacion = new Habitacion(numeroHabitacion, tipoHabitacion, piso, capacidadMaxima, precioNoche,
                estado);
        habitaciones[pos] = habitacion;
    }

    public String registrarHuesped(String nombre, String documento, byte edad, String telefono, String ciudad) {
        String mensaje = "";
        Huesped huespedNuevo = new Huesped(nombre, documento, edad, telefono, ciudad);
        listaHuespedes.add(huespedNuevo);
        mensaje = "Huesped registrado con exito";
        return mensaje;
    }

    public Huesped consultarHuespedPorTelefono(String telefono) {
        for (Huesped aux : listaHuespedes) {
            if (aux.getTelefono().equals(telefono)) {
                return aux;
            }
        }
        return null; // si retorna null es porque no encontro un huesped con ede telefono
    }

    public String registrarReserva(int codigo, String estado, String fecha, byte noches, byte cantHuespedes, Huesped huesped,
                                   String metodoPago, int numHabitacion) {
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
        if (!habitacionSeleccionada.verificarDisponibilidad()) {
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
        Habitacion menor = null;
        for (Habitacion aux : habitaciones) {
            if (aux != null && (menor == null || aux.getPrecioNoche() < menor.getPrecioNoche())) {
                menor = aux;
            }
        }
        return menor;
    }

    public int contarHabitacionesDisponibles() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux != null && aux.getEstado().equalsIgnoreCase("Disponible")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }

    public int contarHabitacionesOcupadas() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux != null && aux.getEstado().equalsIgnoreCase("Ocupada")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }

    public int contarHabitacionesMantenimiento() {
        int contador = 0;
        for (Habitacion aux : habitaciones) {
            if (aux != null && aux.getEstado().equalsIgnoreCase("Mantenimiento")) { // El aux accede a una habitacion
                contador++;
            }
        }
        return contador;
    }

    public void generarMatrizOcupacion() {
        for (int filas = 0; filas < matrizOcupacion.length; filas++) {
            for (int columnas = 0; columnas < 7; columnas++) {
                //Generar estado ocupado para días al azar y generar dinamismo en la tabla
                if (filas % 2 == 0 && columnas % 2 != 0) {
                    matrizOcupacion[filas][columnas] = 'O';
                }
                //Tuvimos que consultar el valor de null para que pueda correr esta parte correctamente
                if (matrizOcupacion[filas][columnas] == '\u0000') {
                    matrizOcupacion[filas][columnas] = 'D';
                }
                //Habitación en mantenimiento
                matrizOcupacion[3][1] = 'M';
                matrizOcupacion[3][2] = 'M';
            }
        }
    }

    public void registrarEstadoHabitacion() {
        String cambioEstado = JOptionPane.showInputDialog(null, "¿Desea registrar el estado de una habitación? (Si/No)");
        if (cambioEstado.equalsIgnoreCase("Si")){
            int filaOcupacion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número posición de la habitación (Según la tabla anterior) :"));
            int columnaOcupacion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número del día (0=Lun, 1=Mar, 2=Mie, 3=Jue, 4=Vie, 5=Sab, 6=Dom) "));
            String estadoOcupacion = JOptionPane.showInputDialog(null, "Ingrese 'O' para ocupar la habitación o 'D' para liberarla o dejarla libre");
            if (estadoOcupacion.equalsIgnoreCase("O")) {
                matrizOcupacion[filaOcupacion][columnaOcupacion] = 'O';
            } else {
                matrizOcupacion[filaOcupacion][columnaOcupacion] = 'D';
            }
        }
    }

    public void obtenerNumerosOcupacion() {
        //Mostrar los días de la semana, habitaciones y la matriz
        String[] dias = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
        //Arreglo que determina cuantos dias a la semana está ocupada la habitación
        int ocupacionPorDia[] = new int[7];
        String texto = "Hab |   ";
        //Generar los días automáticamente (Pone los días en la fila de arriba para cuando vaya a mostrar la matriz)
        for (int dia = 0; dia < 7; dia++) {
            texto += dias[dia] + "      ";
        }
        texto += "\n";
        //Empieza a mostrar las habitaciones y su estado
        for (int fila = 0; fila < matrizOcupacion.length; fila++) {
            //Impime los números de habitación y su estado (dejando el espacio para que se vea como una "tabla")
            texto += habitaciones[fila].getNumeroHabitacion() + "     ";
            for (int col = 0; col < 7; col++) {
                texto += matrizOcupacion[fila][col] + "           ";
                //Condición para sumar uno al contador de días ocupado si se encuentra con un 'O'
                if (matrizOcupacion[fila][col] == 'O') {
                    ocupacionPorDia[col]++;
                }
            }
            texto += "\n";
        }
        //Contadores para saber qué día tiene mayor y menor ocupación
        int mayorDia = 0;
        int menorDia = 0;
        //Ocupación total
        int totalOcupacionSemana = 0;
        for (int dia = 0; dia < 7; dia++) {
            //Cada habitación ocupada por día se le suma a la semana
            totalOcupacionSemana += ocupacionPorDia[dia];
            //Condición para comparar los días y elegir el de mayor y menor ocupación
            if (ocupacionPorDia[dia] > ocupacionPorDia[mayorDia]) mayorDia = dia;
            if (ocupacionPorDia[dia] < ocupacionPorDia[menorDia]) menorDia = dia;
        }
        //Resultado de ocupación
        texto += "\nHab: Habitación " +
                "\nDia con mas ocupacion: " + dias[mayorDia] +
                "\nDia con menos ocupacion: " + dias[menorDia] +
                "\nTotal ocupadas en la semana: " + totalOcupacionSemana;
        JOptionPane.showMessageDialog(null, texto);
    }

    public String buscarReservasEspeciales(int codigoBuscado) {
        // Buscar la reserva por el código ingresado
        Reserva reservaEncontrada = null;
        //Recorro la lista de reservas y busco el código traído del main a este método paara comparar
        for (Reserva busqueda : getReservas()) {
            if (busqueda.getCodigoReserva() == codigoBuscado) {
                reservaEncontrada = busqueda;
                break;
            }
        }
        String mensaje = "";
        if (reservaEncontrada == null) {
            mensaje += "No existe una reserva con ese numero.";
        }
        //Mostrar mensaje final
        mensaje += "Reserva: " + reservaEncontrada.getCodigoReserva() +
                "\nHuesped: " + reservaEncontrada.getHuesped().getNombreCompleto();
        //Si es especial o no
        if (reservaEncontrada.esEspecial()) {
            mensaje += "\nSu reserva es especial";
        } else {
            mensaje += "\nSu reserva no es especial";
        }
        return mensaje;
    }

    public String consultarIngresosFecha(String fechaConsulta) {
        //Variable para calcular el ingreso
        double ingresoTotalFecha = 0;
        String mensaje = "Reservas del " + fechaConsulta + ":\n";
        boolean encontroAlguna = false;
        for (Reserva busquedaFecha : getReservas()) {
            if (busquedaFecha.getFechaReserva().equals(fechaConsulta)) {
                encontroAlguna = true;
                //Si encontró alguna reserva de esa fecha, la muestra
                mensaje += "Reserva #" + busquedaFecha.getCodigoReserva() + " - Huesped: " + busquedaFecha.getHuesped().getNombreCompleto() + "\nValor total de la reserva: $" + busquedaFecha.getValorTotal() + "\n";
                //Calcular el valor de la reserva de ese día
                for (Habitacion habPrecio : busquedaFecha.getHabitaciones()) {
                    double valorHabitacion = habPrecio.getPrecioNoche() * busquedaFecha.getNumeroNoches();
                    mensaje += "   Hab " + habPrecio.getNumeroHabitacion() + " (" + busquedaFecha.getNumeroNoches() + " noches x $" + habPrecio.getPrecioNoche() + ") = $" + valorHabitacion + "\n";
                }
                mensaje += "\n";
                ingresoTotalFecha += busquedaFecha.getValorTotal();
            }
        }
        if (!encontroAlguna) {
            mensaje+="No hay reservas registradas en esa fecha.";
        }
        mensaje += "Ingreso del día " + fechaConsulta + ": $" + ingresoTotalFecha;
        return mensaje;

    }
}




