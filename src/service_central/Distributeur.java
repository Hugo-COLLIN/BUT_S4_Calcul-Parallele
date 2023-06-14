import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur implements ServiceDistributeur {
    List<CalculInterface> services = new ArrayList<>();
    int indice = 0;

    @Override
    public synchronized CalculInterface demanderService() throws RemoteException {
        System.out.println();
        indice++;
        if (indice >= services.size()){
            indice = 0;
        }

        return services.get(indice);
    }

    @Override
    public synchronized void  addCalcule(CalculInterface serviceCalcule) throws RemoteException {
        synchronized (services){
            services.add(serviceCalcule);
            System.out.println(serviceCalcule);
            System.out.println("Service added");
        }


    }

    @Override
    public synchronized void deleteCalcule(CalculInterface serviceCalcule) throws RemoteException {
        synchronized (services){
            services.remove(serviceCalcule);
            System.out.println("Service deleted");
        }

    }


}
