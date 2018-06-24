package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InterestController implements Initializable{

    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private TextField input3;
    
    @FXML
    private RadioButton future;

    @FXML
    private RadioButton present;

    @FXML
    private ToggleGroup value;

    @FXML
    private TableView<InterestInputs> resultsTable;
   
    @FXML
    private TableColumn<InterestInputs, String> yearsCol;
    
    @FXML
    private TableColumn<InterestInputs, String>  amountCol;

    @FXML
    private TableColumn<InterestInputs, String>  interestCol;
    
    @FXML
    private TableColumn<InterestInputs, String>  presentCol;
    
    @FXML
    private TableColumn<InterestInputs, String>  futureCol;
    
    @FXML
    private TableColumn<InterestInputs, String>  resultCol;
    
    ObservableList<InterestInputs> tableItems = FXCollections.observableArrayList(
    		);

	@Override
    public void initialize(URL location, ResourceBundle resources) {
    	yearsCol.setCellValueFactory(new PropertyValueFactory<>("numOfYears"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		interestCol.setCellValueFactory(new PropertyValueFactory<>("annualInterest"));
		presentCol.setCellValueFactory(new PropertyValueFactory<>("presentValue"));
		futureCol.setCellValueFactory(new PropertyValueFactory<>("futureValue"));
		resultCol.setCellValueFactory(new PropertyValueFactory<>("resultValue"));
		resultsTable.getItems().addAll(tableItems);
		}

    @FXML
    void onInterestCalculate(ActionEvent event) {
    	DecimalFormat df = new DecimalFormat("#0.00");	//format the number to 2 decimal places
    	boolean yearEmpty = input1.getText().isEmpty();	//true if year text is empty
    	boolean amountEmpty = input2.getText().isEmpty();	//true if amount text is empty
    	boolean interestEmpty = input3.getText().isEmpty();	//true if interest text is empty
    
    	
    	if(amountEmpty || yearEmpty || interestEmpty){	// true if one of the inputs is empty
    		//System.out.println("One of the values is empty");
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Input Missing");
	    	alert.setContentText("Please fill in all the inputs.");
	    	alert.showAndWait();
    	}else{
    		if(isNumeric(input1.getText()) && isNumeric(input2.getText()) && isNumeric(input3.getText())){
    			double inputYear = Double.parseDouble(input1.getText());	
				double inputAmount = Double.parseDouble(input2.getText());
				double inputInterest = Double.parseDouble(input3.getText());
				
				InterestInputs input = new InterestInputs();	//new item for table
		    	input.setNumOfYears(inputYear);
		    	input.setAmount(inputAmount);
		    	input.setAnnualInterest(inputInterest);
				
    			if(present.isSelected()){
    				double presentValue = (inputAmount)/Math.pow((1+(inputInterest/100)),  inputYear);	//calculates present value
    				input.setPresentValue("Yes");
    				input.setFutureValue("No");
    				input.setResultValue(df.format(presentValue));
    			}else if(future.isSelected()){
    				double percentPlus = 1 + ((double)inputInterest/100);	//calculate percentage increase
    				double futureValue = inputAmount;
    				for(int i=0; i<inputYear; i++){
    					futureValue = futureValue*percentPlus;	//calculates future interest value
    					//System.out.println(df.format(inputAmount));
    				}
    				input.setPresentValue("No");
    				input.setFutureValue("Yes");
    				input.setResultValue(df.format(futureValue));
    			}
    			resultsTable.getItems().add(input);		//adds new item to the table
    		}else{	
    			Alert alert = new Alert(AlertType.WARNING);
    	    	alert.setTitle("Warning Dialog");
    	    	alert.setHeaderText("Input Error");
    	    	alert.setContentText("Please only enter numerical values as inputs.");
    	    	alert.showAndWait();
    		}
    	}
    }
    @FXML
    void onTab(ActionEvent event) throws IOException {	//allows users to change to another UI
    	Parent parent = FXMLLoader.load(getClass().getResource("RatioView.fxml"));
		Scene scene = new Scene(parent);
		Stage secondaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		secondaryStage.setResizable(false);
		secondaryStage.setScene(scene);
		secondaryStage.show();
    }
    @FXML
    void onReset(ActionEvent event) {	//clears all the input fields
    	input1.clear();
    	input2.clear();
    	input3.clear();
    	resultsTable.getItems().clear();
    }
    public boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
