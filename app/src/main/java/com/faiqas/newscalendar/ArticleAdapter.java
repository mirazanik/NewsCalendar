package com.faiqas.newscalendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.faiqas.newscalendar.group.GroupRecyclerAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;



public class ArticleAdapter extends GroupRecyclerAdapter<String, Article> {


    private RequestManager mLoader;

    public ArticleAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        LinkedHashMap<String, List<Article>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        map.put("News", create(0));
        //map.put("Weekly", create(1));
        //map.put("Highest comment", create(2));
        titles.add("News");
        //titles.add("Weekly");
        //titles.add("Highest comment");
        resetGroups(map,titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Article item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(item.getTitle());
        h.mTextContent.setText(item.getContent());
        mLoader.load(item.getImgUrl())
                .asBitmap()
                .centerCrop()
                .into(h.mImageView);
    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private ImageView mImageView;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.tv_title);
            mTextContent = itemView.findViewById(R.id.tv_content);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }


    private static Article create(String title, String content, String imgUrl) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImgUrl(imgUrl);
        return article;
    }

    private static List<Article> create(int p) {
        List<Article> list = new ArrayList<>();
        if (p == 0) {
            list.add(create("Earthquake occurred in New Zealand's",
                    "A 5.7-magnitude earthquake occurred in New Zealand's Kermadek Islands, with a focal depth of 10 kilometers",
                    "http://cms-bucket.nosdn.127.net/catchpic/2/27/27e2ce7fd02e6c096e21b1689a8a3fe9.jpg?imageView&thumbnail=550x0"));
            list.add(create("Russia yells for wrongs",
                    "\"It's shockingly bad,\" but Trump is not to blame. Russian Prime Minister Dmitry Medvedev recently said this when talking about Russia-US relations. Russia has recently been repeatedly accused of \"virulent\" by the United States and a series of attacks by Western countries. Some international public opinion believes that Russia has become a \"backup man,\" and Russia itself has publicly criticized the United States. In the mutual pinch between Russia and the United States, the truth seems to have become less important.",
                    "http://cms-bucket.nosdn.127.net/catchpic/c/c8/c8b0685089258b82f3ca1997def78d8d.png?imageView&thumbnail=550x0"));
            list.add(create("Chinese companies' investment in Brazil wins support",
                    "Reference News Network reported on December 4 that the British media said that the port of Aso near Rio de Janeiro was once called \"the highway to China\" by Eke Batista. More than 10 years ago, this disgraced Brazilian former The richest man created this super port. After the commodity boom ended, almost none of his business empire in Brazil survived and went bankrupt in 2014. However, one project that has continued to flourish since then is Port Aso.",
                    "http://cms-bucket.nosdn.127.net/catchpic/8/8b/8ba2d19b7f63efc5cf714960d5edd2c3.jpg?imageView&thumbnail=550x0"));
            list.add(create("US TV reporter suspended for four weeks for misreporting Flynn news",
                    "[Global Network Report] According to a report by the Russian Satellite Network on December 3, ABC TV reporter Brian Russell was temporarily suspended due to a mistake in a news report about the former national security adviser Michael Flynn of the US President.",
                    "http://cms-bucket.nosdn.127.net/5d18566fde70407b9cc3a728822115c320171203133214.jpeg?imageView&thumbnail=550x0"));
            list.add(create("It is expected to be listed in March next year, revealing the new Audi Q5L without spy photos",
                    "With the previous new generation of domestically produced Audi Q5L in the catalog of the Ministry of Industry and Information Technology, the recently exposed test cars have basically been disguised, but the official launch will still have to wait until March 2018. Judging from the latest exposure of the interior, the lengthening of the wheelbase has significantly improved the rear space.",
                    "http://cms-bucket.nosdn.127.net/eda9ca222352470190c4f0d6b9a8c29420171201160854.jpeg?imageView&thumbnail=550x0"));
        } else if (p == 1) {

        } else if (p == 2) {

        }


        return list;
    }
}
