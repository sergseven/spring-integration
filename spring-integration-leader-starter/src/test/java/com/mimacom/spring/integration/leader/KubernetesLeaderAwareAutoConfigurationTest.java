package com.mimacom.spring.integration.leader;

import com.mimacom.spring.integration.leader.providers.AbstractLeaderInitiatorRegistrar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.kubernetes.leader.LeaderInitiator;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
    "spring.cloud.zookeeper.enabled=false",
    "spring.cloud.hazelcast.enabled=false",
    //    "spring.hazelcast.config=classpath:config/my-hazelcast.xml",
    "spring-integration.leader.type=kubernetes"})
public class KubernetesLeaderAwareAutoConfigurationTest extends AbstractLeaderAwareAutoConfigurationTest {

    @Autowired(required = false)
    private List<LeaderInitiator> leaderInitiator;

    @Autowired
    @Qualifier("test-role-1" + AbstractLeaderInitiatorRegistrar.LEADER_INITIATOR_BEAN_NAME_POSTFIX)
    private LeaderInitiator leaderInitiatorOne;

    @Test
    public void testKubernetesBasedLeaderInitiator() {
        assertThat(leaderInitiator).isNotEmpty().hasOnlyElementsOfType(LeaderInitiator.class);

        assertThat(this.leaderInitiatorOne).isNotNull();
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfigUsingKubernetes {

    }
}
