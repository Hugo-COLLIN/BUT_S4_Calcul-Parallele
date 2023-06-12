import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class LancerCalcul
{
    public static String aide = """
            Noeud de calcul pour le raytracer (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))
            Usage : java LancerCalcul [port-annuaire] [port-annuaire]
            \tport-annuaire : le port où est accessible l'annuaire (par défaut 1099)
            \tport-annuaire : le port où sera accessible le service (par défaut 0)
            """;

    public static void main(String[]args) throws RemoteException
    {
        //--- Gestion des numéros de ports en paramètres du programme ---
        int portService = 0, portAnnuaire = 1099;

        if(args.length > 0) {
            portAnnuaire = Integer.parseInt(args[0]);
            if(args.length > 1)
                portService = Integer.parseInt(args[1]);
        } else {
            System.out.println(aide);
        }

        //--- Gestion de la connexion RMI ---
        try {
            //On crée une instance du service
            Calcul service = new Calcul();

            //On met le service à disposition via un port de la machine
            CalculInterface serviceInterface = (CalculInterface) UnicastRemoteObject.exportObject(service, portService);

            //On récupère l'annuaire via le numero de port où il est accessible
            Registry reg = LocateRegistry.getRegistry(portAnnuaire);

            //On associe le service à un nom dans l'annuaire
            reg.rebind("noeudCalcul", serviceInterface);

            System.out.println("Service noeudCalcul accessible depuis l'annuaire");
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