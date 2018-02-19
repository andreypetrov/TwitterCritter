package com.petrovdevelopment.twittercritter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2018-02-17.
 * Coding is fun!
 */

public class Page {
    public String title;
    public String imageUrl;

    public Page(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public static List<Page> createDummyPages() {
        List<Page> pages = new ArrayList<>();
        pages.add(new Page("First page title", "http://markinternational.info/data/out/574/223958523-cool-background-images.jpg"));
        pages.add(new Page("Second page title", "https://thumb1.shutterstock.com/display_pic_with_logo/2892448/342092249/stock-vector-cool-comic-book-bubble-text-pop-art-retro-style-342092249.jpg"));
        pages.add(new Page("unusual third page", "https://res.cloudinary.com/teepublic/image/private/s--L7cQUwKE--/t_Preview/b_rgb:484849,c_limit,f_jpg,h_630,q_90,w_630/v1475206534/production/designs/707490_1.jpg"));
        return pages;
    }
}
