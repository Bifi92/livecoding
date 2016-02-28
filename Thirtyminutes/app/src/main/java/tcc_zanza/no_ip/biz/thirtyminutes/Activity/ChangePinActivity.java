package tcc_zanza.no_ip.biz.thirtyminutes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import se.simbio.encryption.Encryption;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.PINDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.R;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Encryptor;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.PINCode;

public class ChangePinActivity extends AppCompatActivity {

    String pinCode;

    EditText new_pin;

    EditText current_pin;

    EditText current_confirmation_pin;

    ImageButton btn_save_pin;
    ImageButton btn_cancel;
    static AccountDAO adao;
    static PINDAO dao;

    PINCode pin;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_change_pin);

            pinCode = IndexActivity.getAtualKey(this);

            new_pin = (EditText)findViewById(R.id.edit_new);

            current_pin = (EditText)findViewById(R.id.edit_current);

            current_confirmation_pin = (EditText)findViewById(R.id.edit_current_confirmation);

            btn_save_pin = (ImageButton)findViewById(R.id.btn_save_pin);

            btn_cancel = (ImageButton)findViewById(R.id.btn_cancel);

            dao = new PINDAO(ChangePinActivity.this);

            adao = new AccountDAO(ChangePinActivity.this);

            btn_cancel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(ChangePinActivity.this, R.string.text_cancel, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ChangePinActivity.this, IndexActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            btn_save_pin.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(ChangePinActivity.this, R.string.text_save_config, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            btn_save_pin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(confirmPin()){

                        pin = dao.getKey();
                        dao.close();
                        List<AccountData> accountDatas = adao.getList();
                        adao.close();
                        for(AccountData accountData : accountDatas) {
                            accountData.setPassword(Encryptor.toDecrypt(accountData.getPassword()));
                        }

                        pin.setCode(new_pin.getText().toString());

                        dao.update(pin);
                        dao.close();
                        Encryptor.setGlobalKey(pin.getCode());

                        for(AccountData accountData : accountDatas) {

                            accountData.setPassword(Encryptor.toEncrypt(accountData.getPassword()));

                            adao.update(accountData);
                            adao.close();
                        }

                        Intent intent = new Intent(ChangePinActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }
            });

        }

    public boolean confirmPin(){

        if(current_confirmation_pin.getText().toString().equals(current_pin.getText().toString())
                && current_pin.getText().toString().equals(pinCode)){
            return true;
        }

        if(current_confirmation_pin.getText().toString().equals(null)
                || current_confirmation_pin.getText().toString().isEmpty()){

            Toast.makeText(this, "Confirm your current Pin Code!", Toast.LENGTH_LONG).show();
            return false;

        }

        if(current_pin.getText().toString().equals(null)
                || current_pin.getText().toString().isEmpty()){

            Toast.makeText(this, "Your current Pin Code is empty!", Toast.LENGTH_LONG).show();
            return false;

        }

        if(!current_confirmation_pin.getText().toString().equals(current_pin.getText().toString())){
            Toast.makeText(this, "The confirmation does not match!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!current_pin.getText().toString().equals(pinCode)){
            Toast.makeText(this, "Invalid Pin Code!", Toast.LENGTH_LONG).show();
            return false;
        }

        return false;

    }

}
