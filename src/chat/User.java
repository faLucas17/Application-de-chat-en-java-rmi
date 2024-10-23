package chat;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2010</p>
 * <p>Societ� : AM </p>
 * @author Med Zakaria Tebbessi
 * @version 1.0
 */
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.UnknownHostException;
public class User extends UnicastRemoteObject implements Serializable,Interface1{
//L'objet distant du serveur
static Contrat J;
private Client C;
boolean packFrame = false;
static String adr;
static int num_port;
static String srv_Adr;
static boolean srv_state=false;
//Constructeur de l'objet Client
public User() throws RemoteException {
  super();
  Cli_GUI cli_GUI=new Cli_GUI();
                        cli_GUI.setVisible(true);

  }
public User retourObj(){
  return this;
}
//_________________________________Classe interne d'interface graphique de connexion des clients au serveur
  public class Cli_GUI
      extends JFrame {
       private String image = "C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/am.png";
       private String image_c = "C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/connect.jpg";
       private String image_d = "C:/Documents and Settings/MZT/jbproject/Chat/classes/chat/Images/disconnect.jpg";
       JLabel Bienvenue = new JLabel();
       JLabel Pseudo_L = new JLabel();
       JTextField nom = new JTextField();
       JPasswordField pwd = new JPasswordField();
       JLabel pwd_L = new JLabel();
       JButton Connect = new JButton();
       JButton Annuler = new JButton();
       JLabel jLabel1 = new JLabel();
       JTextField jTextField1 = new JTextField("127.0.0.1");
       JLabel siegle_L;
       public Cli_GUI() {
         try {
           jbInit();
         }
         catch (Exception e) {
           e.printStackTrace();
         }
       }

       private void jbInit() throws Exception {
         this.getContentPane().setLayout(null);
         Bienvenue.setFont(new java.awt.Font("Orange", 1, 24));
         Bienvenue.setForeground(SystemColor.textHighlight);
         Bienvenue.setRequestFocusEnabled(true);
         Bienvenue.setVerifyInputWhenFocusTarget(true);
         Bienvenue.setText("Bienvenue dans Chat");
         Bienvenue.setBounds(new Rectangle(125, 28, 305, 44));
         Pseudo_L.setFont(new java.awt.Font("Dialog", 1, 14));
         Pseudo_L.setText("Pseudonyme :");
         Pseudo_L.setBounds(new Rectangle(75, 94, 128, 28));
         nom.setText("");
         nom.setBounds(new Rectangle(229, 95, 108, 24));
         pwd.setText("");
         pwd.setBounds(new Rectangle(230, 135, 108, 25));
         pwd_L.setFont(new java.awt.Font("Dialog", 1, 14));
         pwd_L.setText("Mot de passe :");
         pwd_L.setBounds(new Rectangle(76, 133, 122, 31));
         gestionnaire_B gestionnaire=new gestionnaire_B();
         Connect.addActionListener(gestionnaire);
         Connect.setBackground(new Color(239, 240, 234));
         Connect.setBounds(new Rectangle(281, 251, 142, 30));
         Connect.setText("Connexion");
         Connect.setIcon(new ImageIcon(image_c));
         Annuler.addActionListener(gestionnaire);
         Annuler.setBackground(new Color(239, 240, 234));
         Annuler.setBounds(new Rectangle(104, 252, 140, 29));
         Annuler.setText("Annuler");
         Annuler.setIcon(new ImageIcon(image_d));
         this.getContentPane().setBackground(new Color(239, 240, 234));
         this.setForeground(new Color(239, 240, 234));
         this.setTitle("Espace Clients");
         jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
         jLabel1.setText("Adresse du serveur :");
         jLabel1.setBounds(new Rectangle(75, 176, 156, 26));
         jTextField1.setBounds(new Rectangle(230, 176, 108, 25));
         Icon siegle = new ImageIcon(image);
         siegle_L = new JLabel("", siegle, SwingConstants.LEFT);
         siegle_L.setBounds(new Rectangle(0, 1, 76, 105));
         this.getContentPane().add(jLabel1, null);
         this.getContentPane().add(pwd_L, null);
         this.getContentPane().add(Pseudo_L, null);
         this.getContentPane().add(jTextField1, null);
         this.getContentPane().add(nom, null);
         this.getContentPane().add(pwd, null);
         this.getContentPane().add(Bienvenue, null);
         this.getContentPane().add(Connect, null);
         this.getContentPane().add(Annuler, null);
         this.getContentPane().add(siegle_L, null);
         this.setSize(new Dimension(439, 321));
         this.setResizable(false);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setVisible(true);
       }
//Pour femer la fenetre de la classe Cli_GUI dans le cas ou le mot de passe et le pseudonyme du client sont valides
void fermer(){
  this.dispose();
}
    private class gestionnaire_B
        implements ActionListener {
      boolean flag=false;
//Variable limitant le nombre d'essai de connexion du client � 3
      int nbr=0;
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Connect) {
          if (nbr < 3) {
            if (nom.getText().length() == 0 || pwd.getPassword().length == 0||jTextField1.getText().length()==0) {
              JOptionPane.showMessageDialog(null,
                                            "Vous devez donner votre pseudonyme, votre mot de passe ainsi que l'adresse du serveur de chat",
                                            "Informations manquantes",
                                            JOptionPane.WARNING_MESSAGE);
              nbr++;
            }
            else {
              try {
                J = (Contrat) Naming.lookup("rmi://"+jTextField1.getText()+ ":1099/JChat");
              }
              catch (RemoteException ex4) {
                JOptionPane.showMessageDialog(null,"La connexion au serveur a echoue","Serveur non demarre",JOptionPane.WARNING_MESSAGE);
                System.out.println(ex4);
                nbr++;
              }
              catch (MalformedURLException ex4) {
                JOptionPane.showMessageDialog(null,"Connexion 2","Erreur de connexion",JOptionPane.WARNING_MESSAGE);
                nbr++;
              }
              catch (NotBoundException ex4) {
                JOptionPane.showMessageDialog(null,"Serveur non joignable","Erreur de connexion",JOptionPane.WARNING_MESSAGE);
                System.out.println(ex4);
                System.exit(0);
              }
              if(J!=null){
              try {//de la m�thode connect distante
                //R�cup�ration de l'adresse de la machine du client
                try {
               adr = InetAddress.getLocalHost().getHostName();
             }
             catch (UnknownHostException ex3) {
             }
//Si le nom et le mot de passe fournies par le client sont valides
//Envoi du nom et mot de passe pour la v�rification de la validit� de ce client ainsi que le num de port
//, adresse de la machine du client et nom de l'objet distant du client pour pouvoir l'invoquer ult�rieurementult�
                num_port=J.get_num_port();
                if (J.connect(nom.getText(), new String(pwd.getPassword()),adr,num_port)) {
                srv_Adr=jTextField1.getText();
                srv_state=true;
                //fermeture de la fenetre de connexion
                  fermer();
                  try {
                    java.rmi.registry.LocateRegistry.createRegistry(num_port);
                    System.out.println("Ecoute sur le port : "+num_port);
//Placement de l'objet distant du client sur sa machine : localhost dans le registre pour le num�ro de port sp�cifi�
                    Naming.rebind("//"+adr+":"+num_port+"/"+nom.getText(), retourObj());
                    System.out.println(Naming.list("//"+adr+":"+num_port)[0]);
                  }
                                    catch (MalformedURLException ex2) {
                    System.out.println(ex2);
                  }
                  catch (RemoteException ex1) {
                    System.out.println(ex1);
                  }
                  //Constructeur graphique de la classe Client
                  C = new Client(nom.getText(), new String(pwd.getPassword()));
                  Essai essai=new Essai();
                  SplashWindow splash=new SplashWindow(C,2000);
                  if (J.getnbrcon() >= 2) {
                    System.out.println("Mise a jour de la liste du nouveau connect�");
                    //Mise � jour de la liste des destinataires du nouveau connect�
                    for (int i = 0; i < J.getnbrcon(); i++)
                      if (J.list_con()[i].compareTo(nom.getText()) != 0)
                        C.ajout(J.list_con()[i]);
                  }
                }
                else {
                  JOptionPane.showMessageDialog(null,
                      "Verifiez votre pseudonyme ou mot de passe")
                      ;
                  nbr++;
                }

              }
              catch (RemoteException ex) {
                System.out.println(ex);
              }
            }
            }
          if(nbr==3) {JOptionPane.showMessageDialog(null,"Nombre maximum d'essai est atteint");
            System.exit(0);}
        }
        }
        else {
          if (e.getSource() == Annuler)
            System.exit(0);
        }
      }
    }
  } //Classe Cli_GUI
//////////////////////////////////////////////////////////////////////////////////////////////////////
/******************************************La m�thode principale*************************************/
//////////////////////////////////////////////////////////////////////////////////////////////////////
 public static void main(String[]args){
      try {
        User user = new User();
      }
      catch (RemoteException ex1) {
      }
}//Main
//______________________________________M�thode qui met � jour la liste des destinataires d'un client lors de la connexion d'un nouveau client
  public synchronized void newcliConnexion(String nom)throws RemoteException {
C.ajout(nom);
  }
//______________________________________M�thode qui met � jour la liste des destinataires d'un client
  public synchronized  void clidisconnect(String nom)throws RemoteException {
C.supp(nom);
  }
 //______________________________________M�thode qui met � jour la liste des messages recues d'un client
  public synchronized  void updatemsgs(Communication com)throws RemoteException {
C.update("["+com.get_src()+"] : "+com.get_msg());
  }

  /**
   * close_notification
   */
  public synchronized  void close_notification()throws RemoteException {
    JOptionPane.showMessageDialog(null, "Le serveur est injoignable pour le moment","Erreur distante",JOptionPane.INFORMATION_MESSAGE);
    srv_state=false;
    C.initialiser();
  }

  /**
   * start_notification
   */
  public synchronized  void start_notification()throws RemoteException {
    JOptionPane.showMessageDialog(null, "Le serveur est de nouveau connecte","Notification du client",JOptionPane.INFORMATION_MESSAGE);
    srv_state=true;
    try {
      for (int i = 0; i < J.getnbrcon(); i++) {
        if (J.list_con()[i].compareTo(C.get_nom())!= 0) {
          C.ajout(J.list_con()[i]);
        }
      }
    }
    catch (RemoteException ex) {
    }
  }
}//Classe Client
