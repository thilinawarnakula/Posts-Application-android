package example.beyondar.com.postexample.service.internal;

import java.util.List;

import example.beyondar.com.postexample.application.App;
import example.beyondar.com.postexample.constants.AppConstants;
import example.beyondar.com.postexample.model.dto.PostsDTO;
import example.beyondar.com.postexample.model.entity.masterdata.Comments;
import example.beyondar.com.postexample.model.entity.masterdata.MasterData;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;

public class MasterDataService {

    public List<MasterData> getMasterData() {
        return App.getInstance().getDatabseInstance().masterDataDao().
                fetchMasterData(AppConstants.Status.ACT.toString());
    }

    public long insertMasterData(MasterData masterData){
        return  App.getInstance().getDatabseInstance().masterDataDao().insertMasterData(masterData);
    }

    public long insertPost(Posts posts){
        long newPost = App.getInstance().getDatabseInstance().postDao().insertPost(posts);
        return newPost;
    }

    public long insertComment(Comments comment){
        long newComment = App.getInstance().getDatabseInstance().commentDao().insertComment(comment);
        return newComment;
    }

    public long insertUser(Users user){
        long newUser = App.getInstance().getDatabseInstance().userDao().insertUser(user);
        return newUser;
    }

    public List<Posts> getPostsData() {
        return App.getInstance().getDatabseInstance().postDao().
                fetchPostDataData();
    }

    public Users getPostUserData(int userId){
        return App.getInstance().getDatabseInstance().userDao().
                getUserFromPost(userId);
    }

    public Posts getPostData(int postId){
        return App.getInstance().getDatabseInstance().postDao().
                getUserFromPost(postId);
    }

    public int getPostCommentCount(int postId){
        return App.getInstance().getDatabseInstance().commentDao().
                getCommentsCount(postId);
    }

}
