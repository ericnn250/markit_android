package com.markit.ecommerce.resources;

import com.markit.ecommerce.R;
import com.markit.ecommerce.models.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Products {
//    public HashMap<String, Product> PRODUCT_MAP = new HashMap<>();
//
//    public Products() {
//        PRODUCT_MAP.put("Phone Case", CELL_PHONE_CASE_1);
//        PRODUCT_MAP.put("Hoody", HOODY_NAVY);
//        PRODUCT_MAP.put("Mug", MUG_11OZ);
//        PRODUCT_MAP.put("T-Shirt", T_SHIRT_WHITE);
//        PRODUCT_MAP.put("Half-Sleeve Shirt", HALF_SLEEVE_GREY);
//        PRODUCT_MAP.put("Snapback", HALF_SLEEVE_GREY);
//        PRODUCT_MAP.put("Tank", TANK_WHITE);
//        PRODUCT_MAP.put("Trucker Hat", TRUCKER_HAT_NAVY);
//    }


        public  HashMap<String, Product> getProducts(){

            return  PRODUCTS;
    }

        public static final Product CELL_PHONE_CASE_1 = new Product("Cell Phone Case 1", R.drawable.cell_phone_case_1, "Phone Case",
                new BigDecimal(10.99), 1515611, R.drawable.cell_phone_case_2,0,0,0,0,0,0,0,0,0);
//        public static final Product CELL_PHONE_CASE_2 = new Product("Cell Phone Case 2", R.drawable.cell_phone_case_2, "Phone Case",
//                new BigDecimal(11.99), 1515612);

        public static final Product HOODY_NAVY = new Product("Navy Hoody", R.drawable.hoody_navy, "Hoody", new BigDecimal(34.99)
                , 7725277, R.drawable.hoody_asphalt, R.drawable.hoody_black, R.drawable.hoody_grey, R.drawable.hoody_purple,0,0,0,0,0,0);
//        public static final Product HOODY_ASPHALT = new Product("Asphalt Hoody", R.drawable.hoody_asphalt, "Hoody",
//                new BigDecimal(34.99), 7725278);
//        public static final Product HOODY_BLACK = new Product("Black Hoody", R.drawable.hoody_black, "Hoody",
//                new BigDecimal(34.99), 7725279);
//        public static final Product HOODY_GREY = new Product("Grey Hoody", R.drawable.hoody_grey, "Hoody",
//                new BigDecimal(34.99), 7725280);
//        public static final Product HOODY_PURPLE = new Product("Purple Hoody", R.drawable.hoody_purple, "Hoody",
//                new BigDecimal(34.99), 7725281);

        public static final Product MUG_11OZ = new Product("Mug (11 oz)", R.drawable.mug_11oz, "Mug",
                new BigDecimal(13.99), 7725282, R.drawable.mug_15oz,0,0,0,0,0,0,0,0,0);
//        public static final Product MUG_15OZ = new Product("Mug (15 oz)", R.drawable.mug_15oz, "Mug",
//                new BigDecimal(14.99), 7725283);

        public static final Product T_SHIRT_WHITE = new Product("White T-Shirt", R.drawable.t_shirt_white, "T-Shirt",
                new BigDecimal(23.99), 2141515, R.drawable.t_shirt_black,R.drawable.t_shirt_grey,R.drawable.t_shirt_navy, R.drawable.t_shirt_red,0,0,0,0,0,0);
//        public static final Product T_SHIRT_BLACK = new Product("Black T-Shirt", R.drawable.t_shirt_black, "T-Shirt",
//                new BigDecimal(23.99), 2141516);
//        public static final Product T_SHIRT_GREY = new Product("Grey T-Shirt", R.drawable.t_shirt_grey, "T-Shirt",
//                new BigDecimal(23.99), 2141517);
//        public static final Product T_SHIRT_NAVY = new Product("Navy T-Shirt", R.drawable.t_shirt_navy, "T-Shirt",
//                new BigDecimal(23.99), 2141518);
//        public static final Product T_SHIRT_RED = new Product("Red T-Shirt", R.drawable.t_shirt_red, "T-Shirt",
//                new BigDecimal(23.99), 2141519);

        public static final Product HALF_SLEEVE_GREY = new Product("Grey Half-Sleeve Shirt", R.drawable.half_sleeve_shirt_grey,
                "Half-Sleeve Shirt", new BigDecimal(28.99), 9704833, R.drawable.half_sleeve_shirt_blue, R.drawable.half_sleeve_shirt_white,R.drawable.half_sleeve_shirt_red,0,0,0,0,0,0,0);
//        public static final Product HALF_SLEEVE_BLUE = new Product("Blue Half-Sleeve Shirt", R.drawable.half_sleeve_shirt_blue,
//                "Half-Sleeve Shirt", new BigDecimal(28.99), 9704834);
//        public static final Product HALF_SLEEVE_WHITE = new Product("White Half-Sleeve Shirt", R.drawable.half_sleeve_shirt_white,
//                "Half-Sleeve Shirt", new BigDecimal(28.99), 9704835);
//        public static final Product HALF_SLEEVE_RED = new Product("Red Half-Sleeve Shirt", R.drawable.half_sleeve_shirt_red,
//                "Half-Sleeve Shirt", new BigDecimal(28.99), 9704836);

        public static final Product SNAPBACK_BLACK = new Product("Black Snapback", R.drawable.snapback_black,
                "Snapback", new BigDecimal(20.99), 9377376, R.drawable.snapback_camo,R.drawable.snapback_grey,R.drawable.snapback_navy,R.drawable.snapback_red,R.drawable.snapback_teal,0,0,0,0,0);
//        public static final Product SNAPBACK_CAMO = new Product("Camo Snapback", R.drawable.snapback_camo,
//                "Snapback", new BigDecimal(20.99), 9377377);
//        public static final Product SNAPBACK_GREY = new Product("Grey Snapback", R.drawable.snapback_grey,
//                "Snapback", new BigDecimal(20.99), 9377378);
//        public static final Product SNAPBACK_NAVY = new Product("Navy Snapback", R.drawable.snapback_navy,
//                "Snapback", new BigDecimal(20.99), 9377379);
//        public static final Product SNAPBACK_RED = new Product("Red Snapback", R.drawable.snapback_red,
//                "Snapback", new BigDecimal(20.99), 9377380);
//        public static final Product SNAPBACK_TEAL = new Product("Teal Snapback", R.drawable.snapback_teal,
//                "Snapback", new BigDecimal(20.99), 9377381);

        public static final Product TANK_WHITE = new Product("White Tank", R.drawable.tank_white, "Tank"
                , new BigDecimal(19.99), 6626622, R.drawable.tank_black,R.drawable.tank_grey, R.drawable.tank_light_blue,R.drawable.tank_navy,0,0,0,0,0,0);
//        public static final Product TANK_BLACK = new Product("Black Tank", R.drawable.tank_black, "Tank"
//                , new BigDecimal(19.99), 6626623);
//        public static final Product TANK_GREY = new Product("Grey Tank", R.drawable.tank_grey, "Tank"
//                , new BigDecimal(19.99), 6626624);
//        public static final Product TANK_LIGHT_BLUE = new Product("Light Blue Tank", R.drawable.tank_light_blue, "Tank"
//                , new BigDecimal(19.99), 6626625);
//        public static final Product TANK_NAVY = new Product("Navy Tank", R.drawable.tank_navy, "Tank"
//                , new BigDecimal(19.99), 6626626);

        public static final Product TRUCKER_HAT_NAVY = new Product("Navy Trucker Hat", R.drawable.trucker_hat_navy,
                "Trucker Hat", new BigDecimal(25.99), 783736, R.drawable.trucker_hat_white,R.drawable.trucker_hat_black, R.drawable.trucker_hat_baige,0,0,0,0,0,0,0);
//        public static final Product TRUCKER_HAT_WHITE = new Product("White Trucker Hat", R.drawable.trucker_hat_white,
//                "Trucker Hat", new BigDecimal(25.99), 783737);
//        public static final Product TRUCKER_HAT_BLACK = new Product("Black Trucker Hat", R.drawable.trucker_hat_black,
//                "Trucker Hat", new BigDecimal(25.99), 783738);
//        public static final Product TRUCKER_HAT_BAIGE = new Product("Baige Trucker Hat", R.drawable.trucker_hat_baige,
//                "Trucker Hat", new BigDecimal(25.99), 783739);

//        public static final Product[] T_SHIRTS = {T_SHIRT_WHITE, T_SHIRT_BLACK, T_SHIRT_GREY, T_SHIRT_NAVY, T_SHIRT_RED};
//
//        public static final Product[] HALF_SLEEVES = {HALF_SLEEVE_GREY, HALF_SLEEVE_BLUE, HALF_SLEEVE_RED, HALF_SLEEVE_WHITE};
//
//        public static final Product[] TRUCKER_HATS = {TRUCKER_HAT_NAVY, TRUCKER_HAT_BLACK, TRUCKER_HAT_WHITE, TRUCKER_HAT_BAIGE};
//
//        public static final Product[] SNAPBACKS = {SNAPBACK_NAVY, SNAPBACK_BLACK, SNAPBACK_CAMO, SNAPBACK_GREY, SNAPBACK_RED, SNAPBACK_TEAL};
//
//        public static final Product[] PHONE_CASES = {CELL_PHONE_CASE_1, CELL_PHONE_CASE_2};
//
//        public static final Product[] TANKS = {TANK_BLACK, TANK_GREY, TANK_LIGHT_BLUE, TANK_NAVY, TANK_WHITE};
//
//        public static final Product[] HOODIES = {HOODY_ASPHALT, HOODY_BLACK, HOODY_GREY, HOODY_NAVY, HOODY_PURPLE};
//
//        public static final Product[] MUGS = {MUG_11OZ, MUG_15OZ};

        public static final Product[] FEATURED_PRODUCTS = {CELL_PHONE_CASE_1, HOODY_NAVY, MUG_11OZ, T_SHIRT_WHITE,
                HALF_SLEEVE_GREY, SNAPBACK_BLACK, TRUCKER_HAT_NAVY, TRUCKER_HAT_NAVY};


//        private static final HashMap<String, Product> PRODUCTS;
//        static
//        {
//            PRODUCTS = new HashMap<String, Product>();
//            Products products = new Products();
//            Iterator it = products.PRODUCT_MAP.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry pair = (Map.Entry)it.next();
//                System.out.println(pair.getKey() + " = " + pair.getValue());
//                for(Product product : (Product[])pair.getValue()){
//                    PRODUCTS.put(String.valueOf(product.getSerial_number()), product);
//                }
//                it.remove(); // avoids a ConcurrentModificationException
//            }
//        }
private static final HashMap<String, Product> PRODUCTS;
    static
    {
        PRODUCTS = new HashMap<String, Product>();

        for (int i=0;i<FEATURED_PRODUCTS.length;i++){
            PRODUCTS.put(String.valueOf(FEATURED_PRODUCTS[i].getSerial_number()),FEATURED_PRODUCTS[i]);
        }
    }

    }

