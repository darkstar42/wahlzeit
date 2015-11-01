package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class VegetablePhotoFactory extends PhotoFactory {
    private static final Logger log = Logger.getLogger(VegetablePhotoFactory.class.getName());

    private static VegetablePhotoFactory instance = null;

    protected VegetablePhotoFactory() {
        // do nothing
    }

    public static synchronized VegetablePhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic VegetablePhotoFactory").toString());
            setInstance(new VegetablePhotoFactory());
        }

        return instance;
    }

    protected static synchronized void setInstance(VegetablePhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize VegetablePhotoFactory twice");
        }

        instance = photoFactory;
    }

    @Override
    public VegetablePhoto createPhoto() {
        return new VegetablePhoto();
    }

    @Override
    public VegetablePhoto createPhoto(PhotoId id) {
        return new VegetablePhoto(id);
    }
}
