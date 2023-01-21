package com.example.plantiside;

import static com.example.plantiside.R.id.fert1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

public class BuyActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        t1 = findViewById(fert1);
        t2 = findViewById(R.id.f2);
        t3 = findViewById(R.id.f3);
        t4 = findViewById(R.id.f4);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://casadeamor.in/products/tomato-fertilizer-for-home-terrace-outdoor-gardening?variant=32106353131619&currency=INR&utm_medium=product_sync&utm_source=google&utm_content=sag_organic&utm_campaign=sag_organic&cmp_id=19248506599&adg_id=&kwd=&device=c&gclid=Cj0KCQiAt66eBhCnARIsAKf3ZNFNl9XyGd_7p3VI1xqMKTaMO6TDmLBjWw27sTCmnYTo5xSk_ueTB9YaAtDlEALw_wcB";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://agrostar.in/product/upl-saaf-carbendazim-12mancozeb-63wp-250-gms/AGS-CP-140?state=madhya-pradesh&gclid=Cj0KCQiAt66eBhCnARIsAKf3ZNHb5aK-PsCkI5KnJR7OZPRlVKjZ37YMn8-EGH54acZ1IX7RFvW5YUEaAisLEALw_wcB";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://agrostar.in/product/bayer-antracol-propineb-70-wp-250-g/AGS-CP-1022?gclid=Cj0KCQiAt66eBhCnARIsAKf3ZNEcW_FQsrJeuNzggh3t5JmEFIDanqpyifa-wK6HO09IzD6SOIz8_rUaAnq7EALw_wcB";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://kisanshop.in/products/corteva-kocide-2000-fungicide?variant=41443071721677&utm_medium=paid&utm_source=Google_ads&utm_campaign=shopping_ads";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}