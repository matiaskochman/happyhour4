package com.happyhour.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;

@Service
@Transactional
public class TokenCreationServiceImpl {
	
	@Autowired
	private PromotionInstanceService promotionInstanceService;
	
	
	private Map<String,String> codeMap;
	private Random rn = new Random();	

    public Map<String, String> getCodeMap() {
    	
    	synchronized(TokenCreationServiceImpl.class) {
    		
	    	if(codeMap==null){
	    		createHashMapCodes();
	    	}
			return codeMap;
    	}
	}



	public void setCodeMap(Map<String, String> codeMap) {
		this.codeMap = codeMap;
	}



	private Map createHashMapCodes(){
		codeMap = new HashMap<String, String>();
		
    	codeMap.put("0", "0");
    	codeMap.put("1", "1");
    	codeMap.put("2", "2");
    	codeMap.put("3", "3");
    	codeMap.put("4", "4");
    	codeMap.put("5", "5");
    	codeMap.put("6", "6");
    	codeMap.put("7", "7");
    	codeMap.put("8", "8");
    	codeMap.put("9", "9");
    	codeMap.put("10", "A");
    	codeMap.put("11", "B");
    	codeMap.put("12", "C");
    	codeMap.put("13", "D");
    	codeMap.put("14", "E");
    	codeMap.put("15", "B");
    	codeMap.put("16", "F");
    	codeMap.put("17", "G");
    	codeMap.put("18", "H");
    	codeMap.put("19", "I");
    	codeMap.put("20", "J");
    	codeMap.put("21", "K");
    	codeMap.put("22", "L");
    	codeMap.put("23", "M");
    	codeMap.put("24", "N");
    	codeMap.put("25", "O");
    	codeMap.put("26", "P");
    	codeMap.put("27", "Q");
    	codeMap.put("28", "R");
    	codeMap.put("29", "S");
    	codeMap.put("30", "T");
    	codeMap.put("31", "U");
    	codeMap.put("32", "V");
    	codeMap.put("33", "W");
    	codeMap.put("34", "X");
    	codeMap.put("35", "Y");
    	codeMap.put("36", "Z");
    	
    	return codeMap;
    } 
    
	/**
	 * Matias Kochman
	 * used to get a random symbol in betweed 0-1 and A-Z  
	 * 36 possible symbols in total 
	 * 
	 */
	public synchronized String getRandomFrom1to36(){
		int n = 35;
		Integer rand = rn.nextInt(n + 1);
		rand++;
		
		//randomList.add(rand);
		return rand.toString();
	}	
	private synchronized String getSymbol(String position){
		
		return codeMap.get(position);
	}	
	
	/**
	 * Matias Kochman
	 * 
	 * It's used to cut the minutes and seconds in two different digits 
	 * so I can add them to the order number
	 * 
	 * In the case that I get a 0 I change it to 1 because the alphanumericMap
	 * starts with 1 and not with 0
	 * 
	 * @param digits
	 * @return
	 */
	private List<String> cutMinutesOrSeconds(Integer digits) {
		List<String> digitsList = new ArrayList<String>();
		
		if(digits>10){
			String stringDigits = digits.toString();
			String firstDigit = stringDigits.substring(0,1);
			String secondDigit = stringDigits.substring(1,2);
		
			
			digitsList.add(firstDigit);
			digitsList.add(secondDigit);
			
		}else{
			String stringDigits = digits.toString();
			String secondDigit = stringDigits.substring(0,1);
			
			digitsList.add(secondDigit);
		}
		
		return digitsList;
	}
		
	public synchronized String generateNewOrderNumber(PromotionRequest promotionRequest) {

		Long promotionInstanceId = new Long(promotionRequest.getPromoId());
		PromotionInstance promotionInstance =  promotionInstanceService.findPromotionInstance(promotionInstanceId);
		String index = promotionInstance.getTokenIndex();
		Set<String> tokenSet = promotionInstance.getTokenSet();
		
		String readablePart = generateReadableAndSortablePart(index,tokenSet);
		
		StringBuffer orderNumber = new StringBuffer(); 
		Calendar cal = Calendar.getInstance();
		
		// I create a timestamp from month to seconds using 0-9 , A-Z and a-z
		Integer month = cal.get(Calendar.MONTH);
		month = month+1;
		Integer day = cal.get(Calendar.DAY_OF_MONTH);
		Integer hour = cal.get(Calendar.HOUR_OF_DAY);
		//Integer minutes = cal.get(Calendar.MINUTE);
		//Integer seconds = cal.get(Calendar.SECOND);
		
		//In one second It could happen to be more than 1 booking 
		//so I add 4 random alphanumeric digits to have uniqueness 
		
		orderNumber.append(readablePart);
		orderNumber.append("-");
		
		orderNumber.append(getCodeMap().get(getRandomFrom1to36()));
		orderNumber.append(getCodeMap().get(getRandomFrom1to36()));
		orderNumber.append(getCodeMap().get(getRandomFrom1to36()));
		
		orderNumber.append(getCodeMap().get(month.toString()));
		orderNumber.append(getCodeMap().get(day.toString()));
		orderNumber.append(getCodeMap().get(hour.toString()));
		
		promotionInstance.getTokenSet().add(orderNumber.toString());
		promotionInstance.setTokenIndex(orderNumber.toString().substring(0, 1));
		promotionInstanceService.savePromotionInstance(promotionInstance);
		
		return orderNumber.toString();
	}	

	private String generateReadableAndSortablePart(String lastLetterSaved,Set<String> tokenSet){
		
		String nextLetter = incrementTokenIndex(lastLetterSaved);
		
		TreeSet<String> indexTreeSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		
		Integer counter = 1;
		if((tokenSet!=null)&&(!tokenSet.isEmpty())){
			
			for (String token : tokenSet) {
				if(token.startsWith(nextLetter)){
					indexTreeSet.add(token);
				}
			}
			
			
			if(!indexTreeSet.isEmpty()){
				String token = indexTreeSet.last();
				
				String[] stringParts = token.split("-");
				
				String part1 = stringParts[0];
				
				counter = new Integer(part1.substring(1));
				counter++;
				
			}else{
				return nextLetter+"1";
			}
			
			return nextLetter+counter;
		}else{
			
			return lastLetterSaved+counter;
		}
		
		
	}



	private String incrementTokenIndex(String index) {
		
		if(index.equalsIgnoreCase("A")){
			return "B";
		}else if(index.equalsIgnoreCase("B")){
			return "C";
		}else if(index.equalsIgnoreCase("C")){
			return "D";
		}else if(index.equalsIgnoreCase("D")){
			return "A";
		}
		return null;
	}
	
}
