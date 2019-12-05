package example.beyondar.com.postexample.model.entity.masterdata;

import javax.annotation.Nonnull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {

    @Nonnull
    @PrimaryKey
    private Integer userID;

    private String userName;

    private String name;

    private String email;

    private String address;

    private String lat;

    private String lng;

    private String webSite;

    private String phone;

    public Users() {
    }

    @Nonnull
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(@Nonnull Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
