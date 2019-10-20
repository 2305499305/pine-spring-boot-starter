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

package com.github.thierrysquirrel.netty.service.core.thread;


import lombok.Data;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: AbstractPineServiceInitThread
 * Description:
 * date: 2019/10/17 20:00
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public abstract class AbstractPineServiceInitThread implements Runnable {
    private ThreadPoolExecutor pingServiceBusinessThreadPool;
    private String pineServiceUrl;

    public AbstractPineServiceInitThread(ThreadPoolExecutor pingServiceBusinessThreadPool, String pineServiceUrl) {
        this.pingServiceBusinessThreadPool = pingServiceBusinessThreadPool;
        this.pineServiceUrl = pineServiceUrl;
    }

    /**
     * pineServiceInit
     * @param pingServiceBusinessThreadPool ThreadPoolExecutor
     * @param pineServiceUrl pineServiceUrl
     */
    protected abstract void pineServiceInit(ThreadPoolExecutor pingServiceBusinessThreadPool, String pineServiceUrl);

    @Override
    public void run() {
        pineServiceInit (this.pingServiceBusinessThreadPool, this.pineServiceUrl);
    }
}
