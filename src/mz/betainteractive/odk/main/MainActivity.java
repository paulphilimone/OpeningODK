package mz.betainteractive.odk.main;




import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import mz.betainteractive.odk.R;
import mz.betainteractive.odk.model.FilledForm;

public class MainActivity extends Activity {
	
	private EditText txtFormId;
	private Button btOpenODK;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
				
		initComponents();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initComponents() {
		txtFormId = (EditText) findViewById(R.id.txtFormId);		
		btOpenODK = (Button) findViewById(R.id.btOpenODK);
		
		Log.d("loading", "init");
		
		btOpenODK.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {			
				onClickedOpenODK();
			}
		});		
	}

	protected void onClickedOpenODK() {
		Log.d("loading", "click");
						
		String formName = txtFormId.getText().toString();
		
		
		FilledForm filledForm = new FilledForm(formName);
		
		filledForm.put("visitId","MAN00000333");
		filledForm.put("locationId", "MAN000001");
		filledForm.put("roundNumber", 16);			
				
		//loadForm(1, filledForm);
	}
    
    
}
