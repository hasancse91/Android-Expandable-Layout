
package com.hellohasan.expandablelayoutcode.Model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ArticleModel implements Parcelable
{

    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("article")
    @Expose
    private String article;
    public final static Creator<ArticleModel> CREATOR = new Creator<ArticleModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ArticleModel createFromParcel(Parcel in) {
            ArticleModel instance = new ArticleModel();
            instance.headline = ((String) in.readValue((String.class.getClassLoader())));
            instance.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.authorName = ((String) in.readValue((String.class.getClassLoader())));
            instance.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.article = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ArticleModel[] newArray(int size) {
            return (new ArticleModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The headline
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * 
     * @param headline
     *     The headline
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 
     * @param authorName
     *     The author_name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 
     * @return
     *     The publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * 
     * @param publishedDate
     *     The published_date
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * 
     * @return
     *     The article
     */
    public String getArticle() {
        return article;
    }

    /**
     * 
     * @param article
     *     The article
     */
    public void setArticle(String article) {
        this.article = article;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(headline);
        dest.writeValue(imageUrl);
        dest.writeValue(authorName);
        dest.writeValue(publishedDate);
        dest.writeValue(article);
    }

    public int describeContents() {
        return  0;
    }

}
