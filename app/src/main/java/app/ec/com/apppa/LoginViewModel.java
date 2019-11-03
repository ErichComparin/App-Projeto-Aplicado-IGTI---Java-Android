package app.ec.com.apppa;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel {

    public ObservableField<String> email = new ObservableField<>("erich@gmail.com");
    public ObservableField<String> password = new ObservableField<>("123456");
    public ObservableBoolean loadingVisibility = new ObservableBoolean(false);
    public ObservableBoolean errorVisibility = new ObservableBoolean(false);
    public ObservableBoolean isLoginDone = new ObservableBoolean(false);
    private FirebaseAuth mAuth;

    public void onLoginClick(){

        String mEmail = email.get();
        String mPassword = password.get();

        if (mEmail.equals("") || mPassword.equals("")){
            return;
        }

        loadingVisibility.set(true);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            isLoginDone.set(true);
                        }else {
                            loadingVisibility.set(false);
                            errorVisibility.set(true);
                        }
                    }
                });

    }

}

