package com.intern.ambassador.data.entity;

import java.util.Arrays;
import java.util.List;

public enum EduGroup {
    TOP("상의", Arrays.asList("POLO", "SLEEVE", "JUMPERS", "SLEEVELESS", "HOOD", "SHIRTS", "NEAT")),
    OUTER("아우터", Arrays.asList("BLOUSON", "SINGLE_COAT", "LEATHER")),
    PANTS("바지", Arrays.asList("DENIM", "COTTON", "SLACKS", "JOGGER")),
    SNEAKERS("스니커즈", Arrays.asList("CANVAS", "FASHION")),
    SHOES("신발", Arrays.asList("LOAFER", "SANDAL", "SLIPPER", "HEEL", "BOOTS")),
    BAG("가방", Arrays.asList("BACKPACK", "CROSS", "POUCH", "SHOULDER", "CARRIER", "ECO")),
    HEADWEAR("모자", Arrays.asList("CAP", "BUCKET", "BERET", "BEANIE")),
    SOCKS("양말", Arrays.asList("SOCKS", "STOCKINGS")),
    UNDERWEAR("속옷", Arrays.asList("HOMEWARE", "WOMAN", "MAN")),
    ACCESSORY("액세서리", Arrays.asList("MASK", "SCARF", "MUFFLER", "BELT", "NECKTIE"));

    private String title;
    private List<String> eduList;

    EduGroup(String title, List<String> eduList) {
        this.title = title;
        this.eduList = eduList;
    }
}
