package example.beyondar.com.postexample.service.internal;

public class ServiceRepository {

    private static ServiceRepository instance;

    private MasterDataService masterDataService;

    private ServiceRepository() {
        this.masterDataService = new MasterDataService();
    }

    public static ServiceRepository getInstance() {
        if(instance == null) {
            instance = new ServiceRepository();
        }

        return instance;
    }

    public MasterDataService getMasterDataService() {
        return masterDataService;
    }
}
