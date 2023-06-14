import raytracer.Disp;
import raytracer.Scene;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static List<Coordonnees> splitImage(int nbPart, int taille){
        int nbLongPart = (int) (Math.sqrt(nbPart));
        int partLength = taille/nbLongPart;
        int x = 0;
        int y = 0;
        List<Coordonnees> res = new ArrayList<>();
        for (int i=0; i<nbLongPart; i++){
            for (int j=0; j<nbLongPart; j++){
                res.add(new Coordonnees(x,y,partLength,partLength));
                x++;
            }
            y++;
        }
        return res;
    }

    public static void calculerImage(ServiceDistributeur distributeur , String scenePath, int nbpart, int taille){

        // Getting coordinates list
        List<Coordonnees> list = Client.splitImage(nbpart, taille);

        // Getting scene
        Scene scene = new Scene(scenePath, taille, taille);

        // Creation of one window
        Disp disp = new Disp("Raytracer", taille, taille);

        // for each coordinates
        for (Coordonnees coor : list){
            // in Thread

            Thread t = new Thread() {
                public void run() {
                    boolean calc = false;

                    // Whyle not calculate
                    while (!calc){
                        // Getting one 'calcule' instance
                        CalculInterface calcService = null;

                        try {
                            System.out.println("here2");
                            calcService = distributeur.demanderService();
                        } catch (RemoteException e) {
                            System.out.println("Server Not enable");
                            e.printStackTrace();
                            break;
                        }

                        // Calcule image
                        try {
                            System.out.println(calcService);
                            disp.setImage(calcService.calculer(scene, coor), coor.x, coor.y);
                            calc = true;
                        }catch (RemoteException r){
                            System.out.println("Failed to make calcul");
                            r.printStackTrace();
//                            try {
//                                distributeur.deleteCalcule(calcService);
//                            } catch (RemoteException e) {
//                                throw new RuntimeException(e);
//                            }
                            System.out.println("Sevice deleted");
                        } catch (ServerNotActiveException e) {
//                            try {
//                                distributeur.deleteCalcule(calcService);
//                            } catch (RemoteException ex) {
//                                throw new RuntimeException(ex);
//                            }
                            System.out.println("Sevice deleted");
                        }
                    }
                }
            };

            // Starting thread
            t.start();
        }

    }
}
