import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    int turno=0;
    static int comenzar=2;
    Carta cartas;
    Jugador player;
    int tokenJugador=0;
    int tokenMaquina=0;
    CPU maquina;
    static Random aleatorio;
    ArrayList<Carta> cartaMazo=new ArrayList<Carta>();
    String nombreJugador="";
    String nombreRival="";
    boolean turnoJugador=false;
    boolean turnoCpu=false;
    boolean perdioJugador=false;
    boolean perdioRival=false;
	private String nombreCartaJugada1;
	private String nombreCartaJugada2;

    public Main(){
    
    
    
    }
    
    public static void main(String[]args){
        
    Main prin=new Main();

    
    prin.anadirCartasalMazo();
    //prin.lista();
    prin.Menu();
    
    }
    
    public void lista(){
        
        for(int i=0;i<cartaMazo.size();i++){
        System.out.println(cartaMazo.get(i).getNombre());
        System.out.println(cartaMazo.get(i).getAccion());
    }
    }
    
    
    public void anadirCartasalMazo(){
        
        for(int i=0;i<=4;i++){
            cartas=new Carta();
            cartas.setNombre("GUARD");
            cartas.setAccion("ADIVINAR MANO");
            cartas.setNumero(1);
            cartaMazo.add(cartas);
            
        }   
        
        for(int i=0;i<=1;i++){
            cartas=new Carta();
            cartas.setNombre("PRIEST");
            cartas.setAccion("PERMITE VER LA MANO DEL RIVAL");
            cartas.setNumero(2);
            cartaMazo.add(cartas);
        }
        
        for(int i=0;i<=1;i++){
            cartas=new Carta();
            cartas.setNombre("BARON");
            cartas.setAccion("COMPARA MANOS CON EL RIVAL, CARTA MAS BAJA SALE DEL JUEGO, CARTAS IGUALES EMPATE");
            cartas.setNumero(3);
            cartaMazo.add(cartas);
            
        }
        for(int i=0;i<=1;i++){
            cartas=new Carta();
            cartas.setNombre("HANDMAID");
            cartas.setAccion("INMUNE POR UN TURNO");
            cartas.setNumero(4);
            cartaMazo.add(cartas);
        }
        
        for(int i=0;i<=1;i++){
            cartas=new Carta();
            cartas.setNombre("PRINCE");
            cartas.setAccion("BOTAR MANO JUGADOR O RIVAL, TOMAR UNA CARTA DEL DECK");
            cartas.setNumero(5);
            cartaMazo.add(cartas);
        }
        
        cartas=new Carta();
        cartas.setNombre("KING");
        cartas.setAccion("INTERCAMBIA MANOS ENTRE JUGADORES");
        cartas.setNumero(6);
        cartaMazo.add(cartas);
        
        cartas=new Carta();
        cartas.setNombre("COUNTESS");
        cartas.setAccion("SI TIENE PRINCE O KING DEBE JUGAR LA COUNTESS");
        cartas.setNumero(7);
        cartaMazo.add(cartas);
        
        cartas=new Carta();
        cartas.setNombre("PRINCESS");
        cartas.setAccion("SI BOTAS ESTA CARTA PIERDES");
        cartas.setNumero(6);
        cartaMazo.add(cartas);
        
    }
    
    public void Menu(){
        
        System.out.println("- = Bienvenido a Love Letter = - ");
        System.out.println("(1)JUGAR UNA PARTIDA");
        System.out.println("(2)SELECCIONAR EL NUMERO DE TOKENS A JUGAR");
        System.out.println("(3)MOSTRAR LAS REGLAS DEL JUEGO");
        System.out.println("(4)SALIR");
        //System.out.println(cartaMazo.size());
        
        Scanner sc=new Scanner(System.in);
        int a= sc.nextInt();
        
        
        if(a==1){
            System.out.println("INGRESE EL NOMBRE DEL JUGADOR");
            Scanner nombre=new Scanner(System.in);  
            nombreJugador=nombre.nextLine();
            System.out.println("INGRESE EL NOMBRE DEL RIVAL");
            nombreRival=nombre.nextLine();
            jugarPartida(nombreJugador,nombreRival);
            
            /*Metodo jugar*/
            
        }else if(a==2){
            //Numero tokens
        }else if(a==3){
            //Reglas
        }else if(a==4){
            System.exit(0);
        }
        
        
    }
    
    public void jugarPartida(String nombre,String cpu){
        
        player=new Jugador();
        player.setNombre(nombre);
        maquina=new CPU();
        maquina.setNombre(cpu);
        //Barajear
        retirarCartasInicio();
        darCarta(player,maquina);
        
        if((tokenJugador==0)&&(tokenMaquina==0)){
        	aleatorio=new Random();
           	turno=aleatorio.nextInt(comenzar);
           	
           	if(turno==1){
           		player.cartaJugador.add(cartaMazo.get(cartaMazo.size()-1));
           		player.setCarta2(player.cartaJugador.get(1).getNombre());
           		cartaMazo.remove(cartaMazo.size()-1);
           		maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size()-1));
           		maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
           		cartaMazo.remove(cartaMazo.size()-1);
           		turnoJugador(player,maquina,turno);
           	}else if(turno==0){
           		player.cartaJugador.add(cartaMazo.get(cartaMazo.size()-1));
           		player.setCarta2(player.cartaJugador.get(1).getNombre());
           		cartaMazo.remove(cartaMazo.size()-1);
           		maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size()-1));
           		maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
           		cartaMazo.remove(cartaMazo.size()-1);
           		turnoJugador(player,maquina,turno);
           	}
           	
           	
        }else{
        	
        	turno=1;
        
       	if(turno==1){
       		player.cartaJugador.add(cartaMazo.get(cartaMazo.size()-1));
       		player.setCarta2(player.cartaJugador.get(1).getNombre());
       		cartaMazo.remove(cartaMazo.size()-1);
       		maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size()-1));
       		maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
       		cartaMazo.remove(cartaMazo.size()-1);
       		turnoJugador(player,maquina,turno);
       	}else if(turno==0){
       		player.cartaJugador.add(cartaMazo.get(cartaMazo.size()-1));
       		player.setCarta2(player.cartaJugador.get(1).getNombre());
       		cartaMazo.remove(cartaMazo.size()-1);
       		maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size()-1));
       		maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
       		cartaMazo.remove(cartaMazo.size()-1);
       		turnoJugador(player,maquina,turno);
       	}
       	
        }
        
    }
    
    public void retirarCartasInicio(){
        
        for(int i=0;i<3;i++){
            cartaMazo.remove(i);
        }
    }
   
    public int cambiarTurno(int turno){
        
        if(turno==1){
            turnoJugador=false;
            return 0;
        }else if(turno==0){
            turnoCpu=false;
            return 1;
        }else{
        	return -1;
        }
        
    }

    public void darCarta(Jugador jugador,CPU cpu) {
        
        jugador.cartaJugador.add(cartaMazo.get(12));
        jugador.setCarta1(jugador.cartaJugador.get(0).getNombre());
        cartaMazo.remove(12);
        
        cpu.cartaCpu.add(cartaMazo.get(11));
        cpu.setCarta1(cpu.cartaCpu.get(0).getNombre());
        cartaMazo.remove(11);
        
            System.out.println(cartaMazo.size());
//        for(int i=0;i<cartaMazo.size();i++){
//            System.out.println(cartaMazo.get(i).getNombre());
//        }
        
    }
    
    public void turnoJugador(Jugador jugador,CPU cpu,int turno){
        
        
        System.out.println("Nombre del Jugador 1: "+jugador.getNombre()+"         Nombre del Jugador 2: "+cpu.getNombre());
        
        if(turno==1){
        	if(perdioRival==true){
        		System.out.println("Has ganado esta partida");
        		
        	}else{
        		if(jugador.cartaJugador.size()==1){
        			if(jugador.cartaJugador.get(0)==null){
        				jugador.cartaJugador.add(0,cartaMazo.get(cartaMazo.size()-1));
        			}else{
        				jugador.cartaJugador.add(cartaMazo.get(cartaMazo.size()-1));
        			}
        		}
        System.out.println("Turno de: "+jugador.getNombre());
        System.out.println("Tienes en tu mano: "+"Nombre Carta 1: "+jugador.getCarta1()+" Nombre Carta 2: "+jugador.getCarta2());
        System.out.println("(1)Usar Carta #1");
        System.out.println("(2)Usar Carta #2");
        System.out.println("(3)Terminar Juego");
        Scanner sc=new Scanner(System.in);
        int opcion=sc.nextInt();
        	
        	if(opcion==1){
        		nombreCartaJugada1 = jugador.getCarta1();
        		if(nombreCartaJugada1.equals("PRINCESS")){
        			perdioJugador=true;
        			turnoJugador(jugador,cpu,cambiarTurno(turno));
        			
        		}else if(nombreCartaJugada1.equals("KING")){
        			jugador.cartaJugador.remove(0);
        			jugador.cartaJugador.add(0, cpu.cartaCpu.get(0));
        			cpu.cartaCpu.remove(0);
        			cpu.cartaCpu.add(0, jugador.cartaJugador.get(1));
        			jugador.cartaJugador.remove(1);
        			jugador.cartaJugador.add(cpu.cartaCpu.get(1));
        			cpu.cartaCpu.remove(1);
        			turnoJugador(jugador,cpu,cambiarTurno(turno));
        			
        		}else if(nombreCartaJugada1.equals("GUARD")){
        			System.out.println("ELIJE UNA CARTA");
        			System.out.println("(2)PRIEST\n"
        					+ "(3)BARON\n"
        					+ "(4)HANDMAID\n"
        					+ "(5)PRINCE\n"
        					+ "(6)KING\n"
        					+ "(7)GUARD\n"
        					+ "(8)PRINCESS\n");
        			opcion=sc.nextInt();
        			
        			if(opcion==cpu.cartaCpu.get(0).getNumero()){
        				perdioRival=true;
        				turnoJugador(jugador,cpu,cambiarTurno(turno));
        			}else if(opcion==cpu.cartaCpu.get(1).getNumero()){
        				perdioRival=true;
        				turnoJugador(jugador,cpu,cambiarTurno(turno));
        			}else{
        				
        			}
        			
        		}
        	}
        	}
        }
        else if(turno==0){
        	if(perdioJugador==true){
        		System.out.println("Ha ganado El Rival");
        	}
        	if(cpu.cartaCpu.size()==1){
    			if(cpu.cartaCpu.get(0)==null){
    				cpu.cartaCpu.add(0,cartaMazo.get(cartaMazo.size()-1));
    			}else{
    				cpu.cartaCpu.add(cartaMazo.get(cartaMazo.size()-1));
    			}
    		}
        	System.out.println("Turno de: "+cpu.getNombre());
            
        }else{
        	
        }
        
        
    }
    
}
