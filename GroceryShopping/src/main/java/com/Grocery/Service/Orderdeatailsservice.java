package com.Grocery.Service;

import com.Grocery.model.Orderdeatails;

public interface Orderdeatailsservice {

	  Orderdeatails findByOrderId(String OrderId);
}
