package com.example.yan.bss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yan.bss.model.Conta;
import com.example.yan.bss.model.Repositorio;

public class ContaActivity extends AppCompatActivity {

        private TextView contaName;
        private ImageButton extrato;
        private ImageButton saldo;
        private ImageButton saque;
        private ImageButton transferencia;
        private Button sair;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_conta);

            contaName = (TextView) findViewById(R.id.nomeCliente);
            Repositorio rep = new Repositorio();
            Bundle extra = getIntent().getExtras();
            final Conta contaLog;
            final int numConta;

            if (extra != null) {
                numConta = extra.getInt("Conta");
                contaLog = rep.checaConta(numConta);
                if (contaLog != null) {
                    contaName.setText(contaLog.getCliente().getNome());
                }

                extrato = (ImageButton) findViewById(R.id.extratoButton);
                saldo = (ImageButton) findViewById(R.id.saldoButton);
                saque = (ImageButton) findViewById(R.id.saqueButton);
                transferencia = (ImageButton) findViewById(R.id.transferenciaButton);
                sair = (Button) findViewById(R.id.sairButton);

                extrato.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContaActivity.this, RelatorioActivity.class);
                        intent.putExtra("Relatorio", contaLog.checaExtrato(5));
                        startActivity(intent);
                    }
                });

                saldo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContaActivity.this, RelatorioActivity.class);
                        intent.putExtra("Relatorio", "Seu saldo é de " + contaLog.getValor());
                        startActivity(intent);
                    }
                });

                saque.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContaActivity.this, SaqueActivity.class);
                        intent.putExtra("Conta", numConta);
                        startActivity(intent);
                    }
                });

                transferencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContaActivity.this, TransferenciaActivity.class);
                        intent.putExtra("ContaOrigem", numConta);
                        startActivity(intent);
                    }
                });

                sair.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ContaActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });

            } else finish();
        }
    }
