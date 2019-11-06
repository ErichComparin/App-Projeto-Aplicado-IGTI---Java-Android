package app.ec.com.apppa;

import android.content.Context;
import android.net.Uri;
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
import app.ec.com.apppa.LayerModel.Album;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class FotoListViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance();
    public ObservableField<Album> album = new ObservableField<>();
    private int mPos;
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

}
