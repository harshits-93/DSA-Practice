package com.dsapractice;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class String_Activity extends AppCompatActivity {

    private static final String TAG = "String_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_string);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String compression = compression("aaabbbbbcc");
        String expansion = expansion("a3b5c2a2");
        Log.d(TAG, "compression: " + compression);
        Log.d(TAG, "expansion: " + expansion);

    }


    public String compression(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(str.charAt(0));
        int i = 1;

        while (i < str.length()) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(str.charAt(i));
                count = 1;
            } else {
                count++;
            }
            i++;
        }

        if (count > 1) {
            sb.append(count);
        }


        return sb.toString();
    }

    public String expansion(String str) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i += 2) {
            char ch = str.charAt(i);
            int count = Character.getNumericValue(str.charAt(i + 1));

            while (count > 0) {
                sb.append(ch);
                count--;
            }
        }

        return sb.toString();
    }
}