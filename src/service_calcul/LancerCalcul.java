import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class LancerCalcul
{
    public static void main(String[]args) throws RemoteException
    {
        //--- Gestion des numéros de ports en paramètres du programme ---
        int portService = 0, portAnnuaire = 1099;

        //--- Gestion de la connexion RMI ---
        try {
            //On crée une instance du service
            Calcul service = new Calcul();

            //On met le service à disposition via un port de la machine
            CalculInterface serviceInterface = (CalculInterface) UnicastRemoteObject.exportObject(service, portService);

            //On récupère l'annuaire via le numero de port où il est accessible
            Registry reg = LocateRegistry.getRegistry(portAnnuaire);

            //On associe le service à un nom dans l'annuaire
            reg.rebind("NomDuServiceDansLAnnuaire", serviceInterface);

            System.out.println("Service accessible depuis l'annuaire");
        }
        catch (java.rmi.server.ExportException e)
        {
            System.err.println("Port " + portService + " déjà utilisé : impossible d'y affecter le service");
            System.exit(-1);
        }
        catch (java.rmi.ConnectException e)
        {
            System.err.println("Impossible de récupérer l'annuaire sur le port " + portAnnuaire);
            System.exit(-1); //sinon le programme ne s'arrête pas après l'exception
        }
    }
}