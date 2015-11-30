package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.Pattern;

@Pattern(
    name = "Abstract Factory",
    participants = {
        "AbstractProduct",
        "ConcreteProduct"
    }
)
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
