package app.ec.com.apppa.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ec.com.apppa.LayerModel.UsuarioPub;
import app.ec.com.apppa.R;

public class CompartilhamentoLineAdapter extends RecyclerView.Adapter<CompartilhamentoLineHolder>{

    private final List<UsuarioPub> mUsuariosPub;

    public CompartilhamentoLineAdapter(List<UsuarioPub> usuariosPub) {
        this.mUsuariosPub = usuariosPub;
    }

    @NonNull
    @Override
    public CompartilhamentoLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompartilhamentoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compartilhamento_line, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompartilhamentoLineHolder holder, final int position) {
        holder.nome.setText(mUsuariosPub.get(position).getNome());

        /*holder.linha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, FotoListActivity.class);
                intent.putExtra("POS", position);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mUsuariosPub != null ? mUsuariosPub.size() : 0;
    }












}
