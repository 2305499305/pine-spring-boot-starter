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

package com.github.thierrysquirrel.netty.core.client;


import com.github.thierrysquirrel.netty.core.client.factory.ClientEventLoopGroupFactory;
import com.github.thierrysquirrel.netty.core.client.factory.InetSocketAddressFactory;
import com.github.thierrysquirrel.netty.core.client.factory.constant.IdleStateConstant;
import com.github.thierrysquirrel.netty.core.client.utils.RandomUtils;
import com.github.thierrysquirrel.netty.domain.PineRequestContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

import java.util.concurrent.CompletableFuture;


/**
 * ClassName: ClientInit
 * Description:
 * date: 2019/10/17 18:35
 *
 * @author ThierrySquirrel
 * @since JDK 1.8
 */
@Data
public class ClientInit {

    private Channel channel;

    private CompletableFuture<PineRequestContext> completableFuture = new CompletableFuture<> ();

    public void init(String url) throws InterruptedException {
        if (channel != null && channel.isActive ()) {
            sheep ();
            completableFuture = new CompletableFuture<> ();
            return;
        }
        Bootstrap b = new Bootstrap ();
        b.group (ClientEventLoopGroupFactory.getClientEventLoopGroup ())
                .channel (NioSocketChannel.class)
                .handler (new ClientInitInitChannelHandler (IdleStateConstant.OTHER_TIMEOUT.getValue (),
                        IdleStateConstant.WRITE_TIMEOUT.getValue (),
                        IdleStateConstant.OTHER_TIMEOUT.getValue (),
                        this));

        ChannelFuture channelFuture = b.connect (InetSocketAddressFactory.getInetSocketAddress (url)).sync ();
        this.channel = channelFuture.channel ();

    }


    public void sheep() throws InterruptedException {
        while (completableFuture != null && !completableFuture.isDone ()) {
            int random = RandomUtils.getRandom ();
            Thread.sleep (random);
        }
    }

    public void call(PineRequestContext pineRequestContext) {
        completableFuture.complete (pineRequestContext);
    }
}
