package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

	public static void main(String[] args) throws Exception {
                
                //Define los parámetros para crear el Registry, donde se almacenarán los métodos remotos
                final String rmiHostname = "192.168.10.174";
                final int registryPort = 8888;
                
                //Se cambia la variable hostname, a la dirección IP, donde estará ubicado el Registry
                System.setProperty("java.rmi.server.hostname", rmiHostname);
                
                //Creación del Registry en el puerto definido anteriormente
                final Registry registry = LocateRegistry.createRegistry(registryPort);
                //Creador de un nuevo objeto de tipo Servidor, donde se encuentran implementados los métodos remotos
		ServidorConf master = new ServidorConf(registry);
                //Registro del objeto remoto 'Servidor' para poder hacer uso de sus métodos
		registry.rebind(ServidorConf.RMI_NAME, master);

		System.out.println("Servidor R de Reserva iniciado...");
		Servidor server = new Servidor();
		synchronized (server) {
			try {
				server.wait();
			} catch (InterruptedException e) {
				// Me han despertado, tengo que terminar la ejecución.
			}
		}
	}
}
