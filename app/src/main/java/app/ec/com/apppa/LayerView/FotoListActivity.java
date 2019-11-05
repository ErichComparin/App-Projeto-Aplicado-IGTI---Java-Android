package app.ec.com.apppa.LayerView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import app.ec.com.apppa.FotoListViewModel;
import app.ec.com.apppa.LayerModel.Album;
import app.ec.com.apppa.R;
import app.ec.com.apppa.RecyclerViews.FotoLineAdapter;
import app.ec.com.apppa.databinding.ActivityFotoListBinding;

public class FotoListActivity extends AppCompatActivity {

    ActivityFotoListBinding binding;
    FotoListViewModel fotoListViewModel;
    RecyclerView mFotoRecyclerView;
    static final int REQUEST_IMAGE_CAPTURE = 1;

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

    public void onInsClick(View view){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            Uri uri = null;
            try{
                uri = fotoListViewModel.createImageFile(this.getBaseContext(), getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            } catch (IOException ex){
                Log.i("eccc", ex.toString());
            }
            if (uri != null) {
                if (ContextCompat.checkSelfPermission(this.getBaseContext(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(FotoListActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_IMAGE_CAPTURE);
                }

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            };
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fotoListViewModel.onInsClick(getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageBitmap);
        }
    }

}
