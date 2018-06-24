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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RatioController implements Initializable{
	
    @FXML
    private TextField input1;

    @FXML
    private TextField input2;

    @FXML
    private Text factor1;
    
    @FXML
    private Text factor2;

    @FXML
    private Text instructionTxt;
    
    @FXML
    private ComboBox<String> ratioComboBox;
    
    @FXML
    private Button calculateBtn;
    
    @FXML
    private Button resetBtn;

    @FXML
    private TableView<RatioInputs> resultsTable;
   
    @FXML
    private TableColumn<RatioInputs, String> input1Col;
    
    @FXML
    private TableColumn<RatioInputs, String>  input2Col;

    @FXML
    private TableColumn<RatioInputs, String>  resultCol;
    
    
    private String selectedRatio;
    
    ObservableList<RatioInputs> tableItems = FXCollections.observableArrayList(
    		
    		);
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = 
        	    FXCollections.observableArrayList(
        	        "Current Ratio",
        	        "Working Capital Ratio",
        	        "Debt to Equity Ratio",
        	        "Gross Profit Margin Ratio"
        	    );
        ratioComboBox.setItems(options);
        input1Col.setCellValueFactory(new PropertyValueFactory<>("input1"));
        input2Col.setCellValueFactory(new PropertyValueFactory<>("input2"));
        resultCol.setCellValueFactory(new PropertyValueFactory<>("result"));
        resultsTable.getItems().addAll(tableItems);
        
    }
    @FXML
    void onTab(ActionEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("InterestView.fxml"));
		Scene scene = new Scene(parent);
		Stage secondaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		secondaryStage.setResizable(false);
		secondaryStage.setScene(scene);
		secondaryStage.show();
    }
    
    @FXML
    void onSelectRatio(ActionEvent event) {
    	selectedRatio = ratioComboBox.getValue();
    	input1.setVisible(true);
    	input2.setVisible(true);
    	factor1.setVisible(true);
    	factor2.setVisible(true);
    	instructionTxt.setVisible(true);
    	calculateBtn.setVisible(true);
    	resetBtn.setVisible(true);
    	
    	switch(selectedRatio){
    		case "Current Ratio":
    			factor1.setText("Current assests");
    			factor2.setText("Current liabilities");
    			input1Col.setText("Current assests");
    			input2Col.setText("Current liabilities");
    			break;
    		case "Working Capital Ratio":
    			factor1.setText("Current assests");
    			factor2.setText("Current liabilities");
    			input1Col.setText("Current assests");
    			input2Col.setText("Current liabilities");
    			break;
    		case "Debt to Equity Ratio":
    			factor1.setText("Total debt");
    			factor2.setText("Total equality");
    			input1Col.setText("Total debt");
    			input2Col.setText("Total equality");
    			break;
    		case  "Gross Profit Margin Ratio":
    			factor1.setText("Gross profit");
    			factor2.setText("Revenue");
    			input1Col.setText("Gross profit");
    			input2Col.setText("Revenue");
    			break;
    	}
    	resultsTable.getItems().clear();
    }
    
    @FXML
    void onCalculate(ActionEvent event) {
    	DecimalFormat df = new DecimalFormat("#0.00");	//format the number to 2 decimal places	
    	boolean input1Empty = input1.getText().isEmpty();	//true if year text is empty
    	boolean input2Empty = input2.getText().isEmpty();	//true if amount text is empty
    	
    	if(input1Empty || input2Empty){
    		Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle("Warning Dialog");
	    	alert.setHeaderText("Input Missing");
	    	alert.setContentText("Please fill in all the inputs.");
	    	alert.showAndWait();
    	}else{
    		if(isNumeric(input1.getText()) && isNumeric(input2.getText())){
    			RatioInputs input = new RatioInputs();
    			double inputOne = Double.parseDouble(input1.getText());	
				double inputTwo = Double.parseDouble(input2.getText());
    			switch(selectedRatio){
        		case "Current Ratio":
        			input.setResult(df.format(inputOne/inputTwo)+" (%)");
        			break;
        		case "Working Capital Ratio":
        			input.setResult("£" + df.format(inputOne-inputTwo));
        			break;
        		case "Debt to Equity Ratio":
        			input.setResult(df.format(inputOne/inputTwo) +" (%)");
        			break;
        		case  "Gross Profit Margin Ratio":
        			System.out.println(df.format(inputOne/inputTwo));
        			input.setResult(df.format(inputOne/inputTwo)+" (%)");
        			break;
    			}
    			input.setInput1(inputOne);
    			input.setInput2(inputTwo);
    			resultsTable.getItems().add(input);	
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
    void onReset(ActionEvent event) {	//clears all input fields
    	input1.clear();
    	input2.clear();
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
