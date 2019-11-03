package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import app.ec.com.apppa.FotoListViewModel;
import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.R;
import app.ec.com.apppa.RecyclerViews.FotoLineAdapter;
import app.ec.com.apppa.databinding.ActivityFotoListBinding;

public class FotoListActivity extends AppCompatActivity {

    ActivityFotoListBinding binding;
    FotoListViewModel fotoListViewModel;
    RecyclerView mFotoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        fotoListViewModel = new FotoListViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foto_list);
        binding.setViewModel(fotoListViewModel);
        binding.executePendingBindings();

        setupFotoRecycler();
        loadObservables();

        fotoListViewModel.onCreate(getIntent().getIntExtra("POS", 0));
    }

    private void setupFotoRecycler(){

        mFotoRecyclerView = (RecyclerView) findViewById(R.id.fotoRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mFotoRecyclerView.setLayoutManager(layoutManager);

        mFotoRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );

    }

    public void loadObservables() {

        binding.getViewModel().album.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Album album = binding.getViewModel().album.get();
                FotoLineAdapter mAdapter = new FotoLineAdapter(album.getImagens());
                mFotoRecyclerView.setAdapter(mAdapter);
            }
        });
    }
}
