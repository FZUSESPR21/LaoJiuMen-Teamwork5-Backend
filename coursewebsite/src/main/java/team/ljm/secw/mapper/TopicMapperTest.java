package team.ljm.secw.mapper;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import team.ljm.secw.entity.Topic;

import static org.junit.Assert.*;

public class TopicMapperTest {
    @Test
    public void findTest(){
       ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationConfig.xml");
       TopicMapper topicMapper=applicationContext.getBean(TopicMapper.class);
        Topic topic=topicMapper.findTopicById(1);
        System.out.println(topic);
    }
}