package framework.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static framework.config.Config.URL_APP;

@Service
public abstract class BaseApiService {

    protected HttpHeaders getFormDataHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    protected void fireFormDataRequest(HttpEntity<MultiValueMap<String, String>> request, String endpoint) {
        new RestTemplate().postForEntity(URL_APP + endpoint, request, String.class);
    }

    protected void fireGetRequest(String endpoint) {
        new RestTemplate().getForObject(URL_APP + endpoint, String.class);
    }
}
