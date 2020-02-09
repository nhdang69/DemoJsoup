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

class NewChapterAdapter extends RecyclerView.Adapter<NewChapterAdapter.NewChapterHolder> {

    private Context context;

    NewChapterAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewChapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_new_chapter_item, parent, false);
        return new NewChapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewChapterHolder holder, int position) {
        Chapter chapter = new Chapter();
        chapter.setImgChapter("https://c1.hako.re/lightnovel/covers/s3601-213a353c-1dba-4ff4-83be-4b1c9f3e4481-m.jpg");
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
        return 18;
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
