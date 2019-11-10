package app.ec.com.apppa;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;
import androidx.databinding.ObservableField;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.Helpers.FirebaseHelper;
import app.ec.com.apppa.Helpers.PermissionManager;
import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerView.CompartilhamentoActivity;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class FotoListViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance();
    public ObservableField<Album> album = new ObservableField<>();
    private int mPos;
    final PermissionManager permissionManager = new PermissionManager();
    private static final int PHOTO_REQUEST_CODE = 102;
    private String path;

    public void onCreate(int pos){
        mPos = pos;
        album.set(fbHelper.retAlbum(mPos));
        fbHelper.registrarObserverUsuarioRD(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        album.set(fbHelper.retAlbum(mPos));
    }

    public void onInsClick(Context context, Activity activity){
        if (permissionManager.userHasPermission(context)) {
            takePicture(context, activity);
        } else {
            permissionManager.requestPermission(activity);
        }
    }

    private void takePicture(Context context, Activity activity) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            Uri photoURI = null;
            try {
                File photoFile = createImageFileWith();
                path = photoFile.getAbsolutePath();
                photoURI = FileProvider.getUriForFile(context,
                        context.getString(R.string.file_provider_authority),
                        photoFile);
            } catch (IOException ex) {
                Log.e("ECERR_FotoListActivity1", ex.getMessage());
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.setClipData(ClipData.newRawUri("", photoURI));
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            activity.startActivityForResult(takePictureIntent, PHOTO_REQUEST_CODE);
        }
    }

    public File createImageFileWith() throws IOException {
        final String imageFileName = "JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        storageDir.mkdirs();
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    public void insFotoFB(){
        fbHelper.insFoto(path, mPos);
    }

    public void onCompartilharClick(Activity activity){

        Intent intent = new Intent(activity, CompartilhamentoActivity.class);

        intent.putExtra("ID_ALBUM", album.get().getId());
        activity.startActivity(intent);
    }
}
