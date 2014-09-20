package Tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bluewall.trafficalarm.DataInterface;
import com.bluewall.trafficalarm.MainActivity;
import com.bluewall.trafficalarm.model.Route;

/**
 * Created by Barney on 21/09/2014.
 */
public class GetRouteDataTask {

    private Context context;
    private Route rData;
    protected MainActivity activity;


    public GetRouteDataTask(Context context, Route routeData) {
        this.context = context;
        rData = routeData;

    }

    public void run() {
        new GetRouteDataAsyncTask().execute((Void) null);
    }

    private class GetRouteDataAsyncTask extends
            AsyncTask<Void, Integer, Route> {

        protected Route doInBackground(Void... urls) {

            try {
                return DataInterface.getRoute(rData);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(Route result) {

           // SharedPrefsUtils.saveConfigFile(context, result);
        }
    }
}
