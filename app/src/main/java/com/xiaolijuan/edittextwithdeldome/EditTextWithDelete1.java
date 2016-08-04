package com.xiaolijuan.edittextwithdeldome;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
//http://blog.csdn.net/qq_20785431/article/details/50762834

/**
 * Created by chenshuai on 2016/8/4.
 */
public class EditTextWithDelete1 extends EditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Drawable imgAble;//删除按钮
    private Context mContext;

    public EditTextWithDelete1(Context context) {

        super(context);
        this.mContext = context;
        init();
    }


    public EditTextWithDelete1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public EditTextWithDelete1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        imgAble = mContext.getResources().getDrawable(R.mipmap.icon_delete_gray);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();

            }
        });

    }

    //设置删除图片
    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            //setCompoundDrawables(left, top, right, bottom)
            // setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
            //意思是设置Drawable显示在text的左、上、右、下位置。

        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();//getX()是表示Widget相对于自身左上角的x坐标,而getRawX()是表示相对于屏幕左上角的x坐标值(注意:这个屏幕左上角是手机屏幕左上角,不管activity是否有titleBar或是否全屏幕),getY(),getRawY()一样的道理如果你的某个Activity中实现OnTouchListener接口，需要重写
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);

            Rect rect = new Rect();//获得edit的矩形
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY))
                setText("");
        }

        return super.onTouchEvent(event);
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}
