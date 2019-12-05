package example.beyondar.com.postexample.model.entity.masterdata;

import javax.annotation.Nonnull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MasterData {

    @Nonnull
    @PrimaryKey
    private Integer masterDataID;

    private String masterDataName;

    private String status;

    public MasterData() {
    }

    @Nonnull
    public Integer getMasterDataID() {
        return masterDataID;
    }

    public void setMasterDataID(@Nonnull Integer masterDataID) {
        this.masterDataID = masterDataID;
    }

    public String getMasterDataName() {
        return masterDataName;
    }

    public void setMasterDataName(String masterDataName) {
        this.masterDataName = masterDataName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
