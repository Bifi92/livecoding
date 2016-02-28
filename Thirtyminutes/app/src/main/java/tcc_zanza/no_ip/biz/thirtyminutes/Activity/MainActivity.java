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
import android.widget.TextView;
import android.widget.Toast;

import tcc_zanza.no_ip.biz.thirtyminutes.AsyncTasks.MainAsync;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Encryptor;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Generator;
import tcc_zanza.no_ip.biz.thirtyminutes.R;

public class MainActivity extends AppCompatActivity {

    ImageButton generate;
    ImageButton btn_save;
    ImageButton all_accounts_btn;
    TextView password;
    EditText site;
    EditText account;
    EditText pass;
    AccountData accountData;
    AccountDAO accountDAO;
    String key;
    MainAsync mainAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key = (String)getIntent().getSerializableExtra("pincode");

        btn_save = (ImageButton)findViewById(R.id.save);

        site = (EditText)findViewById(R.id.edit_site);

        account = (EditText)findViewById(R.id.edit_account);

        generate = (ImageButton)findViewById(R.id.generate);

        all_accounts_btn = (ImageButton)findViewById(R.id.all_accounts_btn);

        password = (TextView)findViewById(R.id.password);

        pass = (EditText)findViewById(R.id.edit_pass);

        accountDAO = new AccountDAO(this);

        generate.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, R.string.generate, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText(Generator.generatePassword());
            }
        });

        btn_save.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, R.string.save, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pass.getText().toString().equals(null) || pass.getText().toString().isEmpty()) {
                    /*mainAsync = new MainAsync(MainActivity.this
                                           , password.getText().toString()
                                           , site.getText().toString()
                                           , account.getText().toString()
                                           , 1);
                    mainAsync.execute();*/
                    accountData = new AccountData(account.getText().toString(),
                            Encryptor.toEncrypt(password.getText().toString()),
                            site.getText().toString());

                } else {
                    /*mainAsync = new MainAsync(MainActivity.this
                            , pass.getText().toString()
                            , site.getText().toString()
                            , account.getText().toString()
                            , 1);
                    mainAsync.execute();*/
                    accountData = new AccountData(account.getText().toString(),
                            Encryptor.toEncrypt(pass.getText().toString()),
                            site.getText().toString());

                }

                accountDAO.insert(accountData);

                accountDAO.close();

                pass.setText(null);
                password.setText("Password");
                account.setText(null);
                site.setText(null);

                Toast.makeText(MainActivity.this, "Account saved.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, AllAccountsActivity.class);

                intent.putExtra("pincode", key);
                startActivity(intent);
                finish();

            }
        });

        all_accounts_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, R.string.all_accounts, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        all_accounts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pass.setText(null);
                password.setText("Password");
                account.setText(null);
                site.setText(null);

                Intent intent = new Intent(MainActivity.this, AllAccountsActivity.class);

                intent.putExtra("pincode", key);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.item_config:
                Intent intent = new Intent(this, ConfigurationActivity.class);

                intent.putExtra("pincode", key);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
