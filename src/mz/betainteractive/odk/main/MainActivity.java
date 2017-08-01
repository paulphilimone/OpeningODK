package mz.betainteractive.odk.main;




import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import mz.betainteractive.odk.FormUtilities;
import mz.betainteractive.odk.R;
import mz.betainteractive.odk.listener.OdkFormResultListener;
import mz.betainteractive.odk.model.FilledForm;

public class MainActivity extends Activity implements OdkFormResultListener {
	
	private EditText txtFormId;
	private Button btOpenODK;	
	
	private FormUtilities formUtilities;
	
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
		
		this.formUtilities = new FormUtilities(this);
		
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
						
		String formId = txtFormId.getText().toString();
		
		
		FilledForm filledForm = new FilledForm(formId);
		
		filledForm.put("visitId","MAN00000333");
		filledForm.put("locationId", "MAN000001");
		filledForm.put("roundNumber", 16);			
				
		formUtilities.loadForm(filledForm);
	}
	
	/* Overriding ActivityResult and pass the results to FormUtilities ActivityResult*/
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        formUtilities.onActivityResult(requestCode, resultCode, data, this);
    }

	@Override
	public void onFormFinalized(Uri contentUri, File xmlFile) {
		 Log.d("form finalized"," "+contentUri+", "+xmlFile);		
	}

	@Override
	public void onFormUnFinalized(Uri contentUri, File xmlFile) {
		Log.d("form unfinalized"," "+contentUri);
	}

	@Override
	public void onDeleteForm(Uri contentUri) {
		Log.d("delete uri", "needs to be implemented");
	}
    
    
}
