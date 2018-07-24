package com.bloodbank.utils;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.vaadin.server.FileResource;

public class Utils {

	private static String basepath = "";

	public static void setBasePath(String path) {
		basepath = path;
	}

	public static String getImagePath() {
		return basepath + "/userImg";
	}

	public static String nullToEmptyString(Object obj) {
		if (obj == null) {
			return new String("default");
		} else if (obj instanceof String && obj != null) {
			return (String) obj;
		} else if (!(obj instanceof String) && obj != null) {
			throw new IllegalArgumentException("OBJ NOT A STRING !!");
		} else {
			System.out.println("VERIFICA AICI !");
			return new String("");
		}
	}

	public static FileResource getUserImage(String userID) {

		if (userID != null && userID != "default") {

			File userImg = new File(Utils.getImagePath() + "/user_" + userID + ".png");

			if (userImg.exists()) {
				FileResource userIconResource = new FileResource(userImg);
				// userIconResource.setCacheTime(0);
				return userIconResource;
			} else {

				FileResource userIconResource1 = new FileResource(new File(basepath + "/user2.png"));
				// userIconResource1.setCacheTime(0);
				return userIconResource1;
			}

		} else {
			FileResource userIconResource2 = new FileResource(new File(basepath + "/user2.png"));
			// userIconResource2.setCacheTime(0);

			return userIconResource2;
		}

	}

	public static List<String> imgAllowed() {
		List<String> allowed = new ArrayList<>();
		allowed.add("image/jpg");
		allowed.add("image/jpeg");
		allowed.add("image/png");
		return allowed;
	}

	public static File getImage(String resource) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		File file = new File(classLoader.getResource("/" + resource + ".png").getFile());
		if (file.exists())
			return file;
		else
			return new File(classLoader.getResource("/bloodDrop.png").getFile());

	}

	public static File getResourceBundle(String resource) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		File file = new File(classLoader.getResource("/" + resource + ".properties").getFile());
		return file;

	}

	public static boolean isSetupFilePresent() {

		File f = new File(basepath + "//configuration.ini");
		// System.out.println(f.getAbsoluteFile().getPath()+"
		// "+f.getAbsoluteFile().exists());
		return f.getAbsoluteFile().exists();// check
	}

	public static File getSetupFile() {

		return new File(basepath + "//configuration.ini");
	}

	public static String simpleEnc(String plain) {
		String b64encoded = Base64.getEncoder().encodeToString(plain.getBytes());

		// Reverse the string
		String reverse = new StringBuffer(b64encoded).reverse().toString();

		StringBuilder tmp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < reverse.length(); i++) {
			tmp.append((char) (reverse.charAt(i) + OFFSET));
		}
		return tmp.toString();
	}

	public static String simpleDec(String secret) {
		StringBuilder tmp = new StringBuilder();
		final int OFFSET = 4;
		for (int i = 0; i < secret.length(); i++) {
			tmp.append((char) (secret.charAt(i) - OFFSET));
		}

		String reversed = new StringBuffer(tmp.toString()).reverse().toString();
		return new String(Base64.getDecoder().decode(reversed));
	}

	public static int getDigitCount(BigInteger num) {
		double factor = Math.log(2) / Math.log(10);
		int digitCount = (int) (factor * num.bitLength() + 1);
		if (BigInteger.TEN.pow(digitCount - 1).compareTo(num) > 0) {
			return digitCount - 1;
		}
		return digitCount;
	}

	public static FileResource getThemeResourceIcon(String icon) {
		return new FileResource(Utils.getImage(icon));
	}

}
