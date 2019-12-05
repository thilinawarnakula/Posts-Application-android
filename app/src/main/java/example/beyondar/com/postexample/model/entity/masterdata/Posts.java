package example.beyondar.com.postexample.model.entity.masterdata;

import javax.annotation.Nonnull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Posts {

    @Nonnull
    @PrimaryKey
    private int id;

    private String title;

    private String body;

    private int userId;

    public Posts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
