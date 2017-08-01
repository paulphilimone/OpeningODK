package mz.betainteractive.odk.listener;

import android.net.Uri;

import java.io.File;

/**
 * Created by paul on 8/11/16.
 */
public interface OdkFormResultListener {

    void onFormFinalized(Uri contentUri, File xmlFile);

    void onFormUnFinalized(Uri contentUri, File xmlFile);

    void onDeleteForm(Uri contentUri);

}
