package com.lab02.pongoelhombro.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lab02.pongoelhombro.Dialog_local;
import com.lab02.pongoelhombro.Model.Local;
import com.lab02.pongoelhombro.Model.Noticia;
import com.lab02.pongoelhombro.R;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "LocationFragment";
    private LocationManager mLocationManager;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FusedLocationProviderClient client;
    double latitud;
    double longitud;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    private ArrayList<Local> locales;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_map, container, false);

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ContextCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
        else
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }
        locales =new ArrayList<Local>();
        db.collection("Locales")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                //Log.d("TOG", document.getId() + " => " + document.getData());
                                if(document.get("idCentroVacunacion")!=null&&
                                        document.get("idUbigeo")!=null&&
                                        document.get("latitud")!=null&&
                                        document.get("longitud")!=null)
                                {
                                    //Log.d("TOG", document.getId() + " => " + document.getData());
                                    int radio=1;
                                    double latmp=Double.parseDouble(document.get("latitud")+"");
                                    double lotmp=Double.parseDouble(document.get("longitud")+"");
                                    if((latmp>latitud-radio&&latmp<latitud+radio)&&(lotmp>longitud-radio&&lotmp<longitud+radio))
                                    {
                                        Local local =new Local(Integer.parseInt(document.get("idCentroVacunacion")+""),
                                                Integer.parseInt( document.get("idUbigeo")+""),
                                                Double.parseDouble(document.get("latitud")+""),
                                                Double.parseDouble(document.get("longitud")+""),
                                                document.get("nombre")+"");
                                        //Noticia noticia=new Noticia(document.get("NotImg")+"",document.get("NotTit" )+"",document.get("NotDes")+"");
                                        locales.add(local);
                                        //prueba.setText("Noticias actuales");

                                        Log.d("TEG", document.getId() + " => " + document.getData());

                                    }

                                }

                            }
                            Log.d("TAMAÑO",  " => "+locales.size() );

                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());

                        }
                        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                Log.d("SIZE",  " => "+locales.size() );
                                for(int i =0;i<locales.size();i++)
                                {
                                    Log.d("TIG",  " => " );
                                    LatLng point =new LatLng(locales.get(i).getLocLat(),locales.get(i).getLocLon());
                                    googleMap.addMarker(
                                            new MarkerOptions()
                                            .position(point)
                                            .title(locales.get(i).getLonNom()));


                                    //aca agregar
                                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        @Override
                                        public boolean onMarkerClick(@NonNull Marker marker) {
                                            Bundle args = new Bundle();
                                            args.putString("name_", marker.getTitle());
                                            args.putString("lat_", Double.toString(marker.getPosition().latitude));
                                            args.putString("log_", Double.toString(marker.getPosition().longitude));
                                            Dialog_local dialog = new Dialog_local();
                                            dialog.setArguments(args);
                                            dialog.show(getFragmentManager(), "Local_dialog");
                                            return false;
                                        }
                                    });

                                }
                                LatLng myUbication =new LatLng(latitud,longitud);
                                googleMap.addMarker(new MarkerOptions().position(myUbication).title("Usted esta aqui"));
                                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myUbication,15));
                                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                                    @Override
                                    public void onMapClick(@NonNull LatLng latLng) {
                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(latLng);
                                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                                        googleMap.clear();
                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                latLng, 10
                                        ));
                                        googleMap.addMarker(markerOptions);
                                    }
                                });
                            }
                        });
                    }
                });


        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==100 && (grantResults.length>0)&&(grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED))
        {
            getCurrentLocation();
        }
        else
        {
            Toast.makeText(getActivity(),"Permission denied",Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();
                    } else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                latitud = location1.getLatitude();
                                longitud = location1.getLongitude();
                            }
                        };
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        client.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());

                    }
                }
            });
        }else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }


}