package ddwu.mobile.network.myretrofittest;

import ddwu.mobile.network.myretrofittest.model.json.BoxOfficeRoot;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IKobisAPIService {
    
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.{type}")
    Call<BoxOfficeRoot> getDailyBoxOfficeResult(@Path("type") String type, @Query("key") String key, @Query("targetDt") String targetDate);
    //이 인터페이스를 상속받은 클래스는 이 메소드를 구현해야하는데, 레트로핏이 자동으로 수행하줌
}
