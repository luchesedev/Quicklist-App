package com.example.bdlistview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bdlistview.dao.ClienteDao;
import com.example.bdlistview.model.Cliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Manutencao extends AppCompatActivity {
    Intent it;
    TextInputLayout hintId,hintNome,hintEmail;
    private Button btnAlterar,btnDeletar,btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manutencao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextInputEditText edtCodigo = findViewById(R.id.editCodigo1);
        TextInputEditText edtNome = findViewById(R.id.editNome1);
        TextInputEditText edtEmail = findViewById(R.id.editEmail1);


        btnAlterar = findViewById(R.id.btnAlterar);
        btnDeletar = findViewById(R.id.btnExcluir);
        btnVoltar = findViewById(R.id.btnvoltar);
        hintId = findViewById(R.id.hintCodigo);
        hintNome = findViewById(R.id.hintNome);
        hintEmail = findViewById(R.id.hintEmail);
        edtCodigo.setEnabled(false);

        Intent intentRecebida = getIntent();

        //hints
        Integer id = intentRecebida.getIntExtra("id", 0);
        String nome = intentRecebida.getStringExtra("nome");
        String email = intentRecebida.getStringExtra("email");

        hintId.setHint("ID selecionado: "+id);
        hintEmail.setHint("Email: "+email);
        hintNome.setHint("Nome: "+nome);
        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(edtNome.getText().toString().isEmpty() || edtEmail.getText().toString().isEmpty()){
                        Snackbar.make(findViewById(android.R.id.content), "Os dados não podem ser vazios", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.parseColor("#1A566B"))
                                .setTextColor(Color.WHITE)
                                .show();
                    }
                    else{
                        Cliente cliente1 = new Cliente(id,edtNome.getText().toString(),edtEmail.getText().toString());
                        ClienteDao dao = new ClienteDao(Manutencao.this);
                        dao.update(cliente1);

                        Snackbar.make(findViewById(android.R.id.content), "Dados atualizados!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.parseColor("#1A566B"))
                                .setTextColor(Color.WHITE)
                                .show();

                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish(); // Volta para a lista atualizada
                            }
                        }, 1500);
                    }


            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logica


                Cliente cliente = new Cliente(id,nome,email);
                ClienteDao dao = new ClienteDao(Manutencao.this);
                dao.delete(cliente);

                Snackbar.make(findViewById(android.R.id.content), "Dados Apagados!", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(Color.parseColor("#1A566B"))
                        .setTextColor(Color.WHITE)
                        .show();

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish(); // Volta para a lista atualizada
                    }
                }, 1500);

                finish();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}