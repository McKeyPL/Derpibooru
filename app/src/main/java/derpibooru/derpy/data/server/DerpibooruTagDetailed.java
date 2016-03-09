package derpibooru.derpy.data.server;

import android.os.Parcel;
import android.os.Parcelable;

public class DerpibooruTagDetailed extends DerpibooruTag {
    private String mDescription;
    private String mSpoilerUrl;

    public DerpibooruTagDetailed(int id, int imageCount, String name,
                                 String description, String spoilerUrl) {
        super(id, imageCount, name);
        mDescription = description;
        mSpoilerUrl = spoilerUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getSpoilerUrl() {
        return mSpoilerUrl;
    }

    protected DerpibooruTagDetailed(Parcel in) {
        super(in);
        mDescription = in.readString();
        mSpoilerUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(mDescription);
        dest.writeString(mSpoilerUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DerpibooruTagDetailed> CREATOR = new Parcelable.Creator<DerpibooruTagDetailed>() {
        @Override
        public DerpibooruTagDetailed createFromParcel(Parcel in) {
            return new DerpibooruTagDetailed(in);
        }

        @Override
        public DerpibooruTagDetailed[] newArray(int size) {
            return new DerpibooruTagDetailed[size];
        }
    };
}
