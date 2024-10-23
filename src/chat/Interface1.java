package chat;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2010</p>
 * <p>Société : </p>
 * @author non attribuable
 * @version 1.0
 */
import java.rmi.*;

public interface Interface1 extends Remote {
  void newcliConnexion(String nom)throws RemoteException;
  void clidisconnect(String nom)throws RemoteException;
  void updatemsgs(Communication com)throws RemoteException;
  void close_notification()throws RemoteException;
  void start_notification()throws RemoteException;
}
