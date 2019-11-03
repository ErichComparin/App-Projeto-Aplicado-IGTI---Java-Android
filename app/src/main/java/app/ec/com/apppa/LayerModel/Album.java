package app.ec.com.apppa.LayerModel;

import java.util.ArrayList;

public class Album {
    private String nome;
    private ArrayList<Imagem> imagens = new ArrayList();;

    public Album() {  }

    public Album(String nome) {
        this.nome = nome;
    }

    public Album(String nome, ArrayList<Imagem> imagens) {
        this.nome = nome;
        this.imagens = imagens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<Imagem> imagens) {
        this.imagens = imagens;
    }

    public void addImagem(Imagem imagem){
        imagens.add(imagem);
    }
}
