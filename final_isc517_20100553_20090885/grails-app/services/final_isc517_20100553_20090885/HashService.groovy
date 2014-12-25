package final_isc517_20100553_20090885

import grails.transaction.Transactional
import org.apache.commons.codec.digest.DigestUtils

@Transactional
class HashService {

    String generateMD5(String toDigest) {
        return DigestUtils.md5Hex( (new Date().toString()) + toDigest)
    }
}
