package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import app.ec.com.apppa.CompartilhamentoViewModel;
import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.LayerModel.UsuarioPub;
import app.ec.com.apppa.R;
import app.ec.com.apppa.RecyclerViews.CompartilhamentoLineAdapter;
import app.ec.com.apppa.databinding.ActivityCompartilhamentoBinding;

public class CompartilhamentoActivity extends AppCompatActivity {

    ActivityCompartilhamentoBinding binding;
    CompartilhamentoViewModel compartilhamentoViewModel;
    RecyclerView mUsuariosRecyclerView;
    private String mIdAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        compartilhamentoViewModel = new CompartilhamentoViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_compartilhamento);
        binding.setViewModel(compartilhamentoViewModel);
        binding.executePendingBindings();

        mIdAlbum = getIntent().getStringExtra("ID_ALBUM");
        String mNomeAlbum = getIntent().getStringExtra("NOME_ALBUM");

        setupAlbumRecycler();
        loadObservables();

        compartilhamentoViewModel.onCreate(mNomeAlbum);

        // Toolbar - botão voltar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupAlbumRecycler(){

        mUsuariosRecyclerView = (RecyclerView) findViewById(R.id.compartilhamentoRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mUsuariosRecyclerView.setLayoutManager(layoutManager);

        mUsuariosRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

    }

    public void loadObservables() {

        binding.getViewModel().usuariosPub.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                ArrayList<UsuarioPub> usuariosPub = binding.getViewModel().usuariosPub.get();
                CompartilhamentoLineAdapter mAdapter = new CompartilhamentoLineAdapter(usuariosPub, mIdAlbum);
                mUsuariosRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // volta para a Activity anterior
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
