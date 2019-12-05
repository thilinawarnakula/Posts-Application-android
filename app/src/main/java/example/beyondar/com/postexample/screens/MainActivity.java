package example.beyondar.com.postexample.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.adapter.PostsListAdapter;
import example.beyondar.com.postexample.adapter.RecyclerTouchListener;
import example.beyondar.com.postexample.api.ApiClient;
import example.beyondar.com.postexample.api.ApiInterface;
import example.beyondar.com.postexample.application.App;
import example.beyondar.com.postexample.constants.AppConstants;
import example.beyondar.com.postexample.model.dto.AddressDTO;
import example.beyondar.com.postexample.model.dto.CommentsDTO;
import example.beyondar.com.postexample.model.dto.GeoDTO;
import example.beyondar.com.postexample.model.dto.PostsDTO;
import example.beyondar.com.postexample.model.dto.UserDTO;
import example.beyondar.com.postexample.model.entity.masterdata.Comments;
import example.beyondar.com.postexample.model.entity.masterdata.MasterData;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.service.internal.ServiceRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostsListAdapter.PostUserRecoardChangeListner{

    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.post_list_recycle_view)
    RecyclerView postListRecycleView;

    private List<Posts> postsList;
    private List<Comments> commentsList;
    private List<Users> usersList;

    private List<Posts> postsDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<MasterData> masterData = ServiceRepository.getInstance().getMasterDataService().getMasterData();
        if (masterData.size() < 1) {
            setUpData();
        }
        setUpViews();

        postListRecycleView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                postListRecycleView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Posts resultsItem = postsDataList.get(position);
                int postID = resultsItem.getId();
                Intent myIntent = new Intent(MainActivity.this, PostDetailsActivity.class);
                myIntent.putExtra("postID", postID);
                startActivity(myIntent);
                finish();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchData();
    }

    private void fetchData() {
        List<Posts> postsData = ServiceRepository.getInstance().getMasterDataService().getPostsData();
        postsDataList = new ArrayList<>();
        postsDataList.addAll(postsData);
        setListRecyclerView();
    }

    private void setListRecyclerView() {

        PostsListAdapter postsListAdapter = new PostsListAdapter(MainActivity.this, postsDataList,this);
        postListRecycleView.setAdapter(postsListAdapter);

        LinearLayoutManager linearLayoutManagerEstatesList = new LinearLayoutManager(MainActivity.this);
        linearLayoutManagerEstatesList.setOrientation(LinearLayoutManager.VERTICAL);
        postListRecycleView.setLayoutManager(linearLayoutManagerEstatesList);

    }

    private void setUpViews() {
        searchView.setQueryHint(getResources().getString(R.string.search_post));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                loadSearchedPost(s);
                return true;
            }
        });
    }

    private void loadSearchedPost(String postName) {
    }

    private void setUpData() {
        downloadMasterDataPosts(true);
        //downloadMasterDataComments(true);
        //downloadMasterDataUsers(true);
        //setMusterDataCompletion();
    }

    private void downloadMasterDataPosts(final boolean showDefaultLoader) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<PostsDTO>> call = apiService.getPostsList();

        if (showDefaultLoader) {
            App.showProgressHood(MainActivity.this);
        }

        call.enqueue(new Callback<List<PostsDTO>>() {
            @Override
            public void onResponse(Call<List<PostsDTO>> call, Response<List<PostsDTO>> response) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
                List<PostsDTO> postsDTOS = response.body();
                postsList = new ArrayList<>();
                for (PostsDTO resultsItem : postsDTOS) {

                    Posts newPost = new Posts();
                    newPost.setId(resultsItem.getId());
                    newPost.setTitle(resultsItem.getTitle());
                    newPost.setBody(resultsItem.getBody());
                    newPost.setUserId(resultsItem.getUserId());
                    postsList.add(newPost);
                }
                insertToPostDb(postsList);

            }

            @Override
            public void onFailure(Call<List<PostsDTO>> call, Throwable t) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
            }
        });
    }

    private void insertToPostDb(List<Posts> results) {
        for (Posts postsItem : results) {
            ServiceRepository.getInstance().
                    getMasterDataService().insertPost(postsItem);
        }
        Toast.makeText(MainActivity.this, getResources().getString(R.string.posts_download),
                Toast.LENGTH_LONG).show();

        downloadMasterDataComments(true);
    }

    private void downloadMasterDataComments(final boolean showDefaultLoader) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<CommentsDTO>> call = apiService.getCommentsList();

        if (showDefaultLoader) {
            App.showProgressHood(MainActivity.this);
        }

        call.enqueue(new Callback<List<CommentsDTO>>() {
            @Override
            public void onResponse(Call<List<CommentsDTO>> call, Response<List<CommentsDTO>> response) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
                List<CommentsDTO> commentsDTOS = response.body();
                commentsList = new ArrayList<>();
                for (CommentsDTO resultsItem : commentsDTOS) {

                    Comments newComment = new Comments();
                    newComment.setId(resultsItem.getId());
                    newComment.setName(resultsItem.getName());
                    newComment.setPostId(resultsItem.getPostId());
                    newComment.setBody(resultsItem.getBody());
                    newComment.setEmail(resultsItem.getEmail());
                    commentsList.add(newComment);
                }
                insertToCommentsDb(commentsList);
            }

            @Override
            public void onFailure(Call<List<CommentsDTO>> call, Throwable t) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
            }
        });
    }

    private void insertToCommentsDb(List<Comments> results) {
        for (Comments commentItem : results) {
            ServiceRepository.getInstance().
                    getMasterDataService().insertComment(commentItem);
        }

        Toast.makeText(MainActivity.this, getResources().getString(R.string.comments_download),
                Toast.LENGTH_LONG).show();

        downloadMasterDataUsers(true);
    }

    private void downloadMasterDataUsers(final boolean showDefaultLoader) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<UserDTO>> call = apiService.getUsersList();

        if (showDefaultLoader) {
            App.showProgressHood(MainActivity.this);
        }

        call.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
                List<UserDTO> userDTOS = response.body();

                usersList = new ArrayList<>();
                for (UserDTO resultsItem : userDTOS) {

                    Users newUser = new Users();

                    newUser.setUserID(resultsItem.getId());
                    newUser.setUserName(resultsItem.getUsername());
                    newUser.setEmail(resultsItem.getEmail());
                    newUser.setName(resultsItem.getName());
                    newUser.setWebSite(resultsItem.getWebsite());
                    newUser.setPhone(resultsItem.getPhone());
                    AddressDTO addressDTO = resultsItem.getAddressDTO();
                    if (addressDTO != null) {
                        newUser.setAddress(addressDTO.getCity());
                    }
                    if (addressDTO.getGeoDTO() != null) {
                        GeoDTO geoDTO = addressDTO.getGeoDTO();
                        if (geoDTO.getLat() != null) {
                            newUser.setLat(geoDTO.getLat());
                        }
                        if (geoDTO.getLng() != null) {
                            newUser.setLng(geoDTO.getLng());
                        }
                    }

                    usersList.add(newUser);
                }
                insertToUsersDb(usersList);
            }

            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                if (showDefaultLoader) {
                    App.hideProgressHood();
                }
            }
        });

    }

    private void insertToUsersDb(List<Users> results) {
        for (Users userItem : results) {
            ServiceRepository.getInstance().
                    getMasterDataService().insertUser(userItem);
        }

        Toast.makeText(MainActivity.this, getResources().getString(R.string.users_download),
                Toast.LENGTH_LONG).show();

        setMusterDataCompletion();
        fetchData();
    }

    private void setMusterDataCompletion() {
        MasterData masterData = new MasterData();
        masterData.setMasterDataName(AppConstants.MasterData.MASTER_DATA.toString());
        masterData.setStatus(AppConstants.Status.ACT.toString());
        ServiceRepository.getInstance().getMasterDataService().insertMasterData(masterData);
    }

    @Override
    public void onUserRecordClicked(int position) {
        Posts resultsItem = postsDataList.get(position);
        int postID = resultsItem.getId();
        Intent myIntent = new Intent(MainActivity.this, UserDetailsActivity.class);
        myIntent.putExtra("postID", postID);
        startActivity(myIntent);
        finish();
    }
}
