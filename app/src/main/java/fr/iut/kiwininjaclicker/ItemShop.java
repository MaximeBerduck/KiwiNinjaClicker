package fr.iut.kiwininjaclicker;

import android.app.Activity;

public class ItemShop extends Activity {
    private String nomItem;
    private Float gainAmelioration;
    private Float prixAmelioration;
    private Float augmentation;

    public ItemShop(String nomItem, Float gainAmelioration, Float prixAmelioration, Float augmentation) {
        this.nomItem = nomItem;
        this.gainAmelioration = gainAmelioration;
        this.prixAmelioration = prixAmelioration;
        this.augmentation = augmentation;
    }

    public void augmenterAmelioration(){
        this.prixAmelioration = this.prixAmelioration * this.augmentation;
        this.gainAmelioration = this.prixAmelioration * this.augmentation;
    }

    //Getters and Setters
    public String getNomItem() {
        return nomItem;
    }

    public void setNomItem(String nomItem) {
        this.nomItem = nomItem;
    }

    public Float getGainAmelioration() {
        return gainAmelioration;
    }

    public void setGainAmelioration(Float gainAmelioration) {
        this.gainAmelioration = gainAmelioration;
    }

    public Float getPrixAmelioration() {
        return prixAmelioration;
    }

    public void setPrixAmelioration(Float prixAmelioration) {
        this.prixAmelioration = prixAmelioration;
    }

    public Float getAugmentation() {
        return augmentation;
    }

    public void setAugmentationPrix(Float augmentation) {
        this.augmentation = augmentation;
    }
}
