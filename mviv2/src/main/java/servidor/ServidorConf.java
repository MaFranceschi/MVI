package servidor;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import middleware.IServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import middleware.Reserva;
import middleware.Vuelo;

//Clase que se encarga de implementar los métodos en la clase IServidor
public class ServidorConf extends UnicastRemoteObject implements IServidor {

        //Se le da el nombre al servidor (D,R y P) que será registrado en el registry
	static final String RMI_NAME = "SVR-D";
       
        //Se crea un registry que será usado posteriormente en la instanciación del servidor
	private final Registry registry;
        
        //Constructor del método servidor
	protected ServidorConf(Registry registry) throws RemoteException {
		super();
                
		this.registry = registry;
	}

    //Genera un nuevo vuelo en el servidor D
    @Override
    public void cargarVuelo(String linea, String origen, String destino, String fecha, 
            String hora, String capacidad, String precio) throws RemoteException {
        Vuelo.CargarVuelo(linea, origen, destino, fecha, hora, capacidad, precio);
    }

    //Reserva un vuelo en el servidor R
    @Override
    public void reservarVuelo(String nombre, String vueloReserva) throws RemoteException {
        Reserva.ReservarVuelo(nombre, vueloReserva);
    }


    //Devuelve una lista de vuelos disponibles del servidor D
    @Override
    public ArrayList<Vuelo> verDisponibilidad() throws RemoteException {
        return Vuelo.ConsultarVuelos();
    }


    //Devuelve una lista de Reservas
    @Override
    public ArrayList<Reserva> verReservas() throws RemoteException {
        return Reserva.ConsultarReservas();
    }

    //Actualiza la capacidad de un vuelo
    @Override
    public void actualizarDisponibilidad(String vueloReserva) throws RemoteException {
        Vuelo.EditarCapacidadVuelo(vueloReserva);
    }

    @Override
    public ArrayList<Reserva> reservasPorNombre(String nombre) throws RemoteException {
        return Reserva.ReservasPorNombre(nombre);
    }

    @Override
    public byte[] leerArchivo(String fileName) throws RemoteException{
      try {
         File file = new File(fileName);
         byte buffer[] = new byte[(int)file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer,0,buffer.length);
         input.close();
         return(buffer);
      } catch(Exception e){
         e.printStackTrace();
         return(null);
      }
   }

    @Override
    public void escribirArchivo(byte[] filedata, String fileName) throws RemoteException {
        File file = new File(fileName);
        try {    
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file.getName()));
            output.write(filedata,0,filedata.length);
            output.flush();
            output.close();
        } catch (IOException ex) {
        }
    }
    
}
