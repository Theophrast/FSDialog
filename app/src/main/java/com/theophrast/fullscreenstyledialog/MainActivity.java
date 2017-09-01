package com.theophrast.fullscreenstyledialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.theophrast.fsdialog.callbacks.FSDialogButtonClickListener;
import com.theophrast.fsdialog.ui.FSDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_showSimpleDialog = (Button) findViewById(R.id.bt_dialog_simple);
        bt_showSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSimpleDialog();
            }
        });

        Button bt_showInputDialog = (Button) findViewById(R.id.bt_dialog_input);
        bt_showInputDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        Button bt_show_no_confirm_button = (Button) findViewById(R.id.bt_dialog_noconfirm);
        bt_show_no_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoConfirmButtonDialog();
            }
        });
    }


    private void showSimpleDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.color_red)
                .setBackgroundColorRes(android.R.color.white)
                .setNoTitle()
                .setConfirmString("Ok")
                .setAutoDismiss(true)
                .setLayoutResource(R.layout.dialog_content);

        FSDialog dialog = builder.build();
        dialog.show();
        dialog.setConfirmListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Confirm clicked", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setDiscardListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Discard clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showInputDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.color_orange)
                .setBackgroundColorRes(android.R.color.white)
                .setTitle("Text input")
                .setConfirmString("Save")
                .setLayoutResource(R.layout.dialog_content_input);

        FSDialog dialog = builder.build();
        dialog.show();

        dialog.setConfirmListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Confirm clicked", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setDiscardListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Discard clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showNoConfirmButtonDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.color_green)
                .setBackgroundColorRes(android.R.color.white)
                .setNoConfirmButton()
                .setTitle("Simple message")
                .setSimpleMessage("This is a simple message!");

        FSDialog dialog = builder.build();
        dialog.show();
    }
}
