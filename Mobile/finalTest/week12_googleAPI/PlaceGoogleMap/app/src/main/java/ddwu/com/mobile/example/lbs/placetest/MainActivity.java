package ddwu.com.mobile.example.lbs.placetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ddwu.mobile.place.placebasic.OnPlaceBasicResult;
import ddwu.mobile.place.placebasic.pojo.PlaceBasic;
import ddwu.mobile.place.placebasic.PlaceBasicManager;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    final static String TAG = "MainActivity";
    final static int PERMISSION_REQ_CODE = 100;

    private EditText etKeyword;

//    Map & Place
    private GoogleMap mGoogleMap;
    private PlaceBasicManager placeBasicManager;   // 주변 지역 검색을 수행하는 매니저 클래스
    // PlaceID를 사용하여 세부정보 요청
    private PlacesClient placesClient;   // 장소의 상세 정보 확인

    private Marker placeMarkers;
    private MarkerOptions markerOptions;
    private Place resultPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etKeyword = findViewById(R.id.etKeyword);

        /* 1. PlaceBasicManager 생성
        * 2. placeBasicManager.setOnPlaceBasicResult() 구현
        * 확인한  위도/경도, 장소명, PlaceID 를 사용하여 지도에 새로운 마커 추가
        * placeID 는 Marker.setTag(placeID)를 사용하여 각 마커에 저장*/

        placeBasicManager = new PlaceBasicManager(getString(R.string.api_key));
        placeBasicManager.setOnPlaceBasicResult(new OnPlaceBasicResult() {
            @Override
            public void onPlaceBasicResult(List<PlaceBasic> list) {
                markerOptions = new MarkerOptions();
                for (PlaceBasic place : list) {
                    Log.d(TAG, place.toString());
                    LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
                    markerOptions.position(latLng);
                    markerOptions.title(place.getName());
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    // 클릭 시 타이틀에 뜨도록 설정
                    placeMarkers = mGoogleMap.addMarker(markerOptions);
                    placeMarkers.setTag(placeMarkers);
                    placeMarkers.showInfoWindow();
                }
            }
        });

     //   checkPermission();
     //   mapLoad();

        if (checkPermission()) {
            mapLoad();
        }

        // place 초기화 및 클라이언트 생성
        Places.initialize(getApplicationContext(), getString(R.string.api_key));
        placesClient = Places.createClient(this);
        System.out.println(placesClient);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                if (etKeyword.getText().toString().equals("카페")) {
                    searchStart(Double.parseDouble(getString(R.string.init_lat)),
                            Double.parseDouble(getString(R.string.init_lng)),
                            100, PlaceTypes.CAFE);
                } else if (etKeyword.getText().toString().equals("식당")) {
                    searchStart(Double.parseDouble(getString(R.string.init_lat)),
                            Double.parseDouble(getString(R.string.init_lng)),
                            100, PlaceTypes.RESTAURANT);
                }
                break;
        }
    }


    /*입력된 유형의 주변 정보를 검색
     * PlaceBasicManager 를 사용해 type 의 정보로 PlaceBasic 을 사용하여 현재위치 주변의 관심장소 확인 */
    private void searchStart(double lat, double lng, int radius, String type) {
        placeBasicManager.searchPlaceBasic(lat, lng, 100, type);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        //checkPermission();   // 권한 체크

        mGoogleMap.setMyLocationEnabled(true);   // 내 위치 버튼 활성화

        // 내 위치버튼 클릭 리스너
        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                return false;   // 추가한 동작 이외에 기본 동작을 수행하고자 할 경우 false
            }
        });

        // 내 위치 클릭 리스너
        mGoogleMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                String loc = String.format("(%.6f, %.6f)", location.getLatitude(), location.getLongitude());
                Toast.makeText(MainActivity.this, loc, Toast.LENGTH_SHORT).show();
            }
        });

        /*마커의 InfoWindow 클릭 시 marker에 Tag 로 보관한 placeID 로
        * Google PlacesAPI 를 이용하여 장소의 상세정보*/
        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
//                1. 마커에서 Marker.getTag() 를 사용하여 placeID 확인
                String placeId = marker.getTag().toString();
                System.out.println(placeId);

//                2. getPlaceDetail() 을 호출하여 Place 검색
                getPlaceDetail(placeId);
//                3. callDetailActivity() 에 Place 정보를 전달하고 DetailActivity 호출 (callDetailActivity() 사용)
                callDetailActivity(resultPlace);

            }
        });
    }


    /*Place ID 의 장소에 대한 세부정보 획득하여 반환
    * 마커의 InfoWindow 클릭 시 호출*/
    private Place getPlaceDetail(String placeId) {
        System.out.println("디테일 함수 시작!");
        // 확인하고자하는 장소 세부정보 종류
        List<Place.Field> placeFields = Arrays.asList(
                Place.Field.ID, Place.Field.NAME,
                Place.Field.PHONE_NUMBER, Place.Field.ADDRESS,
                Place.Field.BUSINESS_STATUS
        );

        // placeAPI 요청 객체 생성
        // 확인하고자하는 장소의 PlaceId를 이용해 요청
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields). build();
        System.out.println("요청햇어요");

        placesClient.fetchPlace(request).addOnSuccessListener(new OnSuccessListener<FetchPlaceResponse>() {
            @Override
            public void onSuccess(FetchPlaceResponse fetchPlaceResponse) {
                System.out.println("성공");
                System.out.println(fetchPlaceResponse.getPlace());
                resultPlace = fetchPlaceResponse.getPlace();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("실패!");
                System.out.println(e);
            }
        });
        System.out.println("디테일 함수 끝!");
        return null;
    }


//    Google PlacesAPI 의 place 객체를 전달 받아 DetailActivity 에 전달하며 액티비티 실행
    private void callDetailActivity(Place place) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("name",place.getName());
                        intent.putExtra("phone",place.getPhoneNumber());
                        intent.putExtra("address",place.getAddress());

        startActivity(intent);
    }



    /*구글맵을 멤버변수로 로딩*/
    private void mapLoad() {
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);      // 매배변수 this: MainActivity 가 OnMapReadyCallback 을 구현하므로
    }


    /* 필요 permission 요청 */
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED ) {
                // 퍼미션을 획득하였을 경우 맵 로딩 실행
                mapLoad();
            } else {
                // 퍼미션 미획득 시 액티비티 종료
                Toast.makeText(this, "앱 실행을 위해 권한 허용이 필요함", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }




    /*구글이 현재 위치의 주변 정보를 확인하는 기본 방법 사용 메소드*/

/*
    private void startCPSearch() {
        // Use fields to define the data types to return.
        List<Place.Field> placeFields = Collections.singletonList(Place.Field.ID);

        // Use the builder to create a FindCurrentPlaceRequest.
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.newInstance(placeFields);

        // Call findCurrentPlace and handle the response (first check that the user has granted permission).

        if (checkPermission()) {
            Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
            placeResponse.addOnCompleteListener(new OnCompleteListener<FindCurrentPlaceResponse>() {
                @Override
                public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                    if (task.isSuccessful()){
                        FindCurrentPlaceResponse response = task.getResult();
                        for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
//                            Log.i(TAG, String.format("Place ID: %s", placeLikelihood.getPlace().getId()));
                            Log.i(TAG, String.format("Place '%s' has likelihood: %f",
                                    placeLikelihood.getPlace().getId(),
                                    placeLikelihood.getLikelihood()));
                        }
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof ApiException) {
                            ApiException apiException = (ApiException) exception;
                            Log.e(TAG, "Place not found: " + apiException.getStatusCode());
                        }
                    }
                }
            });
        }
    }
*/

}
