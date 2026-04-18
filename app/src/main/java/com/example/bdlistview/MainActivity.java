package com.example.bdlistview;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvDados;
    private TextView txtNaohaitem;
    private ImageView imgInsiraItem;
    private Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNaohaitem = findViewById(R.id.txtnaohaitem);
        lsvDados = findViewById(R.id.lsvDados);
        imgInsiraItem = findViewById(R.id.ImgInsiraItem);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String naohaitem ="Não há itens nessa lista. \n" +
                "     Insira itens a lista!";

        txtNaohaitem.setVisibility(GONE);
        imgInsiraItem.setVisibility(GONE);
        // fonte de dados
        List<Cliente> clientes = new ArrayList<Cliente>();


        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(
                this, android.R.layout.simple_list_item_1, clientes);
        lsvDados.setAdapter(adaptador);
        if(clientes.isEmpty()){
            txtNaohaitem.setText(naohaitem);
            txtNaohaitem.setVisibility(VISIBLE);
            imgInsiraItem.setVisibility(VISIBLE);
        }
    }
    //inicializa o menu e puxa ele
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //quando e so um botao faz assim ou um switch case com um default
        if (item.getItemId() == R.id.action_add) {
            // 👉 AQUI é o "onClick" do botão do menu
            it = new Intent(getApplicationContext(), AdicionarCliente.class );
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
