package app.ec.com.apppa;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.databinding.ObservableField;

import java.io.File;

import app.ec.com.apppa.Helpers.FirebaseHelper;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class FotoViewModel {
    public ObservableField<Uri> fotoUri = new ObservableField<>();
    private FirebaseHelper fbHelper = getInstance();
    private int posAlbum;
    private int posFoto;

    public void onCreate(int mPosAlbum, int mPosFoto){
        posAlbum = mPosAlbum;
        posFoto = mPosFoto;

        String link = fbHelper.retImagem(posAlbum, posFoto);
        montaUri(link);
    }

    private void montaUri(String link){
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        File localFile = new File(storageDir, link);
        fotoUri.set(Uri.fromFile(localFile));
    }
}
