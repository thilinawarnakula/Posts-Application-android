package example.beyondar.com.postexample.listeners;

public interface ApiResultListener<T> {
    public void onResultReceived(T results);
    public void onRequestFailed();
}