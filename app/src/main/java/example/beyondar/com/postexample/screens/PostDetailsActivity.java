package example.beyondar.com.postexample.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.service.internal.ServiceRepository;

public class PostDetailsActivity extends AppCompatActivity {

    @BindView(R.id.txt_post_title)
    TextView txtPostTitle;
    @BindView(R.id.txt_post_user)
    TextView txtPostUser;
    @BindView(R.id.txt_post_no_of_comments)
    TextView txtPostNoOfComments;
    @BindView(R.id.txt_post_body)
    TextView txtPostBody;
    @BindView(R.id.img_post)
    ImageView imgPost;
    @BindView(R.id.txt_post_no_data)
    TextView txtPostNoData;

    int postID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
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
        showFields();
        Posts selectedPost = ServiceRepository.getInstance().
                getMasterDataService().getPostData(postID);

        int userId = selectedPost.getUserId();
        Users postUserData = ServiceRepository.getInstance().
                getMasterDataService().getPostUserData(userId);

        int postCommentCount = ServiceRepository.getInstance().
                getMasterDataService().getPostCommentCount(postID);

        txtPostTitle.setText(selectedPost.getTitle());
        txtPostUser.setText("Post by " + postUserData.getUserName());
        txtPostNoOfComments.setText("Number of comments " + String.valueOf(postCommentCount));
        txtPostBody.setText(selectedPost.getBody());
    }

    private void showFields() {
        txtPostTitle.setVisibility(View.VISIBLE);
        txtPostUser.setVisibility(View.VISIBLE);
        txtPostNoOfComments.setVisibility(View.VISIBLE);
        txtPostBody.setVisibility(View.VISIBLE);
        imgPost.setVisibility(View.VISIBLE);
        txtPostNoData.setVisibility(View.GONE);
    }

    private void setUpEmptyView() {
        hideFields();
    }

    private void hideFields() {
        txtPostTitle.setVisibility(View.GONE);
        txtPostUser.setVisibility(View.GONE);
        txtPostNoOfComments.setVisibility(View.GONE);
        txtPostBody.setVisibility(View.GONE);
        imgPost.setVisibility(View.GONE);
        txtPostNoData.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.back_arrow)
    public void onViewClicked() {
        Intent myIntent = new Intent(PostDetailsActivity.this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @OnClick(R.id.txt_post_user)
    public void onViewClickedUser() {
        Intent myIntent = new Intent(PostDetailsActivity.this, UserDetailsActivity.class);
        myIntent.putExtra("postID", postID);
        startActivity(myIntent);
        finish();
    }
}
