package me.threebears.gankio.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class GankEntity implements MultiItemEntity {

    public final static int TYPE_IMAGE = 0;
    public final static int TYPE_NO_IMAGE = 1;

    @Override
    public int getItemType() {
        if (imageList == null || imageList.isEmpty()) {
            return TYPE_NO_IMAGE;
        } else {
            return TYPE_IMAGE;
        }
    }

    /**
     * _id : 5a0bcf5a421aa90fe725363c
     * createdAt : 2017-11-15T13:23:38.991Z
     * desc : [开发利器]在线查看对比 Android 和 Java 任意版本源码 IDEA插件
     * images : ["http://img.gank.io/a3fc2a25-adea-45de-b186-17884187280c"]
     * publishedAt : 2017-11-16T12:01:05.619Z
     * source : web
     * type : Android
     * url : https://github.com/pengwei1024/AndroidSourceViewer
     * used : true
     * who : 舞影凌风
     */

    @SerializedName("_id")
    private String id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    @SerializedName("images")
    private List<String> imageList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "GankEntity{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", imageList=" + imageList +
                '}';
    }

}
