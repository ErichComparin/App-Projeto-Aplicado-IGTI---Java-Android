package app.ec.com.apppa;

import androidx.databinding.ObservableField;

import app.ec.com.apppa.Helpers.FirebaseHelper;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class AlbumInsViewModel {
    public ObservableField<String> albumNome = new ObservableField<>();

    public void onInsert() {
        String mAlbumNome = albumNome.get();

        if (mAlbumNome.equals("")){
            return;
        }

        FirebaseHelper fbHelper = getInstance();

        fbHelper.insAlbum(mAlbumNome);

    }
}
