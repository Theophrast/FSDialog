package com.theophrast.fsdialog.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theophrast.fsdialog.R;
import com.theophrast.fsdialog.callbacks.FSDialogButtonClickListener;

/**
 * Created by theophrast on 2017.08.09..
 */
public class FSDialog {
    private Context mContext;

    private boolean autoDismiss;
    private boolean contentScrollable;

    //colors
    private int titleStringColor;
    private int messageStringColor;
    private int backgroundColor;
    private int titleBackgroundColor;

    //contents
    private String title;
    private String confirmString;
    private String dialogMessage;

    private boolean titleEnabled;
    private boolean confirmButtonEnabled;

    private int layoutResource;

    private FSDialogButtonClickListener discardListener;
    private FSDialogButtonClickListener confirmListener;

    AlertDialog mDialog;
    View contentView;

    private FSDialog(FsDialogBuilder builder) {
        this.mContext = builder.mContext;
        this.titleStringColor = builder.titleStringColor;
        this.messageStringColor = builder.messageStringColor;
        this.backgroundColor = builder.backgroundColor;
        this.titleBackgroundColor = builder.titleBackgroundColor;
        this.title = builder.title;
        this.confirmString = builder.confirmString;
        this.layoutResource = builder.layoutResource;
        this.autoDismiss = builder.autoDismiss;
        this.contentScrollable = builder.contentScrollable;
        this.titleEnabled = builder.titleEnabled;
        this.confirmButtonEnabled = builder.confirmButtonEnabled;
        this.dialogMessage = builder.dialogMessage;
    }

    public void setDiscardListener(FSDialogButtonClickListener discardListener) {
        this.discardListener = discardListener;
    }

    public void setConfirmListener(FSDialogButtonClickListener confirmListener) {
        this.confirmListener = confirmListener;
    }


    public void show() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thDialogBase;
        if (contentScrollable) {
            thDialogBase = inflater.inflate(com.theophrast.fsdialog.R.layout.fsdialog_base_scrollable, null);
        } else {
            thDialogBase = inflater.inflate(com.theophrast.fsdialog.R.layout.fsdialog_base, null);
        }

        setupBaseDialog(thDialogBase);
        setupDialogContent(thDialogBase);

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


    private void setupDialogContent(View baseView) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewGroup container = (ViewGroup) baseView.findViewById(R.id.container);

        if (dialogMessage == null) {
            contentView = inflater.inflate(layoutResource, container);
        } else {
            contentView = inflater.inflate(R.layout.fsdialog_content_simplemessage, container);
            TextView tv_message = (TextView) contentView.findViewById(R.id.tv_message);
            tv_message.setText(dialogMessage);
            tv_message.setTextColor(messageStringColor);
        }
    }


    private void setupBaseDialog(View baseView) {

        //find ui elements
        ImageButton ib_discard = (ImageButton) baseView.findViewById(R.id.ib_fsdialog_discard);
        TextView tv_title = (TextView) baseView.findViewById(R.id.tv_fsdialog_title);
        Button bt_confirm = (Button) baseView.findViewById(R.id.tv_fsdialog_confirm);
        LinearLayout titlebar = (LinearLayout) baseView.findViewById(R.id.fsdialog_title);
        LinearLayout rootView = (LinearLayout) baseView.findViewById(R.id.linlay_fsdialog_root);

        //setup colors
        ib_discard.setColorFilter(titleStringColor, android.graphics.PorterDuff.Mode.MULTIPLY);
        tv_title.setTextColor(titleStringColor);
        bt_confirm.setTextColor(titleStringColor);

        titlebar.setBackgroundColor(titleBackgroundColor);
        rootView.setBackgroundColor(backgroundColor);

        //setup the string values
        tv_title.setText(title);
        tv_title.setVisibility(titleEnabled ? View.VISIBLE : View.INVISIBLE);
        bt_confirm.setText(confirmString);
        bt_confirm.setVisibility(confirmButtonEnabled ? View.VISIBLE : View.INVISIBLE);


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
        private int messageStringColor = Color.parseColor("#757575");
        private int backgroundColor = Color.parseColor("#F0F0F0");
        private int titleBackgroundColor = Color.parseColor("#303F9F");

        private String title = "Title";
        private String confirmString = "Ok";
        private String dialogMessage = null;

        private int layoutResource = R.layout.fsdialog_content_base;

        private boolean autoDismiss = true;
        private boolean contentScrollable = true;

        private boolean titleEnabled = true;
        private boolean confirmButtonEnabled = true;


        public FsDialogBuilder(Context mContext) {
            this.mContext = mContext;
        }

        public FsDialogBuilder setAutoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
            return this;
        }

        public FsDialogBuilder setContentScrollable(boolean contentScrollable) {
            this.contentScrollable = contentScrollable;
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

        public FsDialogBuilder setMessageStringColor(@ColorInt int messageStringColor) {
            this.messageStringColor = messageStringColor;
            return this;
        }

        public FsDialogBuilder setMessageStringColorRes(@ColorRes int messageStringColorRes) {
            this.messageStringColor = ContextCompat.getColor(mContext, messageStringColorRes);
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

        public FsDialogBuilder setTitleRes(@StringRes int titleRes) {
            this.title = mContext.getString(titleRes);
            return this;
        }

        public FsDialogBuilder setConfirmString(String confirmString) {
            this.confirmString = confirmString;
            return this;
        }

        public FsDialogBuilder setConfirmStringRes(@StringRes int confirmStringRes) {
            this.confirmString = mContext.getString(confirmStringRes);
            return this;
        }

        public FsDialogBuilder setSimpleMessage(String dialogMessage) {
            this.dialogMessage = dialogMessage;
            return this;
        }

        public FsDialogBuilder setSimpleMessageRes(@StringRes int dialogMessageRes) {
            this.dialogMessage = mContext.getString(dialogMessageRes);
            return this;
        }

        public FsDialogBuilder setLayoutResource(@LayoutRes int layoutResource) {
            this.layoutResource = layoutResource;
            return this;
        }

        public FsDialogBuilder setNoTitle() {
            this.titleEnabled = false;
            return this;
        }

        public FsDialogBuilder setNoConfirmButton() {
            this.confirmButtonEnabled = false;
            return this;
        }

        public FSDialog build() {
            return new FSDialog(this);
        }
    }

}



