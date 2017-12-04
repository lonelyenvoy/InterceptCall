package ink.envoy.interceptcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String outCallNumber = getResultData();
        if (outCallNumber != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
            String number = sharedPreferences.getString("number", "");
            if (outCallNumber.equals(number)) {
                setResultData(null);
            }
        }
    }
}
