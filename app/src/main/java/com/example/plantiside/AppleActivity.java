package com.example.plantiside;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.plantiside.ml.AppleUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class AppleActivity extends AppCompatActivity {
    TextView result,clickHere, diseases, cure;
    ImageView imageview1;
    Button buy;
    int imagesize=224;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);
        result = findViewById(R.id.diseasesInfo);
        imageview1 = findViewById(R.id.uploadImage);
        diseases = findViewById((R.id.diseases));
        cure = findViewById(R.id.cure);
        buy = findViewById(R.id.buy);


        clickHere=findViewById(R.id.cureInfo);

        clickHere.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
        diseases.setVisibility((View.GONE));
        cure.setVisibility(View.GONE);
        buy.setVisibility(View.GONE);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AppleActivity.this, BuyActivity.class);
                startActivity(i);
            }
        });


        imageview1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    }else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},100);
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK)  {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(),image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image,dimension,dimension);
            imageview1.setImageBitmap(image);

            clickHere.setVisibility(View.VISIBLE);
            result.setVisibility(View.VISIBLE);
            diseases.setVisibility((View.VISIBLE));
            cure.setVisibility(View.VISIBLE);
            buy.setVisibility(View.VISIBLE);

            image = Bitmap.createScaledBitmap(image,imagesize,imagesize,false);
            classifyImage(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void classifyImage(Bitmap image) {
        try {
            AppleUnquant model = AppleUnquant.newInstance(getApplicationContext());


            TensorBuffer inputFeatures = TensorBuffer.createFixedSize(new int[]{1,224,224,3}, DataType.FLOAT32);
            ByteBuffer byteBuffer;
            byteBuffer = ByteBuffer.allocateDirect(4 * imagesize * 3 * imagesize);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValue = new int[imagesize * imagesize];
            image.getPixels(intValue,0,image.getWidth(),0,0,image.getWidth(),image.getHeight());

            int pixel=0;
            for (int i=0; i< imagesize;i++){
                for (int j=0;j< imagesize;j++){
                    int val = intValue[pixel++];
                    byteBuffer.putFloat(((val >> 16 ) & 0xFF) *(1.f / 255.f));
                    byteBuffer.putFloat(((val >> 6 ) & 0xFF) *(1.f / 255.f));
                    byteBuffer.putFloat((val  & 0xFF) *(1.f / 255.f));
                }
            }
            inputFeatures.loadBuffer(byteBuffer);

            AppleUnquant.Outputs outputs =model.process(inputFeatures);
            TensorBuffer outputFeaturesO = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence = outputFeaturesO.getFloatArray();

            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0;i< confidence.length;i++){
                if(confidence[i] > maxConfidence){
                    maxConfidence = confidence[i];
                    maxPos=i;
                }
            }
            String[] classes = {"Apple___Apple_scab","Apple___Black_rot","Apple___Cedar_apple_rust","Apple___healthy"};
            diseases.setText(classes[maxPos]);
            if(classes[maxPos].equals(classes[0])){
                result.setText("Apple scab is a fungal disease caused by Venturia inaequalis that affects the leaves, fruit, and twigs of apple and crabapple trees. Symptoms include brown or black leaf spots and defoliation. The disease can be controlled through the use of fungicides, proper pruning and sanitation, and by planting resistant varieties of apple trees.");
                clickHere.setText("Cure" +
                        "\nThere are several fungicides that can be used to control apple scab disease. The most common fungicides used include:\n" +
                        "\n" +
                        "Chlorothalonil: a broad-spectrum fungicide that can be used to control a variety of diseases, including apple scab.\n" +
                        "\n" +
                        "Myclobutanil: a fungicide that is effective against apple scab and other diseases.\n" +
                        "\n" +
                        "Sulfur: a fungicide that is often used as a natural or organic option for apple scab control.\n" +
                        "\n" +
                        "Copper: a fungicide that is often used as an organic option for apple scab control.\n" +
                        "\n" +
                        "Propiconazole : a fungicide that is effective against apple scab and other diseases.\n" +
                        "\n" +
                        "Difenoconazole: a fungicide that is effective against apple scab and other diseases.\n" +
                        "\n" +
                        "Boscalid: a fungicide that is effective against apple scab and other diseases.\n" +
                        "\n" +
                        "It's important to note that these fungicides should be used according to the label instructions and in combination with cultural control methods, such as proper sanitation and pruning, to achieve the best results. It's also important to alternate the fungicides to avoid resistance development in the fungus.");


            }

            else if(classes[maxPos].equals(classes[1])){
                result.setText("Apple black rot is a fungal disease caused by the fungus Botryosphaeria obtusa, which affects apple and crabapple trees. The fungus infects the fruit, twigs, and branches of the tree, causing cankers, fruit rot, and twig dieback. The disease is most active in warm and humid weather.");
                clickHere.setText("Cure" +
                        "There are several fungicides that can be used to control apple black rot disease caused by the fungus Botryosphaeria obtusa. The most common fungicides used include:\n" +
                        "\n" +
                        "Captan: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "Thiophanate-methyl: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "Propiconazole: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "Difenoconazole: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "Tebuconazole: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "Myclobutanil: a fungicide that is effective against apple black rot and other diseases.\n" +
                        "\n" +
                        "It's important to note that these fungicides should be used according to the label instructions and in combination with cultural control methods, such as proper sanitation and pruning, to achieve the best results. Also, it's important to alternate the fungicides to avoid resistance development in the fungus.\n" +
                        "\n" +
                        "Additionally, it's important to consult with a local extension office or a professional to determine the best fungicide to use and the appropriate timing and frequency of application, based on weather conditions, apple variety, and other factors.");
            }

            else if(classes[maxPos].equals(classes[2])){

                result.setText("Apple rust is a fungal disease caused by the fungus Gymnosporangium juniperi-virginianae which affects apple and crabapple trees. The fungus needs two hosts to complete its life cycle, an apple tree and a juniper or cedar tree.");
                clickHere.setText("Cure" +
                        "There are several fungicides that can be used to control apple rust disease caused by the fungus Gymnosporangium juniperi-virginianae. The most common fungicides used include:\n" +
                        "\n" +
                        "Chlorothalonil: a broad-spectrum fungicide that can be used to control a variety of diseases, including apple rust.\n" +
                        "\n" +
                        "Myclobutanil: a fungicide that is effective against apple rust and other diseases.\n" +
                        "\n" +
                        "Sulfur: a fungicide that is often used as a natural or organic option for apple rust control.\n" +
                        "\n" +
                        "Copper: a fungicide that is often used as an organic option for apple rust control.\n" +
                        "\n" +
                        "Propiconazole : a fungicide that is effective against apple rust and other diseases.\n" +
                        "\n" +
                        "Difenoconazole: a fungicide that is effective against apple rust and other diseases.\n" +
                        "\n" +
                        "Boscalid: a fungicide that is effective against apple rust and other diseases.\n" +
                        "\n");
            }
            else if(classes[maxPos].equals(classes[3])){
                cure.setVisibility(View.GONE);
                clickHere.setVisibility(View.GONE);
                result.setText("Your plant is Healthy!!!!!!!!!!!");
                clickHere.setText("");
            }

            int finalMaxPos = maxPos;
            result.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/search?q="+ classes[finalMaxPos])));
                }
            });
            model.close();
        }catch (IOException e ){
        }
    }
}