package me.threebears.gankio.model;

/**
 * Created time 2017/11/17.
 *
 * @author threeBears
 */
public class XianduTabEntity {

    private String title;
    private String url;

    public XianduTabEntity() {
    }

    public XianduTabEntity(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "XianduTabEntity{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
