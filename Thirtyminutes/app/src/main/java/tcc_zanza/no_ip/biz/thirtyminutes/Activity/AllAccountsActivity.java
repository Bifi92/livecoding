package tcc_zanza.no_ip.biz.thirtyminutes.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import tcc_zanza.no_ip.biz.thirtyminutes.AsyncTasks.AllAccountsAsync;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.Adapter.AccountDataAdapter;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Encryptor;
import tcc_zanza.no_ip.biz.thirtyminutes.R;

public class AllAccountsActivity extends AppCompatActivity {

    ListView account_list;
    String key;
    AllAccountsAsync allAccountsAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_accounts);

        key = (String)getIntent().getSerializableExtra("pincode");

        Encryptor.setGlobalKey(key);

        account_list = (ListView)findViewById(R.id.account_list);

        allAccountsAsync = new AllAccountsAsync(account_list, this);

        allAccountsAsync.execute();
        //loadList();

        account_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AccountDAO dao = new AccountDAO(AllAccountsActivity.this);

                AccountData accountData = (AccountData) parent.getItemAtPosition(position);

                dao.delete(accountData);

                dao.close();

                //allAccountsAsync.execute();
                loadList();

                return false;
            }
        });

    }

    private void loadList(){

        AccountDAO dao = new AccountDAO(AllAccountsActivity.this);

        List<AccountData> accountDatas = dao.getList();
        dao.close();
        AccountDataAdapter adapter = new AccountDataAdapter(accountDatas, this);

        account_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_all_accounts, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_create_account:

                Intent intent = new Intent(this, MainActivity.class);
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
