package com.example.yan.bss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yan.bss.exceptions.ContaInvalidaException;
import com.example.yan.bss.exceptions.SaldoInsuficienteException;
import com.example.yan.bss.model.Conta;
import com.example.yan.bss.model.Repositorio;

public class TransferenciaActivity extends AppCompatActivity {


    private TextView mensagem;
    private EditText valor;
    private EditText contaDestino;
    private Button transferir;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        mensagem = (TextView) findViewById(R.id.mensagemTransferencia);
        valor = (EditText) findViewById(R.id.valorTransferencia);
        contaDestino = (EditText) findViewById(R.id.contaTransferencia);
        transferir = (Button) findViewById(R.id.transferirButton);
        cancelar = (Button) findViewById(R.id.cancelarTransferencia);

        final Repositorio rep = new Repositorio();
        Bundle extra = getIntent().getExtras();
        final Conta contaOrigem;

        if(extra != null) {
            int numConta = extra.getInt("ContaOrigem");
            contaOrigem = rep.checaConta(numConta);
            if(contaOrigem != null) {
                System.out.println("pegou transferencia valor "+transferir);
                transferir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String destinoString = contaDestino.getText().toString();
                        String valorString = valor.getText().toString();
                        if(!TextUtils.isEmpty(destinoString)) {
                            if(!TextUtils.isEmpty(valorString)) {
                                int numContaDestino = Integer.parseInt(destinoString);
                                Intent intent = new Intent(TransferenciaActivity.this, RelatorioActivity.class);
                                try {
                                    Double valorFinal = Double.parseDouble(valorString);
                                    rep.tranferir(contaOrigem, numContaDestino, valorFinal);
                                    intent.putExtra("Relatorio", "Transferência realizado com sucesso\nValor transferido de " + valorFinal + " para a Conta numero " + numContaDestino);
                                } catch (SaldoInsuficienteException e) {
                                    intent.putExtra("Relatorio", "Transferência não realizada\nSaldo insuficiente ");
                                    e.printStackTrace();
                                } catch (ContaInvalidaException e) {
                                    intent.putExtra("Relatorio", "Transferência não realizada\nConta destino inválida insuficiente ");
                                    e.printStackTrace();
                                }
                                contaDestino.setText("");
                                valor.setText("");
                                startActivity(intent);
                            } else {
                                valor.setError("Digite valor para transferência");
                            }
                        } else {
                            contaDestino.setError("Digite conta para transferência");
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
                Intent intent = new Intent(TransferenciaActivity.this, RelatorioActivity.class);
                intent.putExtra("Relatorio", "Conta inválida");
                startActivity(intent);
            }

        } else finish();
    }
}
