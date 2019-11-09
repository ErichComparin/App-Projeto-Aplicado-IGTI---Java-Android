package app.ec.com.apppa.LayerModel;

import java.util.ArrayList;

public class Publico {
    private ArrayList<UsuarioPub> usuarios = new ArrayList();

    public Publico() {
    }

    public Publico(ArrayList<UsuarioPub> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<UsuarioPub> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<UsuarioPub> usuarios) {
        this.usuarios = usuarios;
    }
}
