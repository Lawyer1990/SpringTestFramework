package framework;

import framework.api.TopicService;
import framework.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseTest {

    @Autowired
    protected TopicsRepository topicsRepository;

    @Autowired
    protected TopicService topicService;
}
