package app.ec.com.apppa.LayerModel;

public class CompartilhamentoPub {
    private String de;
    private String para;
    private String album_id;

    public CompartilhamentoPub() {
    }

    public CompartilhamentoPub(String de, String para, String album_id) {
        this.de = de;
        this.para = para;
        this.album_id = album_id;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }
}
