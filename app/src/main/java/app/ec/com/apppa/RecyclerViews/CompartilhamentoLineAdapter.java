package app.ec.com.apppa.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.ec.com.apppa.Helpers.FirebaseHelper;
import app.ec.com.apppa.LayerModel.CompartilhamentoPub;
import app.ec.com.apppa.LayerModel.UsuarioPub;
import app.ec.com.apppa.R;

import static app.ec.com.apppa.Helpers.FirebaseHelper.getInstance;

public class CompartilhamentoLineAdapter extends RecyclerView.Adapter<CompartilhamentoLineHolder>{

    private final List<UsuarioPub> mUsuariosPub;
    private String mId_album;

    public CompartilhamentoLineAdapter(List<UsuarioPub> usuariosPub, String id_album) {
        this.mUsuariosPub = usuariosPub;
        this.mId_album = id_album;
    }

    @NonNull
    @Override
    public CompartilhamentoLineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompartilhamentoLineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.compartilhamento_line, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompartilhamentoLineHolder holder, final int position) {

        Log.e("eccc", "POS: " + position);
        Log.e("eccc", "SIZE: " + mUsuariosPub.size());

        holder.nome.setText(mUsuariosPub.get(position).getNome());

        holder.btnCompartilhar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseHelper fbHelper = getInstance();
                fbHelper.insCompartilhamento(new CompartilhamentoPub("",
                        mUsuariosPub.get(position).getId(),
                        mId_album));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsuariosPub != null ? mUsuariosPub.size() : 0;
    }












}
