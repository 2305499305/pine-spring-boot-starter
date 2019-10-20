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

package com.github.thierrysquirrel.netty.service;

import com.github.thierrysquirrel.core.factory.execution.ThreadPoolExecutorExecution;
import com.github.thierrysquirrel.netty.core.AbstractServiceHandler;
import com.github.thierrysquirrel.netty.domain.PineRequestContext;
import com.github.thierrysquirrel.netty.service.core.thread.execution.PineServiceBusinessThreadExecution;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * ClassName: PineServiceHandler
 * Description:
 * date: 2019/10/17 20:22
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
public class PineServiceHandler extends AbstractServiceHandler {
    private ThreadPoolExecutor pingServiceBusinessThreadPool;

    public PineServiceHandler(ThreadPoolExecutor pingServiceBusinessThreadPool) {
        this.pingServiceBusinessThreadPool = pingServiceBusinessThreadPool;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PineRequestContext msg) throws Exception {
        PineServiceBusinessThreadExecution pineServiceBusinessThreadExecution = new PineServiceBusinessThreadExecution (ctx, msg);
        ThreadPoolExecutorExecution.statsThread (pingServiceBusinessThreadPool, pineServiceBusinessThreadExecution);
    }
}
