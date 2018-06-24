package application;

public class RatioInputs {
	
	private double input1, input2;
	private String result;
	
	public RatioInputs(){
		input1 = 0;
		input2 = 0;
		result = "";
	}
	
	public RatioInputs(double input1, double input2, String result){
		this.input1 = input1;
		this.input2 = input2;
		this.result = result;
	}
	public double getInput1(){
		return input1;
	}
	public void setInput1(double input1){
		this.input1 = input1;
	}
	public double getInput2(){
		return input2;
	}
	public void setInput2(double input2){
		this.input2 = input2;
	}
	public String getResult(){
		return result;
	}
	public void setResult(String result){
		this.result = result;
	}
}
