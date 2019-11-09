package app.ec.com.apppa;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninViewModel {

    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");
    public ObservableBoolean loadingVisibility = new ObservableBoolean(false);
    public ObservableBoolean errorVisibility = new ObservableBoolean(false);
    public ObservableBoolean isSignDone = new ObservableBoolean(false);
    private FirebaseAuth mAuth;

    public void onSiginClick(){

        String mEmail = email.get();
        String mPassword = password.get();

        if (mEmail.equals("") || mPassword.equals("")){
            return;
        }

        loadingVisibility.set(true);

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            isSignDone.set(true);
                        }else {
                            Exception e = task.getException();
                            Log.e("ECERR_SigninViewModel1", e.getMessage());
                            loadingVisibility.set(false);
                            errorVisibility.set(true);
                        }
                    }
           });
    }
}
