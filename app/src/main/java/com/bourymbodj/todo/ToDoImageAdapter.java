package com.bourymbodj.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bourymbodj on 16-07-22.
 */
public class ToDoImageAdapter  extends ArrayAdapter<ToDo>{

        Context context;
        int layoutResourceId;
        ArrayList<ToDo> data = new ArrayList<ToDo>();

        public ToDoImageAdapter(Context context, int resource) {
            super(context, resource);
        }

        public ToDoImageAdapter(Context context, int layoutResourceId, ArrayList<ToDo> data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row= convertView;
            ImageHolder holder= null;
            if (row==null)
            {
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                row= inflater.inflate(layoutResourceId,parent, false)  ;
                holder= new ImageHolder();
                holder.title = (TextView)row.findViewById(R.id.title);
                holder.description = (TextView)row.findViewById(R.id.description);
                holder.date = (TextView)row.findViewById(R.id.date);
                row.setTag(holder);
            }
            else
            {
                holder = (ImageHolder)row.getTag();
            }
            ToDo picture = data.get(position);
            holder.title.setText(picture.getTitle());
            holder.description.setText(picture.getDescription());
            holder.date.setText(picture .getDescription());
//convert byte to bitmap take from contact class
           // byte[] outImage=picture.getStatus();
            //byteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
           // Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            if (picture.getStatus()==1) {
                holder.status.setImageResource(R.drawable.done);
            } else
                holder.status.setImageResource(R.drawable.todo);
            return row;
        }
        static class ImageHolder
        {

            TextView title;
            TextView description;
            ImageView status;
            TextView date;

        }
    }






