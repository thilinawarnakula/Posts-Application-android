package example.beyondar.com.postexample.repository.dto.masterdata;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import example.beyondar.com.postexample.model.entity.masterdata.MasterData;

@Dao
public interface MasterDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long  insertMasterData(MasterData masterData);

    @Query("SELECT * FROM MasterData WHERE status=:status ORDER BY masterDataID LIMIT 1")
    List<MasterData> fetchMasterData(String status);
}
