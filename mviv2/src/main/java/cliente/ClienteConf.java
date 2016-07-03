package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import middleware.IServidor;
import middleware.Reserva;
import middleware.Vuelo;

//Se indica como el cliente va a acceder a los métodos remotos ubicados en el Servidor
public class ClienteConf {

        //Se definen los datos del servidor a conectar
        //Servidor D (Disponibilidad)
        private String remoteHostD = "192.168.10.184";
        private int remotePortD = 7777;

        //Servidor MR (Middleware y Reserva)
        private String remoteHostR = "192.168.10.174";
        private int remotePortR = 8888;
        
        //Servidor MRR (Coordina Replica y Restauración)
        private String remoteHostRR = "192.168.10.188";
        private int remotePortRR = 9999;
        
        //Devuelve una lista de los vuelos disponibles
        public ArrayList<Vuelo> consultarDisponibilidad() throws RemoteException, NotBoundException {
                ArrayList<Vuelo> listaVuelos = new ArrayList();
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostD, remotePortD);
                //Se obtiene el objeto remoto del servidor de disponibilidad, buscandolo por su nombre
		Object obj = registry.lookup("SVR-D");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
		listaVuelos = server.verDisponibilidad();
                return listaVuelos;
		
        }
        
        //Carga un nuevo vuelo
        public void cargarNuevoVuelo(String linea,String origen, String destino, String fecha, String hora, 
                String capacidad, String precio) throws RemoteException, NotBoundException {
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostD, remotePortD);
                //Se obtiene el objeto remoto del servidor de disponibilidad, buscandolo por su nombre
		Object obj = registry.lookup("SVR-D");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
                server.cargarVuelo(linea, origen, destino, fecha, hora, capacidad, precio);

        }

        //Actualiza la capacidad de los vuelos
        public void actualizarDisponibilidad(String vueloReserva) throws RemoteException, NotBoundException {
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostD, remotePortD);
                //Se obtiene el objeto remoto del servidor de disponibilidad, buscandolo por su nombre
		Object obj = registry.lookup("SVR-D");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
                server.actualizarDisponibilidad(vueloReserva);

        }

        //Crea una nueva reserva de un vuelo
        public void reservarVuelo(String nombre, String vueloReserva) throws RemoteException, NotBoundException{
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostR, remotePortR);
                //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
		Object obj = registry.lookup("SVR-R");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
                server.reservarVuelo(nombre, vueloReserva);
        }
        
        //Se obtiene la lista de reservas
        public ArrayList<Reserva> obtenerReservas() throws RemoteException, NotBoundException{
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostR, remotePortR);
                //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
		Object obj = registry.lookup("SVR-R");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
                return server.verReservas();
        }
        
        
        //Obtener lista de reservas por nombre
        public ArrayList<Reserva> obtenerReservasPorNombre(String nombre) throws RemoteException, NotBoundException{
                //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
                Registry registry = LocateRegistry.getRegistry(remoteHostR, remotePortR);
                //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
		Object obj = registry.lookup("SVR-R");
                //Se instancia el objeto para hacer uso a sus métodos remotos
		final IServidor server = (IServidor)obj;
                //Se uso del método deseado
                return server.reservasPorNombre(nombre);
        }
    
        
        public void restaurar() throws RemoteException, NotBoundException {
        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryRR = LocateRegistry.getRegistry(remoteHostRR, remotePortRR);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objRR = registryRR.lookup("SVR-RR");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverRR = (IServidor)objRR;
            
        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryReserva = LocateRegistry.getRegistry(remoteHostR, remotePortR);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objReserva = registryReserva.lookup("SVR-R");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverReserva = (IServidor)objReserva;
        //Leer el archivo a restaurar
        byte[] ArchivoReserva = serverRR.leerArchivo("reserva.xml");
        //Restaura el archivo en el servidor correspondiente
         serverReserva.escribirArchivo(ArchivoReserva, "reserva.xml");
         
        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryDisponibilidad = LocateRegistry.getRegistry(remoteHostD, remotePortD);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objDisponibilidad = registryDisponibilidad.lookup("SVR-D");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverDisponibilidad = (IServidor)objDisponibilidad;
        //Leer el archivo a restaurar
        byte[] ArchivoDisponibilidad = serverRR.leerArchivo("disponibilidad.xml");
        //Restaura el archivo en el servidor correspondiente
        serverDisponibilidad.escribirArchivo(ArchivoDisponibilidad,"disponibilidad.xml");
   }

    public void replicar() throws RemoteException, NotBoundException {
        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryRR = LocateRegistry.getRegistry(remoteHostRR, remotePortRR);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objRR = registryRR.lookup("SVR-RR");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverRR = (IServidor)objRR;

        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryReserva = LocateRegistry.getRegistry(remoteHostR, remotePortR);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objReserva = registryReserva.lookup("SVR-R");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverReserva = (IServidor)objReserva;
        //Se uso del método deseado
        byte[] ArchivoReserva = serverReserva.leerArchivo("reserva.xml");
        //Escribir el archivo en el coordinador para resguardarlo
        serverRR.escribirArchivo(ArchivoReserva,"reserva.xml");
        
        //Se localiza el registry donde se encuentran registrados los objetos remotos, usando una ip y un puerto
        Registry registryDisponibilidad = LocateRegistry.getRegistry(remoteHostD, remotePortD);
         //Se obtiene el objeto remoto del servidor de reserva, buscandolo por su nombre
	Object objDisponibilidad = registryDisponibilidad.lookup("SVR-D");
        //Se instancia el objeto para hacer uso a sus métodos remotos
	final IServidor serverDisponibilidad = (IServidor)objDisponibilidad;
        //Se uso del método deseado
        byte[] ArchivoDisponibilidad = serverDisponibilidad.leerArchivo("disponibilidad.xml");
        //Escribir el archivo en el coordinador para resguardarlo
        serverRR.escribirArchivo(ArchivoDisponibilidad,"disponibilidad.xml");
        
    }
        
        

}
