package example.beyondar.com.postexample.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.api.ApiClient;
import example.beyondar.com.postexample.api.ApiInterface;
import example.beyondar.com.postexample.application.App;
import example.beyondar.com.postexample.model.dto.PostsDTO;
import example.beyondar.com.postexample.model.dto.UserDTO;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.service.internal.ServiceRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    @BindView(R.id.txt_add_post_user)
    TextView txtAddPostUser;
    @BindView(R.id.edit_txt_add_post_title)
    EditText editTxtAddPostTitle;
    @BindView(R.id.edit_txt_add_post_body)
    EditText editTxtAddPostBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);

        setupView();

    }

    private void setupView() {

        int userID = 1;
        Users userData = ServiceRepository.getInstance().getMasterDataService().getUserData(userID);
        txtAddPostUser.setText("Post by - " + userData.getName());
    }

    @OnClick(R.id.back_arrow)
    public void onViewClicked() {
        backToMainActivity();
    }

    private void backToMainActivity() {
        Intent myIntent = new Intent(AddPostActivity.this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick(R.id.add_post)
    public void onViewAddPostClicked() {
        long userID = 1;
        String postTitle = editTxtAddPostTitle.getText().toString();
        String postBody = editTxtAddPostBody.getText().toString();

        if(!postTitle.equals("") && !postBody.equals("")){
            addPost(postTitle,postBody,userID,true);
        }else{
            Toast.makeText(AddPostActivity.this, getResources().
                            getString(R.string.add_post_warning),
                    Toast.LENGTH_LONG).show();
        }

    }

    private void addPost(String postTitle, String postBody, long userID,boolean showDefaultLoader) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PostsDTO> call = apiService.addPost(postTitle,postBody,userID);

        if (showDefaultLoader) {
            App.showProgressHood(AddPostActivity.this);
        }

        call.enqueue(new Callback<PostsDTO>() {
            @Override
            public void onResponse(Call<PostsDTO> call, Response<PostsDTO> response) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
                PostsDTO resultsItem = response.body();
                Posts newPost = new Posts();
                newPost.setId(resultsItem.getId());
                newPost.setTitle(resultsItem.getTitle());
                newPost.setBody(resultsItem.getBody());
                newPost.setUserId(resultsItem.getUserId());
                insertToPostDb(newPost);
            }

            @Override
            public void onFailure(Call<PostsDTO> call, Throwable t) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
            }
        });
    }

    private void insertToPostDb(Posts newPost) {
        ServiceRepository.getInstance().
                getMasterDataService().insertPost(newPost);
        Toast.makeText(AddPostActivity.this, getResources().
                        getString(R.string.new_post_added),
                Toast.LENGTH_LONG).show();

        //clearData();
        backToMainActivity();
    }

    private void clearData() {
        editTxtAddPostTitle.setText("");
        editTxtAddPostBody.setText("");
    }
}
