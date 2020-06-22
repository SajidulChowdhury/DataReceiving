package com.example.datareceiving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(Intent.ACTION_SEND.equals(action) && type != null){
            if("text/plain".equals(type)){
                // handle text being sent
                handleSendText(intent);
            }
            else if(type.startsWith("image/")){
                // handle single image being sent
                handleSendImage(intent);
            }
        }
        else if(Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null){
            if(type.startsWith("image/")){
                // handle multiple images being
                handleSendMultipleImages(intent);
            }
        }
        else{
            // handle other intents
        }
    }

    void handleSendText(Intent intent){
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if(sharedText != null){
            // update UI to reflect text being shared
            TextView textView = (TextView)findViewById(R.id.updateText);
            textView.setText("Shared Text: " +  sharedText);
        }
    }

    void handleSendImage(Intent intent){
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            //update UI to reflect image being shared
            ImageView mImageView = findViewById(R.id.image_view);
            mImageView.setImageURI(imageUri);
            mImageView.setRotation(0);
        }
    }

    void handleSendMultipleImages(Intent intent){
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris != null) {
            //update UI to reflect multiple images being shared
        }
    }
}
