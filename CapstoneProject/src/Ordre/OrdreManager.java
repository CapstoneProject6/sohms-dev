package Ordre;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Produit.IProduit;
import Produit.ProduitDijkstra;
import Ressource.Ressource;
import Ressource.RessourceManager;

public class OrdreManager {

	public static void main(String[] args) {
		// initialisation
		// --------------
		RessourceManager ressM = new RessourceManager();
		
		// Utilisation du SoHMS
		/*SoHMSMock mock = new SoHMSMock();
		try{
			mock.InterfaceMock();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}*/
		
		String str = // TODO get this string with the set up of the IHM
				"{ \"ressources\":[" 
			+ 	"{ "
            +		" \"type\": 1, "
            +		" \"nb\": 3, "
            +		" \"conf\": \"../..\", "
            +	"}"
        	+"] }";
		
		JSONObject obj;
		try {
			obj = new JSONObject(str);
			JSONArray ressources = obj.getJSONArray("ressources");
			for (int i = 0; i < ressources.length(); i++)
			{
				System.out.println(ressources);
				JSONObject ressourceAtt = ressources.getJSONObject(i);
				System.out.println("ressourceAtt + " + ressourceAtt);
				for(int j= 0; j<ressourceAtt.getInt("nb");++j)
				{
					System.out.println("OrdreManager -> JSONParse -> ressourceAtt.getInt(\"nb\"): hellooooo");
					//TODO ouverture fichier ressource.getString(2);
					/*for(int ii = 0; ii<ressourceAtt.getInt(1); ++ii)
					{
						//ressM.addRessource(new Ressource(attributs, id));
					}*/
				}
			}
				
				
		} catch (JSONException e) {
			System.out.println("Format du fichier JSON invalide - impossible d'initialiser les ressources");
			e.printStackTrace();
		}
		
		IProduit p = new ProduitDijkstra("RandomPAthToNowhere");
	}
	
	/**
	 * Création d'un nouvel ordre
	 * @param jsonFile le fichier de configuration du système
	 */
	public void newOrder(File jsonFile) {
		
	}
	
	/**
	 * Valide le contrat
	 */
	public void contractValidation() {
		
	}
	
	/**
	 * Annule le contrat en cours
	 */
	public void avoidContract() {
		
	}
	
	/**
	 * Annule les contrats en cours
	 */
	public void avoidContracts() {
		
	}
	
	/**
	 * Réorganise les ordres en fonction de la panne de la ressource
	 */
	public void reorganizationContract() {
		
	}

}
