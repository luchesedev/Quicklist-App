package com.example.bdlistview;

import static android.view.View.GONE;
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

import com.example.bdlistview.dao.ClienteDao;
import com.example.bdlistview.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvDados;
    private TextView txtNaohaitem,txtLista;
    private ImageView imgInsiraItem;
    private Intent it;

    private ClienteDao dao;
    private List<Cliente> clientes;
    private ArrayAdapter<Cliente> adaptador;
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
        dao = new ClienteDao(this);
        atualizarLista();
        clientes = dao.obterTodos();

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientes);

        lsvDados.setAdapter(adaptador);



        lsvDados.setOnItemClickListener((parent, view, position, id) -> {
            // Pega o objeto que foi clicado
            Cliente clienteClicado = (Cliente) parent.getItemAtPosition(position);
            // Exemplo: Mostrar o nome do cliente ou abrir uma nova tela
            it =  new Intent(getApplicationContext(), Manutencao.class);

            it.putExtra("id", clienteClicado.getCodigo());
            it.putExtra("nome", clienteClicado.getNome());
            it.putExtra("email", clienteClicado.getEmail());

           startActivity(it);
        });
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
            //  "onClick" do botão do menu
            it = new Intent(getApplicationContext(), AdicionarCliente.class );
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//on resume "assiste"  oq voce faz ai toda vez q volta para a tela ele roda esse metodo
    @Override
    protected void onResume() {
        super.onResume();
        // Toda vez que você voltar para esta tela, ele busca os dados novos
        atualizarLista();

    }

    private void atualizarLista() {
        clientes = dao.obterTodos();

        // Criamos o adaptador e sobrescrevemos o método getView para mudar a cor
        adaptador = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes) {
            @Override
            public android.view.View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                android.view.View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);

                // Seta a cor do texto para PRETO
                text.setTextColor(android.graphics.Color.BLACK);

                return view;
            }
        };

        lsvDados.setAdapter(adaptador);

        if (clientes.isEmpty()) {
            txtNaohaitem.setVisibility(VISIBLE);
            imgInsiraItem.setVisibility(VISIBLE);
            // Opcional: garantir que o texto esteja setado
            txtNaohaitem.setText("Não há itens nessa lista. \n   Insira itens na lista!");
        } else {
            // Se a lista NÃO estiver vazia, precisamos esconder os avisos
            txtNaohaitem.setVisibility(GONE);
            imgInsiraItem.setVisibility(GONE);
        }
    }
}



