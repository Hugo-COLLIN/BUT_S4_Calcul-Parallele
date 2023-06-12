import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface CalculInterface extends Remote
{
    /**
     * @param scene
     * @param x
     * @param y
     * @param l
     * @param h
     * @throws RemoteException
     * @throws ServerNotActiveException
     */
    public Image calculer(Scene scene, int x, int y, int l, int h) throws RemoteException, ServerNotActiveException;
}