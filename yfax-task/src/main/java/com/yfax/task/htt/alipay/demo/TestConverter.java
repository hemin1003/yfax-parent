package com.yfax.task.htt.alipay.demo;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.parser.json.JsonConverter;
import com.alipay.api.internal.parser.xml.XmlConverter;

public class TestConverter {

    public static void main(String[] args) throws AlipayApiException, ClassNotFoundException {
        for (int i = 2; i <= 2; i++) {
            System.out.println("\n# ================== case " + i + " ================== #\n");
            TestResponse resultNewJson = null;
            TestResponse resultOldJson = null;
            TestResponse resultOldXml = null;
            {
                System.out.println("========= new json =========");

                String caseString = Util.read("cases/" + i + "/case-new.json");
                System.out.println(caseString);

                if (caseString == null) {
                    continue;
                }

                JsonConverter converter = new JsonConverter();
                resultNewJson = (TestResponse) converter.toResponse(caseString, TestResponse.class);
                System.out.println("\n" + resultNewJson);
            }
            {
                System.out.println("========= old json =========");
                String caseString = Util.read("cases/" + i + "/case-old.json");
                System.out.println(caseString);

                if (caseString == null) {
                    continue;
                }

                JsonConverter converter = new JsonConverter();
                resultOldJson = (TestResponse) converter.toResponse(caseString, TestResponse.class);
                System.out.println("\n" + resultOldJson);
            }
            {
                System.out.println("========= old xml =========");

                String caseString = Util.read("cases/" + i + "/case-old.xml");
                System.out.println(caseString);

                if (caseString == null) {
                    continue;
                }

                XmlConverter converter = new XmlConverter();
                resultOldXml = (TestResponse) converter.toResponse(caseString, TestResponse.class);
                System.out.println("\n" + resultOldXml);
            }

        }
    }

}
