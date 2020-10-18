package com.alexander.scratchpad.jwt.jwks;


import com.alexander.scratchpad.jwt.jwks.model.KeyType;
import com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg;
import java.security.KeyPairGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.stream.Stream;

import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA256;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA384;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.HmacSHA512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.ES512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.HS512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.PS512;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS256;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS384;
import static com.alexander.scratchpad.jwt.jwks.model.algorithms.JwtAlg.RS512;
import static com.alexander.scratchpad.jwt.jwks.model.KeyType.RSA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneralKeyFactoryTest {

    private GeneralKeyFactory generator = new GeneralKeyFactory();

    GeneralKeyFactoryTest() throws NoSuchAlgorithmException {
    }

    @ParameterizedTest
    @MethodSource("createJwtAndAlg")
    void getPrivateKeyPair(JwtAlg alg, KeyType expectedAlgorithm, Class<Exception> e) throws NoSuchAlgorithmException {
        if (e == null) {
            assertThat(generator.getKeyPair(alg).getPrivate().getAlgorithm()).isEqualTo(expectedAlgorithm.name());
        } else {
            assertThrows(RuntimeException.class, () -> generator.getKeyPair(alg));
        }
    }

    private static Stream<Arguments> createJwtAndAlg() {
        return Stream.of(
                Arguments.of(RS256, RSA, null),
                Arguments.of(RS384, RSA, null),
                Arguments.of(RS512, RSA, null),
                Arguments.of(HS256, null, RuntimeException.class),
                Arguments.of(HS384, null, RuntimeException.class),
                Arguments.of(HS512, null, RuntimeException.class),
                Arguments.of(ES256, null, RuntimeException.class),
                Arguments.of(ES384, null, RuntimeException.class),
                Arguments.of(ES512, null, RuntimeException.class),
                Arguments.of(PS256, null, RuntimeException.class),
                Arguments.of(PS384, null, RuntimeException.class),
                Arguments.of(PS512, null, RuntimeException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("createSymmetricJwtAndAlg")
    void testGetSecretKey(JwtAlg alg, KeyType keyType, Class<Exception> e) throws NoSuchAlgorithmException {
        if (e == null) {
            assertThat(generator.getSecretKey(alg).getAlgorithm()).isEqualTo(keyType.name());
        } else {
            assertThrows(RuntimeException.class, () -> generator.getSecretKey(alg));
        }
    }

    private static Stream<Arguments> createSymmetricJwtAndAlg() {
        return Stream.of(
                Arguments.of(RS256, null, RuntimeException.class),
                Arguments.of(RS384, null, RuntimeException.class),
                Arguments.of(RS512, null, RuntimeException.class),
                Arguments.of(HS256, HmacSHA256, null),
                Arguments.of(HS384, HmacSHA384, null),
                Arguments.of(HS512, HmacSHA512, null),
                Arguments.of(ES256, null, RuntimeException.class),
                Arguments.of(ES384, null, RuntimeException.class),
                Arguments.of(ES512, null, RuntimeException.class),
                Arguments.of(PS256, null, RuntimeException.class),
                Arguments.of(PS384, null, RuntimeException.class),
                Arguments.of(PS512, null, RuntimeException.class)
        );
    }

    private final BigInteger primeP = new BigInteger("132793228705689233814572294623431413135048377564626054770793365612659507149498832479938278163689264604824230543118601595833585849157616646758515172748288340670805962438833153634657304186028774013332799727659350363778182719698334878890136244886116204215918137580053286238357794185104333162131080404995203965591");
    private final BigInteger primeQ = new BigInteger("133283368211651712214136082822678814943700925120665731651980401823673336034135923131814142168064701208607153718786697797165297338368269591966779886619451299511352590617282747725671304047590699024156382875303106404057661873796459334527549582034729940197913203824638861575715369000979146091965323491194838070319");
    private final BigInteger modulus = new BigInteger("17699128797594456070148175204134146827235032892232648830858941736954427198745130059206414602424653940323258445092066361243382963526074228205395303550049768875922029253623121801841920440013765293132407262844135465721303960734407633239097222117572570196342929415907149653587302424736545027658960550845852581663163894693567985708188732982771225993523788143060303963199310000522920856982768494042219130762315087418595036161401735694839941020140308933736305826133446231304343319668640886636933426567837255489432141878175088637826942193741404428553996340387339840643269259691648725458149818802689356946246547286566914393529");
    private final BigInteger publicExponent = new BigInteger("65537");
    private final BigInteger privateExponent = new BigInteger("11200059042889318738135482583362853612754494165229906958105833829973511219408243188052679042247207656943195053707953921196966267350555417430369928877239788437405410641412608257411662488186076174325757877300944291535986051536953985774323511843080544290747729812724130040031930148750690379283037245596826193855741006028721112342284519285778158918237585650671109525844584138098585197800901390057346467109819351438261546478720664758990377198135180707195883829276704514198380253001428753061497398812223322304148471963343123257278648697337800308865725767344895655431698877690414013275785389348488318959501514361135078664193");
    private final BigInteger primeExP = new BigInteger("92767762347597431262459639866891895321069284516825111758321040468542338269940798841230176770317093330952215037039461983577140212241460815074914392299712697059834115400572997505325183241998409081692683596395184378611932898564558837671455424943378644462331366899017374472073127500185601247920994721315478868083");
    private final BigInteger primeExQ = new BigInteger("24600494220299571047541422845462575138816582266690952148743492254807194047668726446723692497144381976092451333201137402917673463762800001652427372512275031571238463615512966999990453180990514446738079580901050898982726038724041133475825017153094234331529702433120633355507606072926006819375825063659410277343");
    private final BigInteger qi = new BigInteger("113670896297952753495465485210158590326434547780076264657145057294862811240693000057577523703662932821184618605708489441483509896435569703786840377452510565833580053905498065932784298237227755253737704543405347261196838634057362270655674450106789400087170158119520944268935910347532480522106463593210240513077");

    @Test
    void getKey_givenRSAPrivateCrtKeySpec() throws InvalidKeySpecException {
        RSAPrivateCrtKey keyImpl = generator.getKey(getRSAPrivateCrtKeySpec());
        assertThat(keyImpl.getModulus()).isEqualTo(modulus);
        assertThat(keyImpl.getPublicExponent()).isEqualTo(publicExponent);
        assertThat(keyImpl.getPrivateExponent()).isEqualTo(privateExponent);
        assertThat(keyImpl.getPrimeExponentP()).isEqualTo(primeExP);
        assertThat(keyImpl.getPrimeExponentQ()).isEqualTo(primeExQ);
        assertThat(keyImpl.getPrimeP()).isEqualTo(primeP);
        assertThat(keyImpl.getPrimeQ()).isEqualTo(primeQ);
        assertThat(keyImpl.getCrtCoefficient()).isEqualTo(qi);
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

    @Test
    void getKeySpec_givenRsaPrivateKeyImpl() throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        RSAPrivateCrtKey key = (RSAPrivateCrtKey) gen.generateKeyPair().getPrivate();

        RSAPrivateCrtKeySpec keySpec = generator.getKeySpec(key);

        assertThat(keySpec.getModulus()).isEqualTo(key.getModulus());
        assertThat(keySpec.getPublicExponent()).isEqualTo(key.getPublicExponent());
        assertThat(keySpec.getPrivateExponent()).isEqualTo(key.getPrivateExponent());
        assertThat(keySpec.getPrimeExponentP()).isEqualTo(key.getPrimeExponentP());
        assertThat(keySpec.getPrimeExponentQ()).isEqualTo(key.getPrimeExponentQ());
        assertThat(keySpec.getPrimeP()).isEqualTo(key.getPrimeP());
        assertThat(keySpec.getPrimeQ()).isEqualTo(key.getPrimeQ());
        assertThat(keySpec.getCrtCoefficient()).isEqualTo(key.getCrtCoefficient());
    }
}
