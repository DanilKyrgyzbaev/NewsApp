
package com.mad_devs.modelTechnology;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Enclosure {

    @SerializedName("link")
    @Expose
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
