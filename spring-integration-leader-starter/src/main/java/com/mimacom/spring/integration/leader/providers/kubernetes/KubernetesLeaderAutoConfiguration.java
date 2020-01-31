package com.mimacom.spring.integration.leader.providers.kubernetes;

import com.mimacom.spring.integration.leader.providers.LeaderProvider;
import com.mimacom.spring.integration.leader.providers.LeaderProviderTypeCondition;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.cloud.kubernetes.leader.LeaderAutoConfiguration;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureAfter(LeaderAutoConfiguration.class)
@Import({KubernetesLeaderInitiatorRegistrar.class, KubernetesLeaderProviderRegistrar.class})
@ConditionalOnSingleCandidate(KubernetesClient.class)
@ConditionalOnMissingBean(LeaderProvider.class)
@Conditional(LeaderProviderTypeCondition.class)
public class KubernetesLeaderAutoConfiguration {}
