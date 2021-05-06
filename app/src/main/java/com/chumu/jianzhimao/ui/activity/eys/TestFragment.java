package com.chumu.jianzhimao.ui.activity.eys;

import android.content.Intent;
import android.database.Observable;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.chumu.dt.v24.magicbox.livedatabus.ChuMuLiveDataBus;
import com.chumu.jianzhimao.R;
import com.chumu.jianzhimao.ui.activity.SetAllActivity;
import com.chumu.jianzhimao.ui.mvp.HomeModle;
import com.chumu.jianzhimao.ui.mvp.bean.TestBean;
import com.example.common_base.SPConstant;
import com.example.common_base.base.BaseApplication;
import com.example.common_base.base.BaseMvpFragment;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.common_base.ApiConfig.GET_HOME_TAB;
import static com.example.common_base.ApiConfig.QUERY_STATISTICS_TOTAL;


//发送mail
    /*implementation 'com.sun.mail:android-mail:1.6.2'
            implementation 'com.sun.mail:android-activation:1.6.2'
            //http lib
            implementation 'com.zhy:okhttputils:2.6.2'
            implementation 'com.squareup.okhttp3:okhttp:3.3.1'
            implementation 'com.alibaba:fastjson:1.1.72.android'
            api 'com.google.guava:guava:28.1-android'*/
public class TestFragment extends BaseMvpFragment<HomeModle> {

    @BindView(R.id.line)
    AnnualyieldView line;
    @BindView(R.id.arc)
    ArcView arc;
    private ArrayList<Integer> text = new ArrayList<>();
    private List<Float> yline = new ArrayList<Float>();
    private final static int GETIMG = 11;
    @BindView(R.id.tv_yuzhi)
    TextView tvYuzhi;
    private long mYunzhi = 0;
    private double mLASE = 0;

    @Override
    public void initView() {
        mYunzhi = (Integer) mChuMuSharedPreferences.getValue(SPConstant.Login.moneyLimit, 0);

        ChuMuLiveDataBus.INSTANCE.with("yuzhi").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mYunzhi = Long.parseLong((String) o);
                tvYuzhi.setText(mYunzhi + "");
                initData();
            }
        });
        tvYuzhi.setText(mYunzhi + "");
    }

    @Override
    public void initData() {
        mPresenter.getData(QUERY_STATISTICS_TOTAL, BaseApplication.mToken);

    }

    @Override
    public int getLayoutId() {
        return R.layout.test_activity;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String result = sdf.format(today);
        return result;
    }

    public static String[] pastDay(String time) {
        String[] day = new String[7];
        try {
//            //我这里传来的时间是个string类型的，所以要先转为date类型的。
//            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(Long.parseLong(time));

            int s = 0;
            for (int i = 6; i >= 0; i--) {
                day[s++] = getPastDate(i, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    private void testArcView() {
        List<Times> times = new ArrayList<>();

        if (mYunzhi > mLASE) {
            times.add(new Times(mYunzhi, "阈值"));
        }
        times.add(new Times(mLASE / mYunzhi, "支出"));

        ArcView.ArcViewAdapter myAdapter = arc.new ArcViewAdapter<Times>() {
            @Override
            public double getValue(Times times) {
                return times.hour;
            }

            @Override
            public String getText(Times times) {
                return times.text;
            }
        };//设置adapter
        myAdapter.setData(times);//设置数据集
        arc.setMaxNum(2);//设置可以显示的最大数值 该数值之后的会合并为其他
        //  arcView.setRadius(150);//设置圆盘半径
    }


    @Override
    public HomeModle getModel() {
        return new HomeModle();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

        hide();
        String str = (String) t[0];
        switch (whichApi) {
            default:
                break;
            case QUERY_STATISTICS_TOTAL:
                TestBean testBean = new Gson().fromJson(str, TestBean.class);
                if (testBean != null && testBean.getCode() == 200) {
                    mLASE = testBean.getData().getPay();
                    float num = 10;
                    yline.clear();
                    for (int i = 0; i < testBean.getData().getWeekList().size(); i++) {
                        text.add((int) testBean.getData().getWeekList().get(i).getPayTotal());
                        num += testBean.getData().getWeekList().get(i).getPayTotal();

                    }
                    for (int i = 0; i < testBean.getData().getWeekList().size(); i++) {
                        float i1 = (float) (testBean.getData().getWeekList().get(i).getPayTotal() / num);
                        yline.add(1 - i1);
                    }
                    line.setDataY(yline, text, pastDay(String.valueOf(System.currentTimeMillis())));
                    testArcView();

                }

                break;
        }

    }

    @OnClick(R.id.tv_yuzhi)
    public void onClick() {


        Intent intent = new Intent(getActivity(), SetAllActivity.class);
        intent.putExtra("title", "设置阈值");
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    class Times {
        double hour;
        String text;

        public Times() {
        }

        public Times(double hour, String text) {
            this.hour = hour;
            this.text = text;
        }
    }
}
