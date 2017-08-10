package com.theophrast.fsdialog.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.theophrast.fsdialog.R;
import com.theophrast.fsdialog.callbacks.FSDialogButtonClickListener;

/**
 * Created by theophrast on 2017.08.09..
 */
public class FSDialog {
    private Context mContext;

    boolean autoDismiss;

    private int titleStringColor;
    private int backgroundColor;
    private int titleBackgroundColor;

    private String title;
    private String confirmString;

    private int layoutResource;

    private FSDialogButtonClickListener discardListener;
    private FSDialogButtonClickListener confirmListener;

    AlertDialog mDialog;
    View contentView;

    private FSDialog(FsDialogBuilder builder) {
        this.mContext = builder.mContext;
        this.titleStringColor = builder.titleStringColor;
        this.backgroundColor = builder.backgroundColor;
        this.titleBackgroundColor = builder.titleBackgroundColor;
        this.title = builder.title;
        this.confirmString = builder.confirmString;
        this.layoutResource = builder.layoutResource;
        this.discardListener = builder.discardListener;
        this.confirmListener = builder.confirmListener;
        this.autoDismiss = builder.autoDismiss;
    }

    public void show() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thDialogBase = inflater.inflate(com.theophrast.fsdialog.R.layout.fsdialog_base, null);

        ViewGroup container = (ViewGroup) thDialogBase.findViewById(R.id.container);
        contentView = inflater.inflate(layoutResource, container);

        setupBaseDialog(thDialogBase);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(thDialogBase);
        mDialog = builder.show();
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public View getContentView() {
        return contentView;
    }


    private void setupBaseDialog(View baseView) {

        //find ui elements
        ImageButton ib_discard = (ImageButton) baseView.findViewById(R.id.ib_fsdialog_discard);
        TextView tv_title = (TextView) baseView.findViewById(R.id.tv_fsdialog_title);
        Button bt_confirm = (Button) baseView.findViewById(R.id.tv_fsdialog_confirm);
        RelativeLayout titlebar = (RelativeLayout) baseView.findViewById(R.id.fsdialog_title);
        ScrollView scrollview = (ScrollView) baseView.findViewById(R.id.container_scrollview);

        //setup colors
        ib_discard.setColorFilter(titleStringColor, android.graphics.PorterDuff.Mode.MULTIPLY);
        tv_title.setTextColor(titleStringColor);
        bt_confirm.setTextColor(titleStringColor);

        titlebar.setBackgroundColor(titleBackgroundColor);
        scrollview.setBackgroundColor(backgroundColor);

        //setup the string values
        tv_title.setText(title);
        bt_confirm.setText(confirmString);


        //setup the listeners
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmListener != null) confirmListener.OnButtonClick();
                if (autoDismiss) dismiss();
            }
        });

        ib_discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (discardListener != null) discardListener.OnButtonClick();
                if (autoDismiss) dismiss();
            }
        });

    }

    public static class FsDialogBuilder {

        private Context mContext;

        private int titleStringColor = Color.parseColor("#F6F6F6");
        private int backgroundColor = Color.parseColor("#F0F0F0");
        private int titleBackgroundColor = Color.parseColor("#303F9F");

        private String title = "Title";
        private String confirmString = "Save";

        private int layoutResource = R.layout.fsdialog_content_base;

        private boolean autoDismiss = true;

        private FSDialogButtonClickListener discardListener = null;
        private FSDialogButtonClickListener confirmListener = null;

        public FsDialogBuilder(Context mContext) {
            this.mContext = mContext;
        }

        public FsDialogBuilder setAutoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
            return this;
        }

        public FsDialogBuilder setTitleStringColor(@ColorInt int titleStringColor) {
            this.titleStringColor = titleStringColor;
            return this;
        }

        public FsDialogBuilder setTitleStringColorRes(@ColorRes int titleStringColorRes) {
            this.titleStringColor = ContextCompat.getColor(mContext, titleStringColorRes);
            return this;
        }

        public FsDialogBuilder setBackgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public FsDialogBuilder setBackgroundColorRes(@ColorRes int backgroundColorRes) {
            this.backgroundColor = ContextCompat.getColor(mContext, backgroundColorRes);
            return this;
        }

        public FsDialogBuilder setTitleBackgroundColor(@ColorInt int titleBackgroundColor) {
            this.titleBackgroundColor = titleBackgroundColor;
            return this;
        }

        public FsDialogBuilder setTitleBackgroundColorRes(@ColorRes int titleBackgroundColorRes) {
            this.titleBackgroundColor = ContextCompat.getColor(mContext, titleBackgroundColorRes);
            return this;
        }

        public FsDialogBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public FsDialogBuilder setConfirmString(String confirmString) {
            this.confirmString = confirmString;
            return this;
        }

        public FsDialogBuilder setLayoutResource(@LayoutRes int layoutResource) {
            this.layoutResource = layoutResource;
            return this;
        }

        public FsDialogBuilder setDiscardListener(FSDialogButtonClickListener discardListener) {
            this.discardListener = discardListener;
            return this;
        }

        public FsDialogBuilder setConfirmListener(FSDialogButtonClickListener confirmListener) {
            this.confirmListener = confirmListener;
            return this;
        }

        public FSDialog build() {
            return new FSDialog(this);
        }
    }

}



