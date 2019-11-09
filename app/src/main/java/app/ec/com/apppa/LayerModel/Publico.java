package app.ec.com.apppa.LayerModel;

import java.util.ArrayList;

public class Publico {
    private ArrayList<UsuarioPub> albuns = new ArrayList();

    public Publico() {
    }

    public Publico(ArrayList<UsuarioPub> albuns) {
        this.albuns = albuns;
    }

    public ArrayList<UsuarioPub> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(ArrayList<UsuarioPub> albuns) {
        this.albuns = albuns;
    }
}
