package sa.gov.hajj.meetings.viewModels;

/***
 * Helper View Model used for app localization
 */
public class LanguageViewModel {
    private int mNameResource;
    private int mImage;

    public LanguageViewModel(int nameResource, int image) {
        setNameResource(nameResource);
        setImage(image);
    }

    public int getNameResource() {
        return mNameResource;
    }

    private void setNameResource(int nameResource) {
        mNameResource = nameResource;
    }

    public int getImage() {
        return mImage;
    }

    private void setImage(int image) {
        mImage = image;
    }
}
