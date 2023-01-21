package com.example.plantiside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView corn,apple,peach,strawberry,grape,pepper_bell,potato,tomato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        corn = (CardView) findViewById(R.id.corn);
        apple = (CardView) findViewById(R.id.apple);
        peach = (CardView) findViewById(R.id.peach);
        strawberry = (CardView) findViewById(R.id.strawberry);
        grape = (CardView) findViewById(R.id.grape);
        pepper_bell = (CardView) findViewById(R.id.pepper_bell);
        potato = (CardView) findViewById(R.id.potato);
        tomato = (CardView) findViewById(R.id.tomato);

        corn.setOnClickListener((View.OnClickListener) this);
        apple.setOnClickListener((View.OnClickListener) this);
        peach.setOnClickListener((View.OnClickListener) this);
        strawberry.setOnClickListener((View.OnClickListener) this);
        grape.setOnClickListener((View.OnClickListener) this);
        pepper_bell.setOnClickListener((View.OnClickListener) this);
        potato.setOnClickListener((View.OnClickListener) this);
        tomato.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId())
        {
            case R.id.apple:
                intent = new Intent(MainActivity.this, AppleActivity.class);
                startActivity(intent);
                break;
//          case R.id.corn:
//                intent = new Intent(this,CornActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.grape:
//                intent = new Intent(this,GrapeActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.pepper_bell:
//                intent = new Intent(this, PepperBellActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.potato:
//                intent = new Intent(this,PotatoActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.peach:
//                intent = new Intent(this,PeachActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.strawberry:
//                intent = new Intent(this,StrawberryActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.tomato:
//                intent = new Intent(this, TomatoActivity.class);
//                startActivity(intent);
//                break;  case R.id.corn:
//                intent = new Intent(this,CornActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.grape:
//                intent = new Intent(this,GrapeActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.pepper_bell:
//                intent = new Intent(this, PepperBellActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.potato:
//                intent = new Intent(this,PotatoActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.peach:
//                intent = new Intent(this,PeachActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.strawberry:
//                intent = new Intent(this,StrawberryActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.tomato:
//                intent = new Intent(this, TomatoActivity.class);
//                startActivity(intent);
//                break;
        }
    }
}