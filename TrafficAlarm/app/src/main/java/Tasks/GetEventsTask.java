package Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bluewall.trafficalarm.DataInterface;

/**
 * Created by Barney on 20/09/2014.
 */
public class GetEventsTask {
    private Context context;


    public GetEventsTask(Context context) {
        this.context = context;

    }

    public void run() {
        new GetEventsTaskAsyncTask().execute((Void) null);
    }

    private class GetEventsTaskAsyncTask extends
            AsyncTask<Void, Integer, Integer> {

        protected Integer doInBackground(Void... urls) {

            try {
                return DataInterface.getEvents();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return 0;
        }

        protected void onPostExecute(int result) {

        }
    }
}
