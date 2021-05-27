package com.markit.ecommerce;

import com.markit.ecommerce.models.Product;
import com.markit.ecommerce.models.User;

public interface IMainActivity {
    void inflateViewProfileFragment(User user);

    void onProductSelected(Product message);

    void onBackPressed();
    //void oncartIncrease(int number);
}
