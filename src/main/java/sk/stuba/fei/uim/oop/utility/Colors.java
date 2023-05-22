package sk.stuba.fei.uim.oop.utility;

import lombok.Getter;

import java.awt.*;

public enum Colors {
    GREEN(Color.GREEN,"Zelena",1),
    BLUE(Color.BLUE,"Modra",2),
    RED(Color.RED,"Červena",3);

    @Getter
    private Color color;
    @Getter
    private String translateName;
    @Getter
    private int index;

    Colors(Color color, String name, int index) {
        this.color = color;
        this.translateName = name;
        this.index = index;
    }
}
