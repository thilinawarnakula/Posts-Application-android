package example.beyondar.com.postexample.repository.dto.masterdata;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;


@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPost(Posts posts);

    @Query("SELECT p.* " +
            "FROM Posts p " )
    List<Posts> fetchPostDataData();

    @Query("SELECT * FROM Posts WHERE id=:postId")
    Posts getUserFromPost(int postId);

    @Query("SELECT p.* " +
            "FROM Posts p " +
            "WHERE p.title LIKE :title "
    )
    List<Posts> getPostFromTitle(String title);
}
