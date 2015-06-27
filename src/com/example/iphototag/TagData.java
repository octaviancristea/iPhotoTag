package com.example.iphototag;

public class TagData {
	
	public String variableName;
	public String variableValue;

	private String getVariableName(){
		return this.variableName;
	}
	
	private String getVariableValue(){
		return this.variableValue;
	}
	
	private void setVariableName(String auxName){
		this.variableName = auxName;
	}
	
	private void setVariableValue(String auxValue){
		this.variableValue = auxValue;
	}
}
