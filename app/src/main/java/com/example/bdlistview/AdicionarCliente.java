package com.example.bdlistview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bdlistview.dao.ClienteDao;
import com.example.bdlistview.model.Cliente;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbarAdicionarCliente);
        setSupportActionBar(toolbar);
        TextInputEditText edtCodigo = findViewById(R.id.editCodigo);
        TextInputEditText edtNome = findViewById(R.id.editNome);
        TextInputEditText edtEmail = findViewById(R.id.editEmail);
        Button btnSalvar = findViewById(R.id.btnsalvar);
        Button btnVoltar = findViewById(R.id.btnvoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod = edtCodigo.getText().toString();
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                if(edtNome.getText().toString().isEmpty() ||
                        edtCodigo.getText().toString().isEmpty() ||
                        edtEmail.getText().toString().isEmpty() ){
                    Snackbar.make(findViewById(android.R.id.content), "Preencha todos os Campos", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(Color.parseColor("#1A566B"))
                            .setTextColor(Color.WHITE)
                            .show();

                } else{
                    Cliente cliente = new Cliente(Integer.parseInt(cod),nome,email);
                    ClienteDao clienteDao = new ClienteDao(AdicionarCliente.this);
                    long idGerado = clienteDao.Insert(cliente);

                    if (idGerado != -1) {
                        // Deu certo! Mostra a mensagem de sucesso
                        Snackbar.make(v, "Usuário salvo com sucesso!", Snackbar.LENGTH_LONG).show();
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish(); // 3. Fecha a tela atual e volta para a anterior
                            }
                        }, 2000);
                    } else {
                        // Algo deu errado no SQLite
                        Snackbar.make(v, "Erro ao salvar no banco!", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_add);

        // 2. Defina a visibilidade como false
        if (item != null) {
            item.setVisible(false);
        }

        return true;
    }

}
