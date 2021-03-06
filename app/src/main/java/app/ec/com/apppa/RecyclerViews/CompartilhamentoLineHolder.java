package app.ec.com.apppa.RecyclerViews;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import app.ec.com.apppa.R;

public class CompartilhamentoLineHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public ImageButton btnCompartilhar;

    public CompartilhamentoLineHolder(View itemView) {
        super(itemView);

        nome = (TextView) itemView.findViewById(R.id.nome);
        btnCompartilhar = (ImageButton) itemView.findViewById(R.id.btnCompartilhar);
    }
}
