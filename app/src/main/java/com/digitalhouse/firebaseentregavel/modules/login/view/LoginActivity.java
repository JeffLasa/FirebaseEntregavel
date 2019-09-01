package com.digitalhouse.firebaseentregavel.modules.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.digitalhouse.firebaseentregavel.R;
import com.digitalhouse.firebaseentregavel.modules.Cadastro.RegisterActivity;
import com.digitalhouse.firebaseentregavel.modules.MainActivity.view.MainActivity;
import com.digitalhouse.firebaseentregavel.modules.login.viewmodel.LoginViewModel;
import com.digitalhouse.firebaseentregavel.util.Constantes;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private TextInputEditText emailEditText;
    private TextInputEditText senhaEditText;
    private Button loginButton;
    private TextView cadastroTextView;
    private ProgressBar progressBar;
    private SharedPreferences preferences;
    private CheckBox checkBox;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email_text_nome_id);
        senhaEditText = findViewById(R.id.login_edit_text_password_id);
        loginButton = findViewById(R.id.login_button_criar_conta);
        cadastroTextView = findViewById(R.id.login_text_view_criar_conta_id);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        checkBox = findViewById(R.id.login_checkbox_id);

        SharedPreferences preferences = getSharedPreferences(Constantes.SHARED_PREFERENCES, Context.MODE_PRIVATE);

        if(preferences.contains(Constantes.EMAIL)){
            emailEditText.setText(preferences.getString(Constantes.EMAIL,""));
            senhaEditText.setText(preferences.getString(Constantes.PASSWORD,""));
        }

        loginButton.setOnClickListener( view -> logar());

        cadastroTextView.setOnClickListener(view -> LoginActivity.this.irParaCadastro());

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getAutenticadoLiveData()
                .observe(this, autenticado -> {
                    if (autenticado) {
                        irParaMain();
                    } else {
                        Toast.makeText(this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                    }
                });

        loginViewModel.getLoaderLiveData()
                .observe(this, showLoader -> progressBar.setVisibility(showLoader ? View.VISIBLE : View.GONE));

    }

    private void logar() {
        String email = emailEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();

        loginViewModel.autenticarUsuario(email, senha);
    }

    private void irParaMain() {

        SharedPreferences.Editor editor = getSharedPreferences(Constantes.SHARED_PREFERENCES, MODE_PRIVATE).edit();

        if(checkBox.isChecked()) {
            checkBox.setOnClickListener(view -> {
                editor.putString(Constantes.EMAIL, emailEditText.getText().toString());
                editor.putString(Constantes.PASSWORD, senhaEditText.getText().toString());
                editor.apply();
            });
        } else{
            editor.clear();
            editor.commit();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void irParaCadastro() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}