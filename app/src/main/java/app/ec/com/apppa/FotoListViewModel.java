package app.ec.com.apppa;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.core.content.FileProvider;
import androidx.databinding.ObservableField;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.Firebase.FirebaseHelper;
import app.ec.com.apppa.LayerModel.Album;

import static app.ec.com.apppa.Firebase.FirebaseHelper.getInstance;

public class FotoListViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance();
    public ObservableField<Album> album = new ObservableField<>();
    private int mPos;
    //private String currentPhotoPath;
    private File storageDir;

    public void onCreate(int pos){
        mPos = pos;
        album.set(fbHelper.retAlbum(mPos));
        fbHelper.registrarObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        album.set(fbHelper.retAlbum(mPos));
    }

    public void onInsClick(File pStorageDir, Bitmap imageBitmap){
        storageDir = pStorageDir;
    }

    public Uri createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",   /* suffix */
                storageDir      /* directory */
        );

        Uri photoURI = FileProvider.getUriForFile(context,
                "${applicationId}.fileprovider",
                image);

        // Save a file: path for use with ACTION_VIEW intents
        //currentPhotoPath = image.getAbsolutePath();
        return photoURI;
    }
}
