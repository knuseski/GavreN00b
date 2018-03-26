package com.knuseski.gavrenoob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.knuseski.gavrenoob.app.MyApp;
import com.knuseski.gavrenoob.entity.UserResponse;
import com.knuseski.gavrenoob.services.UserService;
import com.knuseski.gavrenoob.volley.UsersAdapter;
import com.knuseski.gavrenoob.volley.VolleySingleton;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements Response.Listener<String>, UsersAdapter.ClickListener {

    private ProgressBar progressBar;


    private UsersAdapter usersAdapter = new UsersAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        StringRequest getUsers = new StringRequest(Request.Method.GET, MyApp.GET_USERS,
                this, Throwable::printStackTrace);

        progressBar = findViewById(R.id.progressBar);
        usersAdapter.setClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.rvUsers);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnGetUsers = findViewById(R.id.btnGetUsers);
        btnGetUsers.setOnClickListener(view -> {
            view.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            VolleySingleton.getInstance().addToRequestQueue(getUsers);
        });
    }

    @Override
    public void onResponse(String response) {
        // poso ne vrakja list praem dummy service sto ce vrati list
        progressBar.setVisibility(View.INVISIBLE);
        List<UserResponse.User> userList = UserService.getUsers();
        if (!userList.isEmpty()) {
            usersAdapter.setData(userList);
        }
    }

    @Override
    public void onClick(UserResponse.User item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
    }
}
