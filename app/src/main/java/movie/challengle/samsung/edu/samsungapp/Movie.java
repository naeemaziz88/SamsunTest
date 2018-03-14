package movie.challengle.samsung.edu.samsungapp;

/**
 * Created by naeemaziz on 3/14/18.
 */

public class Movie {
    private String title;
    private String imagelink;
    private String popularity;
    private String id;
    private String gen;

    public String gettitle() {
        return title;
    }
    public void settitle(String mName) {
        this.title = mName;
    }
    public String getimagelink() {
        return imagelink;
    }
    public void setimagelink(String imagelink) {
        this.imagelink = imagelink;
    }
    public String getpopularity() {
        return popularity;
    }
    public void setpopularity(String popularity) {
        this.popularity = popularity;
    }
    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
    public String getgen() {
        return gen;
    }
    public void setgen(String gen) {
        this.gen = gen;
    }


}
