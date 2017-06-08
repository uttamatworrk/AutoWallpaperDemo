package com.betterdeveloper.autowallpaperdemo;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.os.Build;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button wallpaperSetButton = (Button) findViewById(R.id.change_wallpaper_button);
        wallpaperSetButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getWlppr();
                    }
                }
        );

    }

    public void getWlppr(){
        WallpaperManager wm = WallpaperManager.getInstance(this);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageDrawable(wm.getDrawable());
    }
    public void changeWallpaper() {
        boolean supported = true;
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!wallpaperManager.isWallpaperSupported() || !wallpaperManager.isSetWallpaperAllowed()) {
                supported = false;
            }
        }
        if (supported) {
            try {
                wallpaperManager.setResource(R.raw.ursexy);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean changeWallpaper(InputStream wallpaperStream) {
        boolean supported = true;
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (!wallpaperManager.isWallpaperSupported() || !wallpaperManager.isSetWallpaperAllowed()) {
                supported = false;
            }
        }
        if (supported) {
            try {
                wallpaperManager.setStream(wallpaperStream);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
