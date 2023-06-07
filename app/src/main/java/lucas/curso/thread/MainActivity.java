package lucas.curso.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btInico, btFim;
    TextView txtResult, txtResult2, txtN1, txtN2;
    boolean flag;
    EditText en1, en2;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInico = findViewById(R.id.btIniciar);
        btFim = findViewById(R.id.btParar);
        txtResult = findViewById(R.id.txtResult);
        txtResult2 = findViewById(R.id.txtResult2);
        txtN1 = findViewById(R.id.txtResultR);
        txtN2 = findViewById(R.id.txtResultR2);
        en1 = findViewById(R.id.eN1);
        en2 = findViewById(R.id.eN2);

        handler = new Handler();

        btInico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                new Thread(){
                    @Override
                    public void run() {
                        double n1 = 1;
                        double n2 = 1;
                        while (flag){
                            String numero1 = en1.getText().toString();
                            String numero2 = en2.getText().toString();
                            if(!numero1.isEmpty() && !numero2.isEmpty()){
                                double n = Double.parseDouble(numero1);
                                double nn = Double.parseDouble(numero2);
                                 n1 = n1 * n;
                                 n2 = n2 * nn;
                                 final double resultado = n1;
                                 final double resultado1 = n2;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtN1.setText(String.valueOf(resultado));
                                        txtN2.setText(String.valueOf(resultado1));
                                    }
                                });
                                n1 = n1 * 2;
                                n2 = n2 * 3;
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Insira um número", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }.start();
            }
        });

        btFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
            }
        });
    }
}