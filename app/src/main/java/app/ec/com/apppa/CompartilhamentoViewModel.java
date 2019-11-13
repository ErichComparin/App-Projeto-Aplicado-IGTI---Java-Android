package app.ec.com.apppa;

import androidx.databinding.ObservableField;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.Helpers.FirebaseHelper;
import app.ec.com.apppa.LayerModel.UsuarioPub;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class CompartilhamentoViewModel implements Observer {
    private FirebaseHelper fbHelper = getInstance(); // instancia classe para iniciar listener do Firebase
    public ObservableField<ArrayList<UsuarioPub>> usuariosPub = new ObservableField<>();
    public ObservableField<String> toolbarTitulo = new ObservableField<>();

    public void onCreate(String nomeAlbum){
        toolbarTitulo.set(nomeAlbum);
        usuariosPub.set(fbHelper.getUsuariosPub());
        fbHelper.registrarObserverPublicoRD(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        usuariosPub.set(fbHelper.getUsuariosPub());
    }
}
