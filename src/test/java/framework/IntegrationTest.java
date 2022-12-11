package framework;

import framework.api.responspojo.TopicPojo;
import framework.entities.Topics;
import framework.utils.RandomUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntegrationTest extends BaseTest {

    private String topicName;

    @BeforeEach
    public void beforeMethod() {
        topicName = RandomUtils.createName();
        topicService.addTopic(topicService.setTopicName(topicName));
    }

    @Test
    @DisplayName("Verify adding topic request")
    public void verifyAddingTopicTest() {
        assertThat(topicsRepository.existsByName(topicName)).isTrue();
    }

    @Test
    @DisplayName("Verify deleting topic request")
    public void verifyDeletingTopicTest() {
        assertThat(topicsRepository.existsByName(topicName)).isTrue();
        topicService.deleteTopic(topicsRepository.findIdByName(topicName));
        assertThat(topicsRepository.existsByName(topicName)).isFalse();
    }

    @Test
    @DisplayName("Verify size topics request")
    public void verifyReceivingTopicTest() {
        List<TopicPojo> topics = topicService.getTopics();
        assertThat(topics.size()).isEqualTo(topicsRepository.findAll().size());
    }

    @Test
    @DisplayName("Verify topic by Id request")
    public void verifyReceivingTopicByIdTest() {
        Topics expectedTopic = topicsRepository.getLastRecord();
        TopicPojo actualTopic = topicService.getTopicById(expectedTopic.getId());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(expectedTopic.getId()).isEqualTo(actualTopic.getId());
        softly.assertThat(expectedTopic.getName()).isEqualTo(actualTopic.getName());
        softly.assertThat(expectedTopic.getDate()).isEqualTo(actualTopic.getDate());
        softly.assertAll();
    }
}
