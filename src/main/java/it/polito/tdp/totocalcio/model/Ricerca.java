package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;
	
	public List<Risultato> cerca(Pronostico pronostico) { //funzione di interfaccia--> dammi problema e ti dò soluzione
		//internamente mette i dati
		this.pronostico=pronostico;
		this.N=pronostico.size();
		
		List<RisultatoPartita> parziale= new ArrayList<RisultatoPartita>();
		int livello=0;
		
		this.soluzione= new ArrayList<Risultato>();
		//dà il lavoro alla ricorsiva (livello zero)
		ricorsiva(parziale, livello); //lo riempie ogni volta che trova una soluzione terminale
		//restutisce il risultato
		return this.soluzione; 
	}

	
	private void ricorsiva(List<RisultatoPartita> parziale, int livello) {
		//caso terminale?
		if(livello==N) {
			//questa soluzione parziale è una soluzione completa
			//System.out.println(parziale);
			this.soluzione.add(new Risultato(parziale));
		} else {
			//caso generale--> cuore della ricorsione
			
			//[parziale da 0 a livello-1] [livello] [livello+1 in poi] -->guardo solo livello (se vale 3, sono nella quarta partita)
			PronosticoPartita pp= pronostico.get(livello);
			//pp sono i sotto-problemi da provare
			
			for(RisultatoPartita ris: pp.getRisultati()) {
				//provo a mettere 'ris' nella posizione livello della soluzione parziale
				
				//costruzione soluzione parziale (sottoproblema)
				parziale.add(ris);
				//chiamata ricorsiva
				ricorsiva(parziale, livello+1);
				//backtracking--> metto le cose a posto
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
	/*
	 * livello = numero di partita che sto considerando
	 * le partite dal livello 0 a N-1 sono già state decise
	 * la partita di indice livello la devo decidere io
	 * le partite da livello+1 in poi le decideranno le procedure ricorsive sottostanti
	 * 
	 * Soluzione parziale: un elenco (lista) di RisultatoPartita di lunghezza pari al livello
	 * 
	 * Soluzione totale: ho N risultati
	 * 
	 * Condizione di terminazione : livello == N
	 * 
	 * Generazionoe delle soluzioni del livello: provando tutti i pronostici definiti per quel livello
	 * 
	 */
}
