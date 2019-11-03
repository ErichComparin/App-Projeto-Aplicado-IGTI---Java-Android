package app.ec.com.apppa.LayerModel;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Album> albuns = new ArrayList();

    public Usuario() { }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(String nome, ArrayList<Album> albuns) {
        this.nome = nome;
        this.albuns = albuns;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Album> getAlbuns() {
        return albuns;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAlbuns(ArrayList<Album> albuns) {
        this.albuns = albuns;
    }

    public void addAlbum(Album album){
        albuns.add(album);
    }

}
