package Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bluewall.trafficalarm.DataInterface;

/**
 * Created by Barney on 20/09/2014.
 */
public class GetConfigTask {
    private Context context;


    public GetConfigTask(Context context) {
        this.context = context;

    }

    public void run() {
        new GetConfigTaskAsyncTask().execute((Void) null);
    }

    private class GetConfigTaskAsyncTask extends
            AsyncTask<Void, Integer, Integer> {

        protected Integer doInBackground(Void... urls) {

            try {
                return DataInterface.getConfig();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return 0;
        }

        protected void onPostExecute(int result) {

            }
        }
    }

