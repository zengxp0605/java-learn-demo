package com.stan.webfluxdemo1;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.stan.webfluxdemo1.shirotest.Base64;
import com.stan.webfluxdemo1.shirotest.MyObjectInputStream;
import com.stan.webfluxdemo1.shirotest.SimpleSession;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: zengxp
 * @Date: 2022/1/7 20:24
 * @Desc:
 */
public class TempTest {
    public static void main(String[] args) {
        List<Float> floatList = new ArrayList<>();
        t1(floatList);

        List<Integer> integerList = new ArrayList<>();
        t1(integerList);

//        List<?> list = new ArrayList<>();
//        list.add("a");
    }

    private static void t1(List<? extends Number> list) {
        System.out.println(list);
    }

    /**
     * 反序列化原有的session 信息
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        String sessionStr = "x'724F304142584E7941437076636D6375595842685932686C4C6E4E6F61584A764C6E4E6C63334E7062323475625764304C6C4E70625842735A564E6C63334E7062323664484B47343159786962674D414148687764774941323351414A4442694E4456695A4455334C5451344E6A4D744E445A6C4D533169596D46684C5463784F546C6A5954637A4D6A63774D6E4E794141357159585A684C6E563061577775524746305A5768716751464C5758515A417741416548423343414141415835473834647765484E78414834414133634941414142666B6853446C4E3464784D41414141424E5032514141414A4D5449334C6A41754D4334786333494145577068646D4575645852706243354959584E6F545746774251666177634D57594E454441414A47414170736232466B526D466A644739795351414A644768795A584E6F6232786B6548412F514141414141414144486349414141414541414141414E3041416831633256795357356D62334E794144526A623230755A6D397A645734755A6D397A645735335A57467959325675644756794C6E567A5A5849756257396B5A5777755A4852764C6C567A5A584A4A626D5A76524652504631666A456A364B5654554341416C4D414156686348424A5A485141456B787159585A684C327868626D6376553352796157356E4F30774142324E6F595735755A57787841483441436B77414347527662574670626B6C6B6441415454477068646D4576624746755A79394A626E526C5A3256794F3077414257567459576C736351422B4141704D414152705A4535766351422B4141704D41415A705A46523563475678414834414330774143473176596D6C735A5535766351422B4141704D41415A7A6447463064584E784148344143307741426E567A5A584A4A5A4851414545787159585A684C327868626D6376544739755A7A74346367416F593239744C6D5A76633356754C6D5A766333567564325668636D4E6C626E526C6369356A6232317462323475516D467A5A5552555478592F5251394F3076734A416741416548423041414230414156685A473170626E4E794142467159585A684C6D7868626D6375535735305A57646C63684C696F4B5433675963344167414253514146646D46736457563463674151616D4632595335735957356E4C6B353162574A6C636F61736C52304C6C4F434C416741416548414141417534634842776441414C4D544D344D5463324F546B324F44647763334941446D7068646D4575624746755A79354D6232356E4F34766B6B4D7950493938434141464B41415632595778315A58687841483441456741414144417869554D516441425162334A6E4C6D467759574E6F5A53357A61476C796279357A64574A715A574E304C6E4E3163484276636E51755247566D5958567364464E31596D706C59335244623235305A5868305830465656456846546C524A51304655525552665530565455306C50546C394C52566C7A63674152616D4632595335735957356E4C6B4A766232786C5957374E49484B41315A7A363767494141566F4142585A686248566C654841426441424E62334A6E4C6D467759574E6F5A53357A61476C796279357A64574A715A574E304C6E4E3163484276636E51755247566D5958567364464E31596D706C59335244623235305A58683058314253535535445356424254464E665530565455306C50546C394C52566C7A6367417962334A6E4C6D467759574E6F5A53357A61476C796279357A64574A715A574E304C6C4E70625842735A5642796157356A6158426862454E766247786C593352706232366F6631676C78714D4953674D414155774144334A6C5957787455484A70626D4E7063474673633351414430787159585A684C33563061577776545746774F3368776333494146327068646D4575645852706243354D615735725A57524959584E6F545746774E4D424F5842427377507343414146614141746859324E6C63334E50636D526C636E687841483441426A3941414141414141414D647767414141415141414141415851414F6D4E766253356D62334E316269356D62334E31626E646C59584A6A5A5735305A58497564584E6C6369357A61476C79627935795A574673625335506448424D62326470626C4A6C59577874587A427A63674158616D46325953353164476C734C6B7870626D746C5A456868633268545A585459624E64616C64307148674941414868794142467159585A684C6E5630615777755347467A61464E6C644C7045685A5757754C63304177414165484233444141414141492F514141414141414141585141437A457A4F4445334E6A6B354E6A673365486741647745426351422B414239346548673D'";
        byte[] cachedSession = sessionStr.getBytes(StandardCharsets.UTF_8);

        // decode
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        byte[] deserialize = (byte[]) serializer.deserialize(cachedSession);
        SimpleSession session1 = (SimpleSession) bytes2Object(Base64.decode(deserialize));
        System.out.println(session1);
        System.out.println(session1.getAttribute("userInfo"));
        System.out.println(session1.getAttributeKeys());
    }

    @Test
    public void test2() throws Exception {
        SimpleSession session = new SimpleSession();
        session.setAttribute("testId", 11111);
        session.setId(UUID.randomUUID());

        byte[] encode = Base64.encode(session2Bytes(session));

        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        byte[] serialize = serializer.serialize(encode);
        System.out.println(new String(serialize, StandardCharsets.UTF_8));

        // decode
        byte[] deserialize = (byte[]) serializer.deserialize(serialize);
        SimpleSession session1 = bytes2Session(Base64.decode(deserialize));
        System.out.println(session1);
        System.out.println(session1.getAttribute("testId"));
    }

//    public String doCreate(SimpleSession session) {
//        String sessionId = "sessionId:1111:";
//        byte[] encode = Base64.encode(session2Bytes(session));
//        redisTemplate.opsForValue().set(sessionId, encode);
//        return sessionId;
//    }

//    public Session doReadSession(Serializable sessionId) {
//        final String sessionIdKey = RedisKeyEnum.SESSION_ID_PREFIX.getKey() + sessionId;
//        Object cachedSession;
//        Session session = null;
//        if(!ObjectUtils.isEmpty(cachedSession = redisManager.get(sessionIdKey))) {
//            session = bytes2Session(Base64.decode((byte[])cachedSession));
//        }
//        return session;
//    }

    public byte[] session2Bytes(SimpleSession session) {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(session);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public SimpleSession bytes2Session(byte[] bytes) {
        SimpleSession session = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            session = (SimpleSession) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return session;
    }

    public Object bytes2Object(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream;
        Object obj = null;
        try {
            objectInputStream = new MyObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

