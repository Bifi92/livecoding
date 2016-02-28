package tcc_zanza.no_ip.biz.thirtyminutes.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import tcc_zanza.no_ip.biz.thirtyminutes.DAO.AccountDAO;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.AccountData;
import tcc_zanza.no_ip.biz.thirtyminutes.VO.Encryptor;

/**
 * Created by VINICIUS on 2/28/2016.
 */
public class MainAsync extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progressDialog;
    private Activity activity;
    private int action;
    private String password;
    private String site;
    private String account;
    private AccountDAO accountDAO;

    public MainAsync(Activity activity, String password, String site, String account, int action){
        this.activity = activity;
        this.action = action;
        this.password = password;
        this.site = site;
        this.account = account;
    }

    @Override
    protected void onPreExecute() {
        switch (action){
            case 1:
                progressDialog = ProgressDialog.show(activity, "Wait...", "Saving your account!");
                break;

            default:
                break;
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("test", "test Main.doInBackground");
        switch (action){
            case 1:

                AccountData accountData = new AccountData(account,
                            Encryptor.toEncrypt(password),
                            site);

                accountDAO = new AccountDAO(activity);

                accountDAO.insert(accountData);

                accountDAO.close();
                break;

            default:
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("test", "test Main.onPostExecute");
        progressDialog.dismiss();
    }
}
