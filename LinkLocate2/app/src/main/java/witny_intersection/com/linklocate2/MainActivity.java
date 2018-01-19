package witny_intersection.com.linklocate2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.gimbal.android.Gimbal;
import com.gimbal.android.Communication;
import com.gimbal.android.CommunicationListener;
import com.gimbal.android.CommunicationManager;

public class MainActivity extends AppCompatActivity {
    private static final String GIMBAL_APP_API_KEY = "d9f80fb2-70c1-4947-bcd1-0f81ae9ccffc";
    private CommunicationListener communicationListener;
   // public static final int LOCATION_PERMISSION_REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gimbal.setApiKey(this, GIMBAL_APP_API_KEY);

        communicationListener = new CommunicationListener() {
            // override methods as required
        };
        CommunicationManager.getInstance().addListener(communicationListener);
    }
}

/*
public class PermissionActivity extends AppCompatActivity {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    ...
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Gimbal.start();
            }
        }
    }
}
*/