package com.gad.a3dshare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gad.a3dshare.api.model.DesignModel;
import com.gad.a3dshare.api.persistence.DesignPersistence;

public class AddDesignActivity extends AppCompatActivity {
    public static final String TAG = AddDesignActivity.class.getSimpleName();
    private static final int READ_REQUEST_CODE_IMAGE = 42;
    private static final int READ_REQUEST_CODE_STL = 43;

    private DesignPersistence designPersistence;

    private EditText nameText;
    private EditText descriptionText;
    private EditText lengthText;
    private EditText widthText;
    private EditText heightText;
    private EditText priceText;

    private Uri imageUri;
    private Uri stlUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_design);

        this.designPersistence = new DesignPersistence();

        initViews();

    }

    private void initViews(){
        nameText = findViewById(R.id.editTextName);
        descriptionText = findViewById(R.id.editTextDescription);
        lengthText = findViewById(R.id.editTextLength);
        widthText = findViewById(R.id.editTextWidth);
        heightText = findViewById(R.id.editTextHeight);
        priceText = findViewById(R.id.editTextPrice);
    }

    public void uploadImage(View v){
        performImageFileSearch();
    }

    public void uploadSTL (View v){
        performSTLFileSearch();
    }

    public void addDesign (View v){
        DesignModel designModel = new DesignModel();
        designModel.setName(nameText.getText().toString());
        designModel.setDescription(descriptionText.getText().toString());
        designModel.setLength(lengthText.getText().toString());
        designModel.setWidth(widthText.getText().toString());
        designModel.setHeight(heightText.getText().toString());
        designModel.setPrice(priceText.getText().toString());
        designModel.setImageUri(this.imageUri);
        designModel.setStlUri(this.stlUri);

        boolean isSaveSuccessful = this.designPersistence.saveDesign(designModel);
        if(isSaveSuccessful){
            Toast.makeText(AddDesignActivity.this, "Design saved successfully",
                    Toast.LENGTH_SHORT).show();
            Intent finishSave = new Intent();
            setResult(Activity.RESULT_OK, finishSave);
            finish();
        } else {
            Toast.makeText(AddDesignActivity.this, "Design save failed",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Fires an intent to spin up the "file chooser" UI and select an image.
     */
    public void performImageFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("image/*");

        startActivityForResult(intent, READ_REQUEST_CODE_IMAGE);
    }

    /**
     * Fires an intent to spin up the "file chooser" UI and select a file.
     */
    public void performSTLFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        // intent.addCategory(Intent.CATEGORY_OPENABLE);

        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("*/*");

        startActivityForResult(intent, READ_REQUEST_CODE_STL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE_IMAGE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            if (resultData != null) {
                this.imageUri = resultData.getData();
                Log.i(TAG, "Uri: " + this.imageUri.toString());
                //showImage(uri);
            }
        }

        if (requestCode == READ_REQUEST_CODE_STL && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            if (resultData != null) {
                this.stlUri = resultData.getData();
                Log.i(TAG, "Uri: " + this.stlUri.toString());
                //showImage(uri);
            }
        }
    }
}
