package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
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
    }

    public void onInsert(View view){
        binding.getViewModel().onInsert();
        finish(); // fecha activity
    }
}
