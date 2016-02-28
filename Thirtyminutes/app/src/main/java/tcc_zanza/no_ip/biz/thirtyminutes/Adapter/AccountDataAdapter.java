package tcc_zanza.no_ip.biz.thirtyminutes.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tcc_zanza.no_ip.biz.thirtyminutes.R;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Encryptor;

/**
 * Created by VINICIUS on 12/30/2015.
 */
public class AccountDataAdapter extends BaseAdapter {

    List<AccountData> accountDataList;
    Activity act;

    public AccountDataAdapter (List<AccountData> accountDataList, Activity act){

        this.accountDataList = accountDataList;

        this.act = act;

    }

    @Override
    public int getCount() {
        return accountDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return accountDataList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AccountData accountData = accountDataList.get(position);

        LayoutInflater inflater = act.getLayoutInflater();

        View line = inflater.inflate(R.layout.account_list_item, null);

        TextView item_site = (TextView)line.findViewById(R.id.item_site);

        TextView item_account = (TextView)line.findViewById(R.id.item_account);

        TextView item_password = (TextView)line.findViewById(R.id.item_password);

        item_site.setText(accountData.getSite());

        item_account.setText(accountData.getUserName());

        //item_password.setText(new String(accountData.getPassword()));
        item_password.setText(Encryptor.toDecrypt(accountData.getPassword()));


        return line;

    }
}
