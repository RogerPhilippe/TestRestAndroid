package br.com.philippesis.testrest.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import br.com.philippesis.testrest.security.Security;
import br.com.philippesis.testrest.utils.AsyncResponse;

public class AuthTask extends AsyncTask<String, Void, String> {

    private Security security = null;

    private Context mContext;

    public AsyncResponse delegate = null;


    public AuthTask(Context context) {
        this.mContext = context;
    }


    @Override
    protected String doInBackground(String... params) {

        security = new Security();

        return security.authToken(params[0], params[1]);
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
        Log.i("Task", "Pos Execute");
    }

    @Override
    protected void finalize() throws Throwable {
        Log.i("Task", "Finaliza");
        super.finalize();
    }
}
