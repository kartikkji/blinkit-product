package blinket.com.product.service;


import blinket.com.product.dto.responseDto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class IdentityServiceClient {


    private final RestTemplate restTemplate;

    @Autowired
    public IdentityServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    public String getAdminNameById(Integer id){

        String url = "http://localhost:8081/api/v1/user/get/" + id;

         UserResponseDto obj = restTemplate.getForEntity(url, UserResponseDto.class).getBody();

        // Return extracted username
        assert obj != null;
        return obj.getName();
    }


}
