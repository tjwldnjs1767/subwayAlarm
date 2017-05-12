package com.arlib.floatingsearchviewdemo.data;

/**
 * Copyright (C) 2015 Ari C.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// TODO: 2017-05-12 change hex → stationName / name  → LINE_NUM / rgb 삭제
public class Wrapper implements Parcelable {

    @SerializedName("STATION_NM")
    @Expose
    private String stationNum;
    @SerializedName("LINE_NUM")
    @Expose
    private String lineNum;


    private Wrapper(Parcel in) {
        stationNum = in.readString();
        lineNum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stationNum);
        dest.writeString(lineNum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @return
     * The hex
     */
    public String getHex() {
        return stationNum;
    }

    /**
     *
     * @param hex
     * The hex
     */
    public void setHex(String hex) {
        this.stationNum = hex;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return lineNum;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.lineNum = name;
    }


    public static final Creator<Wrapper> CREATOR = new Creator<Wrapper>() {
        @Override
        public Wrapper createFromParcel(Parcel in) {
            return new Wrapper(in);
        }

        @Override
        public Wrapper[] newArray(int size) {
            return new Wrapper[size];
        }
    };
}