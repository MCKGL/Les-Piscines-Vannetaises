package service;

public class Adresse {
	// -----------------------------------------------------------------------------------------------------------------------
		/**
		 * ATTRIBUTS
		 * 
		 * id Adresse
		 * numéro
		 * rue
		 * ville
		 * code postal
		 * ajout supplémentaire (autre?)
		 */
		private int idAdresse;
		private String ville;
		private int codePostal;
		private String rue;
		private int numero;
		//private String autre;
		
	// -----------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Constructeur avec ID
		 * @param idAdresse
		 * @param ville
		 * @param codePostal2
		 * @param rue
		 * @param numero2
		 */
		public Adresse(int idAdresse, String ville, int codePostal2, String rue, int numero2) {
			super();
			this.idAdresse = idAdresse;
			this.ville = ville;
			this.codePostal = codePostal2;
			this.rue = rue;
			this.numero = numero2;
		}
		/**
		 * Constructeur sans ID
		 * @param ville
		 * @param codePostal
		 * @param rue
		 * @param numero
		 */
		public Adresse(String ville, int codePostal, String rue, int numero) {
			super();
			this.ville = ville;
			this.codePostal = codePostal;
			this.rue = rue;
			this.numero = numero;
		}

	// -----------------------------------------------------------------------------------------------------------------------

		public int getIdAdresse() {
			return idAdresse;
		}


		public void setIdAdresse(int idAdresse) {
			this.idAdresse = idAdresse;
		}


		public String getVille() {
			return ville;
		}


		public void setVille(String ville) {
			this.ville = ville;
		}


		public int getCodePostal() {
			return codePostal;
		}


		public void setCodePostal(int codePostal) {
			this.codePostal = codePostal;
		}


		public String getRue() {
			return rue;
		}


		public void setRue(String rue) {
			this.rue = rue;
		}


		public int getNumero() {
			return numero;
		}


		public void setNumero(int numero) {
			this.numero = numero;
		}

	// -----------------------------------------------------------------------------------------------------------------------
		/**
		 * 	Methode
		 */
		
	// -----------------------------------------------------------------------------------------------------------------------	
		@Override
		public String toString() {
			return "Adresse [idAdresse=" + idAdresse + ", ville=" + ville + ", codePostal=" + codePostal + ", rue=" + rue
					+ ", numero=" + numero + "]";
		}

}
