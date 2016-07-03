/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware;

import cliente.ClienteConf;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author MarcoA
 */
public class ServicioRR {
    
    ClienteConf cliente = new ClienteConf();
    
    public ServicioRR() {
        
    }
    
    public void restaura() throws RemoteException, NotBoundException{
        cliente.restaurar();
    }
    
    public void replica() throws RemoteException, NotBoundException{
        cliente.replicar();
    }
}
