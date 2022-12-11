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
        words.topicApiService.addTopic(words.topicApiService.setTopicName(topicName));
    }

    @Test
    @DisplayName("Verify adding topic request")
    public void verifyAddingTopicTest() {
        assertThat(words.topicsRepository.existsByName(topicName)).isTrue();
    }

    @Test
    @DisplayName("Verify deleting topic request")
    public void verifyDeletingTopicTest() {
        assertThat(words.topicsRepository.existsByName(topicName)).isTrue();
        words.topicApiService.deleteTopic(words.topicsRepository.findIdByName(topicName));
        assertThat(words.topicsRepository.existsByName(topicName)).isFalse();
    }

    @Test
    @DisplayName("Verify size topics request")
    public void verifyReceivingTopicTest() {
        List<TopicPojo> topics = words.topicApiService.getTopics();
        assertThat(topics.size()).isEqualTo(words.topicsRepository.findAll().size());
    }

    @Test
    @DisplayName("Verify topic by Id request")
    public void verifyReceivingTopicByIdTest() {
        Topics expectedTopic = words.topicsRepository.getLastRecord();
        TopicPojo actualTopic = words.topicApiService.getTopicById(expectedTopic.getId());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(expectedTopic.getId()).isEqualTo(actualTopic.getId());
        softly.assertThat(expectedTopic.getName()).isEqualTo(actualTopic.getName());
        softly.assertThat(expectedTopic.getDate()).isEqualTo(actualTopic.getDate());
        softly.assertAll();
    }
}
