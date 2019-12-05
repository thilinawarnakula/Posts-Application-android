package example.beyondar.com.postexample.repository.dto.masterdata;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import example.beyondar.com.postexample.model.entity.masterdata.Comments;
import example.beyondar.com.postexample.model.entity.masterdata.Users;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(Users comment);

    @Query("SELECT * FROM Users WHERE userId=:userId")
    Users getUserFromPost(int userId);

    @Query("SELECT * FROM Users WHERE userId=:userId")
    Users getUser(int userId);
}
