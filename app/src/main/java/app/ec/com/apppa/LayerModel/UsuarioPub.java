package app.ec.com.apppa.LayerModel;

public class UsuarioPub {
    String nome;
    String id;

    public UsuarioPub() {
    }

    public UsuarioPub(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
