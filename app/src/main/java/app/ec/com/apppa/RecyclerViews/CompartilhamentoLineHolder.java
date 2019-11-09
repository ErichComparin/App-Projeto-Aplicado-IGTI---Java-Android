package app.ec.com.apppa.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import app.ec.com.apppa.R;

public class CompartilhamentoLineHolder extends RecyclerView.ViewHolder {
    public TextView nome;

    public CompartilhamentoLineHolder(View itemView) {
        super(itemView);

        nome = (TextView) itemView.findViewById(R.id.nome);
    }
}
