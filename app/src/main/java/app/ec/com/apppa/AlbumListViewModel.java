package app.ec.com.apppa;

import androidx.databinding.ObservableField;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.Helpers.FirebaseHelper;
import app.ec.com.apppa.LayerModel.Album;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class AlbumListViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance(); // instancia classe para iniciar listener do Firebase
    public ObservableField<ArrayList<Album>> albuns = new ObservableField<>();

    public void onCreate(){
        albuns.set(fbHelper.retAlbuns());
        fbHelper.registrarObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        albuns.set(fbHelper.retAlbuns());
    }
}
