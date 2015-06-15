package com.starnamu.airlineschdule.parser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirlineItemView extends FrameLayout {

    TextView textView02, textView03, textView04, textView05, textView06, textView07;

    /**
     * 페이지가 열려 있는지 알기 위한 플래그
     */
    boolean isPageOpen = false;

    /**
     * 애니메이션 객체
     */
    Animation translateLeftAnim;
    Animation translateRightAnim;

    /**
     * 슬라이딩으로 보여지는 페이지 레이아웃
     */
    Button slidingBtn;

    public AirlineItemView(Context context) {
        super(context);
        init(context);
    }

    public AirlineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listviewitem, this, true);

        textView02 = (TextView) findViewById(R.id.textView02);
        textView03 = (TextView) findViewById(R.id.textView03);
        textView04 = (TextView) findViewById(R.id.textView04);
        textView05 = (TextView) findViewById(R.id.textView05);
        textView06 = (TextView) findViewById(R.id.textView06);
        textView07 = (TextView) findViewById(R.id.textView07);


        // 슬라이딩으로 보여질 Button 객체 참조
        slidingBtn = (Button) findViewById(R.id.slidingBtn);
        slidingBtn.setVisibility(View.INVISIBLE);

        // 애니메이션 객체 로딩
        translateLeftAnim = AnimationUtils.loadAnimation(context, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(context, R.anim.translate_right);

        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                slidingBtn.setVisibility(View.VISIBLE);
                slidingBtn.startAnimation(translateLeftAnim);
                return false;
            }
        });

        slidingBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingBtn.startAnimation(translateRightAnim);
                slidingBtn.setVisibility(GONE);
            }
        });
    }

    private String stringFormConversion(String str) {
        String Hour = str.substring(0, 2);
        String Minute = str.substring(2, 4);
        String timeChange = String.format("%s  :  %s", Hour, Minute);
        return timeChange;
    }

    public void setAirlineItem(AirlineItem items) {

        AirlineItem DAitem = items;

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

