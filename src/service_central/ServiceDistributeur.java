package service_central;

import java.rmi.Remote;

public interface ServiceDistributeur extends Remote {

    /**
     * permet de recuperer un service de calcule
     * @return
     */
    public ServiceCalcule demanderService();


    /**
     * permet d'ajouter ou supprimer des ServiceCalcule
     */
    public void addCalcule(ServiceCalcule serviceCalcule);
    public void deleteCalcule(ServiceCalcule serviceCalcule);
}
