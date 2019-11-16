package app.ec.com.apppa.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerModel.Imagem;

public class DownloadFotos extends Thread {
    private ArrayList<Album> albuns = new ArrayList();

    public DownloadFotos(ArrayList<Album> albuns) {
        this.albuns = albuns;
    }

    @Override
    public void run() {
        synchronized(this) {
            for(Album album : albuns) {
                ArrayList<Imagem> imagens = album.getImagens();
                for (Imagem imagem : imagens) {
                    File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
                    File localFile = new File(storageDir, imagem.getLink());
                    final String path = localFile.getPath();

                    if (!localFile.exists()) {
                        StorageReference fotoRef = FirebaseStorage.getInstance().getReference().child(imagem.getLink());
                        fotoRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                insThumb(path);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Log.e("ECERR_DownloadFotos1", exception.toString());
                            }
                        });
                    }
                }
            }
        }
    }

    public void insThumb(String path){
        final int THUMBSIZE = 128;
        try{
            Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(path), THUMBSIZE, THUMBSIZE);
            saveBitmap(thumbImage, path);
        }catch(Exception exception) {
            Log.e("ECERR_DownloadFotos2", exception.getMessage());
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
}
