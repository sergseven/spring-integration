package com.mimacom.spring.integration.leader.providers.kubernetes;

import com.mimacom.spring.integration.leader.providers.LeaderProvider;
import com.mimacom.spring.integration.leader.providers.LeaderProviderRegistrar;
import org.springframework.cloud.kubernetes.leader.LeaderInitiator;
import org.springframework.integration.leader.Context;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;

class KubernetesLeaderProviderRegistrar
    extends LeaderProviderRegistrar<LeaderInitiator, KubernetesLeaderProviderRegistrar.KubernetesLeaderProvider> {

    static class KubernetesLeaderProvider implements LeaderProvider {

        private final LockRegistryLeaderInitiator lockRegistryLeaderInitiator;

        KubernetesLeaderProvider(LockRegistryLeaderInitiator lockRegistryLeaderInitiator) {
            this.lockRegistryLeaderInitiator = lockRegistryLeaderInitiator;
        }

        @Override
        public Context context() {
            return lockRegistryLeaderInitiator.getContext();
        }
    }
}
