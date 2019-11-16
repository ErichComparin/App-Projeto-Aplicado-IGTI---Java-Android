package app.ec.com.apppa.LayerModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Album {
    private String nome;
    private String id;
    private ArrayList<Imagem> imagens = new ArrayList();;

    public Album() { this.id =  createId();}

    public Album(String nome) {
        this.nome = nome;
        this.id =  createId();
    }

    public Album(String nome, ArrayList<Imagem> imagens) {
        this.nome = nome;
        this.imagens = imagens;
        this.id =  createId();
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

    public ArrayList<Imagem> getImagens() {
        return imagens;
    }

    public ArrayList<Imagem> retImagensReverse() {
        return reverseList(imagens);
    }

    public void setImagens(ArrayList<Imagem> imagens) {
        this.imagens = imagens;
    }

    public void addImagem(Imagem imagem){
        imagens.add(imagem);
    }

    private String createId(){
        return "ALB_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    public static <T> ArrayList<T> reverseList(ArrayList<T> list) {
        int length = list.size();
        ArrayList<T> result = new ArrayList<T>(length);

        for (int i = length - 1; i >= 0; i--) {
            result.add(list.get(i));
        }

        return result;
    }

    public Imagem getImagem(int pos) {
        return imagens.get(pos);
    }
}
