package tcc_zanza.no_ip.biz.thirtyminutes.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import tcc_zanza.no_ip.biz.thirtyminutes.Adapter.AccountDataAdapter;
import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;

/**
 * Created by VINICIUS on 2/28/2016.
 */
public class AllAccountsAsync extends AsyncTask<Void, Void , List<AccountData>>{

    private ListView account_list;
    private Activity activity;
    private ProgressDialog progressDialog;

    public AllAccountsAsync(ListView account_list, Activity activity){
        this.account_list = account_list;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "Wait...", "Loading data!");
    }

    @Override
    protected List<AccountData> doInBackground(Void... params) {
        AccountDAO dao = new AccountDAO(activity);

        List<AccountData> accountDatas = dao.getList();
        dao.close();

        return accountDatas;
    }

    @Override
    protected void onPostExecute(List<AccountData> accountDatas) {
        AccountDataAdapter adapter = new AccountDataAdapter(accountDatas, activity);

        account_list.setAdapter(adapter);

        progressDialog.dismiss();
    }


}
