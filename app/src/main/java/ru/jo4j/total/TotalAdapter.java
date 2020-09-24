package ru.jo4j.total;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.jo4j.total.model.FileModel;
import ru.jo4j.total.model.FileTree;
import ru.jo4j.total.model.ITree;

public class TotalAdapter extends RecyclerView.Adapter<TotalAdapter.TotalViewHolder> {

    private List<FileModel> fileModels;
    Context context;
    private String parentAbsolutePath;
    AppCompatActivity activity;

    public TotalAdapter(List<FileModel> fileModels, Context c) {
        this.fileModels = fileModels;
        context = c;
    }

    public class TotalViewHolder extends RecyclerView.ViewHolder {
        public TotalViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public TotalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.file_item, parent, false);
        return new TotalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TotalAdapter.TotalViewHolder holder, final int position) {
        final FileModel currentFile = fileModels.get(position);
        TextView dateView = holder.itemView.findViewById(R.id.dateView);
        dateView.setText(currentFile.getDate().toString());
        TextView textView = holder.itemView.findViewById(R.id.textView);
        textView.setText("" + currentFile.getName());
        TextView modifiedDateView = holder.itemView.findViewById(R.id.modifiedDateView);
        modifiedDateView.setText(currentFile.getFormattedModificationDate(context));
        ImageView imageView = holder.itemView.findViewById(R.id.imageView);
        if (currentFile.getDirectory()) {
            Picasso.get().load(R.drawable.ic_baseline_folder_24).into(imageView);
        } else {
            Picasso.get().load(R.drawable.ic_insert_drive_file_24).into(imageView);
        }

        View itemLayout = holder.itemView.findViewById(R.id.itemLayout);
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFile.getName().contains(".")) {
                    if ((currentFile.getName().substring(currentFile.getName().lastIndexOf("."))).equals("mp3")) {
                        Log.d("audio", "onClick: " + currentFile.getAbsolutePath());
                        Intent intent = new Intent("audio/mp3", (Uri.parse(currentFile.getAbsolutePath())));
                        activity.startActivity(intent);
                    }
                }
                ITree fileTree = new FileTree();
                parentAbsolutePath = currentFile.getAbsolutePath();
                List<FileModel> list;
                if (((list) = fileTree.getChildrenByParent(currentFile.getAbsolutePath())) != null) {
                    fileModels = list;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return fileModels.size();
    }

    public void onBackPressed() {
        ITree fileTree = new FileTree();
        FileModel parent;
        if ((parent = fileTree.getParentByChild(parentAbsolutePath)) != null) {
            parentAbsolutePath = parent.getAbsolutePath();
            fileModels = fileTree.getChildrenByParent(parent.getAbsolutePath());
            notifyDataSetChanged();
        }
    }

    public String getParentAbsolutePath() {
        return parentAbsolutePath;
    }

    public void setParentAbsolutePath(String parentAbsolutePath) {
        this.parentAbsolutePath = parentAbsolutePath;
    }
}