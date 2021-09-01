package org.javaboy.vhr.config;

import org.javaboy.vhr.model.MailConstants;
import org.javaboy.vhr.service.MailSendLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public final static Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    MailSendLogService mailSendLogService;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if (ack) {
                logger.info(msgId + ":消息发送成功");
                mailSendLogService.updateMailSendLogStatus(msgId, 1);//修改数据库中的记录，消息投递成功
            } else {
                logger.info(msgId + ":消息发送失败");
            }
        });
        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
            logger.info("消息发送失败");
        });
        return rabbitTemplate;
    }

    @Bean
    Queue mailQueue() {
        return new Queue(MailConstants.MAIL_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange mailExchange() {
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

}


怎么才能显得多而且困难？他们是否觉得
实习经历怎么才能显得难一点？让别人觉得我做的东西很多？
你实习的时候遇到了什么困难，你有什么独立的思考？你是否对你正在做的事情感到好奇？
学习能力是否强？好奇心？态度是否端正认真。
如何体现出来？


tomcat、hsf、JPA、HSF、Diamond、
算法、SLS、TSDB、

这中间涉及数据提取的时候是用正则表达式。
然后涉及到聚合，是通过所有的额数据都聚合到这一分钟的数据里。
然后对这一部分数据进行除法操作。

保持好奇心：为了彻底弄清楚这两个指标，去看mysql与Innodb引擎。为了弄清楚TPS、qps这些东西去看了性能测试这本书。


自我介绍的时候，着重介绍自己项目（有哪些模块，遇到什么困难，怎么解决的），
实习经历（遇到什么困难怎么解决的。

第一次实习，而且接触到的东西又是完全没有用过的，所以相当于整天都在学新的东西
我是相当于是开发，但是却相当于做大数据的工作，而且个或者各种测试也都需要自己
        来做，相当于每天都在接触新的东西，一边学新东西，一边用。很不适应。
        如果能够再来一次，肯定会能更加认真，特别认真的对待他。
        做事多问几个为什么，想想可能会出现什么问题。态度端正，特别认真。



技术选型，技术文档评审，提供数据，用来分析数据，

提供appGroup查询，对Flink，通过正则表达式获取数据，

