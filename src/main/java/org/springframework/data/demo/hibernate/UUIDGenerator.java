package org.springframework.data.demo.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

/**
 * Created by corneil on 4/17/14.
 */
public class UUIDGenerator implements IdentifierGenerator {
    private static Logger logger = LoggerFactory.getLogger(UUIDGenerator.class);

    public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        BigInteger result = new BigInteger(uuid, 16);
        uuid = result.toString();
        if (uuid.length() > 38) {
            uuid = uuid.substring(0, 37);
            result = new BigInteger(uuid);
        }
        if (logger.isInfoEnabled()) {
            logger.info("generated id, length = " + uuid.length() + ", value=" + uuid);
        }
        return result;
    }

}