package com.premankoding.mcd3.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModelResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    private MovieModelResult(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        title = in.readString();
        posterPath = in.readString();
        originalLanguage = in.readString();
        overview = in.readString();
        releaseDate = in.readString();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public static final Creator<MovieModelResult> CREATOR = new Creator<MovieModelResult>() {
        @Override
        public MovieModelResult createFromParcel(Parcel in) {
            return new MovieModelResult(in);
        }

        @Override
        public MovieModelResult[] newArray(int size) {
            return new MovieModelResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (voteAverage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(voteAverage);
        }
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeString(originalLanguage);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);


    }
}
