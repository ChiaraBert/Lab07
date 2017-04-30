package it.polito.tdp.dizionario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza che differiscono per un solo carattere
	 */
	public List<String> getAllSimilarWords(String parola, int numeroLettere) {
		
		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ? AND nome LIKE ?;";
		PreparedStatement st;

		try {
			char[] parolaOriginale = parola.toCharArray();
			
			List<String> paroleVicine = new ArrayList<String>();
			for(int i=0;i<parolaOriginale.length;i++){
				String cerca="";
				char temp = parolaOriginale[i];
								
				for(int j=0;j<parolaOriginale.length;j++){
					parolaOriginale[i]='_';
					cerca+=parolaOriginale[j];
					parolaOriginale[i] = temp;}
				
			
				
				st = conn.prepareStatement(sql);
				st.setInt(1, numeroLettere);
				st.setString(2, cerca);
				ResultSet res = st.executeQuery();
				
				while (res.next())
					if(res.getString("nome").compareTo(parola)!=0)
					paroleVicine.add(res.getString("nome"));
				
			} return paroleVicine;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int numeroLettere) {

		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		PreparedStatement st;

		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, numeroLettere);
			ResultSet res = st.executeQuery();

			List<String> parole = new ArrayList<String>();

			while (res.next())
				parole.add(res.getString("nome"));

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

}
