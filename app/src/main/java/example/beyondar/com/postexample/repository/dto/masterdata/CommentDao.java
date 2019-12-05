package example.beyondar.com.postexample.repository.dto.masterdata;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import example.beyondar.com.postexample.model.entity.masterdata.Comments;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertComment(Comments comment);

    @Query("SELECT COUNT(id) FROM Comments WHERE postId=:postId")
    int getCommentsCount(int postId);
}
