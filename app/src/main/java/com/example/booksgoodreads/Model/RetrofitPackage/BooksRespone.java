package com.example.booksgoodreads.Model.RetrofitPackage;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksRespone {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    public BooksRespone(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    //Item class
    public class Item {

        @SerializedName("volumeInfo")
        @Expose
        private VolumeInfo volumeInfo;

        public Item(VolumeInfo volumeInfo) {
            this.volumeInfo = volumeInfo;
        }

        public VolumeInfo getVolumeInfo() {
            return volumeInfo;
        }

        public class VolumeInfo{

            @SerializedName("title")
            @Expose
            private String title;

            @SerializedName("authors")
            @Expose
            private List<String> authors;

            @SerializedName("categories")
            @Expose
            private List<String> categories;

            @SerializedName("imageLinks")
            @Expose
            private ImageLinks imageLinks;

            @SerializedName("averageRating")
            @Expose
            private String averageRating;

            public VolumeInfo(String title, List<String> authors, List<String> categories, ImageLinks imageLinks, String averageRating) {
                this.title = title;
                this.authors = authors;
                this.categories = categories;
                this.imageLinks = imageLinks;
                this.averageRating = averageRating;
            }

            public String getTitle() {
                return title;
            }

            public List<String> getAuthors() {
                return authors;
            }

            public List<String> getCategories() {
                return categories;
            }

            public ImageLinks getImageLinks() {
                return imageLinks;
            }

            public String getAverageRating() {
                return averageRating;
            }

            @Override
            public String toString() {
                return "VolumeInfo{" +
                        "title='" + title + '\'' +
                        ", authors=" + authors +
                        ", categories=" + categories +
                        ", imageLinks=" + imageLinks +
                        ", averageRating='" + averageRating + '\'' +
                        '}';
            }

            public class ImageLinks {

                @SerializedName("thumbnail")
                @Expose
                private String thumbnail;

                public ImageLinks(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public String getThumbnail() {
                    return thumbnail;
                }
            }
        }
    }
}




