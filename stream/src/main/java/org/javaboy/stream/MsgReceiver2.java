package org.javaboy.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

@EnableBinding(MyChannel.class)
public class MsgReceiver2 {
    public final static Logger logger = LoggerFactory.getLogger(MsgReceiver2.class);

    @StreamListener(MyChannel.INPUT)
    public void receive(Object payload) {
        logger.info("received2:" + payload+":"+new Date());
    }
}
