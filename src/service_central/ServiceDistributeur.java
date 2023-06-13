
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceDistributeur extends Remote {

    /**
     * permet de recuperer un service de calcule
     * @return
     */
    public CalculInterface demanderService() throws RemoteException;


    /**
     * permet d'ajouter ou supprimer des ServiceCalcule
     */
    public void addCalcule(CalculInterface serviceCalcule) throws RemoteException;
    public void deleteCalcule(CalculInterface serviceCalcule) throws RemoteException;
}
