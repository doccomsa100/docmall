package com.docmall.basic.kakaopay;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by kakaopay
 */
@Slf4j
@Service
public class SampleService {
//    @Value("${kakaopay.api.secret.key}")
//    private String kakaopaySecretKey;
//
//    @Value("${cid}")
//    private String cid;
//
//    @Value("${sample.host}")
//    private String sampleHost;
//
    private String tid;

    public ReadyResponse ready(String agent, String openType) {
        // Request header
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "DEV_SECRET_KEY " + kakaopaySecretKey);
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "SECRET_KEY DEVFF7BA28D71B6BBF1464A8EBB571FF9725C6BA");
		headers.set("Content-type", "application/json;charset=utf-8");

        // Request param
        ReadyRequest readyRequest = ReadyRequest.builder()
                .cid("TC0ONETIME")
                .partnerOrderId("1")
                .partnerUserId("1")
                .itemName("상품명")
                .quantity(1)
                .totalAmount(1100)
                .taxFreeAmount(0)
                .vatAmount(100)
                .approvalUrl("http://localhost:9090/kakao/orderApproval")
                .cancelUrl("http://localhost:9090/kakao/orderApproval")
                .failUrl("http://localhost:9090/kakao/orderApproval")
                .build();

        // Send reqeust
        HttpEntity<ReadyRequest> entityMap = new HttpEntity<>(readyRequest, headers);
        ResponseEntity<ReadyResponse> response = new RestTemplate().postForEntity(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                entityMap,
                ReadyResponse.class
        );
        ReadyResponse readyResponse = response.getBody();
        
        log.info("응답데이터: " + readyResponse);

        // 주문번호와 TID를 매핑해서 저장해놓는다.
        // Mapping TID with partner_order_id then save it to use for approval request.
        this.tid = readyResponse.getTid();
        return readyResponse;
    }

    public String approve(String pgToken) {
        // ready할 때 저장해놓은 TID로 승인 요청
        // Call “Execute approved payment” API by pg_token, TID mapping to the current payment transaction and other parameters.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY DEVFF7BA28D71B6BBF1464A8EBB571FF9725C6BA");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request param
        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid("TC0ONETIME")
                .tid(tid)
                .partnerOrderId("1")
                .partnerUserId("1")
                .pgToken(pgToken)
                .build();

        // Send Request
        HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    "https://open-api.kakaopay.com/online/v1/payment/approve",
                    entityMap,
                    String.class
            );

            // 승인 결과를 저장한다.
            // save the result of approval
            String approveResponse = response.getBody();
            return approveResponse;
        } catch (HttpStatusCodeException ex) {
            return ex.getResponseBodyAsString();
        }
    }
}
