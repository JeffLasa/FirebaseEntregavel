package com.digitalhouse.firebaseentregavel.modules.Cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.digitalhouse.firebaseentregavel.R;
import com.digitalhouse.firebaseentregavel.modules.login.view.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "CadastroActivity";
    private FirebaseAuth firebaseAuth;
    private TextInputEditText editTextSenha;
    private TextInputEditText editTextConfirmarSenha;
    private TextInputEditText editTextEmail;
    private TextInputEditText editTextNome;
    private Button buttonCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editTextSenha = findViewById(R.id.register_edit_text_password_id);
        editTextConfirmarSenha = findViewById(R.id.register_edit_text_confirm_password_id);
        editTextEmail = findViewById(R.id.register_edit_text_email_id);
        editTextNome = findViewById(R.id.register_edit_text_nome_id);
        buttonCriarConta = findViewById(R.id.register_button_criar_conta_id);

        buttonCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botaoClicado(this,view);
            }
        });

    }


    public void botaoClicado(View.OnClickListener onClickListener, View view) {
        editTextSenha.setError(null);
        editTextConfirmarSenha.setError(null);
        editTextEmail.setError(null);

        if (!editTextSenha.getEditableText().toString().equals(editTextConfirmarSenha.getEditableText().toString())) {
            editTextSenha.setError("As senhas não conferem");
            editTextConfirmarSenha.setError("As senhas não conferem");
        } else if (editTextSenha.getEditableText().toString().equals("")) {
            editTextSenha.setError("Campo obrigatório");
        } else if (editTextEmail.getEditableText().toString().equals("")) {
            editTextEmail.setError("Campo obrigatório");
        } else {
            Snackbar.make(view, "Usuário cadastro com sucesso", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cadastrarUsuario();
                            irParaLogin();
                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorAzul))
                    .show();
        }
    }



    private void cadastrarUsuario() {
        String email = editTextEmail.getEditableText().toString();
        String senha = editTextSenha.getEditableText().toString();

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            atualizarDadosUsuario(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void atualizarDadosUsuario(FirebaseUser user) {
        String nome = editTextNome.getEditableText().toString();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            finish();
                        }
                    }
                });

    }
    private void irParaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}