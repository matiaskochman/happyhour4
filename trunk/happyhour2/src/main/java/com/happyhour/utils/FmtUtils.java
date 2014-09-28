package com.happyhour.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import antlr.Utils;

public class FmtUtils {
	private static final Logger LOG = Logger.getLogger(FmtUtils.class);
	
	/**
     * html entity translation table
     */
    private static final String[][] escapteEntities = {
                                                          { "&lt;", "<" },
                                                          { "&gt;", ">" }
                                                      };

    /**
     * html entity translation table
     */
    private static final String[][] escapeCharacters = {
                                                           { "&amp;", "&" },
                                                           { "&quot;", "\"" },
                                                           { "&apos;", "'" },
                                                           { "&agrave;", "\u00E0" },
                                                           { "&Agrave;", "\u00C0" },
                                                           { "&acirc;", "\u00E2" },
                                                           { "&auml;", "\u00E4" },
                                                           { "&Auml;", "\u00C4" },
                                                           { "&Acirc;", "\u00C2" },
                                                           { "&aring;", "\u00E5" },
                                                           { "&Aring;", "\u00C5" },
                                                           { "&aelig;", "\u00E6" },
                                                           { "&AElig;", "\u00C6" },
                                                           { "&ccedil;", "\u00E7" },
                                                           { "&Ccedil;", "\u00c7" },
                                                           { "&eacute;", "\u00E9" },
                                                           { "&Eacute;", "\u00C9" },
                                                           { "&egrave;", "\u00E8" },
                                                           { "&Egrave;", "\u00C8" },
                                                           { "&ecirc;", "\u00EA" },
                                                           { "&Ecirc;", "\u00CA" },
                                                           { "&euml;", "\u00EB" },
                                                           { "&Euml;", "\u00CB" },
                                                           { "&iuml;", "\u00EF" },
                                                           { "&Iuml;", "\u00CF" },
                                                           { "&ocirc;", "\u00F4" },
                                                           { "&Ocirc;", "\u00d4" },
                                                           { "&ouml;", "\u00F6" },
                                                           { "&Ouml;", "\u00d6" },
                                                           { "&oslash;", "\u00f8" },
                                                           { "&Oslash;", "\u00d8" },
                                                           { "&szlig;", "\u00df" },
                                                           { "&ugrave;", "\u00F9" },
                                                           { "&Ugrave;", "\u00d9" },
                                                           { "&ucirc;", "\u00FB" },
                                                           { "&Ucirc;", "\u00db" },
                                                           { "&uuml;", "\u00FC" },
                                                           { "&Uuml;", "\u00dc" },
                                                           { "&nbsp;", " " },
                                                           { "&ndash;", "-" },
                                                           { "&reg;", "\u00a9" },
                                                           { "&copy;", "\u00ae" },
                                                           { "&euro;", "\u20a0" },
                                                           { "&icirc;", "\u00ee" },
                                                           { "&Icirc;", "\u00ce" },
                                                           { "&oacute;", "\u00f3" },
                                                           { "&aacute;", "\u00e1" },
                                                           { "&uacute;", "\u00fa" }
                                                       };
    private static final String[][] escapeUnicode = {
                                                        { "&#34;", "34" },
                                                        { "&#38;", "38" },
                                                        { "&#60;", "60" },
                                                        { "&#62;", "62" },
                                                        { "&#160;", "160" },
                                                        { "&#161;", "161" },
                                                        { "&#162;", "162" },
                                                        { "&#163;", "163" },
                                                        { "&#164;", "164" },
                                                        { "&#165;", "165" },
                                                        { "&#166;", "166" },
                                                        { "&#167;", "167" },
                                                        { "&#168;", "168" },
                                                        { "&#169;", "169" },
                                                        { "&#170;", "170" },
                                                        { "&#171;", "171" },
                                                        { "&#172;", "172" },
                                                        { "&#173;", "173" },
                                                        { "&#174;", "174" },
                                                        { "&#175;", "175" },
                                                        { "&#176;", "176" },
                                                        { "&#177;", "177" },
                                                        { "&#178;", "178" },
                                                        { "&#179;", "179" },
                                                        { "&#180;", "180" },
                                                        { "&#181;", "181" },
                                                        { "&#182;", "182" },
                                                        { "&#183;", "183" },
                                                        { "&#184;", "184" },
                                                        { "&#185;", "185" },
                                                        { "&#186;", "186" },
                                                        { "&#187;", "187" },
                                                        { "&#188;", "188" },
                                                        { "&#189;", "189" },
                                                        { "&#190;", "190" },
                                                        { "&#191;", "191" },
                                                        { "&#192;", "192" },
                                                        { "&#193;", "193" },
                                                        { "&#194;", "194" },
                                                        { "&#195;", "195" },
                                                        { "&#196;", "196" },
                                                        { "&#197;", "197" },
                                                        { "&#198;", "198" },
                                                        { "&#199;", "199" },
                                                        { "&#200;", "200" },
                                                        { "&#201;", "201" },
                                                        { "&#202;", "202" },
                                                        { "&#203;", "203" },
                                                        { "&#204;", "204" },
                                                        { "&#205;", "205" },
                                                        { "&#206;", "206" },
                                                        { "&#207;", "207" },
                                                        { "&#208;", "208" },
                                                        { "&#209;", "209" },
                                                        { "&#210;", "210" },
                                                        { "&#211;", "211" },
                                                        { "&#212;", "212" },
                                                        { "&#213;", "213" },
                                                        { "&#214;", "214" },
                                                        { "&#215;", "215" },
                                                        { "&#216;", "216" },
                                                        { "&#217;", "217" },
                                                        { "&#218;", "218" },
                                                        { "&#219;", "219" },
                                                        { "&#220;", "220" },
                                                        { "&#221;", "221" },
                                                        { "&#222;", "222" },
                                                        { "&#223;", "223" },
                                                        { "&#224;", "224" },
                                                        { "&#225;", "225" },
                                                        { "&#226;", "226" },
                                                        { "&#227;", "227" },
                                                        { "&#228;", "228" },
                                                        { "&#229;", "229" },
                                                        { "&#230;", "230" },
                                                        { "&#231;", "231" },
                                                        { "&#232;", "232" },
                                                        { "&#233;", "233" },
                                                        { "&#234;", "234" },
                                                        { "&#235;", "235" },
                                                        { "&#236;", "236" },
                                                        { "&#237;", "237" },
                                                        { "&#238;", "238" },
                                                        { "&#239;", "239" },
                                                        { "&#240;", "240" },
                                                        { "&#241;", "241" },
                                                        { "&#242;", "242" },
                                                        { "&#243;", "243" },
                                                        { "&#244;", "244" },
                                                        { "&#245;", "245" },
                                                        { "&#246;", "246" },
                                                        { "&#247;", "247" },
                                                        { "&#248;", "248" },
                                                        { "&#249;", "249" },
                                                        { "&#250;", "250" },
                                                        { "&#251;", "251" },
                                                        { "&#252;", "252" },
                                                        { "&#253;", "253" },
                                                        { "&#254;", "254" },
                                                        { "&#255;", "255" }
                                                    };
    private static final String UNICODE_CHAR_ENCODING = "UTF-8";
    private static final String BUNDLE_EXTENSION = "properties";
    private static final Control UNICODE_CONTROL = new UnicodeControl();

   /* private static final LoadingCache<MultiKey, ResourceBundle> RESOURCE_BUNDLE_CACHE = CacheBuilder.newBuilder()
    		.build(new CacheLoader<MultiKey, ResourceBundle>() {
				@Override
				public ResourceBundle load(MultiKey key) {
					return ResourceBundle.getBundle((String) key.getKey(0), (Locale) key.getKey(1), UNICODE_CONTROL);
				}
    		});*/
    
    /**
     * Formate un fichier XML pour la description des hotels
     */
    public static final String formateXML(String htmlStr) {
        if(isNullOrEmpty(htmlStr)) {
            return htmlStr;
        }

        try {
            // Transforme les "&" du fichier xml sous leur bonne forme
            // "&amp;apos;" --> "&apos;"
            //htmlStr = htmlStr.replaceAll("&amp;", "&");
            htmlStr = unescapeHTML(htmlStr, false);

            // Enleve les caract�res inutiles du type "&#x00", "&#x01" et les 
            // remplace par une chaine vide.
            String regExp = "&#x[A-F_0-9]{2}";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(htmlStr);
            htmlStr = m.replaceAll("");
        } catch(Exception e) {
        }

        return htmlStr;
    }

    private static boolean isNullOrEmpty(String htmlStr) {
    	
    	if(htmlStr == null){
    		return true;
    	}else{
    		if(htmlStr.isEmpty()){
    			return true;
    		}
    	}
    	return false;
    }

	/**
     * Get the ResourceBuncle for the current locale
     * @param locale
     *            la locale utilis�e
         * @return le ResourceBundle
     */
    public static ResourceBundle getBundle(Locale locale) {
        return getBundle("egencia.components.i18n.egencia-i18n", locale);
    }

    /**
     * Get the ResourceBuncle for the current locale and name
     * @param locale
     *            la locale utilis�e
         * @param name
     *            le nom du bundle
     * @return le ResourceBundle
     */
    public static ResourceBundle getBundle(String name, Locale locale) {

           return ResourceBundle.getBundle(name, locale, UNICODE_CONTROL);

    }


    /**
     * Translate the message key to message based on user locale
     * @param locale user locale
     * @param keyInput the key of the message to translate
     * @param parameters the parameters to pass to the formatter
     * @return the translated message
     */
    public static String message(Locale locale, String keyInput, Object[] parameters) {
        String msg = message(locale, keyInput);

        if(isNullOrEmpty(msg)) {
            return "[" + keyInput + "]";
        } else {
            return MessageFormat.format(msg, parameters);
        }
    }

    /**
     * Translate the message key to message based on user locale
     * @param locale user locale
     * @param keyInput the key of the message to translate
     * @return the translated message
     */
    public static String message(Locale locale, String keyInput) {
        if(locale == null) {
            locale = Locale.ENGLISH;
        }

        if((keyInput == null) || "".equals(keyInput)) {
            return "";
        }

        String message = "";

        ResourceBundle bundle = getBundle(locale);

        try {
            message = bundle.getString(keyInput);
        } catch(MissingResourceException mre) {
            //
            message = "[" + keyInput + "]";
        }

        return message;
    }
    
    /**
     * @param locale
     * @param keyInput
     * @param withDefault
     * @return
     */
    public static String message(Locale locale, String keyInput, boolean withDefault) {
    	String message = FmtUtils.message(locale, keyInput);
    	if (withDefault) {
	    	if (message.contains("["+keyInput+"]")) {
	    		return FmtUtils.message(Locale.ENGLISH, keyInput);
	    	}
    	}
    	return message;
    }


    public static String message(Locale locale, String keyInput, String bundleName) {
        if((keyInput == null) || "".equals(keyInput)) {
            return "";
        }

        String message = "";

        ResourceBundle bundle = getBundle(bundleName, locale);

        try {
            message = bundle.getString(keyInput);
        } catch(MissingResourceException mre) {
            //
            message = "[" + keyInput + "]";
        }

        return message;
    }

    /**
     * translate the input key in Unescaped HTML,
     * for exemple, "&agrave;" will be replaced by "�"
     * This method can be used by java code
     * @param locale the user locale
     * @param keyInput the key
     * @return translated value
     */
    public static String messageUnescapeHTML(Locale locale, String keyInput) {
        String result = message(locale, keyInput);

        return unescapeHTML(result);
    }

    /**
     * Unescape HTML entity elements,
     * for exemple, "&agrave;" will be replaced by �
     * @param htmlStr html String to be unescaped
     * @return unescaped String
     */
    public static final String unescapeHTML(String htmlStr) {
        return FmtUtils.unescapeHTML(htmlStr, true);
    }

    public static final String unescapeHTML(String htmlStr, boolean escapeEntities) {
        if(isNullOrEmpty(htmlStr)) {
            return htmlStr;
        }

        try {
            for(int i = 0; i < escapeCharacters.length; i++) {
                String[] escapeArray = escapeCharacters[i];
                htmlStr = htmlStr.replaceAll(escapeArray[0], escapeArray[1]);
            }

            if(escapeEntities) {
                for(int i = 0; i < escapteEntities.length; i++) {
                    String[] escapeArray = escapteEntities[i];
                    htmlStr = htmlStr.replaceAll(escapeArray[0], escapeArray[1]);
                }
            }

            for(int i = 0; i < escapeUnicode.length; i++) {
                String unicodeValue = String.valueOf(Character.valueOf((char) Integer.parseInt(escapeUnicode[i][1])));
                htmlStr = htmlStr.replaceAll(escapeUnicode[i][0], unicodeValue);
            }
        } catch(Exception e) {
        }

        return htmlStr;
    }

    private static class UnicodeControl extends Control {
		@Override
		public ResourceBundle newBundle(String baseName, Locale locale,
				String format, ClassLoader loader, boolean reload)
				throws IllegalAccessException, InstantiationException,
				IOException {
			// The below code is from the base class and only the PropertyResourceBundle
			// line is changed to read the file in Unicode.
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
			ResourceBundle bundle = null;
			InputStream stream = null;
			if (reload) {
				URL url = loader.getResource(resourceName);
				if (url != null) {
					URLConnection connection = url.openConnection();
					if (connection != null) {
						connection.setUseCaches(false);
						stream = connection.getInputStream();
					}
				}
			} else {
				stream = loader.getResourceAsStream(resourceName);
			}
			if (stream != null) {
				try {
					bundle = new PropertyResourceBundle(new InputStreamReader(
							stream, UNICODE_CHAR_ENCODING));
				} finally {
					stream.close();
				}
			}
			return bundle;
		}
	}
}
