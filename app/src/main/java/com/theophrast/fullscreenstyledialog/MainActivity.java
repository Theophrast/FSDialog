package com.theophrast.fullscreenstyledialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.theophrast.fsdialog.callbacks.FSDialogButtonClickListener;
import com.theophrast.fsdialog.ui.FSDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_showCustomLayoutDialog = (Button) findViewById(R.id.bt_dialog_custom);
        bt_showCustomLayoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomLayoutDialog();
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

        Button bt_show_no_titlebar = (Button) findViewById(R.id.bt_dialog_notitlebar);
        bt_show_no_titlebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoTitleBarDialog();
            }
        });
    }


    private void showCustomLayoutDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setTitleStringColorRes(android.R.color.white)
                .setTitleBackgroundColorRes(R.color.color_red)
                .setBackgroundColorRes(android.R.color.white)
                .setTitle("Settings dialog")
                .setConfirmString("Ok")
                .setAutoDismiss(true)
                .setLayoutResource(R.layout.dialog_content);

        FSDialog dialog = builder.build();
        dialog.show();
        dialog.setConfirmListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Confirm clicked", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setDiscardListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                Toast.makeText(MainActivity.this, "Discard clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNoTitleBarDialog() {
        FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
                .setNoTitleBar()
                .setAutoDismiss(true)
                .setLayoutResource(R.layout.dialog_content_notitlebar);

        final FSDialog dialog = builder.build();
        dialog.show();

        Button bt_got_it = (Button) (dialog.getContentView()).findViewById(R.id.bt_got_it);
        bt_got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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
                .setAutoDismiss(false)
                .setCancelable(false)
                .setAutoHideKeyboardOnDismiss(true)
                .setLayoutResource(R.layout.dialog_content_input);

        final FSDialog dialog = builder.build();
        dialog.show();

        View contentView = dialog.getContentView();
        final EditText et_input = (EditText) contentView.findViewById(R.id.et_input);


        dialog.setConfirmListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                if (et_input.getText().toString().isEmpty()) {
                    et_input.setError("Empty field");
                    return;
                } else {
                    String toastMessage = "Text from input field:\n" + et_input.getText().toString();
                    Toast.makeText(MainActivity.this,
                            toastMessage,
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }


            }
        });
        dialog.setDiscardListener(new FSDialogButtonClickListener() {
            @Override
            public void OnButtonClick() {
                dialog.dismiss();
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
