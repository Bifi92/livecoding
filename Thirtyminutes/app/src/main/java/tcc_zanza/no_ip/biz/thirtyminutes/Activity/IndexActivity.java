package tcc_zanza.no_ip.biz.thirtyminutes.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import tcc_zanza.no_ip.biz.thirtyminutes.AsyncTasks.AllAccountsAsync;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.PINDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.R;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.PINCode;

public class IndexActivity extends AppCompatActivity {

    EditText pinCode;
    ImageButton enter;
    ImageButton new_pin;
    ImageButton change_pin;
    static PINDAO pindao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        pindao = new PINDAO(IndexActivity.this);

        pindao.insert(new PINCode("pin"));

        pindao.close();

        new_pin = (ImageButton)findViewById(R.id.btn_new_pin);

        change_pin = (ImageButton)findViewById(R.id.btn_change_pin);

        pinCode = (EditText)findViewById(R.id.edit_pincode);

        enter = (ImageButton)findViewById(R.id.btn_enter);

        enter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(IndexActivity.this, R.string.text_enter, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pincode = IndexActivity.getAtualKey(IndexActivity.this);

                if (pincode != null && !pincode.isEmpty() && pincode.equals(pinCode.getText().toString())) {
                    Intent intent = new Intent(IndexActivity.this, AllAccountsActivity.class);
                    intent.putExtra("pincode", pinCode.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(IndexActivity.this, "Invalid PinCode", Toast.LENGTH_LONG).show();
                }
            }
        });

        new_pin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(IndexActivity.this, R.string.text_new_pin, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        new_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pindao = new PINDAO(IndexActivity.this);

                PINCode pin = new PINCode();

                AccountDAO ad = new AccountDAO(IndexActivity.this);

                pindao.deleteAll();
                pindao.close();

                ad.deleteAll();
                ad.close();

                pin.setCode(pinCode.getText().toString());

                pindao.insert(pin);
                pindao.close();

                Toast.makeText(IndexActivity.this, "Loading data.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(IndexActivity.this, AllAccountsActivity.class);
                intent.putExtra("pincode", pinCode.getText().toString());
                startActivity(intent);
                finish();

            }
        });

        change_pin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(IndexActivity.this, R.string.text_change_pin, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IndexActivity.this, ChangePinActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    public static String getAtualKey(Context ctx){
        pindao = new PINDAO(ctx);
        PINCode pin = pindao.getKey();
        pindao.close();

        if(!pin.equals(null) || !pin.getCode().equals(null) && !pin.getCode().isEmpty()){

            return pin.getCode();

        }else{
            return null;
        }
    }

}
