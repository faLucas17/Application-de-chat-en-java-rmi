package chat;

import java.io.*;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2010</p>
 * <p>Societé : </p>
 * @author MZT
 * @version 1.0
 */

public class Communication implements Serializable  {
private String dest;
private String msg;
private String src;
public Communication(String src,String dest,String msg){
    this.dest=dest;
    this.src=src;
    this.msg=msg;
}
  public String get_dest(){
    return dest;
  }
  public String get_src(){
   return src;
 }
 public String get_msg(){
   return msg;
 }

public String toString(){
   return "["+src+"-->"+dest+"]"+": "+msg;
}

}
