package app.ec.com.apppa.RecyclerViews;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import app.ec.com.apppa.R;

public class FotoLineHolder extends RecyclerView.ViewHolder {

    public ImageView imagem;

    public FotoLineHolder(View itemView) {
        super(itemView);

        imagem = (ImageView) itemView.findViewById(R.id.imagem);
    }

}
