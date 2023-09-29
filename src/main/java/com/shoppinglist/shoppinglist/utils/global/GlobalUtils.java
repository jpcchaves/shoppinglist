package com.shoppinglist.shoppinglist.utils.global;

import com.shoppinglist.shoppinglist.payload.dto.ApiMessageResponse;
import org.springframework.stereotype.Component;

@Component
public class GlobalUtils {
    public ApiMessageResponse generateApiResponse(String message) {
        return new ApiMessageResponse(message);
    }
}
