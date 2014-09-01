package mz.betainteractive.odk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A filled form represents an ODK form that has been prefilled with values from
 * the OpenHDS application.
 */
public class FilledForm {
    private String formName;
    private Map<String,Object> values;
    
    public FilledForm(String formName) {
        this.formName = formName;
    }
    
    public void put(String variable, Object value){
    	values.put(variable, value);
    }
    
    public Object get(String variable){
    	return values.get(variable);
    }

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public List<String> getVariables(){
		return new ArrayList<String>(this.values.keySet()); 
	}
    
	
}
