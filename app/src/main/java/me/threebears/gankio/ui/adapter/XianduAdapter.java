package me.threebears.gankio.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import me.threebears.gankio.R;
import me.threebears.gankio.model.XianduEntity;

/**
 * Created time 2017/11/17.
 *
 * @author threeBears
 */
public class XianduAdapter extends BaseQuickAdapter<XianduEntity, BaseViewHolder> {

    private Context mContext;

    public XianduAdapter(Context context) {
        super(R.layout.item_recycler_xiandu);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, XianduEntity item) {
        helper.setText(R.id.item_desc, item.getDesc())
                .setText(R.id.item_date, item.getDate())
                .setText(R.id.item_title, item.getCategoryName());
        Glide.with(mContext)
                .load(item.getCategoryIcon())
                .asBitmap()
                .centerCrop()
                .into((ImageView) helper.getView(R.id.item_icon));
    }
}
