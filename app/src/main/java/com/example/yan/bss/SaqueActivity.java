package com.example.yan.bss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yan.bss.exceptions.SaldoInsuficienteException;
import com.example.yan.bss.model.Conta;
import com.example.yan.bss.model.Repositorio;

public class SaqueActivity extends AppCompatActivity {

    private TextView mensagem;
    private EditText valor;
    private Button sacar;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saque);

        mensagem = (TextView) findViewById(R.id.mensagemSaque);
        valor = (EditText) findViewById(R.id.valorSaque);
        sacar = (Button) findViewById(R.id.sacarButton);
        cancelar = (Button) findViewById(R.id.cancelarSaque);

        final Repositorio rep = new Repositorio();
        Bundle extra = getIntent().getExtras();
        final Conta conta;

        if(extra != null) {
            int numConta = extra.getInt("Conta");
            conta = rep.checaConta(numConta);
            if(conta != null) {
                System.out.println("pegou saque valor");
                sacar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String saqueString = valor.getText().toString();
                        if(!TextUtils.isEmpty(saqueString)) {
                            Intent intent = new Intent(SaqueActivity.this, RelatorioActivity.class);
                            try {
                                Double saque = Double.parseDouble(saqueString);
                                rep.sacar(conta, saque);
                                intent.putExtra("Relatorio", "Saque realizado com sucesso\nValor sacado de " + saque);
                            } catch (SaldoInsuficienteException e) {
                                intent.putExtra("Relatorio", "Saque não realizado\nSaldo insuficiente ");
                                e.printStackTrace();
                            }
                            valor.setText("");
                            startActivity(intent);
                        } else {
                            valor.setError("Digite valor para saque");
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else {
                Intent intent = new Intent(SaqueActivity.this, RelatorioActivity.class);
                intent.putExtra("Relatorio", "Conta inválida");
                startActivity(intent);
            }
        } else finish();
    }
}
