package com.happyhour.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.w3c.tidy.Tidy;

import com.mysql.jdbc.Constants;

public class Utils {
	private static final Log LOG = LogFactory.getLog(Utils.class);
	private static final long MILLISECONDS_PER_DAY = 1000L * 60L * 60L * 24L;
	private static final String EMAIL_VALID_REGEX = "";
	
	
	private final static DateFormat ISO_WRITE_DATETIME_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private final static DateFormat ISO_READ_DATETIME_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		ISO_READ_DATETIME_Z.setTimeZone(TimeZone.getTimeZone("Zulu"));
	}

	public static String dateToString(final Date date){
		return ISO_WRITE_DATETIME_Z.format(date);
	}

	public static Date stringToDate(final String source){
		try {
			return ISO_READ_DATETIME_Z.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Transforms characters in the form &#xxx; into "normal" (java UTF-16)
	 * characters.
	 *
	 * @param str
	 *
	 * @return the UTF-16 string
	 */
	public static String NCR2UnicodeString(final String str) {
		StringBuffer ostr = new StringBuffer();
		int i1 = 0;
		int i2 = 0;

		while(i2 < str.length()) {
			i1 = str.indexOf("&#", i2);

			if(i1 == -1) {
				ostr.append(str.substring(i2, str.length()));

				break;
			}

			ostr.append(str.substring(i2, i1));
			i2 = str.indexOf(";", i1);

			if(i2 == -1) {
				ostr.append(str.substring(i1, str.length()));

				break;
			}

			String tok = str.substring(i1 + 2, i2);

			try {
				int radix = 10;

				if(tok.trim().charAt(0) == 'x') {
					radix = 16;
					tok = tok.substring(1, tok.length());
				}

				ostr.append((char) Integer.parseInt(tok, radix));
			} catch(NumberFormatException exp) {
				ostr.append('?');
			}

			i2++;
		}

		return ostr.toString();
	}

	/**
	 * M�thode qui ajoute tous les �l�ments d'un tableau � une collection
	 *
	 * @param source la collection source
	 * @param objects les �l�ments � ajouter
	 *
	 * @return la collection complete
	 */
	public static Collection<Object> addAllElements(final Collection<Object> source, final Object[] objects) {
		for (Object object : objects)
        {
			source.add(object);
		}

		return source;
	}

	public static boolean allCollectionsAreNullOrEmpty(final Collection<?>... collections) {
		for(Collection<?> collection : collections) {
			if((collection != null) && !collection.isEmpty()) {
				return false;
			}
		}

		return true;
	}


	/**
	 * DOCUMENT ME!
	 *
	 * @param year DOCUMENT ME!
	 * @param month DOCUMENT ME!
	 * @param day DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws ParseException DOCUMENT ME!
	 */
	public static Date buildDateFromStrings(final String year, final String month, final String day)
	throws ParseException {
		StringBuilder builder = new StringBuilder();

		if(day.length() < 2) {
			builder.append("0");
		}

		builder.append(day);

		if(month.length() < 2) {
			builder.append("0");
		}

		builder.append(month);
		builder.append(year);

		return getDateDDMMYYYY(builder.toString());
	}

	/**
	 * M�thode permettant de calculer le CRC d'une chaine
	 *
	 * @param s la chaine dont on veut calculer le CRC
	 *
	 * @return le CRC de la chaine
	 */
	public static int calculateCRC(final String s) {
		int crc = 0xFFFFFFFF;

		for(int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);

			for(int i = 0; i < 8; i++) {
				boolean c31 = ((crc >>> 31 & 1) == 1);
				boolean bit = ((c >>> (7 - i) & 1) == 1);
				crc <<= 1;

				/***************************************************************
				 * Uses irreducible polynomial: 1 + x + x^2 + x^4 + x^5 + x^7 +
				 * x^8 + x^10 + x^11 + x^12 + x^16 + x^22 + x^23 + x^26
				 *
				 * 0000 0100 1100 0001 0001 1101 1011 0111 0 4 C 1 1 D B 7
				 **************************************************************/
				if(c31 ^ bit) {
					crc ^= 0x04C11DB7;
				}
			}
		}

		return crc;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param cal DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Date calendarToDate(final Calendar cal) {
		return cal.getTime();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param cals DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Date[] calendarsToDates(final Calendar[] cals) {
		Date[] result = new Date[cals.length];
		int i = 0;

		for(Calendar cal : cals) {
			result[i++] = calendarToDate(cal);
		}

		return result;
	}

	/**
	 * Compare 2 dates. (A cause de M. Sun qui a d�cid� d'ajouter
	 * compareTo(Timestamp ts) dans java.sql.Timestamp dans le JDK 5).
	 *
	 * @param date1 la premi�re date
	 * @param date2 la deuxi�me date
	 *
	 * @return a negative integer, zero, or a positive integer as this object is less
	 *         than, equal to, or greater than the specified object.
	 *
	 * @see #compareTo()
	 */
	public static int compareDates(final Date date1, final Date date2) {
		long date1Time = date1.getTime();
		long date2Time = date2.getTime();

		return (date1Time < date2Time) ? (-1) : ((date1Time == date2Time) ? 0 : 1);
	}

	/**
	 * Verifie si une chaine contient des lettre en majuscules
	 * @param test la chaine a tester
	 * @return true s'il y a au moins une lettre en majuscule, false sinon
	 */
	public static boolean containsUpperCase(final String test) {
		return matchRegex(test, "[A-Z]+");
	}

	/**
	 * Corrige le html pour qu'il soit valide
	 * @param htmlText
	 * @return le html valide
	 */
	public static String correctHtml(final String htmlText) {
		if(htmlText == null) {
			return null;
		}

		LOG.debug("starting tidy");

		Reader reader = new StringReader(htmlText);
		Writer writer = new StringWriter();
		Tidy tidy = new Tidy();
		tidy.setXHTML(true);
		tidy.setShowWarnings(true);
		tidy.setPrintBodyOnly(true);
		tidy.setInputEncoding("UTF-8");
		tidy.parse(reader, writer);
		LOG.debug("done tidy");

		return writer.toString();
	}

	/**
	 * Corrige le html pour qu'il soit valide dans une r�ponse AJAX
	 * @param htmlText
	 * @return le html valide
	 */
	public static String correctHtmlForAjax(final String htmlText) {
		if(htmlText == null) {
			return null;
		}

		String result = correctHtml(htmlText);

		// On enleve la chaine '&quot;' car la methode unescapeHTML va la remplacer
		// par '"' ce qui risque de compromettre l'integrite du html
		result = result.replace("&quot;", "");

		// Dans une reponse AJAX, il faut que les accents soient dans leurs formes originales,
		// et non pas dans la forme Html (&eacute par exemple).
		// On les reconvertis donc dans la forme originale
		// car la methode correctHtml convertie les accents en format html.
		result = FmtUtils.unescapeHTML(result);

		// Au cas ou il y aurait encore des & non geres par unescapeHTML, on echappe le caratere &
		// pour plus de securite.
		return result.replace("&", "&amp;");
	}



	/**
	 * DOCUMENT ME!
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Calendar dateToCalendar(final Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);

		return result;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param dates DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static Calendar[] datesToCalendars(final Date[] dates) {
		Calendar[] result = new Calendar[dates.length];
		int i = 0;

		for(Date date : dates) {
			result[i++] = dateToCalendar(date);
		}

		return result;
	}

	/** Read the object from Base64 string. */
	public static Object decodeStringToObject(final String s)
	throws IOException, ClassNotFoundException {
		byte[] data = Base64.decodeBase64(s.getBytes());
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();

		return o;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param query DOCUMENT ME!
	 * @param pattern DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static StringBuilder deleteLastPatternIfExists(final StringBuilder query, final String pattern) {
		if(query.toString().endsWith(pattern)) {
			query.delete(query.length() - pattern.length(), query.length());
		}

		return query;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param fileInputStream DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws IOException DOCUMENT ME!
	 * @throws ClassNotFoundException DOCUMENT ME!
	 */
	public static Object deserialize(final FileInputStream fileInputStream)
	throws IOException, ClassNotFoundException {
		ObjectInputStream is = null;

		try {
			is = new ObjectInputStream(new BufferedInputStream(fileInputStream));

			Object ret = is.readObject();

			return ret;
		} finally {
			if(is != null) {
				is.close();
			}
		}
	}

	/** Write the object to a Base64 string. */
	public static String encodeObjectToString(final Serializable o)
	throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();

		return new String(Base64.encodeBase64(baos.toByteArray()));
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param obj1 DOCUMENT ME!
	 * @param obj2 DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static boolean equals(final Object obj1, final Object obj2) {
		if(obj1 == null) {
			if(obj2 == null) {
				return true; // les deux valent nulls
			}

			return false; // 1 des deux vaut null seulement
		}

		if(obj2 == null) {
			return false; // 1 des deux vaut null seulement
		}

		return obj1.equals(obj2); // Aucun des deux ne vaut null, on fait la comparaison classique
	}

	/*
	 * this method fixes bug 137, avoid fail data store when a user name /*
	 * contains an quote : '
	 */

	/**
	 * DOCUMENT ME!
	 *
	 * @param name DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String escapeAphostrophe(final String name) {
		if(name == null) {
			return null;
		}

		return name.replaceAll("'", "\\\\'");
	}

	public static String escapeApostropheInAlert(final String result) {
		if(StringUtils.isEmpty(result)) {
			return result;
		}

		int beginIndex = StringUtils.indexOf(result, "alert('");

		if(beginIndex < 0) {
			return result;
		}

		beginIndex += "alert('".length();

		int endIndex = StringUtils.indexOf(result, "');", beginIndex);

		if(endIndex < 0) {
			return result;
		}

		String alertContent = result.substring(beginIndex, endIndex);
		String escapeAlertContent = StringUtils.replace(alertContent, "'", "\\'");

		String tmp = StringUtils.replace(result, alertContent, escapeAlertContent);

		return StringUtils.replace(tmp, "\\\\'", "\\'");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param number DOCUMENT ME!
	 * @param numberOfDecimals DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String formatNumberWithNDecimals(final double number, final int numberOfDecimals) {
		DecimalFormat f = new DecimalFormat();
		f.setMinimumFractionDigits(numberOfDecimals);
		f.setMaximumFractionDigits(numberOfDecimals);
		f.setGroupingUsed(false);

		return f.format(number).replaceAll(",", ".");
	}

	/**
	 * Formatte un texte de fa�on � ce qu'il soit valide pour etre affich� en
	 * Ajax
	 *
	 * @param text
	 *
	 * @return
	 */
	public static String formatTextAjax(final String text) {
		if(text == null) {
			return null;
		}

		return text.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
	}

	/**
	 * Prend un texte en entr�e et le formate pour que l'affichage des lignes ne
	 * d�passent pas la taille sp�cifi�e en parametre
	 *
	 * @param text la chaine de caract�re intitiale
	 * @param width taille maximum de la ligne
	 *
	 * @return la chaine format�e
	 */
	public static String formatTextRowSize(final String text, final int width) {
		if(text != null) {
			StringBuffer buf = new StringBuffer(text);
			int lastspace = -1;
			int linestart = 0;
			int i = 0;

			while(i < buf.length()) {
				if(buf.charAt(i) == ' ') {
					lastspace = i;
				}

				if(buf.charAt(i) == '\n') {
					lastspace = -1;
					linestart = i + 1;
				}

				if(i > ((linestart + width) - 1)) {
					if(lastspace != -1) {
						buf.setCharAt(lastspace, '\n');
						linestart = lastspace + 1;
						lastspace = -1;
					} else {
						buf.insert(i, '\n');
						linestart = i + 1;
					}
				}

				i++;
			}

			return buf.toString();
		}

		return "";
	}

	/**
	 * R�cup�re une date � partir d'un calendrier XML
	 *
	 * @param c le calendrier
	 *
	 * @return la date
	 */
	public static Date fromCalendarToDate(final XMLGregorianCalendar c) {
		if(c == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(c.getYear(), c.getMonth() - 1, c.getDay(), c.getHour(), c.getMinute(), 0);
		cal.set(Calendar.MILLISECOND, 0); // C'est mieux d'�tre pile poil � l'heure � la milliseconde pr�s !

		return cal.getTime();
	}

	/**
	 * R�cup�re une date � partir d'un calendrier XML
	 *
	 * @param c le calendrier
	 *
	 * @return la date
	 */
	public static Date fromCalendarToDateWithSecondAndMilli(final XMLGregorianCalendar c) {
		if(c == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(c.getYear(), c.getMonth() - 1, c.getDay(), c.getHour(), c.getMinute(), c.getSecond());
		cal.set(Calendar.MILLISECOND, c.getMillisecond());

		return cal.getTime();
	}

	/**
	 * R�cup�re une date � partir d'un calendrier XML
	 *
	 * @param c le calendrier
	 *
	 * @return la date
	 */
	public static Date fromCalendarToDateWithoutHour(final XMLGregorianCalendar c) {
		if(c == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(c.getYear(), c.getMonth() - 1, c.getDay(), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0); // C'est mieux d'�tre pile poil �
		// l'heure � la milliseconde pr�s !

		return cal.getTime();
	}

	/**
	 * R�cup�re une date � partir de deux calendriers XML compl�mentaires
	 *
	 * @param date le calendrier contenant la date
	 * @param time le calendrier contenant l'heure
	 *
	 * @return la date
	 */
	public static Date fromCalendarsToDate(final XMLGregorianCalendar date, final XMLGregorianCalendar time) {
		if(date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth() - 1, date.getDay(), time.getHour(), time.getMinute(), 0);
		cal.set(Calendar.MILLISECOND, 0); // C'est mieux d'�tre pile poil � l'heure � la milliseconde pr�s !

		return cal.getTime();
	}

	/**
	 *
    DOCUMENT ME!
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return une calendrier gr�gorien pour le XML
	 */
	public static XMLGregorianCalendar fromDateToXMLCalendar(final Date date) {
		if(date == null) {
			return null;
		}

		try {
			GregorianCalendar gCalendar = new GregorianCalendar();
			gCalendar.setTime(date);
			
			XMLGregorianCalendar xgCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
			xgCalendar.setTimezone(0);

			return xgCalendar;
		} catch (DatatypeConfigurationException e) {
			LOG.error("Cannot convert Date to XMLGregorianCalendar", e);
			return null;
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param d1 DOCUMENT ME!
	 * @param d2 DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static long getAbsoluteNumberOfDaysFromDate(final Date d1, final Date d2) {
		if(d1.after(d2)) {
			return -getAbsoluteNumberOfDaysFromDate(d2, d1);
		}

		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		long aFromOffset = c1.get(Calendar.DST_OFFSET);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		long aToOffset = c2.get(Calendar.DST_OFFSET);

		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		int numberOfDays = 0;

		do {
			if(c1.equals(c2)) {
				return numberOfDays;
			}

			numberOfDays++;
			c1.add(Calendar.DATE, 1);
		} while(numberOfDays < 1000);

		long t1 = (c1.getTimeInMillis() + aFromOffset);
		long t2 = (c2.getTimeInMillis() + aToOffset);
		long aDayDiffInMili = t2 - t1;

		return (aDayDiffInMili / MILLISECONDS_PER_DAY);
	}

	/**
	 * Get Date properties day month year format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateDDMMYYYY(final Date date) {
		return getFormattedDate(date, "dd/MM/yyyy");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param jjmmyyyy DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws ParseException DOCUMENT ME!
	 */
	public static Date getDateDDMMYYYY(final String jjmmyyyy)
	throws ParseException {
		if(jjmmyyyy == null) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

		return formatter.parse(jjmmyyyy);
	}

	/**
	 * Get Date properties day month year format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateDDMMYYYY2(final Date date) {
		return getFormattedDate(date, "ddMMyyyy");
	}

	/**
	 * Get Date properties day month year format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateDDMMYYYYHHMMSS(final Date date) {
		return getFormattedDate(date, "dd/MM/yyyy HH:mm");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param jjmmyyyy DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws ParseException DOCUMENT ME!
	 */
	public static Date getDateDDMMYYYYWithSlash(final String jjmmyyyy)
	throws ParseException {
		if(jjmmyyyy == null) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		return formatter.parse(jjmmyyyy);
	}

	/**
	 * Get Date properties day month year format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateDotYYYYMMDD(final Date date) {
		return getFormattedDate(date, "yyyy-MM-dd");
	}

	/**
	 * Get Date properties hour format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateHH(final Date date) {
		return getFormattedDate(date, "HH");
	}

	/**
	 * Get HH H MM display format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateHHHMM(final Date date) {
		String hhmm = getDateHH(date) + " H " + getDateMM(date);

		if(hhmm.equals("00 H 00")) {
			return "";
		}

		return hhmm;
	}

	/**
	 * Get HHMM display format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateHHMM(final Date date) {
		String hhmm = getDateHH(date) + getDateMM(date);

		if(hhmm.equals("0000")) {
			return "";
		}

		return hhmm;
	}

	/**
	 * Get Date properties minute format
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDateMM(final Date date) {
		return getFormattedDate(date, "mm");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param jjmmyyyy DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws ParseException DOCUMENT ME!
	 */
	public static Date getDateYYYYMMDD(final String jjmmyyyy)
	throws ParseException {
		if(Utils.isNullOrEmpty(jjmmyyyy)) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		return formatter.parse(jjmmyyyy);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param d DOCUMENT ME!
	 * @param format DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getFormattedDate(final Date d, final String format) {
		if(d == null) {
			return "";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {
			return formatter.format(d);
		} catch(Exception ex) {
			return "NA";
		}
	}

	public static String getFullDate(final Date date, final Locale locale) {
		try {
			DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, locale);

			return formatter.format(date);
		} catch(Exception ex) {
			return "NA";
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param d DOCUMENT ME!
	 * @param locale DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getLocalizedFormattedDate(final Date d, final Locale locale) {
		try {
			DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);

			return formatter.format(d);
		} catch(Exception ex) {
			return "NA";
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param d DOCUMENT ME!
	 * @param locale DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getLocalizedFormattedDateTime(final Date d, final Locale locale) {
		String date = "NA";

		try {
			DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
			date = formatter.format(d);
			formatter = DateFormat.getTimeInstance(DateFormat.SHORT, locale);

			return date + " " + formatter.format(d);
		} catch(Exception ex) {
			return date;
		}
	}

	/**
	 * Get now
	 *
	 * @return DOCUMENT ME!
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * Return the begining of a string, ending with ... if it exceeds the limit
	 * @param originalString
	 * @param limit
	 * @return
	 */
	public static String getShorterString(final String originalString, final int limit) {
		if((originalString != null) && (originalString.length() > limit)) {
			return originalString.substring(0, limit - 3) + "...";
		}

		return originalString;
	}

	/**
	 * Retourne un stream sous forme de chaine
	 *
	 * @param is le stream � r�cup�rer
	 *
	 * @return une chaine de caract�re stockant le contenu du stream
	 *
	 * @exception IOException en cas de probl�mes de lecture
	 */
	public static String getStreamAsString(final InputStream is, final boolean fixXml)
	throws IOException {
		char[] buffer = new char[1024];

		// HotelFilterInputStream stream = new HotelFilterInputStream(is);
		StringWriter bos = new StringWriter();
		InputStreamReader ir = new InputStreamReader(is, "UTF-8");
		int length = 0;

		do {
			// length = stream.read(buffer);
			length = ir.read(buffer);

			if(length > 0) {
				for(int i = 0; i < buffer.length; i++) {
					if(buffer[i] < 32) {
						buffer[i] = 32;
					}
				}

				bos.write(buffer, 0, length);
			}
		} while(length > 0);

		if (fixXml) {
			return FmtUtils.formateXML(bos.toString());
		}
		else {
			return bos.toString();
		}
	}

	/**
	 * Retourne le contenu d'un flux dans un StringBuilder.
	 *
	 * @param inputStream le flux
	 *
	 * @return le contenu du flux
	 *
	 * @throws IOException en cas de probl�me sur le flux
	 */
	public static StringBuilder getStreamContent(final InputStream inputStream)
	throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String str;

		while((str = in.readLine()) != null) {
			stringBuilder.append(str);
			stringBuilder.append("\n");
		}

		in.close();

		return stringBuilder;
	}

	/**
	 * Get today (now without hour and seconds)
	 *
	 * @return DOCUMENT ME!
	 */
	public static Date getToday() {
		Date now = getNow();

		try {
			return getDateDDMMYYYY(getFormattedDate(now, "ddMMyyyy"));
		} catch(ParseException e) {
			return now;
		}
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getYYYYMMDD(final Date date) {
		return getFormattedDate(date, "yyyyMMdd");
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param date DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String getDDMMYYYYSlash(Date date) {
		return getFormattedDate(date, "dd/MM/yyyy");
	}
	public static String getYYYYMMDDSlash(final Date date) {
		return getFormattedDate(date, "dd/MM/yyyy");
	}

	/*
	 * this method fixes bug 137, avoid fail data store when a user name /*
	 * contains an quote : '
	 */

	/**
	 * DOCUMENT ME!
	 *
	 * @param name DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String insertAphostrophe(final String name) {
		if(name == null) {
			return null;
		}

		return name.replaceAll("'", "''");
	}

	/**
	 * Verifie si une chaine est au format adresse mail ou pas
	 * @param string la chaine a verifier
	 * @return true si la chaine est au format adresse mail
	 */
	public static boolean isEmailLike(final String stringToTest) {
		return matchRegex(stringToTest, EMAIL_VALID_REGEX);
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param number DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static boolean isLuhnValidNumber(final String number) {
		int sum = 0;

		boolean alternate = false;

		for(int i = number.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(number.substring(i, i + 1));

			if(alternate) {
				n *= 2;

				if(n > 9) {
					n = (n % 10) + 1;
				}
			}

			sum += n;
			alternate = !alternate;
		}

		return ((sum % 10) == 0);
	}

	/**
	 * Teste si un tableau est nul ou vide
	 *
	 * @param a le tableau d'objets � tester
	 *
	 * @return true si le tableau est vide ou nul
	 */
	public static boolean isNullOrEmpty(final Object[] a) {
		return (a == null) || (a.length == 0);
	}

	/**
	 * Teste si une collection est nulle ou vide
	 *
	 * @param c la collection � tester
	 *
	 * @return true si la collection est vide ou nulle
	 */
	public static boolean isNullOrEmpty(final Collection<?> c) {
		return (c == null) || c.isEmpty();
	}

	/**
	 * Teste si une chaine est nulle ou vide
	 *
	 * @param s la chaine � tester
	 *
	 * @return true si la chaine est vide ou nulle
	 */
	public static boolean isNullOrEmpty(final String s) {
		return nullIsEmpty(s).equals("");
	}

	/**
	 * Teste si une chaine est nulle ou vide
	 *
	 * @param s la chaine � tester
	 *
	 * @return true si la chaine est vide ou nulle
	 */
	public static boolean isNullOrEmpty(final Object s) {
		return (s == null) || isNullOrEmpty(s.toString());
	}

	/**
	 * V�rifie que 2 Calendar sont dans le m�me mois.
	 *
	 * @param cal1 le premier Calendar, ne peut �tre null
	 * @param cal2 le premier Calendar, ne peut �tre null
	 * @return true s'ils repr�sente le m�me mois
	 */
	public static boolean isSameMonth(final Calendar cal1, final Calendar cal2) {
		if((cal1 == null) || (cal2 == null)) {
			throw new IllegalArgumentException("The date must not be null");
		}

		return ((cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)) &&
				(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) &&
				(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)));
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param value DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static boolean isToday(final Date value) {
		return getAbsoluteNumberOfDaysFromDate(value, new Date()) == 0;
	}

	/**
	 * Ins�rer des retours de ligne HTML (<br/>) dans un texte afin de limiter plus ou moins la largeur
	 * du paragraphe form� lors d'une insertion dans une page web
	 * NB : le message n'est pas suppos� d�j� contenir des retours de ligne
	 *
	 * @param message
	 * @param maxLength
	 * @return
	 */
	public static String limitHtmlTextLength(String message, final int maxLength) {
		StringBuffer sb = new StringBuffer();

		while(message.length() > maxLength) {
			int pos = message.indexOf(' ', maxLength);

			if(pos == -1) {
				break; // inutile de continuer
			}

			sb.append(message.substring(0, pos)); // c�sure
			sb.append("<br/>"); // ajout du saut de ligne

			message = message.substring(pos + 1); // retrait de l'espace
		}

		sb.append(message);

		return sb.toString();
	}

	/**
	 * Charge le {@link Reader} dans une String
	 *
	 * @param reader le {@link Reader} � lire
	 *
	 * @return le contenu du {@link Reader}
	 *
	 * @throws IOException
	 */
	public static String loadToString(final Reader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		char[] cbuf = new char[256];
		int nbRead;

		while((nbRead = reader.read(cbuf)) != -1) {
			sb.append(cbuf, 0, nbRead);
		}

		return sb.toString();
	}

	public static void main(final String[] args) {
		String test = removeHtmlTagsFromString(FmtUtils.message(Locale.FRENCH, "eotg_alerts_note_fr"));
		System.out.println(test);
	}

	/**
	 * Verifie si une chaine matche ou nom une expression reguliere
	 * @param la chaine a tester
	 * @param la regex a tester
	 * @return le resultat du test
	 */
	public static boolean matchRegex(final String stringToTest, final String regex) {
		Matcher match;
		Pattern p;
		p = Pattern.compile(regex);
		match = p.matcher(stringToTest);

		return match.matches();
	}

	/**
	 * Convertie des miles en KM
	 *
	 * @param distance la distance � convertir
	 *
	 * @return la distance convertie
	 */
	public static double milesToKm(final double distance) {
		double convertedDistance = distance * 1.609344d;
		LOG.debug("MileToKm => " + distance + " miles = " + convertedDistance + " KM");

		return round(convertedDistance, 1);
	}

	/**
	 * parse un double et le convertie de miles vers KM
	 *
	 * @param distance la distance � convertir
	 *
	 * @return la distance convertie
	 */
	public static double milesToKm(final String distance) {
		return milesToKm(parseDoubleWithoutException(distance));
	}

	/**
	 * Renvoie le m�lange d'une chaine de caract�res
	 *
	 * @param stringToMix
	 * @param rand
	 *
	 * @return la chaine des caract�res m�lang�s
	 */
	public static String mixString(final String stringToMix, final Random rand) {
		List<Character> charList = new ArrayList<Character>();

		for(char c : stringToMix.toCharArray()) {
			charList.add(Character.valueOf(c));
		}

		Collections.shuffle(charList, rand);

		StringBuffer sb = new StringBuffer(charList.size());

		for(Character c : charList) {
			sb.append(c);
		}

		return sb.toString();
	}

	/**
	 * Retourne le nombre de jours entre 2 dates.
	 *
	 * @param date1 la premi�re date
	 * @param date2 la deuxi�me date
	 *
	 * @return le nombre de jours entre les 2 dates
	 */
	public static long absNbDaysBetweenDates(Date d1, Date d2) {
		return Math.abs(Days.daysBetween(new DateMidnight(d1), new DateMidnight(d2)).getDays());
	}

	public static long nbDaysBetweenDates(Date d1, Date d2) {
		return Days.daysBetween(new DateMidnight(d1), new DateMidnight(d2)).getDays();
	}


	/**
	 * Retourne le nombre d'heures entre 2 dates.
	 *
	 * @param date1 la premi�re date
	 * @param date2 la deuxi�me date
	 *
	 * @return le nombre d'heures entre les 2 dates
	 */
	public static float nbHoursBetweenDates(final Date date1, final Date date2) {
		if((date2.getTime() - date1.getTime()) > 0) {
			return ((date2.getTime() - date1.getTime()) / 1000.0f / 3600.0f);
		}

		return 0;
	}

	/**
	 * Retourne une chaine vide si la chaine est nulle.
	 *
	 * @param s la chaine a tester
	 *
	 * @return s ou ""
	 */
	public static String nullIsEmpty(final String s) {
		if(s == null) {
			return "";
		}

		return s.trim();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param s DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 */
	public static String nullIsEmpty(final Enum<?> s) {
		if(s == null) {
			return "";
		}

		return s.name();
	}

	/**
	 * Returns -1.00 if s is not double parsable
	 *
	 * @param s le double � parser
	 *
	 * @return la valeur pars�e ou -1
	 */
	public static double parseDoubleWithoutException(final String s) {
		try {
			return Double.parseDouble(s);
		} catch(Exception e) {
			LOG.error("Impossible de convertir en double :" + s + ". " + e);

			return -1.0d;
		}
	}

	/**
	 * Returns -1.00 if s is not float parsable
	 *
	 * @param s le float � parser
	 *
	 * @return la valeur pars�e ou -1
	 */
	public static float parseFloatWithoutException(final String s) {
		try {
			return Float.parseFloat(s);
		} catch(Exception e) {
			LOG.error("Impossible de convertir en float :" + s + ". " + e);

			return -1.0f;
		}
	}

	/**
	 * Returns -1 if s is not long parsable
	 *
	 * @param s le long � parser
	 *
	 * @return la valeur pars�e ou -1
	 */
	public static int parseIntWithoutException(final String s) {
		try {
			return Integer.parseInt(s);
		} catch(Exception e) {
			LOG.error("Impossible de convertir en int :" + s + ". " + e);

			return -1;
		}
	}

	/**
	 * Returns -1 if s is not long parsable
	 *
	 * @param s le long � parser
	 *
	 * @return la valeur pars�e ou -1
	 */
	public static long parseLongWithoutException(final String s) {
		try {
			return Long.parseLong(s);
		} catch(Exception e) {
			LOG.error("Impossible de convertir en long :" + s + ". " + e);

			return -1;
		}
	}


	/**
	 * suppression des balises HTML dans un texte donn� ; conversion en texte simple
	 * le contenu des balises n'est pas effac�, c'est juste la "mise en forme" HTML
	 *
	 * @param text
	 *                 le texte � expurger de son code HTML
	 *
	 * @return un texte sans balises HTML
	 */
	public static String removeHtmlTagsFromString(final String text) {
		String result = text;

		if(text != null) {
			if(text.contains("<")) {
				// sauts de ligne
				result = result.replaceAll("(?i)<br[^>]*>", "\n");
				// balises
				result = result.replaceAll("<[^>]*>", "");
			}

			// entities
			result = result.replaceAll("&#x27;", "'").replaceAll("&nbsp;", " ");
		}

		return result;
	}

	/**
	 * Arrondi un double � n chiffres apr�s la virgule <br>
	 * Ex: d=124.4567 digits=0 => 124.0, digits=1 => 124.5, digits=2 => 124.46
	 *
	 * @param d : le nombre � arrondir au format double
	 * @param digits : le nombre de chiffres apr�s la virgule (0 � n)
	 *
	 * @return : le nombre arrondi au format double
	 */
	public static double round(final double d, final int digits) {
		int shift = 1;

		try {
			for(int i = 0; i < digits; i++) {
				shift *= 10;
			}

			return ((double) Math.round(d * shift)) / shift;
		} catch(Exception e) {
			return d;
		}
	}

	/**
	 * Arrondi un float � n chiffres apr�s la virgule <br>
	 * Ex: d=124.4567 digits=0 => 124.0, digits=1 => 124.5, digits=2 => 124.46
	 *
	 * @param d : le nombre � arrondir au format float
	 * @param digits : le nombre de chiffres apr�s la virgule (0 � n)
	 *
	 * @return : le nombre arrondi au format float
	 */
	public static float round(final float d, final int digits) {
		int shift = 1;

		for(int i = 0; i < digits; i++) {
			shift *= 10;
		}

		return ((float) Math.round(d * shift)) / shift;
	}

	/**
	 * parse et arroundi un double
	 *
	 * @param d : le nombre � arrondir au format double
	 * @param digits : le nombre de chiffres apr�s la virgule (0 � n)
	 *
	 * @return : le nombre arrondi au format String
	 */
	public static double round(final String d, final int digits) {
		return round(parseDoubleWithoutException(d), digits);
	}

	/**
	 * Enl�ve tous le code html d'une chaine.
	 * @param param la chaine � n�toyer
	 * @return la chaine n�toy�e.
	 */
	public static String sanitizeHtml(final String param) {
		if((param == null) || (param.length() == 0)) {
			return param;
		}

		String tmp = FmtUtils.unescapeHTML(param, false);
		tmp = tmp.replace("<", "&lt;");
		tmp = tmp.replace(">", "&gt;");

		return tmp;
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @param fileOutputStream DOCUMENT ME!
	 * @param data DOCUMENT ME!
	 *
	 * @throws IOException DOCUMENT ME!
	 */
	public static void serialize(final FileOutputStream fileOutputStream, final Object data)
	throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
		os.writeObject(data);
		os.close();
		fileOutputStream.close();
	}

	/**
	 * split
	 *
	 * @param toSplit the String to split
	 * @param delim the splitting delimitor
	 *
	 * @return an ArrayList of the trimed elements (retrieved with a StringTokenizer)
	 *
	 * @deprecated String#split + Arrays#asList ?
	 */
	@Deprecated
    public static List<String> split(final String toSplit, final String delim) {
		List<String> a = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(toSplit, delim);

		while(st.hasMoreTokens()) {
			a.add(st.nextToken().trim());
		}

		return a;
	}

	/**
	 * build a string array from email List separated by ";"
	 *
	 * @param list
	 *            email List separated by ";"
	 */
	public static String[] toStringArray(final String list) {
		if(!Utils.isNullOrEmpty(list)) {
			return list.split(";");
		}

		return null;
	}

	/**
	 * Retourne une liste unique de string
	 * @param list la liste de depart
	 * @return une liste unique de string
	 */
	public static List<String> uniqueImageLinks(final List<String> list) {
		List<String> result = new ArrayList<String>();

		for(String string : list) {
			if(!Utils.isNullOrEmpty(string) && !result.contains(string.trim())) {
				result.add(string.trim());
			}
		}

		return result;
	}

	/**
	 * Concat�ne un tableau de cha�nes sous forme de chaine CSV
	 *
	 * @param tokens le tableau (ou la liste, la collection) des �lements � concat�ner
	 * @param delim le delimiteur
	 *
	 * @return la chaine CSV
	 *
	 * @see Utils#unsplit(String, String...) si on n'a pas de liste
	 */
	public static String unsplit(final Iterable<String> tokens, final String delim) {
		StringBuffer sb = new StringBuffer();

		for(String s : tokens) {
			sb.append(s).append(delim);
		}

		if(sb.length() > delim.length()) {
			sb.deleteCharAt(sb.length() - delim.length());
		}

		return sb.toString().trim();
	}

	/**
	 * Concat�ne un tableau de cha�nes sous forme de chaine CSV
	 *
	 * @param delim le delimiteur
	 *
	 * @return la chaine
	 *
	 * @see Utils#unsplit(List, String) mais on n'a pas ici besoin de cr�er de liste
	 */
	public static String unsplit(final String delim, final String... tokens) {
		StringBuffer sb = new StringBuffer();

		for(String s : tokens) {
			sb.append(s).append(delim);
		}

		if(sb.length() > delim.length()) {
			sb.deleteCharAt(sb.length() - delim.length());
		}

		return sb.toString().trim();
	}

	/**
	 * Retourne le prix moyen d'une liste
	 *
	 * @param c
	 *            la collection sur laquelle on veut calculer le prix
	 * @return le prix moyen
	 */
	public static Double getAverage(final Collection<Double> c) {
		Iterator<Double> it = c.iterator();
		double somme = 0;
		int nbElem = 0;
		Double nr = null;

		while(it.hasNext()) {
			nr = it.next();
			somme += nr.doubleValue();
			nbElem++;
		}

		return new Double(somme / nbElem);
	}

	/**
	 *
	 * Removes the (0) from the phone numbers starting with +44
	 * when the phone number not starting with +44 then just the paretheses are removed.
	 *
	 *
	 * @param phoneNumber
	 * @return the fixed phone number without parentheses
	 */
	//Bug 13164
	public static String suppressParenthesesForPhoneNumbers(final String phoneNumber) {
		if (phoneNumber != null) {
			String tmpNum = phoneNumber.trim();

			if (tmpNum.startsWith("+44")) {
				tmpNum = tmpNum.replaceAll("\\(\\s*0\\s*\\)", "");
			} else {
				tmpNum = tmpNum.replaceAll("\\(", "");
				tmpNum = tmpNum.replaceAll("\\)", "");
			}
			return tmpNum;
		}
		return null;
	}

	public static boolean checkIntervalForBigDecimal(BigDecimal value, int low, int high) {
    	if(value==null) {
    		return false;
    	}
    	BigDecimal lower = new BigDecimal(low);
		BigDecimal higher = new BigDecimal(high);
		try {
			return value.compareTo(lower)>0 && value.compareTo(higher)<0;
		} catch (Exception e) {
			LOG.error("Unable to check value " + value + "for interval [" + low + " ; " + high);
			return false;
		}
    }

	public static String toStringOneZero(boolean bool) {
		return BooleanUtils.toString(bool, "1", "0");
	}

	public static BigDecimal addAll(BigDecimal...bigDecimals) {
		if (bigDecimals==null || bigDecimals.length<=0) {
			return null;
		}

		BigDecimal sum = bigDecimals[0];
		for (int i=1 ; i<bigDecimals.length ; i++) {
			sum = sum.add(bigDecimals[i]);
		}
		return sum;
	}

	/**
	 * Cuts a list into sublists of length L
	 *
	 * @return an unmodifiable list
	 */
	public static <T> List<List<T>> cutList(List<T> list, final int L) {
		List<List<T>> subLists = new ArrayList<List<T>>();
		int N = list.size();

		for (int i = 0; i < N; i += L) {
			subLists.add(
				list.subList(i, Math.min(N, i + L))
			);
		}

		return subLists;
	}
	
	/**
	 * Same as String#format(String format, Object ... args), except this rule :
	 * if one of the <b>args</b> objects is null, it is formatted as "" instead of "null".
	 */
	public static String formatNullToEmpty(String format, Object ... args) {
        List<Object> objects = new ArrayList<Object>();
        
        for (Object object : args) {
            object = (object != null) ? object : "";
            
            objects.add(object);
        }
        
        return String.format(format, objects.toArray());        
    }
	
	public static boolean isPositive(Integer l) {
		return l != null && l.intValue()>NumberUtils.INTEGER_ZERO;
	}

	public static String extractUrlHostName(String string) {
		Pattern p = Pattern.compile(".*(https|http)://(.*)");
		Matcher m = p.matcher(string);
		
		if (m.matches()) {
			String url = m.group(m.groupCount());
			if (StringUtils.contains(url, "/")) {
				url = StringUtils.substring(url, 0, url.indexOf('/'));
			}
			
			return url;
		}
		
		return "";
	}


	
	public static String getBaseUrl( HttpServletRequest request ) {
	    if ( ( request.getServerPort() == 80 ) ||
	         ( request.getServerPort() == 443 ) )
	      return request.getScheme() + "://" +
	             request.getServerName() +
	             request.getContextPath();
	    else
	      return request.getScheme() + "://" +
	             request.getServerName() + ":" + request.getServerPort() +
	             request.getContextPath();
	  }
	
	public static Date getVanExpirationDate(Date date, int noOfDays){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, noOfDays);
		return cal.getTime();
	}
	
	public static int getMaxAuthAmountWithTolerance(double dossierTotalAmount,
			float maxAuthAmtPercent) {
		double maxAuthAmount = dossierTotalAmount
				+ ((dossierTotalAmount * maxAuthAmtPercent) / 100);
		int finalAmount = (int) (maxAuthAmount * 100);
		return finalAmount;
	}
	
	public static String transformJavaReturnCarriageToHtml(String s) {
		if (StringUtils.isNotBlank(s)) {
			s = s.replaceAll("(\r\n|\n\r|\r|\n)", "<br />");
		}
		
		return s;
	}
}
