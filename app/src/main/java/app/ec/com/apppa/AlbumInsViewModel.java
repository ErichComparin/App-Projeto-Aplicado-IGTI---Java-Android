package app.ec.com.apppa;

import androidx.databinding.ObservableField;

import app.ec.com.apppa.Firebase.FirebaseHelper;

import static app.ec.com.apppa.Firebase.FirebaseHelper.getInstance;

public class AlbumInsViewModel {
    public ObservableField<String> albumNome = new ObservableField<>();

    public void onInsert() {
        String mAlbumNome = albumNome.get();
        FirebaseHelper fbHelper = getInstance();

        fbHelper.insUsuario(mAlbumNome);

    }
}
