package com.taiping.app.cryptoUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class Main {

	private static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApgmxgkwuBOEcZveYv0POVZoZJSWtNqNU"
			+ "\r"
			+ "MrJnvaMb7IzfcKL59rh2DkrHwwdWVNkeNC+zU1BoJsN87F0YCaVrpdhbJeZFT3loO3T2MIz3h0HU"
			+ "\r"
			+ "DjJxaAkqQNdVi+JfmRk8dztPhdTYEPxy3vzDm/AdAmkGuSeom3154mzlnxtN2vpCX34WhVSJQHv7"
			+ "\r"
			+ "KpMYhnKzoMbGS9zFTTvltcsvWqgKqVmmfkS4812EJueJh6Po51O/N00tlZTaXzyLCOPgLpN5ucjl"
			+ "\r"
			+ "bgHgnnz0H/NR8oU/gVMBHAZhFYeonQJs7TahgsMZfn9TbhvP4KjxDyB7CsTlH1f5BM2Nb0FDYO+p"
			+ "\r" + "ifdqqwIDAQAB" + "\r";

	private static String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmCbGCTC4E4Rxm95i/Q85Vmhkl"
			+ "\r"
			+ "Ja02o1Qysme9oxvsjN9wovn2uHYOSsfDB1ZU2R40L7NTUGgmw3zsXRgJpWul2Fsl5kVPeWg7dPYw"
			+ "\r"
			+ "jPeHQdQOMnFoCSpA11WL4l+ZGTx3O0+F1NgQ/HLe/MOb8B0CaQa5J6ibfXnibOWfG03a+kJffhaF"
			+ "\r"
			+ "VIlAe/sqkxiGcrOgxsZL3MVNO+W1yy9aqAqpWaZ+RLjzXYQm54mHo+jnU783TS2VlNpfPIsI4+Au"
			+ "\r"
			+ "k3m5yOVuAeCefPQf81HyhT+BUwEcBmEVh6idAmztNqGCwxl+f1NuG8/gqPEPIHsKxOUfV/kEzY1v"
			+ "\r"
			+ "QUNg76mJ92qrAgMBAAECggEAP6yl+28Vkt91kfNQC8GIJoHjNMC/LZ3zU0Hh0PL1aYEYy0xlCf3x"
			+ "\r"
			+ "oEeDVkLBE+bz01Wbss66Mmtzw/1rk39xyfcv9VfaqsDHfU28gB//aUqQdSVZImmpX5Z1AyE8Qi1Q"
			+ "\r"
			+ "Z/VB6PQYLGNz9hn/NhUeOudmIamY2pCDJpdWc+73q4sBkaOL2+W3E0cZT75vzoZQ90S7h6sfkX0G"
			+ "\r"
			+ "Erjw/9nUBJBy8+V1w7BSoK4+fcvrLqo8ffVo0PSYVNC57c/jd3+hogo7iKorvTsqwm4NMXTsto5x"
			+ "\r"
			+ "vXQxjvcfav0znZiN0Cc2wTMROkw47DBC1dZYbGuPO+w6Six5e6x0ZU/qWKDZQQKBgQDXiOsK/Szi"
			+ "\r"
			+ "WHfLvx2EDycqRlY1Kii3RTz3kcE5FI9HPa5isjNbUSG/F3wSNoVg/0AdzeWQbSixkZNau94Wur6i"
			+ "\r"
			+ "+97KLwD2qq8J6i/noZIv/P8OQvo/vwHYR/IFbyQf6e2CiK3FhhGyws151PQoqirsmyTlJJZAZWLf"
			+ "\r"
			+ "iZTZpe1/ywKBgQDFNddTXixeSBOz0Qjm49V1JhurlILa3JaKUol4iDbvYOh0t1CsjUfGjrkunDHX"
			+ "\r"
			+ "6P/T5ACOX+PmnzkjHW0ZSPAddx1A9Hy34o+MGON0GX8nj4p4YoalIW1k/jlGFcctkSoFVFg/RKop"
			+ "\r"
			+ "QQGz+XNde3Yr0UK7J0EXzlVRnwYmrbSkoQKBgErxrOjRR74cjOsntReqPTAR7P/nfOjdBmn/IHS8"
			+ "\r"
			+ "lWVsKSrgU8M43scXX2jl1FL57k1uvpgNnMzBlb9C++JjZM4/TiR3W5pplxuXdrjQEYjmK7nFyEZK"
			+ "\r"
			+ "IFYYDiudja4bJR7yb5nzGExUOCZYyd0p7mr/N0EGC8iweETKDhvv+jkpAoGAOR9iUoSZp2mLQ2+N"
			+ "\r"
			+ "+4sM3lT+eNGYoZp5hHFp3l7eQrI4Qu6CUKjPnITkwMp/aYHU8GQ/gP3nfnqqSzCP1F4bJv3EnHb0"
			+ "\r"
			+ "1TKrz7G52Hw2J5hdTIeFZrlq/XDh2BogymMc39RRh2n1O+PXgXEE6JQFY6XGhX1WTnX2oqDEYFm6"
			+ "\r"
			+ "VGECgYAcLBMZQyRUdKL9XYSdgFLm9YZOu/RGJYoXaHHZlacx5qXwcgz/hhxBnMy9UrMfGvdvx3Lj"
			+ "\r"
			+ "lJcAveFt+LgEMGsjU/LntN0QEM1cNrQVOl2MwtV2IeUqTLPJDwoFU1ZRPnzD4ehEidllyui6x2Ri"
			+ "\r" + "hozmpCbTKFYi/pK6GNJLEOLpMw==" + "\r";

	public static void main() throws InvalidKeyException,
			NoSuchAlgorithmException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		String data = "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试";

		String encrypt = AesAndRsaMixtureCryptoUtil.encrypt(data, PUBLIC_KEY);
		String decrypt = AesAndRsaMixtureCryptoUtil.decrypt(encrypt,PRIVATE_KEY);
		System.out.println(decrypt);

	}

}
