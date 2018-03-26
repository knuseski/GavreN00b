package com.knuseski.gavrenoob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.knuseski.gavrenoob.app.MyApp;
import com.knuseski.gavrenoob.entity.UserResponse;
import com.knuseski.gavrenoob.volley.VolleySingleton;

public class MainActivity extends AppCompatActivity implements Response.Listener<String> {

    private Gson gson = new Gson();
    private ProgressBar progressBar;

    private TextView tvName;
    private TextView tvNickName;
    private ImageView ivImage;

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvName = findViewById(R.id.tvName);
        tvNickName = findViewById(R.id.tvNickName);
        ivImage = findViewById(R.id.ivImage);
        Button btnGetUser = findViewById(R.id.btnGetUser);
        btnNext = findViewById(R.id.btnNext);

        StringRequest getUser = new StringRequest(Request.Method.GET, MyApp.GET_USER, this, Throwable::printStackTrace);
        btnGetUser.setOnClickListener(view -> {
            view.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            VolleySingleton.getInstance().addToRequestQueue(getUser);
        });

        btnNext.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(this, SecondActivity.class));
        });
    }

    @Override
    public void onResponse(String response) {
        progressBar.setVisibility(View.INVISIBLE);
        UserResponse userResponse = gson.fromJson(response, UserResponse.class);
        if (userResponse != null && userResponse.getUser() != null) {
            changeUI(userResponse.getUser());
        }
    }

    private void changeUI(UserResponse.User user) {
        btnNext.setEnabled(true);
        tvName.setText(user.getName());
        tvNickName.setText(user.getNickName());

        VolleySingleton.getInstance().getImageLoader().get(user.getImgUrl(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                ivImage.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
}
