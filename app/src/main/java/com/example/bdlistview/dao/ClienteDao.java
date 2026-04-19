package com.example.bdlistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bdlistview.model.Cliente;
import com.example.bdlistview.util.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private ConnectionFactory conexao;
    private SQLiteDatabase banco;


        public ClienteDao(Context context) {
            try {
                conexao = new ConnectionFactory(context, "dbcliente.db", null, 1);
                // É aqui que o banco realmente tenta abrir ou criar as tabelas
                banco = conexao.getWritableDatabase();
            } catch (Exception e) {
                // Se o banco falhar, o Logcat vai te avisar exatamente o porquê
                Log.e("ERRO_BANCO", "Erro ao abrir banco: " + e.getMessage());
            }
        }

    public long Insert(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("id",cliente.getCodigo());
        values.put("nome",cliente.getNome());
        values.put("email",cliente.getEmail());
        long resultado = banco.insert("tbcliente",null,values);

        return resultado;
    }
    public void update(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("email", cliente.getEmail());
        String args[] = {cliente.getCodigo().toString()};
        banco.update("tbcliente",values,"id=?",args);
    }
    public void delete(Cliente cliente){
        String args[] = {cliente.getCodigo().toString()};
        banco.delete("tbcliente","id=?",args);
    }
    public  List<Cliente> obterTodos() {
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = banco.query("tbcliente", new String[]{"id", "nome", "email"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Cliente a = new Cliente();
            a.setCodigo(cursor.getInt(0));
            a.setNome((cursor.getString(1)));
            a.setEmail((cursor.getString(2)));
            clientes.add(a);
        }
        cursor.close();
        return clientes;
    }
}
