package example.beyondar.com.postexample.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;

import com.facebook.stetho.Stetho;
import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.database.AppDatabase;

public class App extends Application {

    private static Context context;
    private static final String DATABASE_NAME = "posts_db";

    private AppDatabase database;

    public static App instance = null;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext() {
        return App.context;
    }

    public AppDatabase getDatabseInstance() {
        if (database == null) {
            database = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public KProgressHUD progressHUD;

    public static void showProgressHood(Context context) {
        try {
            if (App.getInstance().progressHUD != null && App.getInstance().progressHUD.isShowing()) {
                App.getInstance().progressHUD.dismiss();
            }

            LayoutInflater inflater = LayoutInflater.from(context);
            ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.loader_view, null, false);

            App.getInstance().progressHUD = KProgressHUD.create(context)
                    .setCustomView(layout)
                    .setDimAmount(0.6f)
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setCancellable(false)
                    .show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void hideProgressHood() {
        try {
            App.getInstance().progressHUD.dismiss();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
