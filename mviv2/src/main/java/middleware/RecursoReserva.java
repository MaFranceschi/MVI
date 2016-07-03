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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MarcoA
 */
@Path("reservas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecursoReserva {
    
    ServicioReserva reservas;
    
    public RecursoReserva() throws RemoteException, NotBoundException
    {
        reservas = new ServicioReserva();
    }
    
    @GET
    public List<Reserva> obtenerReservas() throws RemoteException, NotBoundException
    {
        return reservas.obtenerReservas();
    }
    
    @GET
    @Path("/{ReservaId}")
    public Reserva obtenerReserva(@PathParam("ReservaId") String reserva) throws RemoteException, NotBoundException {
        return reservas.obtenerReserva(reserva);
    }

    @POST
    public Response agregarReserva(Reserva reserva){
        try {
            reservas.reservar(reserva);
        } catch (RemoteException ex) {
            Logger.getLogger(RecursoReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(RecursoReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok().build();
    }
    
}
