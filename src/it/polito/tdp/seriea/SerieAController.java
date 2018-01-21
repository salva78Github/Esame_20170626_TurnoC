/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.exception.SerieAException;
import it.polito.tdp.seriea.model.Goal;
import it.polito.tdp.seriea.model.GoalsCouple;
import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxNumeroDiGoal"
    private ChoiceBox<Integer> boxNumeroDiGoal; // Value injected by FXMLLoader

    @FXML // fx:id="boxSquadra1"
    private ChoiceBox<Team> boxSquadra1; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaConnessioniGoal"
    private Button btnCalcolaConnessioniGoal; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizzaRisultati"
    private Button btnAnalizzaRisultati; // Value injected by FXMLLoader

    @FXML // fx:id="boxSquadra2"
    private ChoiceBox<Team> boxSquadra2; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimulaStagioni"
    private Button btnSimulaStagioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void doAnalizzaRisultati(ActionEvent event) {
    	try {
			model.creaGrafo();
			this.boxNumeroDiGoal.getItems().addAll(model.listGoalstoInteger());
			this.boxNumeroDiGoal.setDisable(false);
			this.btnCalcolaConnessioniGoal.setDisable(false);
		} catch (SerieAException e) {
			this.txtResult.setText("Errore nella creazione del grafo");
		}
    }

    @FXML
    void doCalcolaConnessioniGoal(ActionEvent event) {
    	Integer homeGoals = this.boxNumeroDiGoal.getValue();
    	
    	List<GoalsCouple> gcList = model.retrieveListOfResults(new Goal(homeGoals, "H"));
    	
    	this.txtResult.setText(String.format("Risultati per il numero di gol %d della squadra di casa. \n",homeGoals));
    	for(GoalsCouple gc : gcList){
    		this.txtResult.appendText(String.format("%d - %d ---> %d \n", gc.getGoal1().getScore(), gc.getGoal2().getScore(), gc.getNumberOfResult()));
    		
    	}
    }

    @FXML
    void doSimulaStagioni(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxNumeroDiGoal != null : "fx:id=\"boxNumeroDiGoal\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxSquadra1 != null : "fx:id=\"boxSquadra1\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniGoal != null : "fx:id=\"btnCalcolaConnessioniGoal\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaRisultati != null : "fx:id=\"btnAnalizzaRisultati\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxSquadra2 != null : "fx:id=\"boxSquadra2\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaStagioni != null : "fx:id=\"btnSimulaStagioni\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
