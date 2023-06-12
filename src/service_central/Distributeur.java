package service_central;

import java.util.List;

public class Distributeur implements ServiceDistributeur{
    List<ServiceCalcule> services;
    int indice = 0;

    @Override
    public ServiceCalcule demanderService() {
        indice++;
        if (indice> services.size()){
            indice = 0;
        }
        return services.get(indice);
    }

    @Override
    public void addCalcule(ServiceCalcule serviceCalcule) {
        services.add(serviceCalcule);
    }

    @Override
    public void deleteCalcule(ServiceCalcule serviceCalcule) {
        services.remove(serviceCalcule);
    }

}
