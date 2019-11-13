package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import app.ec.com.apppa.R;
import app.ec.com.apppa.AlbumInsViewModel;
import app.ec.com.apppa.databinding.ActivityAlbumInsBinding;

public class AlbumInsActivity extends AppCompatActivity {

    ActivityAlbumInsBinding binding;
    AlbumInsViewModel albumInsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        albumInsViewModel = new AlbumInsViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_ins);
        binding.setViewModel(albumInsViewModel);
        binding.executePendingBindings();

        // Toolbar - botão voltar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void onInsert(View view){
        binding.getViewModel().onInsert();
        finish(); // fecha activity
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
