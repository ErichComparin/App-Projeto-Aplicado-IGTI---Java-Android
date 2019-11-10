package app.ec.com.apppa.Helpers;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("ECERR_FirebaseHelper1", exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                insFotoRD(taskSnapshot.getMetadata().getName());
            }
        });
    }

    private void insFotoRD(String link){
        final DatabaseReference refUsuario = getRefUsuario();
        usuarioRD.getAlbum(albumPos).addImagem(new Imagem(link));
        refUsuario.setValue(usuarioRD);
    }

    public void downloadFoto(String link) throws IOException {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference fotoRef = storageRef.child(link);
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        File localFile = new File(storageDir, link);

        if (!localFile.exists()){
            fotoRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    notificarObserversUsuarioRD();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("ECERR_FirebaseHelper2", exception.toString());
                }
            });
        } else{
            notificarObserversUsuarioRD();
        }
    }

    //--PÚBLICO-----------------------------

    public ArrayList<UsuarioPub> getUsuariosPub() {
        return publicoRD.getUsuarios();
    }

    public void insCompartilhamento(CompartilhamentoPub compartilhamento){
        final DatabaseReference refPublico = getRefPublico();
        compartilhamento.setDe(getFirebaseUser().getUid());

        publicoRD.addCompartilhamento(compartilhamento);

        refPublico.setValue(publicoRD);
    }
}


