package app.ec.com.apppa.LayerModel;

import android.util.Log;

import java.io.IOException;

import app.ec.com.apppa.Helpers.FirebaseHelper;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class Imagem {
    private String link;

    public Imagem() { }

    public Imagem(String link) {
        this.link = link;
    }

    public String getLink() {
        FirebaseHelper fbHelper = getInstance();
        try{
            fbHelper.downloadFoto(link);
        } catch (IOException ex) {
            Log.e("ECERR_Imagem1", ex.getMessage());
        }
        return link;
    }

    public String retThumb(){
        return getLink().replace(".jpg", "_THUMB.jpg");
    }

    public void setLink(String link) {
        this.link = link;
    }

}
