package com.example.yan.bss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RelatorioActivity extends AppCompatActivity {

        private TextView extratoMessage;
        private Button voltarButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_relatorio);

            extratoMessage = (TextView) findViewById(R.id.extratoMessage);
            voltarButton = (Button) findViewById(R.id.voltarExtratoButton);
            Bundle extra = getIntent().getExtras();

            if(extra != null) {
                extratoMessage.setText(extra.getString("Relatorio"));

                voltarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            } else finish();
        }
    }
