package com.piesat.redistest.service;

import com.piesat.redistest.Utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pie.data.cache.Pedis;
import pie.data.cache.redis.common.RedisMode;
import pie.data.cache.redis.common.SentinelOption;
import pie.data.cache.redis.common.ShardOption;

import javax.annotation.PostConstruct;

@Service
@PropertySource(value = "cache.properties", encoding = "utf-8")
public class TestRedisService {

    private static final Logger logger = LoggerFactory.getLogger(TestRedisService.class);

    @Value("${redis.mode}")
    private String          mRedisMode;
    @Autowired
    private SentinelOption mSentinelOption;
    @Autowired
    private ShardOption mShardOption;

    @Autowired
    private Pedis mPedisClient = null;

    @PostConstruct
    private void init() {
        initRedis();
    }

    private boolean initRedis() {
        try {
            if(mRedisMode.equalsIgnoreCase("SENTINEL")) {  //哨兵模式
                mPedisClient = new Pedis();
                mPedisClient.init(RedisMode.SENTINEL,mSentinelOption);

            } else if(mRedisMode.equalsIgnoreCase("SHARD")) {  //切分模式
                mPedisClient = new Pedis();
                mPedisClient.init(RedisMode.SHARD,mShardOption);
            }
            return true;
        }catch (Exception exp) {
            exp.printStackTrace();
            //退出应用程序
            logger.error(ExceptionUtil.getMessage(exp));
            System.exit(1);
            return false;
        }
    }

    public String getTestInfoFromRedis(){
        mPedisClient.set("test:hello","测试成功",0);
        return mPedisClient.get("test:hello");
    }
}
