package example.beyondar.com.postexample.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import example.beyondar.com.postexample.model.entity.masterdata.Comments;
import example.beyondar.com.postexample.model.entity.masterdata.MasterData;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.repository.dto.masterdata.CommentDao;
import example.beyondar.com.postexample.repository.dto.masterdata.MasterDataDao;
import example.beyondar.com.postexample.repository.dto.masterdata.PostDao;
import example.beyondar.com.postexample.repository.dto.masterdata.UserDao;

@Database(entities = {
        MasterData.class,
        Posts.class,
        Comments.class,
        Users.class
}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract MasterDataDao masterDataDao();

    public abstract PostDao postDao();

    public abstract CommentDao commentDao();

    public abstract UserDao userDao();
}
