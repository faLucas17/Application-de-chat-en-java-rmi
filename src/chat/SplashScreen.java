package chat;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class SplashWindow extends JWindow {


    public SplashWindow(final Frame f, int waitTime) {
        super(f);
        //cree un label avec notre image
        JLabel jlabel = new JLabel("");
        jlabel.setIcon(new ImageIcon("https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.pngtree.com%2Ffreepng%2Fsocial-media-chat-icons-set-friendship-people-diversity-vector_9369334.html&psig=AOvVaw1ITfW7cG_fj5dvUomlRY-i&ust=1706642580436000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOjOpqKpg4QDFQAAAAAdAAAAABAI"));
        //jlabel.setFont(new Font("serif",Font.BOLD,14));
        //jlabel.setBackground(Color.BLUE);
        //ajoute le label au panel
        getContentPane().add(jlabel, BorderLayout.CENTER);
        pack();

        //centre le splash screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = jlabel.getPreferredSize();
        setLocation(screenSize.width / 2 - (labelSize.width / 2),
                screenSize.height / 2 - (labelSize.height / 2));

        //rend le splash screen invisible lorsque l'on clique dessus
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });

        //afin d'acceder � la valeur WaitTime
        final int pause = waitTime;

        //thread pour fermer le splash screen
        final Runnable closeRunner = new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
                f.setVisible(true);
            }
        };


        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(pause);
                    //lance le thread qui ferme le splash screen
                    SwingUtilities.invokeAndWait(closeRunner);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //affiche le splash screen
        setVisible(true);

        //lance le thread qui ferme le splash screen apres un certain temps
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
}
