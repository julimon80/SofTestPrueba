package com.demoblaze.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class HomeUI {


    public static Target BTN_PRODUCTO(int producto) {
        return Target.the("Select " + producto)
                .located(By.xpath("//div[@id='tbodyid']/div[" + producto + "]"));
    }

    public static Target TXT_PRECIO(int producto) {
        return Target.the("Select " + producto)
                .located(By.xpath("//div[@id='tbodyid']//div[" + producto + "]//h5"));
    }


}
