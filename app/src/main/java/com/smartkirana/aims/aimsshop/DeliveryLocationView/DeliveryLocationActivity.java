//package com.smartkirana.aims.aimsshop.DeliveryLocationView;
//
//import android.Manifest;
//import android.animation.ValueAnimator;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.animation.LinearInterpolator;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.google.android.gms.maps.model.SquareCap;
//import com.smartkirana.aims.aimsshop.R;
//
//import java.util.List;
//
//import static com.google.android.gms.maps.model.JointType.ROUND;
//import static com.smartkirana.aims.aimsshop.DeliveryLocationView.MapUtils.getBearing;
//
//public class DeliveryLocationActivity extends CurrentLocation implements OnMapReadyCallback, LocationListener {
//    private static final long DELAY = 4500;
//    private static final long ANIMATION_TIME_PER_ROUTE = 3000;
//    String polyLine = "q`epCakwfP_@EMvBEv@iSmBq@GeGg@}C]mBS{@KTiDRyCiBS";
//    GoogleMap googleMap;
//    private PolylineOptions polylineOptions;
//    private Polyline greyPolyLine;
//    private SupportMapFragment mapFragment;
//    private Handler handler;
//    private Marker carMarker;
//    private int index;
//    private int next;
//    private LatLng startPosition;
//    private LatLng endPosition;
//    private float v;
//    Button button2;
//    List<LatLng> polyLineList;
//    private double lat, lng;
//    // banani
//
//
//    protected LocationManager locationManager;
//    private String TAG = "HomeActivity";
//
//    public static final String URL_DRIVER_LOCATION_ON_RIDE = "*******";
//    private boolean isFirstPosition = true;
//    private Double startLatitude;
//    private Double startLongitude;
//    CurrentLocation currentLocation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_delivery_location);
//
//        mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//        handler = new Handler();
//        handler.post(mStatusChecker);
//
//
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//
//    }
//
//    Runnable staticCarRunnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.i(TAG, "staticCarRunnable handler called...");
//            if (index < (polyLineList.size() - 1)) {
//                index++;
//                next = index + 1;
//            } else {
//                index = -1;
//                next = 1;
//                stopRepeatingTask();
//                return;
//            }
//
//            if (index < (polyLineList.size() - 1)) {
////                startPosition = polyLineList.get(index);
//                startPosition = carMarker.getPosition();
//                endPosition = polyLineList.get(next);
//            }
//
//            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
//            valueAnimator.setDuration(3000);
//            valueAnimator.setInterpolator(new LinearInterpolator());
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
////                    Log.i(TAG, "Car Animation Started...");
//
//                    v = valueAnimator.getAnimatedFraction();
//                    lng = v * endPosition.longitude + (1 - v)
//                            * startPosition.longitude;
//                    lat = v * endPosition.latitude + (1 - v)
//                            * startPosition.latitude;
//                    LatLng newPos = new LatLng(lat, lng);
//                    carMarker.setPosition(newPos);
//                    carMarker.setAnchor(0.5f, 0.5f);
//                    carMarker.setRotation(getBearing(startPosition, newPos));
//                    googleMap.moveCamera(CameraUpdateFactory
//                            .newCameraPosition
//                                    (new CameraPosition.Builder()
//                                            .target(newPos)
//                                            .zoom(18.0f)
//                                            .build()));
//
//
//                }
//            });
//            valueAnimator.start();
//            handler.postDelayed(this, 5000);
//
//        }
//    };
//
//    private void startCarAnimation(Double latitude, Double longitude) {
//        LatLng latLng = new LatLng(latitude, longitude);
//
//        carMarker = googleMap.addMarker(new MarkerOptions().position(latLng).
//                flat(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.new_car_small)));
//
//
//        index = -1;
//        next = 1;
//        handler.postDelayed(staticCarRunnable, 3000);
//    }
//
//    void stopRepeatingTask() {
//
//        if (staticCarRunnable != null) {
//            handler.removeCallbacks(staticCarRunnable);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopRepeatingTask();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        this.googleMap = googleMap;
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.setTrafficEnabled(false);
//        googleMap.setIndoorEnabled(false);
//        googleMap.setBuildingsEnabled(false);
//        //googleMap.getUiSettings().setZoomControlsEnabled(true);
//    }
//
//    private void startBikeAnimation(final LatLng start, final LatLng end) {
//
//        Log.i(TAG, "startBikeAnimation called...");
//
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
//        valueAnimator.setDuration(ANIMATION_TIME_PER_ROUTE);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//
//                //LogMe.i(TAG, "Car Animation Started...");
//                v = valueAnimator.getAnimatedFraction();
//                lng = v * end.longitude + (1 - v)
//                        * start.longitude;
//                lat = v * end.latitude + (1 - v)
//                        * start.latitude;
//
//                LatLng newPos = new LatLng(lat, lng);
//                carMarker.setPosition(newPos);
//                carMarker.setAnchor(0.5f, 0.5f);
//                carMarker.setRotation(getBearing(start, end));
//
//                // todo : Shihab > i can delay here
//                googleMap.moveCamera(CameraUpdateFactory
//                        .newCameraPosition
//                                (new CameraPosition.Builder()
//                                        .target(newPos)
//                                        .zoom(18.0f)
//                                        .build()));
//
//                startPosition = carMarker.getPosition();
//
//            }
//
//        });
//        valueAnimator.start();
//    }
//
//    Runnable mStatusChecker = new Runnable() {
//        @Override
//        public void run() {
//            try {
//
//                getDriverLocationUpdate();
//
//
//            } catch (Exception e) {
//                Log.e(TAG, e.getMessage());
//            }
//
//            handler.postDelayed(mStatusChecker, DELAY);
//
//        }
//    };
//
//    private void getDriverLocationUpdate() {
//
//
//     Location location = currentLocation.getLoc();
//     if(location!=null) {
//         startLatitude = location.getLatitude();
//         startLongitude = location.getLongitude();
//     }else {
//         startLatitude = 27.691373;
//         startLongitude =85.369345;
//
//     }
//        Log.d(TAG, startLatitude + "--" + startLongitude);
//
//        if (isFirstPosition) {
//            startPosition = new LatLng(startLatitude, startLongitude);
//
//            carMarker = googleMap.addMarker(new MarkerOptions().position(startPosition).
//                    flat(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.new_car_small)));
//            carMarker.setAnchor(0.5f, 0.5f);
//
//            googleMap.moveCamera(CameraUpdateFactory
//                    .newCameraPosition
//                            (new CameraPosition.Builder()
//                                    .target(startPosition)
//                                    .zoom(18.0f)
//                                    .build()));
//
//            isFirstPosition = false;
//
//        } else {
//            endPosition = new LatLng(startLatitude, startLongitude);
//
//            Log.d(TAG, startPosition.latitude + "--" + endPosition.latitude + "--Check --" + startPosition.longitude + "--" + endPosition.longitude);
//
//            if ((startPosition.latitude != endPosition.latitude) || (startPosition.longitude != endPosition.longitude)) {
//
//                Log.e(TAG, "NOT SAME");
//                startBikeAnimation(startPosition, endPosition);
//
//            } else {
//
//                Log.e(TAG, "SAMME");
//            }
//        }
//    }
//
//
//    void CreatePolyLineOnly() {
//
//        googleMap.clear();
//
//        polyLineList = MapUtils.decodePoly(polyLine);
//
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (LatLng latLng : polyLineList) {
//            builder.include(latLng);
//        }
//        LatLngBounds bounds = builder.build();
//        CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
//        googleMap.animateCamera(mCameraUpdate);
//
//        polylineOptions = new PolylineOptions();
//        polylineOptions.color(Color.BLACK);
//        polylineOptions.width(5);
//        polylineOptions.startCap(new SquareCap());
//        polylineOptions.endCap(new SquareCap());
//        polylineOptions.jointType(ROUND);
//        polylineOptions.addAll(polyLineList);
//        greyPolyLine = googleMap.addPolyline(polylineOptions);
//
//
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//        Log.d("Latitude", "disable");
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        Log.d("Latitude", "enable");
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        Log.d("Latitude", "status");
//    }
//}
