package com.ygg.pr1_okunevve;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /* making the button actually do smth with a listener
        in this case, it checks input field's text and passes the appropriate "response" for the popup
        */
        final Button verifyButton = findViewById(R.id.button_verify);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputField = findViewById(R.id.edittext_prompt);
                String popupText = (inputField.getText().toString().equalsIgnoreCase("make a lotta money drive a fancy car"))?
                        "Lario" : ">:(";

                processPopup(v, popupText);
            }
        });
    }

    public void processPopup(View view, String text) {
        //most of the following code was roughly copied from the Inters Net, because i uhh am not too experienced in this :)

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);

        //some window settings + the window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        //get into the window and modify le text
        TextView popup_text = popupView.findViewById(R.id.popup_fromButton);
        popup_text.setText(text);
        popup_text.setBackgroundColor((text.equalsIgnoreCase("lario")? Color.parseColor("#00cc00") :
                Color.parseColor("#cc0000")));

        //show the popup
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //make it go away on any tap
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}