package com.ebookfrenzy.mapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ebookfrenzy.mapdemo.databinding.ActivityMainBinding;
import com.ebookfrenzy.mapdemo.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    // TODO when device rotation noticed, if now in landscape, change constraints to match, but if in portrait change back

    // TODO place markers on map using SQL database

    // TODO convert to using ViewModel to hold data instead of within MapsFragment

    private ActivityMainBinding binding;

    /**
     * obtain a reference to the fragment_text instance and call the changeText() method on the object
     * @param marker
     */
    public boolean onMarkerClick(Marker marker) {

        MapsFragment mMap = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        PointOfInterest poi = mMap.getPointOfInterest(2);
        String newTitleText = poi.getPlaceTitle();
        String newDescriptionText = poi.getPlaceDescription();


        Drawable newImage = poi.getPlacePhoto();

        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text);
        textFragment.changeText(newTitleText, newDescriptionText, newImage);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

// TODO fix this spaghetti code - can't access the res/drawable folder from within the map fragment so have added it here temporarily
        Drawable myImage = ResourcesCompat.getDrawable(getResources(), R.drawable.fishermanscottage, null);
        MapsFragment mMap = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        PointOfInterest poi = mMap.getPointOfInterest(0);
        mMap.setPointOfInterestPhoto(poi, myImage);
         poi = mMap.getPointOfInterest(1);
        mMap.setPointOfInterestPhoto(poi, myImage);
         poi = mMap.getPointOfInterest(2);
         myImage = ResourcesCompat.getDrawable(getResources(), R.drawable.rnli, null);
        mMap.setPointOfInterestPhoto(poi, myImage);

    }
}