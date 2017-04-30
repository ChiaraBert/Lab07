package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	
	private Model m;
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;
	
	private int numeroLettere=0;

	void setModel(Model m){
		this.m=m;
	}
	
	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
		inputNumeroLettere.clear();
		inputParola.clear();
		
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		try {
			String lunghezza="";
			lunghezza=inputNumeroLettere.getText();
			
			if(lunghezza.matches("[a-zA-Z]*")){
				txtResult.setText("Inserire solo caratteri numerici");
				return;}
			
			numeroLettere=Integer.parseInt(lunghezza);
			
			if(numeroLettere<2||numeroLettere>5){
				txtResult.setText("Numero di lettere troppo grande");
				return;}
			
			m.createGraph(numeroLettere);
			
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try { String parola="";
			
			List<String> vicini = new ArrayList<String>();
			
				if(numeroLettere==0){
				txtResult.setText("Stabilire prima la lunghezza delle parole");
				return;}
				
			parola=m.findMaxDegree();
			vicini=m.displayNeighbours(parola);
				
			txtResult.setText("Grado Massimo:\n"+parola+": grado "+vicini.size()+"\nVicini: "+vicini);
			

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		
		try {String parola = "";
			
			List<String> vicini = new ArrayList<String>();
			parola=inputParola.getText();
			
			if(numeroLettere==0){
				txtResult.setText("Stabilire prima la lunghezza delle parole");
				return;}
			
			if(parola.length()!= numeroLettere){
				txtResult.setText("La parola deve essere lunga "+numeroLettere);
				return;}
			
			vicini=m.displayNeighbours(parola);
			
			txtResult.setText(""+vicini);
			

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}