package com.starnamu.airlineschdule.slidinglayout;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starnamu.airlineschdule.MainActivity;
import com.starnamu.airlineschdule.database.AlarmDBControll;
import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirlineItemView extends FrameLayout {

    TextView textView02, textView03, textView04, textView05, textView06, textView07;
    AirlineItem DAitem;
    LinearLayout slidingLayoutView;
    boolean isPageOpen = false;/*페이지가 열려 있는지 알기 위한 플래그*/
    Animation translateLeftAnim;/* 애니메이션 객체*/
    Animation translateRightAnim;
    ViewPager viewPager;
    AlarmDBControll alarmDBControll;
    Context mainContext;

    public AirlineItemView(Context context) {
        super(context);
        init(context);
    }

    public AirlineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mainContext = MainActivity.getMainActivityContext();

        alarmDBControll = new AlarmDBControll();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listviewitem, this, true);

        textView02 = (TextView) view.findViewById(R.id.textView02);
        textView03 = (TextView) view.findViewById(R.id.textView03);
        textView04 = (TextView) view.findViewById(R.id.textView04);
        textView05 = (TextView) view.findViewById(R.id.textView05);
        textView06 = (TextView) view.findViewById(R.id.textView06);
        textView07 = (TextView) view.findViewById(R.id.textView07);

        view.findViewById(R.id.alramBtn).setOnClickListener(onClickListener);
        view.findViewById(R.id.infoBtn).setOnClickListener(onClickListener);
        view.findViewById(R.id.mapViewBtn).setOnClickListener(onClickListener);
        viewPager = ((ViewPager) ((Activity) mainContext).findViewById(R.id.pager));
        slidingLayoutView = (LinearLayout) view.findViewById(R.id.slidingLayout);

        // 슬라이딩으로 보여질 Button 객체 참조
        slidingLayoutView.setVisibility(View.GONE);

        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(context, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(context, R.anim.translate_right);

        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                slidingLayoutView.setVisibility(View.VISIBLE);
                slidingLayoutView.startAnimation(translateLeftAnim);
                isPageOpen = true;
                return false;
            }
        });

        textView02.setOnClickListener(onClicktextView02);
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.alramBtn:
//                    viewPager.setCurrentItem(4);
                    alarmDBControll.setDataTable(DAitem);
                    Toast.makeText(getContext(), "알람이 설정되었습니다", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.infoBtn:
                    viewPager.setCurrentItem(5);
                    break;

                case R.id.mapViewBtn:
                    viewPager.setCurrentItem(6);
                    break;
            }
        }
    };

    OnClickListener onClicktextView02 = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isPageOpen == true) {
                slidingLayoutView.startAnimation(translateRightAnim);
                slidingLayoutView.setVisibility(GONE);
                isPageOpen = false;
            }
        }
    };

    private String stringFormConversion(String str) {
        String Hour = str.substring(0, 2);
        String Minute = str.substring(2, 4);
        String timeChange = String.format("%s  :  %s", Hour, Minute);
        return timeChange;
    }

    public void setAirlineItem(AirlineItem items) {

        DAitem = items;

        String flightId = DAitem.getStriItem(3);
        textView02.setText(flightId);

        String scheduleDateTime = DAitem.getStriItem(4);
        textView03.setText(stringFormConversion(scheduleDateTime));

        String estimatedDateTime = DAitem.getStriItem(5);
        textView04.setText(stringFormConversion(estimatedDateTime));

        String gatenumber = DAitem.getStriItem(7);
        textView05.setText(gatenumber);

        String remark = DAitem.getStriItem(9);
        textView06.setText(remark);

        String carousel = DAitem.getStriItem(8);
        textView07.setText(carousel);
    }
}

