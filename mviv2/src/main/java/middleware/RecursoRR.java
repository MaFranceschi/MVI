/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MarcoA
 */
@Path("rr")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecursoRR {
        ServicioRR rr;
    
    public RecursoRR() throws RemoteException, NotBoundException
    {
        rr = new ServicioRR();
    }
    
    @POST
    @Path("/restaurar")
    public Response restaurar(){
            try {
                rr.restaura();
            } catch (RemoteException ex) {
                Logger.getLogger(RecursoRR.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(RecursoRR.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Response.ok().build();
    }
    
    @POST
    @Path("/replicar")
    public Response replicar(){
            try {
                rr.replica();
            } catch (RemoteException ex) {
                Logger.getLogger(RecursoRR.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(RecursoRR.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Response.ok().build();
    }

}
