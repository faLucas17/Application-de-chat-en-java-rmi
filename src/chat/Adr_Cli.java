package chat;

import java.io.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2010</p>
 * <p>Société : </p>
 * @author MZT
 * @version 1.0
 */

public class Adr_Cli  {
  private String nom;
  private String adr;
  private int num_port;
  public Adr_Cli(String nom,String adr,int num_port) {
    this.nom=nom;
    this.adr=adr;
    this.num_port=num_port;
  }
public String get_adr(){
  return adr;
}
public int get_port(){
  return num_port;
}
public String get_n(){
  return nom;
}
}
