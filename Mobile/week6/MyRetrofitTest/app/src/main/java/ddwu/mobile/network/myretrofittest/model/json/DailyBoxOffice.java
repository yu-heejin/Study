package ddwu.mobile.network.myretrofittest.model.json;

public class DailyBoxOffice {
    //영화 한 편을 표현
    private String rank;
    private String movieCd;
    private String movieNm;
    private String openDt;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMovieCd() {
        return movieCd;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    //출력 시 string으로 출력하기 위해 선언

    @Override
    public String toString() {
        return "DailyBoxOffice{" +
                "rank='" + rank + '\'' +
                ", movieCd='" + movieCd + '\'' +
                ", movieNm='" + movieNm + '\'' +
                ", openDt='" + openDt + '\'' +
                '}';
    }
}
