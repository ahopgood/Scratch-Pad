package com.alexander.scratchpad.jwt.jwks;

import com.alexander.scratchpad.jwt.jwks.exception.KeyException;
import com.alexander.scratchpad.jwt.jwks.model.PrivateJsonWebKey;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.spec.RSAPrivateCrtKeySpec;

import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS512;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RsaPrivateKeyMapperTest {

    private final RsaPrivateKeyMapper mapper = new RsaPrivateKeyMapper();

    private final BigInteger primeP = new BigInteger("132793228705689233814572294623431413135048377564626054770793365612659507149498832479938278163689264604824230543118601595833585849157616646758515172748288340670805962438833153634657304186028774013332799727659350363778182719698334878890136244886116204215918137580053286238357794185104333162131080404995203965591");
    private final BigInteger primeQ = new BigInteger("133283368211651712214136082822678814943700925120665731651980401823673336034135923131814142168064701208607153718786697797165297338368269591966779886619451299511352590617282747725671304047590699024156382875303106404057661873796459334527549582034729940197913203824638861575715369000979146091965323491194838070319");
    private final BigInteger modulus = new BigInteger("17699128797594456070148175204134146827235032892232648830858941736954427198745130059206414602424653940323258445092066361243382963526074228205395303550049768875922029253623121801841920440013765293132407262844135465721303960734407633239097222117572570196342929415907149653587302424736545027658960550845852581663163894693567985708188732982771225993523788143060303963199310000522920856982768494042219130762315087418595036161401735694839941020140308933736305826133446231304343319668640886636933426567837255489432141878175088637826942193741404428553996340387339840643269259691648725458149818802689356946246547286566914393529");
    private final BigInteger publicExponent = new BigInteger("65537");
    private final BigInteger privateExponent = new BigInteger("11200059042889318738135482583362853612754494165229906958105833829973511219408243188052679042247207656943195053707953921196966267350555417430369928877239788437405410641412608257411662488186076174325757877300944291535986051536953985774323511843080544290747729812724130040031930148750690379283037245596826193855741006028721112342284519285778158918237585650671109525844584138098585197800901390057346467109819351438261546478720664758990377198135180707195883829276704514198380253001428753061497398812223322304148471963343123257278648697337800308865725767344895655431698877690414013275785389348488318959501514361135078664193");
    private final BigInteger primeExP = new BigInteger("92767762347597431262459639866891895321069284516825111758321040468542338269940798841230176770317093330952215037039461983577140212241460815074914392299712697059834115400572997505325183241998409081692683596395184378611932898564558837671455424943378644462331366899017374472073127500185601247920994721315478868083");
    private final BigInteger primeExQ = new BigInteger("24600494220299571047541422845462575138816582266690952148743492254807194047668726446723692497144381976092451333201137402917673463762800001652427372512275031571238463615512966999990453180990514446738079580901050898982726038724041133475825017153094234331529702433120633355507606072926006819375825063659410277343");
    private final BigInteger qi = new BigInteger("113670896297952753495465485210158590326434547780076264657145057294862811240693000057577523703662932821184618605708489441483509896435569703786840377452510565833580053905498065932784298237227755253737704543405347261196838634057362270655674450106789400087170158119520944268935910347532480522106463593210240513077");

    @Test
    void testMap_givenNullPrivateKey_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(null, "1234", JwtAlg.RS256));
    }

    @Test
    void testMap_givenNullJwtAlg_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(getRSAPrivateCrtKeySpec(), "1234", null));
    }

    @Test
    void testMap_givenNullKeyId_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(getRSAPrivateCrtKeySpec(), null, JwtAlg.ES256));
    }

    @Test
    void testMap_givenEmptyKeyId_thenThrowException() {
        assertThrows(KeyException.class, () -> mapper.map(getRSAPrivateCrtKeySpec(), "   ", JwtAlg.ES256));
    }

    @Test
    void testMap_givenRSAPrivateCrtKeyImpl() {
        PrivateJsonWebKey jsonKey = mapper.map(getRSAPrivateCrtKeySpec(), "1234", JwtAlg.RS256);
        assertThat(jsonKey.getAlg()).isEqualTo("RS256");
        assertThat(jsonKey.getKeyType()).isEqualTo("RSA");
        assertThat(jsonKey.getPrimeP()).isEqualTo(primeP.toString());
        assertThat(jsonKey.getPrimeQ()).isEqualTo(primeQ.toString());
        assertThat(jsonKey.getModulus()).isEqualTo(modulus.toString());
        assertThat(jsonKey.getKid()).isEqualTo("1234");
        assertThat(jsonKey.getPrivateExponent()).isEqualTo(privateExponent.toString());
        assertThat(jsonKey.getPublicExponent()).isEqualTo(publicExponent.toString());
        assertThat(jsonKey.getDp()).isEqualTo(primeExP.toString());
        assertThat(jsonKey.getDq()).isEqualTo(primeExQ.toString());
        assertThat(jsonKey.getQi()).isEqualTo(qi.toString());
    }

    @Test
    void tesMap_givenPrivateJsonWebKey() {
        RSAPrivateCrtKeySpec privateKey = mapper.map(getPrivateJsonWebKey());
        assertThat(privateKey.getPrimeP()).isEqualTo(primeP);
        assertThat(privateKey.getPrimeQ()).isEqualTo(primeQ);
        assertThat(privateKey.getModulus()).isEqualTo(modulus);
        assertThat(privateKey.getPrivateExponent()).isEqualTo(privateExponent);
        assertThat(privateKey.getPublicExponent()).isEqualTo(publicExponent);
        assertThat(privateKey.getPrimeExponentP()).isEqualTo(primeExP);
        assertThat(privateKey.getPrimeExponentQ()).isEqualTo(primeExQ);
        assertThat(privateKey.getCrtCoefficient()).isEqualTo(qi);
    }

    PrivateJsonWebKey getPrivateJsonWebKey() {
        return PrivateJsonWebKey.builder()
                .alg(RS512.name())
                .keyType(RS512.getKeyType().name())
                .kid("5678")
                .modulus(modulus.toString())
                .publicExponent(publicExponent.toString())
                .privateExponent(privateExponent.toString())
                .primeP(primeP.toString())
                .primeQ(primeQ.toString())
                .dp(primeExP.toString())
                .dq(primeExQ.toString())
                .qi(qi.toString())
                .build();
    }


    RSAPrivateCrtKeySpec getRSAPrivateCrtKeySpec() {
        return new RSAPrivateCrtKeySpec(
                modulus,
                publicExponent,
                privateExponent,
                primeP,
                primeQ,
                primeExP,
                primeExQ,
                qi
        );
    }
}