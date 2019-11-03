package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import app.ec.com.apppa.RecyclerViews.AlbumLineAdapter;
import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.R;
import app.ec.com.apppa.AlbumListViewModel;
import app.ec.com.apppa.databinding.ActivityAlbumListBinding;

public class AlbumListActivity extends AppCompatActivity {

    ActivityAlbumListBinding binding;
    AlbumListViewModel albumListViewModel;
    RecyclerView mAlbumRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        albumListViewModel = new AlbumListViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list);
        binding.setViewModel(albumListViewModel);
        binding.executePendingBindings();

        setupAlbumRecycler();
        loadObservables();

        albumListViewModel.onCreate();

    }

    public void onInsClick(View view){
        startActivity(
                new Intent(AlbumListActivity.this, AlbumInsActivity.class));
    }

    private void setupAlbumRecycler(){

        mAlbumRecyclerView = (RecyclerView) findViewById(R.id.albumRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAlbumRecyclerView.setLayoutManager(layoutManager);

        mAlbumRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

    }
    public void loadObservables() {

        binding.getViewModel().albuns.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                ArrayList<Album> albuns = binding.getViewModel().albuns.get();
                AlbumLineAdapter mAdapter = new AlbumLineAdapter(albuns);
                mAlbumRecyclerView.setAdapter(mAdapter);
            }
        });
    }

}
