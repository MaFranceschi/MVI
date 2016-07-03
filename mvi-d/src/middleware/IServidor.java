package middleware;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Define las acciones que puede hacer el objeto Servidor
 */
public interface IServidor extends Remote {
        
        //Aquí se definen la firma de los métodos que serán implementados posteriormente en la clase ServidorConf
        //Los métodos deberán arrojar la excepción RemoteException
        public void cargarVuelo(String linea,String origen, String destino, String fecha, String hora, 
                String capacidad, String precio) throws RemoteException;
        public void reservarVuelo(String nombre, String vueloReserva) throws RemoteException;
        
        public ArrayList<Reserva> verReservas() throws RemoteException;
        public void actualizarDisponibilidad(String vueloReserva) throws RemoteException;
        
        public ArrayList<Vuelo> verDisponibilidad() throws RemoteException;
        public ArrayList<Reserva> reservasPorNombre(String nombre) throws RemoteException;
        
        public byte[] leerArchivo(String fileName) throws RemoteException;
        public void escribirArchivo(byte[] filedata, String fileName) throws RemoteException;

}
