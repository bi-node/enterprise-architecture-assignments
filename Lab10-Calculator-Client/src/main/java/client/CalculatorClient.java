package client;

import generated.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CalculatorClient extends WebServiceGatewaySupport {

    @Value("${calculator.service.url}")
    private String serviceUrl;

    public int add(int a, int b) {
        AddRequest request = new AddRequest();
        request.setNumber1(a);
        request.setNumber2(b);

        AddResponse response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(serviceUrl, request);
        return response.getResult();
    }
    public int difference(int a, int b) {
        SubtractRequest request = new SubtractRequest();
        request.setNumber1(a);
        request.setNumber2(b);

        SubtractResponse response = (SubtractResponse) getWebServiceTemplate().marshalSendAndReceive(serviceUrl, request);
        return response.getResult();
    }

    public int product(int a, int b) {
        MultiplyRequest request = new MultiplyRequest();
        request.setNumber1(a);
        request.setNumber2(b);

        MultiplyResponse response = (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(serviceUrl, request);
        return response.getResult();
    }





}
