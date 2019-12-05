package example.beyondar.com.postexample.constants;

import org.apache.commons.lang3.StringUtils;

public class AppConstants {

    public static String postImgURL = "https://api.adorable.io/avatars/60/";
    public static String postImgDefaultURL = "https://api.adorable.io/avatars/60/defult/";

    public enum Status {
        ACT, INA;

        public static Status resolveStatus(String statusStr) {
            Status matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = Status.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum MasterData {
        MASTER_DATA;

        public static Status resolveData(String statusStr) {
            Status matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = Status.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }
}
