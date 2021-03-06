package com.example.hauthenticationproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Button button_signup;
    //list of images
    public int[] lstimages = {R.drawable.farmeri, R.drawable.helpi};
    public String[] lsttitle = {"\t\t\t\t\t\t\t\tLogin As Farmer\n\nSwipe to Login As Professionals,\nResearcher or Students","Login As Professionals,\nResearcher or Students"};
    public SliderAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lstimages.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutSlide = (LinearLayout) view.findViewById(R.id.slidelayout);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        button_signup = (Button) view.findViewById(R.id.slidesignupbutton);
        TextView textView= (TextView) view.findViewById(R.id.slidetext);
        imageView.setImageResource(lstimages[position]);
        textView.setText(lsttitle[position]);

        if(position==0) {
            button_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    v.getContext().startActivity(i);

                }
            });
        }
        else
        {
            button_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, NavigationActivity.class);
                    v.getContext().startActivity(i);
                }
            });
        }

        container.addView(view);
        return view;
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }
}
