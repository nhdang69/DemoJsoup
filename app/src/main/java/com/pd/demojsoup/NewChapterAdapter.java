package com.pd.demojsoup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

class NewChapterAdapter extends RecyclerView.Adapter<NewChapterAdapter.NewChapterHolder> {

    private Context context;
    private List<Chapter> chapters;

    NewChapterAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public NewChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_new_chapter_item, parent, false);
        return new NewChapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewChapterHolder holder, int position) {
        Chapter chapter = chapters.get(position);
        chapter.setNameChapter("Chương 66: Một buổi sáng yên bình");
        chapter.setVolChapter("VOL III: HỌC VIỆN ANH HÙNG");
        chapter.setNameManga("Maou Gakuin No Futekigousha");

        Glide.with(context)
                .load(chapter.getImgChapter()).centerCrop().into(holder.imgChapter);

        holder.tvChapter.setText(chapter.getNameChapter());
        holder.tvNameManga.setText(chapter.getNameManga());
        holder.tvVolChapter.setText(chapter.getVolChapter());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class NewChapterHolder extends RecyclerView.ViewHolder {
        ImageView imgChapter;
        TextView tvNameManga, tvVolChapter, tvChapter;

        NewChapterHolder(@NonNull View itemView) {
            super(itemView);
            imgChapter = itemView.findViewById(R.id.imgChapter);
            tvNameManga = itemView.findViewById(R.id.tvNameManga);
            tvVolChapter = itemView.findViewById(R.id.tvVolChapter);
            tvChapter = itemView.findViewById(R.id.tvChapter);

        }
    }
}
