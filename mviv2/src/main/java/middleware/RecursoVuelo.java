/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MarcoA
 */
@Path("vuelos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecursoVuelo {
    
    ServicioVuelo vuelos= new ServicioVuelo();;
    
    
        @GET
    public List<Vuelo> obtenerVuelos() throws RemoteException, NotBoundException
    {
        return vuelos.obtenerVuelos();
    }
    
    @GET
    @Path("/{vueloId}")
    public Vuelo obtenerVuelo(@PathParam("vueloId") String vuelo) {
        Vuelo v1;
        try {
            v1 = vuelos.obtenerVuelo(vuelo);
            return v1;
        } catch (RemoteException ex) {
            Logger.getLogger(RecursoVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(RecursoVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @POST
    public Vuelo agregarVuelo(Vuelo vuelo) {
        try {
            vuelos.agregarVuelo(vuelo);
        } catch (RemoteException ex) {
            Logger.getLogger(RecursoVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(RecursoVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vuelo;
    }

}
