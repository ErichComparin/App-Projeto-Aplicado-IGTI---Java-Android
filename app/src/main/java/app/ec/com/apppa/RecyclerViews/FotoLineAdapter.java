package app.ec.com.apppa.RecyclerViews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import app.ec.com.apppa.LayerModel.Imagem;
import app.ec.com.apppa.R;

public class FotoLineAdapter extends RecyclerView.Adapter<FotoLineHolder> {

    private final List<Imagem> mFotos;

    public FotoLineAdapter(List<Imagem> mFotos) {
        this.mFotos = mFotos;
    }

    @NonNull
    @Override
    public FotoLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FotoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foto_line, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FotoLineHolder holder, int position) {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        File localFile = new File(storageDir, mFotos.get(position).getLink());
        holder.imagem.setImageURI(Uri.fromFile(localFile));
    }

    @Override
    public int getItemCount() {
        return mFotos != null ? mFotos.size() : 0;
    }

}
