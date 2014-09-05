package LoveLetter;
import java.util.ArrayList;


public class Jugador {
    
    String nombre="";
    int tokenGanados=0;
    String carta1="";
    String carta2="";
    boolean handmaid=false;
    ArrayList<Carta> cartaJugador=new ArrayList<Carta>();
    
    public Jugador(){
        
    }

    public boolean isHandmaid() {
        return handmaid;
    }

    public void setHandmaid(boolean handmaid) {
        this.handmaid = handmaid;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTokenGanados() {
        return tokenGanados;
    }

    public void setTokenGanados(int tokenGanados) {
        this.tokenGanados = tokenGanados;
    }

    public String getCarta1() {
        return carta1;
    }

    public void setCarta1(String carta1) {
        this.carta1 = carta1;
    }

    public String getCarta2() {
        return carta2;
    }

    public void setCarta2(String carta2) {
        this.carta2 = carta2;
    }

    
    
    
    
}
