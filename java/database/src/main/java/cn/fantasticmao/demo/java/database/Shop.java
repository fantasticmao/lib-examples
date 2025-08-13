package cn.fantasticmao.demo.java.database;

/**
 * Shop
 *
 * @author fantasticmao
 * @since 2025-08-13
 */
public class Shop {
    private String shopName;
    private Point location;

    public static final Shop ORIENTAL_PEARL_TOWER = new Shop("东方明珠广播电视塔", new Point(121.499718, 31.239703));
    public static final Shop MACDONALD_YU_GARDEN = new Shop("麦当劳(豫园店)", new Point(121.490369, 31.22825));
    public static final Shop MACDONALD_YUE_HUI = new Shop("麦当劳(黄浦悦荟广场店)", new Point(121.483837, 31.23661));
    public static final Shop MACDONALD_XIN_MEI = new Shop("麦当劳(新梅联合广场店)", new Point(121.515827, 31.229333));
    public static final Shop MACDONALD_HUAI_HAI = new Shop("麦当劳(淮海百盛店)", new Point(121.459424, 31.217457));
    public static final Shop MACDONALD_MO_LING = new Shop("麦当劳(秣陵路店)", new Point(121.454167, 31.247346));

    public Shop() {
    }

    public Shop(String shopName, Point location) {
        this.shopName = shopName;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Shop{" +
            "shopName='" + shopName + '\'' +
            ", location=" + location +
            '}';
    }

    // getter and setter

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}
