package example.beyondar.com.postexample.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.constants.AppConstants;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.service.internal.ServiceRepository;

public class UserDetailsActivity extends AppCompatActivity {

    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_user_full_name)
    TextView txtUserFullName;
    @BindView(R.id.txt_user_email)
    TextView txtUserEmail;
    @BindView(R.id.txt_user_address)
    TextView txtUserAddress;
    @BindView(R.id.txt_user_web)
    TextView txtUserWeb;
    @BindView(R.id.txt_user_tel)
    TextView txtUserTel;

    int postID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        Intent mIntent = getIntent();
        postID = mIntent.getIntExtra("postID", 0);

        if (postID > 0) {
            setUpPostViews();
        } else {
            setUpEmptyView();
        }
    }

    private void setUpPostViews() {

        Posts selectedPost = ServiceRepository.getInstance().
                getMasterDataService().getPostData(postID);

        int userId = selectedPost.getUserId();
        Users postUserData = ServiceRepository.getInstance().
                getMasterDataService().getPostUserData(userId);

        txtUserName.setText(postUserData.getUserName());
        txtUserFullName.setText(postUserData.getName());
        txtUserEmail.setText(postUserData.getEmail());
        txtUserAddress.setText(postUserData.getAddress());
        txtUserWeb.setText(postUserData.getWebSite());
        txtUserTel.setText(postUserData.getPhone());
        Glide.with(UserDetailsActivity.this)
                .load(AppConstants.postImgURL + postUserData.getEmail() + ".png")
                .placeholder(R.drawable.user)
                .into(imgUser);
    }

    private void setUpEmptyView() {
        txtUserName.setText("-");
        txtUserFullName.setText("-");
        txtUserEmail.setText("-");
        txtUserAddress.setText("-");
        txtUserWeb.setText("-");
        txtUserTel.setText("-");
        Glide.with(UserDetailsActivity.this)
                .load(AppConstants.postImgDefaultURL + ".png")
                .placeholder(R.drawable.user)
                .into(imgUser);
    }

    @OnClick(R.id.back_arrow)
    public void onViewClicked() {
        Intent myIntent = new Intent(UserDetailsActivity.this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        // your code.
    }
}
