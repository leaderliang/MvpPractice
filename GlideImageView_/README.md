Glide 4.x Placeholder（占位图）不会被处理成圆角

解决这个问题一种方法是让UI直接切圆角图，另一种就是通过thumbnail方法对占位图再做圆角处理,具体实现如下：

```

    public static void loadRoundImg(ImageView imageView, String url, @DrawableRes int placeholderId,
                                    @DrawableRes int errorId, float radius) {

        Glide.with(imageView.getContext()).load(url)
                .apply(new RequestOptions().placeholder(placeholderId).error(errorId).centerCrop()
                        .transform(new GlideRoundTransform(imageView.getContext(), radius)))
                .thumbnail(loadTransform(imageView.getContext(),placeholderId,radius))
                .thumbnail(loadTransform(imageView.getContext(),errorId,radius))
                .into(imageView);
    }
    
     private static  RequestBuilder<Drawable> loadTransform(Context context, @DrawableRes int placeholderId, float radius) {

        return Glide.with(context)
                .load(placeholderId)
                .apply(new RequestOptions().centerCrop()
                        .transform(new GlideRoundTransform(context, radius)));

    }
```