
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Start {
    public static void main(String[] args) {
        ServiceDistributeur dist = null;
        try {
            Registry reg = LocateRegistry.getRegistry(args[0], 4552);
            dist = (ServiceDistributeur) reg.lookup("distributeur");
        } catch (AccessException a) {
            System.out.println("Failed access ");
        } catch(RemoteException r) {
            System.out.println("Error in getting registry" + r.getMessage());
            System.exit(0);
        } catch (NotBoundException a) {
            System.out.println("service named "+ a.getMessage() + " not found");
            System.exit(0);
        }

        // Settings
        int Length = 512;
        int nbPart = 100;
        String scenePath = "./simple.txt";

        // Start image calcule
        Client.calculerImage(dist,scenePath,nbPart,Length);
    }
}
