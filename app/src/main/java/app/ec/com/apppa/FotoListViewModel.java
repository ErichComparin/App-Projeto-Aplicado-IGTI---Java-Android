package app.ec.com.apppa;

import android.util.Log;

import androidx.databinding.ObservableField;

import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.Firebase.FirebaseHelper;
import app.ec.com.apppa.LayerModel.Album;

import static app.ec.com.apppa.Firebase.FirebaseHelper.getInstance;

public class FotoListViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance();
    public ObservableField<Album> album = new ObservableField<>();
    public int mPos;

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
