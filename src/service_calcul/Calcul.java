import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.time.Duration;
import java.time.Instant;

public class Calcul implements CalculInterface
{
    public Calcul() throws RemoteException
    {
        //code à exécuter à la création de l'objet (affectations...)
    }

    /**
     * @param scene
     * @param x
     * @param y
     * @param l
     * @param h
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    @Override
    public Image calculer(Scene scene, int x, int y, int l, int h) throws RemoteException, ServerNotActiveException {
        // Chronométrage du temps de calcul
        Instant debut = Instant.now();
        System.out.println("Calcul de l'image :\n - Coordonnées : "+ x +","+ y +"\n - Taille "+ l + "x" + h);
        Image image = scene.compute(x, y, l, h);
        Instant fin = Instant.now();

        long duree = Duration.between(debut, fin).toMillis();

        return image;
    }
}