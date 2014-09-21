package Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bluewall.trafficalarm.DataInterface;
import com.bluewall.trafficalarm.GoogleRouteAPI;
import com.bluewall.trafficalarm.MainActivity;
import com.bluewall.trafficalarm.model.Route;

import org.json.JSONArray;
import org.json.JSONObject;

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
               // Log.d("route string", GoogleRouteAPI.shortestDistance("26-32 Pirrama Road, Pyrmont, New South Wales", "17 Bridge Street, Sydney, New South Wales"));

               //String pLines =  GoogleRouteAPI.shortestDistance("26-32 Pirrama Road, Pyrmont, New South Wales", "17 Bridge Street, Sydney, New South Wales");

                //JSONObject jObject  = new JSONObject(pLines);

                //JSONArray routes = jObject.getJSONArray("routes");
                //JSONArray legs = routes.getJSONArray(0);
                //JSONArray steps = legs.getJSONArray(1);

                //JSONObject polyline = steps.getJSONObject(0);
                //String p = polyline.getString("points");


                rData.setRoute("vaumEi}xy[r@]dBu@@?l@Yz@e@^Od@SJGLGVMFITYNYFMFU");

                return DataInterface.getRoute(rData);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        protected void onPostExecute(Route result) {
            //Log.d("route string", ""+result.getMaxTravelTime());
           // SharedPrefsUtils.saveConfigFile(context, result);
        }
    }
}
