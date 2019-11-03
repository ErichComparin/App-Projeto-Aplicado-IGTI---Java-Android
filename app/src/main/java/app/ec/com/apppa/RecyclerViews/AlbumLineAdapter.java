package app.ec.com.apppa.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerView.FotoListActivity;
import app.ec.com.apppa.R;

public class AlbumLineAdapter extends RecyclerView.Adapter<AlbumLineHolder> {

    private final List<Album> mAlbuns;

    public AlbumLineAdapter(List<Album> albuns) {
        this.mAlbuns = albuns;
    }

    @NonNull
    @Override
    public AlbumLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumLineHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.album_line, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumLineHolder holder, final int position) {
        holder.album.setText(mAlbuns.get(position).getNome());

        holder.linha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, FotoListActivity.class);
                intent.putExtra("POS", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlbuns != null ? mAlbuns.size() : 0;
    }

}
