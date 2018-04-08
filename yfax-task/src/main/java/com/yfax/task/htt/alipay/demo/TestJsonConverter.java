package com.yfax.task.htt.alipay.demo;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.parser.json.JsonConverter;

public class TestJsonConverter {

    public static void main(String[] args) throws Exception {
        case2();
    }

    public static void case2() {

        String testString1 = "{\"_a\":{\"list_data\":[{\"id\":1000,\"user_info_list\":[{\"out_id\":2000}],\"list_user_info\":[{\"out_id\":2000}],\"point\":[{\"point_value\":3000}]}],\"success\":true}}";
        String testString2 = "{\"_a\":{\"list_data\":{\"data\":[{\"id\":1000,\"user_info_list\":{\"user_info\":[{\"out_id\":2000}]},\"point\":[[{\"point_value\":3000}]]}]},\"success\":true}}";

        JsonConverter converter = new JsonConverter();
        try {
            TestResponse a1 = (TestResponse) converter.toResponse(testString1, TestResponse.class);
            System.out.println(a1);

            System.out.println("===================================");

            TestResponse a2 = (TestResponse) converter.toResponse(testString2, TestResponse.class);
            System.out.println(a2);

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
