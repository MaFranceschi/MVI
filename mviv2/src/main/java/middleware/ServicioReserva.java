/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import cliente.ClienteConf;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


/**
 *
 * @author MarcoA
 */
public class ServicioReserva {
    
    ClienteConf cliente = new ClienteConf();
    private List<Reserva> listaReservas;
    
    public ServicioReserva() {
        
    }
    
    public List<Reserva> obtenerReservas() throws RemoteException, NotBoundException{
        this.listaReservas = cliente.obtenerReservas();
        return this.listaReservas;
    }
    
    public List<Reserva> obtenerReservasPorNombre(String nombre){
        List<Reserva> listaReservasPorNombre = null;
        return listaReservasPorNombre;
    }
    
    public Reserva obtenerReserva(String reservaId) throws RemoteException, NotBoundException{
        this.listaReservas = cliente.obtenerReservas();
        for(Reserva r1 : this.listaReservas)
        {
             if(r1.getReserva().equalsIgnoreCase(reservaId))
                 return r1;
        }
        return null;
    }
    
    public void reservar(Reserva reserva) throws RemoteException, NotBoundException{
        cliente.reservarVuelo(reserva.getNombre(), reserva.getVuelo());
        cliente.actualizarDisponibilidad(reserva.getVuelo());
    }
}
