package framework.api;

import framework.api.responspojo.TopicPojo;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static framework.config.Config.URL_APP;

@Service
public class TopicApiService extends BaseApiService {

    private static final String TOPIC_ADD_ENDPOINT = "topic/add";
    private static final String TOPICS_ALL_ENDPOINT = "topics";
    private static final String TOPIC_ID_ENDPOINT = "topic/";
    private static final String TOPIC_DELETE_ENDPOINT = "topic/delete/";
    private static final String NAME = "name";

    public MultiValueMap<String, String> setTopicName(String name) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(NAME, name);
        return map;
    }

    public void addTopic(MultiValueMap<String, String> map) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, getFormDataHeaders());
        fireFormDataRequest(request, TOPIC_ADD_ENDPOINT);
    }

    public void deleteTopic(int id) {
        fireGetRequest(TOPIC_DELETE_ENDPOINT + id);
    }

    public List<TopicPojo> getTopics() {
        ResponseEntity<Object[]> responseEntity =
                new RestTemplate().getForEntity(URL_APP + TOPICS_ALL_ENDPOINT, Object[].class);
        return new TopicPojo().getInstance(responseEntity.getBody());
    }

    public TopicPojo getTopicById(int id) {
        ResponseEntity<Object> responseEntity =
                new RestTemplate().getForEntity(URL_APP + TOPIC_ID_ENDPOINT + id, Object.class);
        return new TopicPojo().getInstance(responseEntity.getBody());
    }
}
