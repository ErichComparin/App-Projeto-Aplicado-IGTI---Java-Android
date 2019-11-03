package app.ec.com.apppa.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerModel.Imagem;
import app.ec.com.apppa.LayerModel.Usuario;

public class FirebaseHelper extends Observable{

    private static FirebaseHelper firebaseHelperInstance; // Singleton
    private List<Observer> observers = new ArrayList<>();
    private Usuario usuarioFB = new Usuario();

    private FirebaseHelper(){
        addUsuarioListener();
    }

    public static synchronized FirebaseHelper getInstance(){
        if (firebaseHelperInstance == null)
            firebaseHelperInstance = new FirebaseHelper();

        return firebaseHelperInstance;
    }

    public void notificarObservers(){
        for(Observer observer : observers){
            observer.update(this, null);
        }
    }

    public void registrarObserver(Observer observer){
        observers.add(observer);
    }

    public FirebaseUser getFirebaseUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public DatabaseReference getRefUsuario(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        return database.getReference().child(getFirebaseUser().getUid());
    }

    public Usuario getUsuarioFromSnapshot(DataSnapshot dataSnapshot){
        return dataSnapshot.getValue(Usuario.class);
    }

    public void addUsuarioListener(){
        final DatabaseReference refUsuario = getRefUsuario();

        refUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    usuarioFB = getUsuarioFromSnapshot(dataSnapshot);
                    notificarObservers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    public void insUsuario(final String albumNome){
        final DatabaseReference refUsuario = getRefUsuario();
        usuarioFB.addAlbum(new Album(albumNome));
        refUsuario.setValue(usuarioFB);
    }

    public ArrayList<Album> retAlbuns(){
        return usuarioFB.getAlbuns();
    }

    public Album retAlbum(int pos){
        ArrayList<Album> albuns = retAlbuns();
        Album album = albuns.get(pos);
        ArrayList<Imagem> imagens = album.getImagens();

        return album;
    }
}


