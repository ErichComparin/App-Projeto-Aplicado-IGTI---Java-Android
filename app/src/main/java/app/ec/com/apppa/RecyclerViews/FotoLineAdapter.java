package app.ec.com.apppa.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import app.ec.com.apppa.LayerView.FotoActivity;
import app.ec.com.apppa.LayerView.FotoListActivity;
import app.ec.com.apppa.R;

public class FotoLineAdapter extends RecyclerView.Adapter<FotoLineHolder> {

    private final List<Imagem> mFotos;
    private int posAlbum;

    public FotoLineAdapter(List<Imagem> mFotos, int mPosAlbum) {
        this.mFotos = mFotos;
        this.posAlbum = mPosAlbum;
    }

    @NonNull
    @Override
    public FotoLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FotoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.foto_line, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FotoLineHolder holder, final int position) {
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AppPA");
        File localFile = new File(storageDir, mFotos.get(position).retThumb());
        holder.imagem.setImageURI(Uri.fromFile(localFile));

        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, FotoActivity.class);
                int posInvertida = mFotos.size() - 1 - position;
                intent.putExtra("POS_ALBUM", posAlbum);
                intent.putExtra("POS_FOTO", posInvertida);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFotos != null ? mFotos.size() : 0;
    }

}
