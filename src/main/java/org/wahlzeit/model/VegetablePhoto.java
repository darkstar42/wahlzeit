package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass(index = true)
public class VegetablePhoto extends Photo {
    protected String scientificName = "Cucurbita";

    public VegetablePhoto() {
        super();
    }

    public VegetablePhoto(PhotoId myId) {
        super(myId);
    }

    /**
     * @methodtype get
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * @methodtype set
     */
    public void setScientificName(String name) {
        this.scientificName = name;
    }
}
