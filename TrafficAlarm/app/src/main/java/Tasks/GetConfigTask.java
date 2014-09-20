package Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bluewall.trafficalarm.DataInterface;
import com.bluewall.trafficalarm.MainActivity;
import com.bluewall.trafficalarm.SharedPrefsUtils;
import com.bluewall.trafficalarm.model.RealTimeConfig;

/**
 * Created by Barney on 20/09/2014.
 */
public class GetConfigTask {
    private Context context;
    protected MainActivity activity;


    public GetConfigTask(Context context) {
        this.context = context;

    }

    public void run() {
        new GetConfigTaskAsyncTask().execute((Void) null);
    }

    private class GetConfigTaskAsyncTask extends
            AsyncTask<Void, Integer, RealTimeConfig> {

        protected RealTimeConfig doInBackground(Void... urls) {

            try {
                return DataInterface.getConfig();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(RealTimeConfig result) {

            SharedPrefsUtils.saveConfigFile(context, result);
            }
        }
    }

