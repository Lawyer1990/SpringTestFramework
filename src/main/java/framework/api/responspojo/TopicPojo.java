package framework.api.responspojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopicPojo {
    private int id;
    private String name;
    private String date;

    public List<TopicPojo> getInstance(Object[] objects) {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, TopicPojo.class))
                .collect(Collectors.toList());
    }

    public TopicPojo getInstance(Object object) {
        return new ObjectMapper().convertValue(object, TopicPojo.class);
    }
}
