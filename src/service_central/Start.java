package service_central;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


public class Start {
    public static void main(String[] args) {
        try {
            Distributeur object = new Distributeur();
            ServiceDistributeur rd = (ServiceDistributeur)UnicastRemoteObject.exportObject(object,0);
            Registry reg = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            reg.rebind("distributeur", rd);
            System.out.println("Service and Registry started");
        } catch (AccessException a) {
            System.out.println("Failed to access regsitry : " + a.getMessage());
        } catch(RemoteException r){
            System.out.println("Error in getting registry" + r.getMessage());
        }
    }
}