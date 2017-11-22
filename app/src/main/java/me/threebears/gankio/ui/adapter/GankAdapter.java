package me.threebears.gankio.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.threebears.gankio.R;
import me.threebears.gankio.model.GankEntity;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class GankAdapter extends BaseMultiItemQuickAdapter<GankEntity, BaseViewHolder> {

    private Context mContext;

    public GankAdapter(Context context) {
        super(null);
        mContext = context;
        addItemType(GankEntity.TYPE_NO_IMAGE, R.layout.item_recycler_gank_no_image);
        addItemType(GankEntity.TYPE_IMAGE, R.layout.item_recycler_gank_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankEntity item) {
        helper.setText(R.id.item_gank_desc, item.getDesc())
                .setText(R.id.item_gank_who, item.getWho())
                .setText(R.id.item_gank_date, dateConversion(item.getPublishedAt()))
                .addOnClickListener(R.id.item_gank_more);

        switch (helper.getItemViewType()) {
            case GankEntity.TYPE_NO_IMAGE:

                break;
            case GankEntity.TYPE_IMAGE:
                Glide.with(mContext)
                        .load(item.getImageList().get(0))
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) helper.getView(R.id.item_gank_image));
                break;
            default:
        }
    }

    private String dateConversion(String publishedAt) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        try {
            Date date = format.parse(publishedAt);
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return format.format(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
