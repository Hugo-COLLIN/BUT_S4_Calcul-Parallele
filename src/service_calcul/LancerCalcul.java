import java.rmi.NotBoundException;
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
        int portService = 0;

        //--- Gestion de la connexion RMI ---
        try {
            //On crée une instance du service
            Calcul service = new Calcul();

            //On met le service à disposition via un port de la machine
            CalculInterface serviceInterface = (CalculInterface) UnicastRemoteObject.exportObject(service, portService);

            // Getting central service
            Registry reg = LocateRegistry.getRegistry(args[0], 4552);
            ServiceDistributeur dist = (ServiceDistributeur) reg.lookup("distributeur");

            // Register to the Central service
            dist.addCalcule(serviceInterface);

            System.out.println("Service noeudCalcul Started");
        }catch (java.rmi.server.ExportException e) {
            System.err.println("Port " + portService + " déjà utilisé : impossible d'y affecter le service");
            System.exit(-1);
        }catch (RemoteException r){
            System.out.println("Failed to add calcule to serveer");
            r.printStackTrace();
        }
         catch (NotBoundException e) {
            System.out.println("Failed to get central Service named 'Distibuteur' ");
        }
    }
}