package me.threebears.gankio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class HttpResult<T> {

    private boolean error;

    @SerializedName("results")
    private List<T> gankList;

    public boolean isSuccess() {
        return !isError();
    }

    private boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getGankList() {
        return gankList;
    }

    public void setGankList(List<T> gankList) {
        this.gankList = gankList;
    }
}
