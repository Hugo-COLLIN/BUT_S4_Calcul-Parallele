import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Distributeur implements ServiceDistributeur {
    List<CalculInterface> services = new ArrayList<>();
    int indice = 0;

    @Override
    public CalculInterface demanderService() throws RemoteException {
        indice++;
        if (indice <= services.size()){
            indice = 0;
        }

        return services.get(indice);
    }

    @Override
    public void addCalcule(CalculInterface serviceCalcule) throws RemoteException {
        services.add(serviceCalcule);
        System.out.println(serviceCalcule);
        System.out.println("Service added");
    }

    @Override
    public void deleteCalcule(CalculInterface serviceCalcule) throws RemoteException {
        services.remove(serviceCalcule);
        System.out.println("Service deleted");
    }

}
