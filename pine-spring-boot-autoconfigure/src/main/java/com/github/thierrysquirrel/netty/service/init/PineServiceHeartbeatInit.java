/**
 * Copyright 2019 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.thierrysquirrel.netty.service.init;

import com.github.thierrysquirrel.autoconfigure.PineServiceProperties;
import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.netty.service.core.factory.ThreadPoolFactory;
import com.github.thierrysquirrel.netty.service.core.thread.execution.PineServiceHeartbeatThreadExecution;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ClassName: PineServiceHeartbeatInit
 * Description:
 * date: 2019/10/18 15:14
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PineServiceHeartbeatInit {
    private PineServiceProperties pineServiceProperties;

    public PineServiceHeartbeatInit(PineServiceProperties pineServiceProperties) {
        this.pineServiceProperties = pineServiceProperties;
    }

    @PostConstruct
    private void init() {
        ScheduledThreadPoolExecutor pingServiceHeartbeatThreadPool = ThreadPoolFactory.createPineServiceHeartbeatThreadPool ();
        PineServiceHeartbeatThreadExecution pineServiceHeartbeatThreadExecution = new PineServiceHeartbeatThreadExecution ();
        int heartbeatTime = pineServiceProperties.getHeartbeatTime ();
        ThreadPoolExecutorExecution.statsTimingThread (pingServiceHeartbeatThreadPool, pineServiceHeartbeatThreadExecution, heartbeatTime, heartbeatTime);
    }
}
