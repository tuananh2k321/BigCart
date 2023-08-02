package com.example.bigcart.view.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bigcart.R
import com.example.bigcart.model.Comment
import com.example.bigcart.model.User

class CommentAdapter(context: Context, listComment: ArrayList<Comment>):
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>()
{
    private var context: Context
    private var listComment: ArrayList<Comment>

    // list img
    private lateinit var imgList: Array<String>
    private lateinit var commentImgAdapter: CommentImgAdapter

    init {
        this.context = context
        this.listComment = listComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.item_user_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listComment.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentItem = listComment[position]
        var user = currentItem.getUserId()
        val imageUrl: String =
            "https://antimatter.vn/wp-content/uploads/2022/10/hinh-anh-gai-xinh-de-thuong.jpg"
        Glide.with(context).load(imageUrl).fitCenter().into(holder.imgAvatar)
        holder.tvContent.text = currentItem.getContent()
        holder.tvCreateAt.text = currentItem.getCreateAt()

        when(currentItem.getRating()) {
            1 -> {
                holder.imgStar1.setImageResource(R.drawable.icon_baseline_star_24)

                // star no color
                holder.imgStar2.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar2.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar3.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar3.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar4.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar4.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar5.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar5.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));
            }
            2 -> {
                holder.imgStar1.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar2.setImageResource(R.drawable.icon_baseline_star_24)

                // star no color
                holder.imgStar3.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar3.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar4.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar4.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar5.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar5.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));
            }
            3 -> {
                holder.imgStar1.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar2.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar3.setImageResource(R.drawable.icon_baseline_star_24)

                // star no color
                holder.imgStar4.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar4.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));

                holder.imgStar5.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar5.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));
            }
            4 -> {
                holder.imgStar1.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar2.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar3.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar4.setImageResource(R.drawable.icon_baseline_star_24)

                // star no color
                holder.imgStar5.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar5.setColorFilter(ContextCompat.getColor(context ,R.color.detailColor));
            }
            5 -> {
                holder.imgStar1.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar2.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar3.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar4.setImageResource(R.drawable.icon_baseline_star_24)
                holder.imgStar5.setImageResource(R.drawable.icon_baseline_star_24)
            }
        }



        // list img comment
        imgList = currentItem.getListImage()
        var layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rcvListImg.layoutManager = layoutManager
        commentImgAdapter = CommentImgAdapter(context, imgList)
        holder.rcvListImg.adapter = commentImgAdapter
    }



    class CommentViewHolder(itemVIew: View): RecyclerView.ViewHolder(itemVIew){
        var imgAvatar: ImageView = itemVIew.findViewById(R.id.img_avatar)
        var tvNameUser: TextView = itemView.findViewById(R.id.tv_name_user)
        var tvCreateAt: TextView = itemView.findViewById(R.id.tv_create_at)
        var tvContent: TextView = itemView.findViewById(R.id.tv_comment)
        var rcvListImg: RecyclerView = itemVIew.findViewById(R.id.rcv_list_img)
        // rating
        var imgStar1: ImageView = itemVIew.findViewById(R.id.img_star1)
        var imgStar2: ImageView = itemVIew.findViewById(R.id.img_star2)
        var imgStar3: ImageView = itemVIew.findViewById(R.id.img_star3)
        var imgStar4: ImageView = itemVIew.findViewById(R.id.img_star4)
        var imgStar5: ImageView = itemVIew.findViewById(R.id.img_star5)
    }
}