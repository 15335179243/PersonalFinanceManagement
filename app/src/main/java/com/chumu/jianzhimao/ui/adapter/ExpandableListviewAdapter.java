package com.chumu.jianzhimao.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.mvp.bean.GroutBean;

import java.util.ArrayList;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private ArrayList<String> groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private ArrayList<ArrayList<GroutBean.DataBean.RowsBean.GroupUserRespBean.UsersBean>> childs;
    private Context context;

    public ExpandableListviewAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<GroutBean.DataBean.RowsBean.GroupUserRespBean.UsersBean>> childs) {
        this.context = context;
        this.groups = groups;
        this.childs = childs;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (childs!=null&&childs.size()>0) {
            return childs.get(i).size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childs.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
 */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_qunzhu_group, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.parent_textview_id = convertView.findViewById(R.id.parent_textview_id);
            groupViewHolder.parent_image = convertView.findViewById(R.id.parent_image);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.parent_textview_id.setText(groups.get(groupPosition));
        //如果是展开状态，
        if (isExpanded) {
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.xianjiantou));
        } else {
            groupViewHolder.parent_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.youjiantou));
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_qunzhu_chind, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.chidren_item = (TextView) convertView.findViewById(R.id.chidren_username);
            childViewHolder.chidren_img = (ImageView) convertView.findViewById(R.id.img_pic);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.chidren_item.setText(childs.get(groupPosition).get(childPosition).getNickName());
        Glide.with(convertView.getContext()).load((childs.get(groupPosition).get(childPosition).getHeadPhoto()))
                .error(R.drawable.common_base_no_login_head)
                .placeholder(R.drawable.common_base_no_login_head)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(childViewHolder.chidren_img);

        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setlist(ArrayList<String> groups, ArrayList<ArrayList<GroutBean.DataBean.RowsBean.GroupUserRespBean.UsersBean>> childs) {
        this.groups = groups;
        this.childs = childs;
    }

    static class GroupViewHolder {
        TextView parent_textview_id;
        ImageView parent_image;
    }

    static class ChildViewHolder {
        TextView chidren_item;
        ImageView chidren_img;

    }
}
