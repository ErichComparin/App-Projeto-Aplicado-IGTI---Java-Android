package app.ec.com.apppa.LayerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import app.ec.com.apppa.R;
import app.ec.com.apppa.SigninViewModel;
import app.ec.com.apppa.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {

    ActivitySigninBinding binding;
    SigninViewModel signinViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        signinViewModel = new SigninViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        binding.setViewModel(signinViewModel);
        binding.executePendingBindings();

        loadObservables();

    }

    public void onSignClick(View view){
        binding.getViewModel().onSiginClick();
    }

    public void loadObservables() {

        binding.getViewModel().isSignDone.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        boolean isSignDone = binding.getViewModel().isSignDone.get();

                        if (isSignDone){
                            startActivity(
                                    new Intent(SigninActivity.this, AlbumListActivity.class));
                        }
                    }
                });
    }
}
