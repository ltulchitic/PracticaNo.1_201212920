import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	int turno = 0;
	static int comenzar = 2;
	static Main prin;
	Carta cartas;
	Jugador player;
	int tokenJugador = 0;
	int tokenMaquina = 0;
	CPU maquina;
	static Random aleatorio;
	static ArrayList<Carta> cartaMazo = new ArrayList<Carta>();
	String nombreJugador = "";
	String nombreRival = "";
	boolean turnoJugador = false;
	boolean turnoCpu = false;
	boolean perdioJugador = false;
	boolean perdioRival = false;
	Reglas rule;
	private String nombreCartaJugada1;
	private String nombreCartaJugada2;
	int cpuAdivina = 0;

	// CONSTRUCTOR
	public Main() {

	}

	// METODO MAIN
	public static void main(String[] args) {

		prin = new Main();

		aleatorio = new Random();

		prin.anadirCartasalMazo();
		System.out.println(cartaMazo.size());
		// prin.lista();
		prin.Menu();

	}

	// METODOD LISTA (TAMA�O DE MAZO)
	public void lista() {

		for (int i = 0; i < cartaMazo.size(); i++) {
			System.out.println(cartaMazo.get(i).getNombre());
			System.out.println(cartaMazo.get(i).getAccion());
		}
	}

	// METODOD MAZO
	// SE CREA MAZO

	public void anadirCartasalMazo() {

		for (int i = 0; i <= 4; i++) {
			cartas = new Carta();
			cartas.setNombre("GUARD");
			cartas.setAccion("ADIVINAR MANO");
			cartas.setNumero(1);
			cartaMazo.add(cartas);

		}

		for (int i = 0; i <= 1; i++) {
			cartas = new Carta();
			cartas.setNombre("PRIEST");
			cartas.setAccion("PERMITE VER LA MANO DEL RIVAL");
			cartas.setNumero(2);
			cartaMazo.add(cartas);
		}

		for (int i = 0; i <= 1; i++) {
			cartas = new Carta();
			cartas.setNombre("BARON");
			cartas.setAccion("COMPARA MANOS CON EL RIVAL, CARTA MAS BAJA SALE DEL JUEGO, CARTAS IGUALES EMPATE");
			cartas.setNumero(3);
			cartaMazo.add(cartas);

		}
		for (int i = 0; i <= 1; i++) {
			cartas = new Carta();
			cartas.setNombre("HANDMAID");
			cartas.setAccion("INMUNE POR UN TURNO");
			cartas.setNumero(4);
			cartaMazo.add(cartas);
		}

		for (int i = 0; i <= 1; i++) {
			cartas = new Carta();
			cartas.setNombre("PRINCE");
			cartas.setAccion("BOTAR MANO JUGADOR O RIVAL, TOMAR UNA CARTA DEL DECK");
			cartas.setNumero(5);
			cartaMazo.add(cartas);
		}

		cartas = new Carta();
		cartas.setNombre("KING");
		cartas.setAccion("INTERCAMBIA MANOS ENTRE JUGADORES");
		cartas.setNumero(6);
		cartaMazo.add(cartas);

		cartas = new Carta();
		cartas.setNombre("COUNTESS");
		cartas.setAccion("SI TIENE PRINCE O KING DEBE JUGAR LA COUNTESS");
		cartas.setNumero(7);
		cartaMazo.add(cartas);

		cartas = new Carta();
		cartas.setNombre("PRINCESS");
		cartas.setAccion("SI BOTAS ESTA CARTA PIERDES");
		cartas.setNumero(8);
		cartaMazo.add(cartas);

	}

	// METODO MENU
	public void Menu() {

		System.out.println("- = Bienvenido a Love Letter = -");
		System.out.println("(1)JUGAR UNA PARTIDA");
		System.out.println("(2)SELECCIONAR EL NUMERO DE TOKENS A JUGAR");
		System.out.println("(3)MOSTRAR LAS REGLAS DEL JUEGO");
		System.out.println("(4)SALIR");
		// System.out.println(cartaMazo.size());

		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();

		// JUGAR UNA PARTIDA
		if (a == 1) {
			System.out.println("INGRESE EL NOMBRE DEL JUGADOR");
			Scanner nombre = new Scanner(System.in);
			nombreJugador = nombre.nextLine();
			System.out.println("INGRESE EL NOMBRE DEL RIVAL");
			nombreRival = nombre.nextLine();
			jugarPartida(nombreJugador, nombreRival);

			// NUMERO DE TOKENS A JUGAR
		} else if (a == 2) {
			Scanner toK = new Scanner(System.in);
			System.out.println("SELECCIONE EL NUMERO DE TOKENS A JUGAR\n"
					+ "(1) JUGAR A 1 TOKEN\n" + "(2) JUGAR A 3 TOKENS");
			int tokenGanar = toK.nextInt();
			System.out.println("(3)REGRESAR AL MENU");
			int back = toK.nextInt();
			if (back == 3) {
				Main inicial = new Main();
				inicial.Menu();
			}

			// MOSTRAR LAS REGLAS DEL JUEGO
		} else if (a == 3) {
			rule = new Reglas();
			rule.rules();
			System.out.println("\n(1)REGRESAR AL MENU");
			Scanner bak = new Scanner(System.in);
			int back = bak.nextInt();
			if (back == 1) {
				Main inicial = new Main();
				inicial.Menu();
			}
		} else if (a == 4) {
			System.out.println("SALIENDO DEL JUEGO........" + "....ADIOS");
			System.exit(0);
		}

	}

	// METODO JUGAR

	public void jugarPartida(String nombre, String cpu) {

		player = new Jugador();  
		player.setNombre(nombre);
		maquina = new CPU();
		maquina.setNombre(cpu);
		player.cartaJugador.clear();

		// BARAJEAR

		retirarCartasInicio();
		darCarta(player, maquina);
		
		if((tokenJugador==0)&&(tokenMaquina==0)){
        	aleatorio=new Random();
           	turno=aleatorio.nextInt(comenzar);
           	

		turno = 1;

		if (turno == 1) {
			player.cartaJugador.add(cartaMazo.get(cartaMazo.size() - 1));
			player.setCarta2(player.cartaJugador.get(1).getNombre());
			cartaMazo.remove(cartaMazo.size() - 1);
			maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size() - 1));
			maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
			cartaMazo.remove(cartaMazo.size() - 1);
			turnoJugadores(player, maquina, turno);
		} else if (turno == 0) {
			player.cartaJugador.add(cartaMazo.get(cartaMazo.size() - 1));
			player.setCarta2(player.cartaJugador.get(1).getNombre());
			cartaMazo.remove(cartaMazo.size() - 1);
			maquina.cartaCpu.add(cartaMazo.get(cartaMazo.size() - 1));
			maquina.setCarta2(maquina.cartaCpu.get(1).getNombre());
			cartaMazo.remove(cartaMazo.size() - 1);
			turnoJugadores(player, maquina, turno);
			
		}
		}

	}

	// SE RETIRAN 3 CARTAS AL INICIO
	public void retirarCartasInicio() {

		for (int i = 0; i < 3; i++) {
			cartaMazo.remove(i);
		}
	}

	// CAMBIAR UN TURNO
	public int cambiarTurno(int turno) {

		if (turno == 1) {
			turnoJugador = false;
			return 0;
		} else if (turno == 0) {
			turnoCpu = false;
			return 1;
		} else {
			return -1;
		}

	}

	// DARA CARTA INICIAL

	public void darCarta(Jugador jugador, CPU cpu) {

		// JUGADOR
		jugador.cartaJugador.add(cartaMazo.get(11));
		jugador.setCarta1(jugador.cartaJugador.get(0).getNombre());
		cartaMazo.remove(11);

		// RIVAL
		cpu.cartaCpu.add(cartaMazo.get(10));
		cpu.setCarta1(cpu.cartaCpu.get(0).getNombre());
		cartaMazo.remove(10);

		System.out.println(cartaMazo.size());
		// for(int i=0;i<cartaMazo.size();i++){
		// System.out.println(cartaMazo.get(i).getNombre());
		// }

	}

	// METODO DE EL TURNO DE JUGADORES

	public void turnoJugadores(Jugador jugador, CPU cpu, int turno) {

		System.out.println(" Jugador 1: " + jugador.getNombre() + "   TOKENS: "
				+ tokenJugador + "          Jugador 2: " + cpu.getNombre()
				+ "   TOKENS: " + tokenMaquina);

		// TURNO DEL JUGADOR

		if (turno == 1) {
			if (perdioRival == true) {
				tokenJugador += 1;
				System.out.println("Has ganado esta partida");
				cartaMazo.clear();
				anadirCartasalMazo();
				jugarPartida(jugador.getNombre(), cpu.getNombre());

				// SI EL RIVAL PROTEGIDO POR HANDMAID
			} else if (cpu.isHandmaid() == true) {
				if (jugador.cartaJugador.size() == 1) {
					if (jugador.cartaJugador.get(0) == null) {
						jugador.cartaJugador.add(0,
								cartaMazo.get(cartaMazo.size() - 1));
					} else {
						jugador.cartaJugador
								.add(cartaMazo.get(cartaMazo.size() - 1));

					}
					System.out.println("Turno de: " + jugador.getNombre());
					System.out.println("Tienes en tu mano: "
							+ "Nombre Carta 1:   " + jugador.getCarta1()
							+ " Nombre Carta 2:  " + jugador.getCarta2());
					System.out.println("(1)Usar Carta #1");
					System.out.println("(2)Usar Carta #2");
					System.out.println("(3)Terminar Juego");
					Scanner sc = new Scanner(System.in);
					int opcion = sc.nextInt();

					if (opcion == 1) {
						jugador.cartaJugador.remove(0);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));
						cpu.setHandmaid(false);
					} else if (opcion == 2) {
						jugador.cartaJugador.remove(1);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));
						cpu.setHandmaid(false);
					} else if (opcion == 3) {
						prin.Menu();
					}

				}

				// TURNO NORMAL DEL JUGADOR
			} else {
				if (jugador.cartaJugador.size() == 1) {
					if (jugador.cartaJugador.get(0) == null) {
						jugador.cartaJugador.add(0,
								cartaMazo.get(cartaMazo.size() - 1));
					} else {
						jugador.cartaJugador
								.add(cartaMazo.get(cartaMazo.size() - 1));
					}
				}
				System.out.println("Turno de: " + jugador.getNombre());
				System.out.println("Tienes en tu mano: " + "Nombre Carta 1: "
						+ jugador.getCarta1() + " Nombre Carta 2: "
						+ jugador.getCarta2());
				System.out.println("(1)Usar Carta #1");
				System.out.println("(2)Usar Carta #2");
				System.out.println("(3)Terminar Juego");
				Scanner sc = new Scanner(System.in);
				int opcion = sc.nextInt();

				// USAR CARTA NUMERO 1 DEL JUGADOR
				if (opcion == 1) {

					nombreCartaJugada1 = jugador.getCarta1();

					if (nombreCartaJugada1.equals("GUARD")) {
						jugador.cartaJugador.remove(0);
						System.out
								.println("SELECCIONE LA CARTA QUE TIENE SU RIVAL");
						System.out.println("(2)PRIEST\n" + "(3)BARON\n"
								+ "(4)HANDMAID\n" + "(5)PRINCE\n" + "(6)KING\n"
								+ "(7)COUNTESS\n" + "(8)PRINCESS\n");
						opcion = sc.nextInt();

						if (opcion == cpu.cartaCpu.get(0).getNumero()) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else if (opcion == cpu.cartaCpu.get(1).getNumero()) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else {

						}

					} else if (nombreCartaJugada1.equals("PRIEST")) {
						jugador.cartaJugador.remove(0);
						String cartar1 = cpu.cartaCpu.get(0).getNombre();
						String cartar2 = cpu.cartaCpu.get(1).getNombre();
						System.out.println("LAS CARTAS DEL RIVAL SON: "
								+ cartar1 + " Y " + cartar2);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada1.equals("BARON")) {
						jugador.cartaJugador.remove(0);
						System.out.println("");
						int cartar1 = cpu.cartaCpu.get(0).getNumero();
						int cartar2 = cpu.cartaCpu.get(1).getNumero();
						int cartaj2 = jugador.cartaJugador.get(1).getNumero();
						if ((cartaj2 < cartar2) || (cartaj2 < cartar1)) {
							perdioJugador = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else if ((cartaj2 > cartar2) || (cartaj2 > cartar1)) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));

						}

					} else if (nombreCartaJugada1.equals("HANDMAID")) {
						jugador.setHandmaid(true);
						jugador.cartaJugador.remove(0);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada1.equals("PRINCE")) {
						jugador.cartaJugador.remove(0);
						System.out.println("ELIGA A QUIEN BOTAR LA MANO: \n"
								+ "(1)" + jugador.getNombre() + "O \n" + "(2)"
								+ cpu.getNombre());
						Scanner ele = new Scanner(System.in);
						int e = ele.nextInt();
						if (e == 1)
							jugador.cartaJugador.remove(1);
						else if (e == 2) {
							cpu.cartaCpu.remove(0);
							cpu.cartaCpu.remove(1);
						}
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada1.equals("KING")) {
						jugador.cartaJugador.remove(0);
						jugador.cartaJugador.add(0, cpu.cartaCpu.get(0));
						cpu.cartaCpu.remove(0);
						cpu.cartaCpu.add(0, jugador.cartaJugador.get(1));
						jugador.cartaJugador.remove(1);
						jugador.cartaJugador.add(1, cpu.cartaCpu.get(1));
						cpu.cartaCpu.remove(1);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada1.equals("COUNTESS")) {
						jugador.cartaJugador.remove(0);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada1.equals("PRINCESS")) {
						jugador.cartaJugador.remove(0);
						System.out.println("TIRASTE PRINCESS");
						perdioJugador = true;
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					}
				}

				// USAR CARTA NUMERO 2
				else if (opcion == 2) {

					nombreCartaJugada2 = jugador.getCarta2();

					if (nombreCartaJugada2.equals("GUARD")) {
						jugador.cartaJugador.remove(1);
						System.out.println("ELIJE UNA CARTA");
						System.out.println("(2)PRIEST\n" + "(3)BARON\n"
								+ "(4)HANDMAID\n" + "(5)PRINCE\n" + "(6)KING\n"
								+ "(7)COUNTESS\n" + "(8)PRINCESS\n");
						opcion = sc.nextInt();

						if (opcion == cpu.cartaCpu.get(0).getNumero()) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else if (opcion == cpu.cartaCpu.get(1).getNumero()) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else {
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						}

					} else if (nombreCartaJugada2.equals("PRIEST")) {
						jugador.cartaJugador.remove(1);
						String cartar1 = cpu.cartaCpu.get(0).getNombre();
						String cartar2 = cpu.cartaCpu.get(1).getNombre();
						System.out.println("LAS CARTAS DEL RIVAL SON: "
								+ cartar1 + " Y " + cartar2);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada2.equals("BARON")) {
						jugador.cartaJugador.remove(1);
						int cartar1 = cpu.cartaCpu.get(0).getNumero();
						int cartar2 = cpu.cartaCpu.get(1).getNumero();
						int cartaj1 = jugador.cartaJugador.get(0).getNumero();
						if ((cartaj1 < cartar2) || (cartaj1 < cartar1)) {
							perdioJugador = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));
						} else if ((cartaj1 > cartar2) || (cartaj1 > cartar1)) {
							perdioRival = true;
							turnoJugadores(jugador, cpu, cambiarTurno(turno));

						}

					} else if (nombreCartaJugada2.equals("HANDMAID")) {
						jugador.cartaJugador.remove(1);
						jugador.setHandmaid(true);
						jugador.cartaJugador.remove(0);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada2.equals("PRINCE")) {
						jugador.cartaJugador.remove(1);
						System.out.println("ELIGA A QUIEN BOTAR LA MANO: \n"
								+ "(1)" + jugador.getNombre() + " \n" + "(2)"
								+ cpu.getNombre());
						Scanner ele = new Scanner(System.in);
						int e = ele.nextInt();
						if (e == 1)
							jugador.cartaJugador.remove(0);
						else if (e == 2) {
							cpu.cartaCpu.remove(0);
							cpu.cartaCpu.remove(1);
						}
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada2.equals("KING")) {
						jugador.cartaJugador.remove(1);
						jugador.cartaJugador.add(1, cpu.cartaCpu.get(1));
						cpu.cartaCpu.remove(1);
						cpu.cartaCpu.add(0, jugador.cartaJugador.get(0));
						jugador.cartaJugador.remove(0);
						jugador.cartaJugador.add(0, cpu.cartaCpu.get(0));
						cpu.cartaCpu.remove(0);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada2.equals("COUNTESS")) {
						jugador.cartaJugador.remove(1);
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					} else if (nombreCartaJugada2.equals("PRINCESS")) {
						jugador.cartaJugador.remove(1);
						System.out.println("TIRASTE PRINCESS");
						perdioJugador = true;
						turnoJugadores(jugador, cpu, cambiarTurno(turno));

					}

					// TERMINAR JUEGO DESPUES MUESTRA EL MENU
				} else if (opcion == 3) {
					prin.Menu();

				}

			}

			// TURNO DE RIVAL
		} else if (turno == 0) {

			if (perdioJugador == true) {
				tokenMaquina += 1;
				System.out.println("Ha ganado El Rival");
				cartaMazo.clear();
				anadirCartasalMazo();
				jugarPartida(jugador.getNombre(), cpu.getNombre());
				
				
				//SI EL JUGADOR ESTA EN HANDMAID
			} else if (jugador.isHandmaid() == true) {

				if (cpu.cartaCpu.size() == 1) {
					if (cpu.cartaCpu.get(0) == null) {
						cpu.cartaCpu
								.add(0, cartaMazo.get(cartaMazo.size() - 1));
					} else {
						cpu.cartaCpu.add(cartaMazo.get(cartaMazo.size() - 1));
					}
				}
				int cartaRemover = 0;
				aleatorio = new Random();
				cartaRemover = aleatorio.nextInt(2);
				cpu.cartaCpu.remove(cartaRemover);
				jugador.setHandmaid(false);
				turnoJugadores(jugador, cpu, cambiarTurno(turno));
			}
			
			
			//TURNO NORMAL DEL RIVAL

			if (cpu.cartaCpu.size() == 1) {
				if (cpu.cartaCpu.get(0) == null) {
					cpu.cartaCpu.add(0, cartaMazo.get(cartaMazo.size() - 1));
				} else {
					cpu.cartaCpu.add(cartaMazo.get(cartaMazo.size() - 1));
				}
			}
			System.out.println("Turno de: " + cpu.getNombre());
			System.out.println("EL RIVAL TIENE EN SU MANO:  CARTA 1: "
					+ cpu.getCarta1() + " CARTA 2: " + cpu.getCarta2());

			// LOGICA DEL RIVAL

			aleatorio = new Random();

			nombreCartaJugada1 = cpu.getCarta1();
			nombreCartaJugada2 = cpu.getCarta2();

			if (nombreCartaJugada1.equals("GUARD")
					&& nombreCartaJugada2.equals("GUARD")) {

				cpuAdivina = (int) (aleatorio.nextDouble() * 7 + 2);

				if ((cpuAdivina == jugador.cartaJugador.get(0).getNumero())
						|| (cpuAdivina == jugador.cartaJugador.get(1)
								.getNumero())) {
					perdioJugador = true;
					turnoJugadores(jugador, cpu, cambiarTurno(turno));
				} else {
					turnoJugadores(jugador, cpu, cambiarTurno(turno));
					cpu.cartaCpu.remove(0);
				}

			} else if ((nombreCartaJugada1.equals("PRIEST") && nombreCartaJugada2
					.equals("GUARD"))
					|| (nombreCartaJugada1.equals("GUARD") && nombreCartaJugada2
							.equals("PRIEST"))) {

				cpuAdivina = (int) (aleatorio.nextDouble() * 7 + 2);

				if ((cpuAdivina == jugador.cartaJugador.get(0).getNumero())
						|| (cpuAdivina == jugador.cartaJugador.get(1)
								.getNumero())) {
					perdioJugador = true;
					turnoJugadores(jugador, cpu, cambiarTurno(turno));
				} else {
					if (nombreCartaJugada1.equals("PRIEST")) {
						cpu.cartaCpu.remove(1);
					} else {
						cpu.cartaCpu.remove(0);
					}
					turnoJugadores(jugador, cpu, cambiarTurno(turno));

				}

			} else if (nombreCartaJugada1.equals("PRIEST")
					&& nombreCartaJugada2.equals("PRIEST")) {
				cpu.cartaCpu.remove(0);
				String cartar1 = jugador.cartaJugador.get(0).getNombre();
				String cartar2 = jugador.cartaJugador.get(1).getNombre();
				System.out.println("LAS CARTAS DEL JUGADOR SON: " + cartar1
						+ " Y " + cartar2);
				turnoJugadores(jugador, cpu, cambiarTurno(turno));
			} else if ((nombreCartaJugada1.equals("BARON") && nombreCartaJugada2
					.equals("PRIEST"))
					|| (nombreCartaJugada1.equals("PRIEST") && nombreCartaJugada2
							.equals("BARON"))) {

				if (nombreCartaJugada1.equals("BARON")) {
					cpu.cartaCpu.remove(1);
				} else {
					cpu.cartaCpu.remove(0);
				}
				String cartar1 = jugador.cartaJugador.get(0).getNombre();
				String cartar2 = jugador.cartaJugador.get(1).getNombre();
				System.out.println("LAS CARTAS DEL JUGADOR SON: " + cartar1
						+ " Y " + cartar2);
				turnoJugadores(jugador, cpu, cambiarTurno(turno));

			} else if ((nombreCartaJugada1.equals("BARON") && nombreCartaJugada2
					.equals("GUARD"))
					|| (nombreCartaJugada1.equals("GUARD") && nombreCartaJugada2
							.equals("BARON"))) {
				cpuAdivina = (int) (aleatorio.nextDouble() * 7 + 2);

				if ((cpuAdivina == jugador.cartaJugador.get(0).getNumero())
						|| (cpuAdivina == jugador.cartaJugador.get(1)
								.getNumero())) {
					perdioJugador = true;
					turnoJugadores(jugador, cpu, cambiarTurno(turno));
				} else {
					if (nombreCartaJugada1.equals("BARON")) {
						cpu.cartaCpu.remove(1);
					} else {
						cpu.cartaCpu.remove(0);
					}
					turnoJugadores(jugador, cpu, cambiarTurno(turno));

				}

			} else if (nombreCartaJugada1.equals("BARON")
					&& nombreCartaJugada2.equals("BARON")) {
				cpu.cartaCpu.remove(0);
				int cartar1 = jugador.cartaJugador.get(0).getNumero();
				int cartar2 = jugador.cartaJugador.get(1).getNumero();
				int cartaj2 = cpu.cartaCpu.get(1).getNumero();
				if ((cartaj2 < cartar2) || (cartaj2 < cartar1)) {
					perdioRival = true;
					turnoJugadores(jugador, cpu, cambiarTurno(turno));
				} else if ((cartaj2 > cartar2) || (cartaj2 > cartar1)) {
					perdioJugador = true;
					turnoJugadores(jugador, cpu, cambiarTurno(turno));

				}

			} else if (nombreCartaJugada1.equals("BARON")
					&& nombreCartaJugada2.equals("BARON")) {

			}

		}
	}
}
