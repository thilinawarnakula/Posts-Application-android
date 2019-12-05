package example.beyondar.com.postexample.api;

import java.util.List;

import example.beyondar.com.postexample.model.dto.CommentsDTO;
import example.beyondar.com.postexample.model.dto.PostsDTO;
import example.beyondar.com.postexample.model.dto.UserDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Call<List<PostsDTO>> getPostsList();

    @GET("/comments")
    Call<List<CommentsDTO>> getCommentsList();

    @GET("/users")
    Call<List<UserDTO>> getUsersList();

}