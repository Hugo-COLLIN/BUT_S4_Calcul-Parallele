import raytracer.Image;
import raytracer.Scene;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.time.Duration;
import java.time.Instant;

public class Calcul implements CalculInterface
{
    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\n" +
            "Usage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n" +
            "\tfichier-scène : la description de la scène (par défaut simple.txt)\n" +
            "\tlargeur : largeur de l'image calculée (par défaut 512)\n" +
            "\thauteur : hauteur de l'image calculée (par défaut 512)\n";

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