package example.beyondar.com.postexample.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import example.beyondar.com.postexample.R;

public class PostsListAdapterListHolder extends RecyclerView.ViewHolder {

    TextView textViewPostTitle;
    TextView textViewPostBody;
    TextView textViewPostUser;
    ImageView imageViewPicture;

    public PostsListAdapterListHolder(View itemView) {
        super(itemView);

        textViewPostTitle = itemView.findViewById(R.id.text_view_post_title);
        textViewPostBody = itemView.findViewById(R.id.text_view_post_body);
        textViewPostUser = itemView.findViewById(R.id.text_view_post_user);
        imageViewPicture = itemView.findViewById(R.id.image_view_picture);
    }
}
