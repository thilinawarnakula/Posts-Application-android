package example.beyondar.com.postexample.model.entity.masterdata;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comments {

    @Nonnull
    @PrimaryKey
    private int id;

    private String name;

    private int postId;

    private String body;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
