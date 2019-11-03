package app.ec.com.apppa.LayerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import app.ec.com.apppa.R;
import app.ec.com.apppa.LoginViewModel;
import app.ec.com.apppa.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        loginViewModel = new LoginViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(loginViewModel);
        binding.executePendingBindings();

        loadObservables();

        //TESTE
        binding.getViewModel().onLoginClick();
        //TESTE

    }

    public void onLoginClick(View view){
        binding.getViewModel().onLoginClick();
    }

    public void onSiginClick(View view){
        startActivity(
                new Intent(LoginActivity.this, SigninActivity.class));
    }

    public void loadObservables() {

        binding.getViewModel().isLoginDone.addOnPropertyChangedCallback(
                new Observable.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable observable, int i) {
                        boolean isLoginDone = binding.getViewModel().isLoginDone.get();

                        if (isLoginDone){
                            startActivity(
                                    new Intent(LoginActivity.this, AlbumListActivity.class));
                        }
                    }
                });
    }

}

