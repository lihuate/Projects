package com.evilkingmedia.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import com.evilkingmedia.R;
import com.evilkingmedia.demand.WebViewActivity;
import com.evilkingmedia.model.MoviesModel;

public class BindListSeries3SeasonAdapter extends RecyclerView.Adapter<BindListSeries3SeasonAdapter.myview> {
    private List<MoviesModel> movielistFiltered;
    private List<MoviesModel> moviesList;
    Context context;
    private ProgressDialog mProgressDialog;
    String videoPath;
    private int itemposition;
    BindListAdapter adapter;


    public class myview extends RecyclerView.ViewHolder {

        private CardView card_view;
        private ImageView imgcontent;
        private TextView txtMovieTitle, txtMovieRating, txtMovieYear, txtMovieDuration;
        View view1, view2;

        public myview(View view) {
            super(view);
            card_view = view.findViewById(R.id.card_view);
            imgcontent = view.findViewById(R.id.imgcontent);
            txtMovieTitle = view.findViewById(R.id.txtMovieTitle);
            view1 = view.findViewById(R.id.view1);
            view2 = view.findViewById(R.id.view2);
            txtMovieRating = view.findViewById(R.id.txtMovieRating);
            txtMovieYear = view.findViewById(R.id.txtMovieYear);
            txtMovieDuration = view.findViewById(R.id.txtMovieDuration);
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            txtMovieRating.setVisibility(View.GONE);
            txtMovieYear.setVisibility(View.GONE);
            txtMovieDuration.setVisibility(View.GONE);

        }
    }

    public BindListSeries3SeasonAdapter(List<MoviesModel> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
        this.movielistFiltered = moviesList;
    }

    @NonNull
    @Override
    public BindListSeries3SeasonAdapter.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_list_series3, parent, false);

        return new myview(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BindListSeries3SeasonAdapter.myview holder, final int position) {

        final MoviesModel movie = moviesList.get(position);

        if (movie.getImage() == "") {
            holder.imgcontent.setImageResource(R.color.colorWhite);
            //Picasso.with(context).load(R.drawable.ic_image).into(holder.imgcontent);
        } else {
            Picasso.get().load(movie.getImage()).into(holder.imgcontent);
        }

        holder.txtMovieTitle.setText(movie.getTitle());
       /* holder.txtMovieRating.setText(movie.getRating());
        holder.txtMovieYear.setText(movie.getYear());
        holder.txtMovieDuration.setText(movie.getDuration());*/

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent webIntent = new Intent(context, MusicWebViewActivity.class);
                webIntent.putExtra("url", "https://speedvideo.net/embed-nalqx7svhsmq-607x360.html");
                context.startActivity(webIntent);*/
                itemposition = position;
                new prepareMovieData().execute();

            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private class prepareMovieData extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {


                Document doc = Jsoup.connect(moviesList.get(itemposition).getUrl()).timeout(10000).maxBodySize(0).get();

                Elements data = doc.select("div[class=container-fluid]").select("div[class=row]").select("div[class=col-md-8]").select("div[class=vital]").select("div[class=video1]");
                String url = data.select("div[class=video-container]").select("iframe").attr("src");

                videoPath=url;

              /*  Elements em = data.tagName("li").select("em");

                for(int i=0;i<em.size();i++)
                {

                    if(em.get(i).select("a").size()>0)
                    {
                        videoPath= em.get(i).select("a").attr("href");
                        break;
                    }
                }
*/

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent webIntent = new Intent(context, WebViewActivity.class);
            webIntent.putExtra("url", videoPath);
            context.startActivity(webIntent);
           /* Intent i = new Intent(context, SeriesActivityCatServer3.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("episode", (Serializable) moviesList);
            i.putExtras(bundle);
            context.startActivity(i);*/
        }
    }

}






