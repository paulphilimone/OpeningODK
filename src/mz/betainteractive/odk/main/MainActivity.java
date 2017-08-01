package mz.betainteractive.odk;




import mz.betainteractive.odk.FormsProviderAPI;
import mz.betainteractive.odk.listener.OdkFormLoadListener;
import mz.betainteractive.odk.model.FilledForm;
import mz.betainteractive.odk.task.OdkGeneratedFormLoadTask;
import mz.betainteractive.testodk.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText txtFormId;
	Button btOpenODK;	
	//private final FormFiller formFiller = new FormFiller();
	
	private Uri contentUri;
	private String jrFormId;
	
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
				
		loadForm(1, filledForm);
	}

	public void loadForm(final int requestCode, final FilledForm filledForm) {
		
		new OdkGeneratedFormLoadTask(getBaseContext(), filledForm, new OdkFormLoadListener() {
            public void onOdkFormLoadSuccess(Uri contentUri) {
            	Log.d("contenturi", contentUri+"");
            	
            	Cursor cursor = getCursorForFormsProvider(filledForm.getFormName());
                if (cursor.moveToFirst()) {
                    jrFormId = cursor.getString(0);
                }
                
                MainActivity.this.contentUri = contentUri;
                
                startActivityForResult(new Intent(Intent.ACTION_EDIT, contentUri), requestCode);
            }

            public void onOdkFormLoadFailure() {
                //Toast.makeText(MainActivity.this, "Cant open ODK Form", 4000);
            	Log.d("Cant open ODK Form", "odk");
            }
        }).execute();
    }
	
	private Cursor getCursorForFormsProvider(String name) {
    	ContentResolver resolver = getContentResolver();
        return resolver.query(FormsProviderAPI.FormsColumns.CONTENT_URI, new String[] {
                FormsProviderAPI.FormsColumns.JR_FORM_ID, FormsProviderAPI.FormsColumns.FORM_FILE_PATH },
                FormsProviderAPI.FormsColumns.JR_FORM_ID + " like ?", new String[] { name + "%" }, null);
    }
    
    
}
