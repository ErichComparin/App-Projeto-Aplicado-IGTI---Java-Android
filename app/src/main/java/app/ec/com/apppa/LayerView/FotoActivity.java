package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import app.ec.com.apppa.FotoViewModel;
import app.ec.com.apppa.R;
import app.ec.com.apppa.databinding.ActivityFotoBinding;

public class FotoActivity extends AppCompatActivity {

    ActivityFotoBinding binding;
    FotoViewModel fotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        fotoViewModel = new FotoViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foto);
        binding.setViewModel(fotoViewModel);
        binding.executePendingBindings();

        loadObservables();

        int posAlbum = getIntent().getIntExtra("POS_ALBUM", 0);
        int posFoto = getIntent().getIntExtra("POS_FOTO", 0);
        fotoViewModel.onCreate(posAlbum, posFoto);

        // Toolbar - botão voltar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void loadObservables() {

        binding.getViewModel().fotoUri.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        Uri fotoUri = binding.getViewModel().fotoUri.get();
                        ImageView foto = (ImageView) findViewById(R.id.foto);
                        foto.setImageURI(fotoUri);
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
