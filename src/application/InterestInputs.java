package application;
public class InterestInputs {
	
	private double numOfYears, amount, annualInterest; 
	private String presentValue, futureValue, resultValue;


	
	public InterestInputs(){
		this.numOfYears = 0;
		this.amount = 0;
		this.annualInterest = 0;
		this.presentValue = "";
		this.futureValue = "";
		this.resultValue = "";
	}
	
	public InterestInputs(double numOfYears, double amount, double annualInterest, String presentValue, String futureValue, String resultValue){
		this.numOfYears = numOfYears;
		this.amount = amount;
		this.annualInterest = annualInterest;
		this.presentValue = presentValue;
		this.futureValue = futureValue;
		this.resultValue = resultValue;
	}
	public double getNumOfYears(){
		return numOfYears;
	}
	public void setNumOfYears(double numOfYears){
		this.numOfYears = numOfYears;
	}
	public double getAmount(){
		return amount;
	}
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAnnualInterest(){
		return annualInterest;
	}
	public void setAnnualInterest(double annualInterest){
		this.annualInterest = annualInterest;
	}
	public String getPresentValue(){
		return presentValue;
	}
	public void setPresentValue(String presentValue){
		this.presentValue = presentValue;
	}
	public String getFutureValue(){
		return futureValue;
	}
	public void setFutureValue(String futureValue){
		this.futureValue = futureValue;
	}
	public String getResultValue(){
		return resultValue;
	}
	public void setResultValue(String resultValue){
		this.resultValue = resultValue;
	}
	
}
