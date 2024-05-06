package org.generation.italy;

//import LocalTime
import java.time.LocalTime;
//import scanner
import java.util.Scanner;

class AutoveloxAlfieriStefanoEx {

	public static void main(String[] args) {

		// dichiarazione oggetto scanner e inserimento dati
		Scanner sc = new Scanner(System.in);
		int velocità, limite, eccesso;
		int tolleranza = 0;
		String speciali, neopatentato, rispostaRestart;
		// inizio ciclo DO-WHILE
		do {
			// titolo più 2 volte a capo
			System.out.println("Calcolo delle sanzioni per eccesso di velocità\n\n");

			// inserimento limite
			System.out.println("Inserire limite di velocità: ");
			limite = sc.nextInt();
			sc.nextLine();
			while (limite <= 0 || limite > 200) {
				System.out.println("limite di velocità non valido, reinserire: ");
				limite = sc.nextInt();
				sc.nextLine();
			}
			// inserimento velocità
			System.out.println("Inserire la velocità dell'auto: ");
			velocità = sc.nextInt();
			sc.nextLine();
			while (velocità <= 0 || velocità > 450) {
				System.out.println("Velocità non valida, reinserire: ");
				velocità = sc.nextInt();
				sc.nextLine();
			}
			int ora, minuti;
			// inserire input ore e minuti
			System.out.println("inserire l'ora:");
			ora = sc.nextInt();
			minuti = sc.nextInt();
			sc.nextLine();
			// utilizzo while per validare ora e minuti
			while (ora < 0 || ora >= 24 || minuti < 0 || minuti >= 60) {
				System.out.println("orario non valido, si prega di reinserire correttamente l'orario:");
				ora = sc.nextInt();
				minuti = sc.nextInt();
				sc.nextLine();
			}

			// utilizzo oggetto LocalTime per inserire un orario di inizio e di fine
			LocalTime limiteDa = LocalTime.of(22, 0);
			LocalTime limiteA = LocalTime.of(7, 0);
			LocalTime oraMulta = LocalTime.of(ora, minuti);

			// utilizzo un booleano per verificare se la multa avviene nell'orario compreso
			boolean notte = oraMulta.isAfter(limiteDa) || oraMulta.isBefore(limiteA);

			// richiesta se neopatentato e verifica tramite booleano
			System.out.println("sei neopatentato?");
			neopatentato = sc.nextLine();

			boolean newbie = (neopatentato.equalsIgnoreCase("si"))||(neopatentato.equalsIgnoreCase("sì"));

			// richiesta mezzi speciali e verifica tramite booleano
			System.out.println(
					"Eri alla guida di mezzi speciali (adibiti al trasporto di merci pericolose, autoveicoli con rimorchi, autobus e filobus, autoveicoli destinati al trasporto di cose o mezzi d’opera che viaggiano a pieno carico)?");
			speciali = sc.nextLine();

			boolean specialCar = ((speciali.equalsIgnoreCase("si"))||(speciali.equalsIgnoreCase("sì")));
			// *fine inserimento dati*

			// calcolo eccesso di velocità, tolleranza e stampa
			eccesso = velocità - limite;
			if (velocità < 100 && eccesso > 0) {
				tolleranza=5;
				System.out.println("avrai diritto a 5 km/h di tolleranza essendo la tua velocità inferiore a 100 km/h");
			} else if (velocità > 100 && eccesso > 0) {
				tolleranza = (velocità * 5) / 100;
				System.out.println("avrai diritto a " + tolleranza
						+ " km/h di tolleranza, ossia il 5% della tua velocità " + velocità + " km/h");
			}
			eccesso = eccesso-tolleranza;
			if (eccesso <= 0) {
				System.out.println(
						"Essendo il limite di velocità di " + limite + " km/h e la velocità portata dall'auto di "
								+ velocità + " km/h e tenuto conto della tolleranza, non sei soggetto a nessuna sanzione.");
			} else {
				System.out.print("Il limite di velocità è di " + limite + " km/h, la velocità portata dall'auto è di "
						+ velocità + " km/h. L'eccesso oltre il limite è di " + eccesso + " km/h, tenuto conto della tolleranza, ");
				// inizio calcolo multa
				if (eccesso < 10) {
					System.out.println(
							"la sanzione prevista è multa compresa tra 42 e 173 euro.");
				} else if (eccesso >= 10 && eccesso < 40) {
					System.out.println(
							"la sanzione prevista è multa compresa tra 173 e 694 euro e la decurtazione di 3 punti dalla patente.");
				} else if (eccesso >= 40 && eccesso < 60) {
					System.out.println(
							"la sanzione prevista è multa compresa tra 543 e 2170 euro e la decurtazione di 6 ppunti dalla patente.");
				} else if (eccesso >= 60) {
					System.out.println(
							"la sanzione prevista è multa compresa tra 845 e 3382 euro, la sospensione della patente da 6 a 12 mesi e la decurtazione di 10 punti dalla patente.");
				}
				if (notte) {
					System.out.println("Essendo orario notturno, la multa sarà maggiorata di un terzo.");
				}
				if (newbie) {
					System.out.println("Essendo neopatentato, la decurtazione dei punti sarà raddoppaiata.");
				}
				if (specialCar) {
					System.out.println(
							"Essendo alla guida di un mezzo speciale, ogni sanzione, sia amministrativa che accessoria sarà raddoppiata.");
				}
				System.out.println(
						"pagando la multa entro 5 giorni dalla notifica, avrai diritto a una riduzione del 30%. Se la notifica non avviene entro 90 giorni, facendo ricorso avrai diritto all'annullamento della sanzione.");
			} // fine calcolo multa
			System.out.println("Desideri effettuare un nuovo calcolo?");
			rispostaRestart = sc.nextLine();
		} while ((rispostaRestart.equalsIgnoreCase("si"))||(rispostaRestart.equalsIgnoreCase("sì")));
		System.out.println("grazie, arrivederci.");
		sc.close();
	} // fine main

}// fine progetto
