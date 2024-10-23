package chat;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2010</p>
 * <p>Soci�t� : </p>
 * @author non attribuable
 * @version 1.0
 */


import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Fenetre
    extends JPanel{
    private String image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.craiyon.com%2Fimage%2FymwURhDKSnqoOnlU0vBhgg&psig=AOvVaw2Hp7y3OVf6FmFGpkf6AGqz&ust=1706710049187000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCLjGrcmkhYQDFQAAAAAdAAAAABAE";
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(Toolkit.getDefaultToolkit().getImage(image),0,0,this);
    }
    public Fenetre(){
      super();
    }
}
