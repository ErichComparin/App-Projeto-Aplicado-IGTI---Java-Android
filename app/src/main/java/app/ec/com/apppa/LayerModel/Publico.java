package app.ec.com.apppa.LayerModel;

import java.util.ArrayList;

public class Publico {
    private ArrayList<UsuarioPub> usuarios = new ArrayList();
    private ArrayList<CompartilhamentoPub> compartilhamentos = new ArrayList();

    public Publico() {
    }

    public Publico(ArrayList<UsuarioPub> usuarios, ArrayList<CompartilhamentoPub> compartilhamentos) {
        this.usuarios = usuarios;
        this.compartilhamentos = compartilhamentos;
    }

    public ArrayList<UsuarioPub> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<UsuarioPub> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<CompartilhamentoPub> getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(ArrayList<CompartilhamentoPub> compartilhamentos) {
        this.compartilhamentos = compartilhamentos;
    }

    public void addUsuario(UsuarioPub usuario){
        usuarios.add(usuario);
    }

    public void addCompartilhamento(CompartilhamentoPub compartilhamento){
        compartilhamentos.add(compartilhamento);
    }
}
