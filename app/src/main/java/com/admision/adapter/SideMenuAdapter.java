package com.admision.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.admision.R;
import com.admision.objects.MenuItem;
import com.admision.utils.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kartum Infotech (Bhavesh Hirpara) on 10/31/2017.
 */
public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuAdapter.MyViewHolder> {

    ImageLoader imageLoader;
    //    private ArrayList<Spinner> data = new ArrayList<Spinner>();
    private ArrayList<MenuItem> data = new ArrayList<MenuItem>();
    Context context;
    Eventlistener mEventlistener;

    public SideMenuAdapter(Context c) {
        this.context = c;
        imageLoader = Utils.initImageLoader(context);
    }

//    public void add(ArrayList<Spinner> mData) {
//        data.add(mData);
//        notifyDataSetChanged();
//    }

    public MenuItem getItem(int pos) {
        return data.get(pos);
    }

    //
    public void addAll(ArrayList<MenuItem> mData) {
        data.clear();
        data.addAll(mData);
        notifyDataSetChanged();
    }
//
//    public void selectAll(boolean selectall) {
//        for (int i = 0; i < data.size(); i++) {
//            data.get(i).isOn = selectall;
//        }
//
//        notifyDataSetChanged();
//    }
//
//    public void changeSelection(int position, boolean isMultiSel) {
//
//        for (int i = 0; i < data.size(); i++) {
//            if (position == i) {
//                data.get(i).isOn = !data.get(i).isOn;
//            } else if (!isMultiSel) {
//                data.get(i).isOn = false;
//            }
//        }
//
//        notifyDataSetChanged();
//    }

//    public int getObjectId(int position) {
//        try {
//            return data.(position).getInt("id");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
//    public JSONObject getItem(int position) {
//        return data.get(position);
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.item_side_menu, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvMenuName.setText(Utils.nullSafe("" + data.get(position).name));
        holder.imgMenuIcon.setImageResource(data.get(position).icon);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEventlistener != null) {
                    mEventlistener.OnMenuItemclick(position, view);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgMenuIcon)
        ImageView imgMenuIcon;
        @BindView(R.id.tvMenuName)
        TextView tvMenuName;
        @BindView(R.id.container)
        View container;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface Eventlistener {
        void OnMenuItemclick(int position, View view);
    }

    public void setmEventlistener(Eventlistener eventlistener) {
        this.mEventlistener = eventlistener;
    }
}
