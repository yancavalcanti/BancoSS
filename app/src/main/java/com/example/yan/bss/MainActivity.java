package com.example.yan.bss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.bss.exceptions.ContaInvalidaException;
import com.example.yan.bss.exceptions.SaldoInsuficienteException;
import com.example.yan.bss.model.Cliente;
import com.example.yan.bss.model.Conta;
import com.example.yan.bss.model.Repositorio;

public class MainActivity extends AppCompatActivity {


    private EditText cpfCampo;
    private EditText contaCampo;
    private Button acessar;
    private Repositorio rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rep = new Repositorio();

        cpfCampo = (EditText) findViewById(R.id.cpfText);
        contaCampo = (EditText) findViewById(R.id.contaText);
        acessar = (Button) findViewById(R.id.acessarContaButton);

        Cliente cliente01 = new Cliente("01", "Fulano", "fulano@fulano");
        Cliente cliente10 = new Cliente("10", "Cicrano", "cicrano@c");
        Conta conta01 = new Conta(cliente01, 1, 1000);
        Conta conta10 = new Conta(cliente10, 2, 5);

        rep.adicionaCliente(cliente01);
        rep.adicionaCliente(cliente10);
        rep.adicionarConta(conta01);
        rep.adicionarConta(conta10);
        try {
            rep.sacar(conta01, 100);
            rep.sacar(conta01, 150);
            rep.sacar(conta01, 200);
            rep.sacar(conta01, 150);
            rep.sacar(conta01, 100);
            rep.tranferir(conta01, conta10.getNumConta(), 200);
        } catch (SaldoInsuficienteException e) {
            e.printStackTrace();
        } catch (ContaInvalidaException e) {
            e.printStackTrace();
        }


        acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cpf = cpfCampo.getText().toString();
                System.out.println("passou");
                String contaString = contaCampo.getText().toString();
                if(!TextUtils.isEmpty(cpf)) {
                    if (!TextUtils.isEmpty(contaString)) {
                        int conta = Integer.parseInt(contaString);
                        Cliente clienteLog = rep.checaCliente(cpf);
                        Conta contaLog = rep.checaConta(conta);

                        if (clienteLog != null && contaLog != null && contaLog.getCliente().equals(clienteLog)) {
                            cpfCampo.setText("");
                            contaCampo.setText("");
                            Intent intent = new Intent(MainActivity.this, ContaActivity.class);
                            intent.putExtra("Conta", conta);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                            intent.putExtra("Relatorio", "CPF ou conta inválidos");
                            startActivity(intent);
                        }
                    } else {
                        contaCampo.setError("Digite número da conta");
                    }
                } else {
                    cpfCampo.setError("Digite CPF");
                }
            }
        });

    }
}
