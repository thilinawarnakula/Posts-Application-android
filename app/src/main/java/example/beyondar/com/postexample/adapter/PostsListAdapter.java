package example.beyondar.com.postexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import example.beyondar.com.postexample.R;
import example.beyondar.com.postexample.constants.AppConstants;
import example.beyondar.com.postexample.model.entity.masterdata.Posts;
import example.beyondar.com.postexample.model.entity.masterdata.Users;
import example.beyondar.com.postexample.service.internal.ServiceRepository;

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapterListHolder> {

    private Context context;
    private List<Posts> resultsItems;
    private PostUserRecoardChangeListner postUserRecoardChangeListner;

    public PostsListAdapter(Context context, List<Posts> resultsItems,
                            PostUserRecoardChangeListner postUserRecoardChangeListner) {
        this.context = context;
        this.postUserRecoardChangeListner = postUserRecoardChangeListner;
        this.resultsItems = resultsItems;
    }

    @Override
    public PostsListAdapterListHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item,parent,false);
        PostsListAdapterListHolder holder=new PostsListAdapterListHolder(v);
        return holder;
    }

    public interface PostUserRecoardChangeListner {
        void onUserRecordClicked(int position);
    }

    @Override
    public void onBindViewHolder(PostsListAdapterListHolder holder, final int position) {
        Posts post = resultsItems.get(position);
        int userId = post.getUserId();
        Users postUserData = ServiceRepository.getInstance().getMasterDataService().getPostUserData(userId);
        holder.textViewPostTitle.setText(post.getTitle());
        holder.textViewPostBody.setText(post.getBody());
        holder.textViewPostUser.setText("Post by "+ postUserData.getUserName());
        Glide.with(context)
                .load(AppConstants.postImgURL + postUserData.getEmail() + ".png")
                .placeholder(R.drawable.document)
                .into(holder.imageViewPicture);

        holder.textViewPostUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postUserRecoardChangeListner != null) {
                    postUserRecoardChangeListner.onUserRecordClicked(position);
                }
            }
        });

        holder.imageViewPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postUserRecoardChangeListner != null) {
                    postUserRecoardChangeListner.onUserRecordClicked(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

}
