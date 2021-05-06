package com.chumu.jianzhimao.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.HomeActivity;
import com.chumu.jianzhimao.ui.zhihu.activity.SpecialActivity;
import com.example.common_base.base.BaseMvpFragment;


public class RenMengFragment extends BaseMvpFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_remeng;
    }

    @Override
    public void initView() {
        ViewHolder viewHolder = new ViewHolder(mInflate);
        viewHolder.mBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SpecialActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public Object getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    static
    class ViewHolder {
        View view;
        AppCompatTextView mBtnText;

        ViewHolder(View view) {
            this.view = view;
            this.mBtnText = (AppCompatTextView) view.findViewById(R.id.btn_text);
        }
    }
}
