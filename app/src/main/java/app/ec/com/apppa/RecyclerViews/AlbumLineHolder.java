package app.ec.com.apppa.RecyclerViews;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import app.ec.com.apppa.R;

public class AlbumLineHolder extends RecyclerView.ViewHolder{

    public TextView album;
    public ConstraintLayout linha;

    public AlbumLineHolder(View itemView) {
        super(itemView);

        album = (TextView) itemView.findViewById(R.id.album);
        linha = (ConstraintLayout) itemView.findViewById(R.id.linha);
    }

}
