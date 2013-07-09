/*
 * cn.wanto.dto.bean.ProductDto.java
 * Sep 17, 2012 
 */
package cn.wanto.dto.bean;

import java.util.List;

/**
 * Sep 17, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ProductsDto {
    List<String> icons;
    List<String> titles;
    List<String> texts;
    List<String> names;
    List<String> prices;
    
    
    /**
     * @return the icons
     */
    public List<String> getIcons() {
        return icons;
    }

    /**
     * @param icons
     *            the icons to set
     */
    public void setIcons(List<String> icons) {
        this.icons = icons;
    }

    /**
     * @return the titles
     */
    public List<String> getTitles() {
        return titles;
    }

    /**
     * @param titles
     *            the titles to set
     */
    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    /**
     * @return the texts
     */
    public List<String> getTexts() {
        return texts;
    }

    /**
     * @param texts
     *            the texts to set
     */
    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
    

    /**
     * @return the names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(List<String> names) {
        this.names = names;
    }
    
    /**
     * @return the prices
     */
    public List<String> getPrices() {
        return prices;
    }

    /**
     * @param prices the prices to set
     */
    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public boolean isBlank() {
        return getIcons() == null || getIcons().isEmpty() || getTitles() == null || getTitles().isEmpty()
                || getTexts() == null || getTexts().isEmpty() || getNames() ==null || getNames().isEmpty()
                || getPrices()==null || getPrices().isEmpty();
    }

    public boolean isNotBlank() {
        return !isBlank();
    }
}
