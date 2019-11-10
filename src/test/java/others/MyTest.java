package others;

import com.jzy.entity.User;
import com.jzy.util.user.UserUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;


public class MyTest extends UnitTestBase {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> vOps;

    @Resource(name = "redisTemplate")
    HashOperations<String, String, Object> hOps;

    @Resource(name = "redisTemplate")
    ListOperations<String, String> lOps;

    //先赋值set在申明失效时间expire
    @Test
    public void v1() {
//        String key = "123";
//        vOps.set("1231", key + "213");
        System.out.println(vOps.get("myKey"));

    }

    @Test
    public void v2() {
        User user = new User();
        user.setUserNickname("adsad");
        hOps.put("user", "1", user);
        hOps.put("user", "2", "123");
        User user1 = (User) hOps.get("user", "1");

        System.out.println(user1);
    }

    @Test
    public void v3() {
        lOps.leftPush("news", "1231");
        lOps.leftPushAll("news", "111", "222", "333");
        List<String> list = lOps.range("news", 0, -1);
        System.out.println(list);
        System.out.println(lOps.size("news"));
    }

    @Test
    public void t() {
        User user = (User) hOps.get(UserUtil.KEY_USER_LOGIN_NAMEANDPASSWORD, "516030910429");
        System.out.println(user);
    }

    @Test
    public void t1() {
        final String baseKey = UserUtil.KEY_USER_LOGIN_NAMEANDPASSWORD;
        HashOperations<String, String, Object> hOps = redisTemplate.opsForHash();
        hOps.delete(baseKey,"000000000001");
    }

    public static void main(String[] args) {
//        JedisPoolConfig poolConfig=new JedisPoolConfig();
//        JedisPool pool=new JedisPool(poolConfig,"localhost",6379);
//        Jedis jedis=pool.getResource();
//        jedis.auth("123");
////
////        jedis.set("string","字符串");
////
////        System.out.println(jedis.get("string"));
//
//        if (jedis.exists("h1")){
//            Map<String,String> map=jedis.hgetAll("h1");
//            System.out.println(map);
//        } else {
//        }
//
//        jedis.close();


    }
}
