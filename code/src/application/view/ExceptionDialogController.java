package application.view;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import application.DailyBankState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.orm.exception.ApplicationException;

/**
 * Page qui apparait lors d'une exception
 */
public class ExceptionDialogController implements Initializable {

	// Etat application
	private DailyBankState dbs;

	// Fenêtre physique
	private Stage primaryStage;

	// Données de la fenêtre
	private ApplicationException ae;
	
	/**
	 * Spécifie toutes les variables de la page
	 * @param _primaryStage : scene de la page
	 * @param _dbstate : Sont toutes les données de la session en cours de l'utilisateur
	 * @param _ae : données de la page
	 */
	// Manipulation de la fenêtre
	public void initContext(Stage _primaryStage, DailyBankState _dbstate, ApplicationException _ae) {
		this.primaryStage = _primaryStage;
		this.dbs = _dbstate;
		this.ae = _ae;
		this.configure();
	}

	private void configure() {
		this.primaryStage.setOnCloseRequest(e -> this.closeWindow(e));
		this.lblTitre.setText(this.ae.getMessage());
		this.txtTable.setText(this.ae.getTableName().toString());
		this.txtOpe.setText(this.ae.getOrder().toString());
		this.txtException.setText(this.ae.getClass().getName());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		this.ae.printStackTrace(pw);
		this.txtDetails.setText(sw.toString());
	}

	/**
	 * Affiche la page et attend une action de la part de l'utilisateur
	 */
	public void displayDialog() {
		this.primaryStage.showAndWait();
	}

	// Gestion du stage
	private Object closeWindow(WindowEvent e) {
		return null;
	}

	// Attributs de la scene + actions
	@FXML
	private Label lblTitre;
	@FXML
	private TextField txtTable;
	@FXML
	private TextField txtOpe;
	@FXML
	private TextField txtException;
	@FXML
	private TextArea txtDetails;

	/**
	 * Changement de la fonction initialize
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	/**
	 * Permet de fermer la page lorsque l'on clique sur le bouton "OK"
	 */
	@FXML
	private void doOK() {
		this.primaryStage.close();
	}
}
