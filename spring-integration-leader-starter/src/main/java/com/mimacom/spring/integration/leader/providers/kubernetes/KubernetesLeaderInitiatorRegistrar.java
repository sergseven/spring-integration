package com.mimacom.spring.integration.leader.providers.kubernetes;

import com.hazelcast.core.HazelcastInstance;
import com.mimacom.spring.integration.leader.providers.AbstractLeaderInitiatorRegistrar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.integration.leader.DefaultCandidate;

import java.util.UUID;

public class KubernetesLeaderInitiatorRegistrar extends AbstractLeaderInitiatorRegistrar {

    @Override
    protected BeanDefinition leaderInitiatorBeanDefinition(BeanFactory beanFactory, String role) {
        return BeanDefinitionBuilder.genericBeanDefinition(KubernetesLeaderInitiatorFactoryBean.class)
            .addConstructorArgValue(beanFactory.getBean(HazelcastInstance.class))
            .addConstructorArgValue(new DefaultCandidate(UUID.randomUUID().toString(), role))
            .getBeanDefinition();
    }
}
