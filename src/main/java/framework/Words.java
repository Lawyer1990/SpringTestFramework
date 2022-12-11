package framework;

import framework.api.TopicApiService;
import framework.repository.TopicsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Words {
    @Autowired
    TopicsRepository topicsRepository;

    @Autowired
    TopicApiService topicApiService;
}
