/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import cliente.ClienteConf;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcoA
 */
public class ServicioVuelo {
    
    ClienteConf cliente = new ClienteConf();
    private List<Vuelo> listaVuelos;

    public ServicioVuelo()  {
    }
    
    public List<Vuelo> obtenerVuelos() throws RemoteException, NotBoundException{
        this.listaVuelos = cliente.consultarDisponibilidad();
        return this.listaVuelos;
    }
    
    public Vuelo obtenerVuelo(String vueloId) throws RemoteException, NotBoundException{
        this.listaVuelos = cliente.consultarDisponibilidad();
        for(Vuelo v1 : this.listaVuelos)
        {
            if( v1.getId().equalsIgnoreCase(vueloId))
                return v1;
        }
        return null;
    }
    
    public Vuelo agregarVuelo(Vuelo vuelo) throws RemoteException, NotBoundException {
        if(vuelo != null)
            cliente.cargarNuevoVuelo(vuelo.getLinea(), vuelo.getOrigen(), vuelo.getDestino(), vuelo.getFecha(), vuelo.getHora(), vuelo.getCapacidad(), vuelo.getPrecio());
       return vuelo;
    }
}
