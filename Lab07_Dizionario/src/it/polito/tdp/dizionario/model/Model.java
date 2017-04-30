package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	private List<String> parole = new ArrayList<String>();
	private List<String> paroleVicine = new ArrayList<String>();
	private UndirectedGraph<String,DefaultEdge> grafo = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
	private int numeroLettere;
	
	public List<String> createGraph(int numeroLettere) {
		this.numeroLettere=numeroLettere;
		WordDAO wD= new WordDAO();
		parole=wD.getAllWordsFixedLength(numeroLettere);
		
		
		for(String s: parole){
			grafo.addVertex(s);
		}
		
		for(String s:parole){
			paroleVicine=this.displayNeighbours(s);
			
			System.out.println(paroleVicine);
											
			for(String p:paroleVicine)
				grafo.addEdge(s, p);		
		}
		
		return parole;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		WordDAO wD= new WordDAO();
		paroleVicine=wD.getAllSimilarWords(parolaInserita, numeroLettere);
		
		return paroleVicine;
	}

		
	public String findMaxDegree() {
		
		String parola="";
		int max=0;
		for(String s:parole){
			if(grafo.degreeOf(s)>=max){
				max=grafo.degreeOf(s);
				parola=s;
			}
		}
				
		return parola;
	}
}
