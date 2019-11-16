package app.ec.com.apppa.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerModel.CompartilhamentoPub;
import app.ec.com.apppa.LayerModel.Imagem;
import app.ec.com.apppa.LayerModel.Publico;
import app.ec.com.apppa.LayerModel.Usuario;
import app.ec.com.apppa.LayerModel.UsuarioPub;

public class FirebaseHelper extends Observable{

    private static FirebaseHelper firebaseHelperInstance; // Singleton
    private List<Observer> observersUsuarioRD = new ArrayList<>();
    private Usuario usuarioRD = new Usuario(); // Dados do usuário logado
    private List<Observer> observersPublicoRD = new ArrayList<>();
    private Publico publicoRD = new Publico(); // Lista pública de usuários e compartilhamento
    private int albumPos;

    //--CONSTRUCTOR-----------------------------
    private FirebaseHelper(){
        addUsuarioListener();
        addUsuariosPubListener();
    }

    public static synchronized FirebaseHelper getInstance(){
        if (firebaseHelperInstance == null)
            firebaseHelperInstance = new FirebaseHelper();

        return firebaseHelperInstance;
    }

    //--Observers-----------------------------
    private void notificarObserversUsuarioRD(){
        for(Observer observer : observersUsuarioRD){
            observer.update(this, null);
        }
    }

    public void registrarObserverUsuarioRD(Observer observer){
        observersUsuarioRD.add(observer);
    }

    private void notificarObserversPublicoRD(){
        for(Observer observer : observersPublicoRD){
            observer.update(this, null);
        }
    }

    public void registrarObserverPublicoRD(Observer observer){
        observersPublicoRD.add(observer);
    }

    //--REALTIME DATABASE-----------------------------
    private FirebaseUser getFirebaseUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    private DatabaseReference getRefUsuario(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference().child(getFirebaseUser().getUid());
    }

    private void addUsuarioListener(){
        final DatabaseReference refUsuario = getRefUsuario();

        refUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    usuarioRD = dataSnapshot.getValue(Usuario.class);
                    notificarObserversUsuarioRD();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private DatabaseReference getRefPublico(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        return database.getReference().child("publico");
    }

    private void addUsuariosPubListener(){
        final DatabaseReference refUsuariosPub = getRefPublico();

        refUsuariosPub.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    publicoRD = dataSnapshot.getValue(Publico.class);
                    notificarObserversPublicoRD();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    //--CLOUD STORAGE-----------------------------


    //--ÁLBUNS-----------------------------
    public void insAlbum(final String albumNome){
        final DatabaseReference refUsuario = getRefUsuario();
        usuarioRD.addAlbum(new Album(albumNome));
        refUsuario.setValue(usuarioRD);
    }

    public ArrayList<Album> retAlbuns(){
        return usuarioRD.getAlbuns();
    }

    public ArrayList<Album> retAlbunsReverse(){
        return reverseList(retAlbuns());
    }

    public Album retAlbum(int pos){
        ArrayList<Album> albuns = retAlbuns();
        Album album = albuns.get(pos);
        ArrayList<Imagem> imagens = album.getImagens();

        return album;
    }

    //--FOTOS-----------------------------
    public void insFoto(String path, int pos){
        albumPos = pos;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        Uri file = Uri.fromFile(new File(path));
        StorageReference imgsRef = storageRef.child(file.getLastPathSegment());
        UploadTask uploadTask = imgsRef.putFile(file);
        final String pathFinal = path;

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("ECERR_FirebaseHelper1", exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                insFotoRD(taskSnapshot.getMetadata().getName());
                insThumb(pathFinal);
            }
        });
    }

    public void insThumb(String path){
        final int THUMBSIZE = 128;
        try{
            Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path),
                    THUMBSIZE, THUMBSIZE);
            saveBitmap(thumbImage, path);
        }catch(Exception exception) {
            Log.e("ECERR_FirebaseHelper3", exception.getMessage());
        }
    }

    public void saveBitmap(Bitmap bmp, String path) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(path.replace(".jpg", "_THUMB.jpg"));
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
    }

    private void insFotoRD(String link){
        final DatabaseReference refUsuario = getRefUsuario();
        usuarioRD.getAlbum(albumPos).addImagem(new Imagem(link));
        refUsuario.setValue(usuarioRD);

        insFotoBE(usuarioRD.getAlbum(albumPos).getId(), link);
    }

    public void downloadFoto(String link) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference fotoRef = storageRef.child(link);
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        File localFile = new File(storageDir, link);
        final String pathFinal = localFile.getPath();

        if (!localFile.exists()){
            fotoRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    insThumb(pathFinal);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("ECERR_FirebaseHelper2", exception.toString());
                }
            });
        }
    }

    public String retImagem(int posAlbum, int posFoto){
        return usuarioRD.getAlbum(posAlbum).getImagem(posFoto).getLink();
    }

    //--PÚBLICO-----------------------------

    public ArrayList<UsuarioPub> getUsuariosPub() {
        return publicoRD.getUsuarios();
    }

    public void insCompartilhamento(CompartilhamentoPub compartilhamento){

        boolean isCompartilhado = false;

        for (CompartilhamentoPub comp : publicoRD.getCompartilhamentos()){
            if (comp.getPara().equals(compartilhamento.getPara()) &&
                    comp.getAlbum_id().equals(compartilhamento.getAlbum_id())){
                isCompartilhado = true;
                break;
            }
        }

        if (!isCompartilhado) {
            compartilhamento.setDe(getFirebaseUser().getUid());

            final DatabaseReference refPublico = getRefPublico();
            publicoRD.addCompartilhamento(compartilhamento);
            refPublico.setValue(publicoRD);

            insCompartilhamentoBE(compartilhamento);
        }

    }

    //--BACKEND-----------------------------
    //--MIGRAR PARA FIREBASE CLOUD FUNCTIONS

    public void insCompartilhamentoBE(CompartilhamentoPub compartilhamento){
        FirebaseDatabase dbComp = FirebaseDatabase.getInstance();
        final DatabaseReference refComp = dbComp.getReference().child(compartilhamento.getPara());
        Album albumComp = new Album();

        for (Album album : usuarioRD.getAlbuns()) {
            if (album.getId().equals(compartilhamento.getAlbum_id())) {
                albumComp = album;
                break;
            }
        }

        final Album albumCompFinal = albumComp;

        refComp.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Usuario usuarioCompRD = dataSnapshot.getValue(Usuario.class);
                    usuarioCompRD.addAlbum(albumCompFinal);
                    refComp.setValue(usuarioCompRD);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void insFotoBE(String albumId, String link){
        ArrayList<String> usuariosId = new ArrayList();

        for (CompartilhamentoPub comp : publicoRD.getCompartilhamentos()){
            if (!comp.getPara().equals(getFirebaseUser().getUid()) &&
                    comp.getAlbum_id().equals(albumId)){
                usuariosId.add(comp.getPara());
            }
        }

        for (String usuarioId : usuariosId){
            insFotoUsuarioBE(usuarioId, albumId, link);
        }

    }

    public void insFotoUsuarioBE(String usuarioId, String albumId, String link){
        FirebaseDatabase dbUsuario = FirebaseDatabase.getInstance();
        final DatabaseReference refUsuario = dbUsuario.getReference().child(usuarioId);
        final String albumIdFinal = albumId;
        final String linkFinal = link;

        refUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Usuario usuarioRD = dataSnapshot.getValue(Usuario.class);
                    for (Album album : usuarioRD.getAlbuns()){
                        if (album.getId().equals(albumIdFinal)){
                            album.addImagem(new Imagem(linkFinal));
                        }
                    }
                    refUsuario.setValue(usuarioRD);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void insUsuarioBE(String nome, String usuarioId){
        final DatabaseReference refPublico = getRefPublico();
        publicoRD.addUsuario(new UsuarioPub(nome, usuarioId));
        refPublico.setValue(publicoRD);
        final String nomeFinal = nome;

        FirebaseDatabase dbUsuario = FirebaseDatabase.getInstance();
        final DatabaseReference refUsuario = dbUsuario.getReference().child(usuarioId);

        refUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                refUsuario.setValue(new Usuario(nomeFinal));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }

    //--UTILS---------------------
    public static <T> ArrayList<T> reverseList(ArrayList<T> list) {
        int length = list.size();
        ArrayList<T> result = new ArrayList<T>(length);

        for (int i = length - 1; i >= 0; i--) {
            result.add(list.get(i));
        }

        return result;
    }
}


