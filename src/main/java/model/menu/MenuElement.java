package model.menu;

public class MenuElement {

    private String href;
    private String caption;
    private boolean isSelected;

    public MenuElement(String caption, String href) {

        this.caption = caption;
        this.href = href;
        this.isSelected = false;

    }

    public String getHref() {
        return href;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
