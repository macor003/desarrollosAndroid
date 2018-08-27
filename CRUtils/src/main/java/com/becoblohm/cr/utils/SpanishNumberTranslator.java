package com.becoblohm.cr.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.becoblohm.cr.types.CRBigDecimal;
/**
 * 
 * @author eve0017820
 * tomado de http://www.rgagnon.com/javadetails/java-0426.html y modificado al
 * castellano, de acuerdo al autor hay una version actualizada en 
 * http://sourceforge.net/projects/numberreader/ que procesa desde 0 
 * hasta 10E63.
 */
public class SpanishNumberTranslator {
	private static final String[] tensNames = {
	    "",
	    " diez",
	    " viente",
	    " treinta",
	    " cuarenta",
	    " cincuenta",
	    " sesenta",
	    " setenta",
	    " ochenta",
	    " noventa"
	  };

	  private static final String[] numNames = {
	    "",
	    " uno",
	    " dos",
	    " tres",
	    " cuatro",
	    " cinco",
	    " seis",
	    " siete",
	    " ocho",
	    " nueve",
	    " diez",
	    " once",
	    " doce",
	    " trece",
	    " catorce",
	    " quince",
	    " dieciseis",
	    " diecisiete",
	    " dieciocho",
	    " diecinueve"
	  };
	  
	  private static final String[] higherNumNames = {
		  	"",
		    " un",
		    " dos",
		    " tres",
		    " cuatro",
		    " cinco",
		    " seis",
		    " siete",
		    " ocho",
		    " nueve",
		    " diez",
		    " once",
		    " doce",
		    " trece",
		    " catorce",
		    " quince",
		    " dieciseis",
		    " diecisiete",
		    " dieciocho",
		    " diecinueve"
	  };

	  private SpanishNumberTranslator() {}
	  /**
	   * 
	   * @param number
	   * @return
	   */
	  private static String convertLessThanOneThousand(int number, String[] namesVector) {
	    String soFar = "";
	    if (number % 100 < 20){
	      soFar = namesVector[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = namesVector[number % 10];
	      number /= 10;
	      
	      if(soFar.equalsIgnoreCase("")){
	    	  soFar = tensNames[number % 10] +soFar;
	      }else if(number % 10 == 2){
	    	  soFar = "veinti"+soFar.substring(1);
	      }else{
	    	  soFar = tensNames[number % 10] + " y "+soFar;	    	  
	      }
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    if (number == 1 && soFar.equalsIgnoreCase("")) return "cien";
	    if (number == 1 && !soFar.equalsIgnoreCase("")) return "ciento"+soFar;
	    if (number == 5) return "quinientos "+ soFar;
	    if (number == 7) return "setecientos "+soFar;
	    if (number == 9) return "novecientos "+soFar;
	    return numNames[number] + "cientos " + soFar;
	  }

	  /**
	   * 
	   * @param number
	   * @return
	   */
	  public static String convert(long number) {
	    // 0 to 999 999 999 999
	    if (number == 0) { return "cero"; }

	    String snumber = Long.toString(number);

	    // pad with "0"
	    String mask = "000000000000";
	    DecimalFormat df = new DecimalFormat(mask);
	    snumber = df.format(number);

	    // XXXnnnnnnnnn
	    int billions = Integer.parseInt(snumber.substring(0,3));
	    // nnnXXXnnnnnn
	    int millions  = Integer.parseInt(snumber.substring(3,6));
	    // nnnnnnXXXnnn
	    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
	    // nnnnnnnnnXXX
	    int thousands = Integer.parseInt(snumber.substring(9,12));

	    String tradBillions;
	    switch (billions) {
	    case 0:
	      tradBillions = "";
	      break;
	    case 1 :
	      tradBillions = convertLessThanOneThousand(billions,higherNumNames)
	      + " millardo ";//billon en castellano es millardo
	      break;
	    default :
	      tradBillions = convertLessThanOneThousand(billions,higherNumNames)
	      + " millardos ";
	    }
	    String result =  tradBillions;

	    String tradMillions;
	    switch (millions) {
	    case 0:
	      tradMillions = "";
	      break;
	    case 1 :
	      tradMillions = "un millon ";
	      break;
	    default :
	      tradMillions = convertLessThanOneThousand(millions,higherNumNames)
	         + " millones ";
	    }
	    result =  result + tradMillions;

	    String tradHundredThousands;
	    switch (hundredThousands) {
	    case 0:
	      tradHundredThousands = "";
	      break;
	    case 1 :
	      tradHundredThousands = "mil ";
	      break;
	    default :
	      tradHundredThousands = convertLessThanOneThousand(hundredThousands,higherNumNames)
	         + " mil ";
	    }
	    result =  result + tradHundredThousands;

	    String tradThousand;
	    tradThousand = convertLessThanOneThousand(thousands,numNames);
	    result =  result + tradThousand;

	    // remove extra spaces!
	    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	  }

	  public static String convert(BigDecimal num,boolean convertCents){
		  
		  BigDecimal bd = num.setScale(2, RoundingMode.HALF_UP);
		  String[] parts = bd.toString().split("\\.");
		  String cents ="";
		  int intWhole = Integer.valueOf(parts[0]),
			  intCents = 0;
		  if(parts.length > 1){
			  intCents = Integer.valueOf(parts[1]);
		  }
		  cents = (convertCents ? SpanishNumberTranslator.convert( intCents ):""+intCents);
		  if(intCents == 0 && intWhole == 0)
			  return "Cero";
		  if(intCents == 0)
			 return SpanishNumberTranslator.convert( intWhole ) + " exactos";
		  if(intWhole == 0){
			  return "Cero con "+cents+"/100";
		  }
		  
		  
		  return  SpanishNumberTranslator.convert( intWhole ) + " con "
		             + cents+"/100" ;
	  }
	  	  
	  public static String convertAll(CRBigDecimal num){
		  return convert(num.getValue(),true);
	  }
	  
	  public static String convertIntegerPart(CRBigDecimal num){
		  return convert(num.getValue(),false);
	  }
	  /**
	   * testing
	   * @param args
	   */
	  public static void main(String[] args) {
	    System.out.println("*** " + SpanishNumberTranslator.convert(0));
	    System.out.println("*** " + SpanishNumberTranslator.convert(1));
	    System.out.println("*** " + SpanishNumberTranslator.convert(16));
	    System.out.println("*** " + SpanishNumberTranslator.convert(20));
	    System.out.println("*** " + SpanishNumberTranslator.convert(21));
	    System.out.println("*** " + SpanishNumberTranslator.convert(25));
	    System.out.println("*** " + SpanishNumberTranslator.convert(100));
	    System.out.println("*** " + SpanishNumberTranslator.convert(118));
	    System.out.println("*** " + SpanishNumberTranslator.convert(200));
	    System.out.println("*** " + SpanishNumberTranslator.convert(219));
	    System.out.println("*** " + SpanishNumberTranslator.convert(224));
	    System.out.println("*** " + SpanishNumberTranslator.convert(444));
	    System.out.println("*** " + SpanishNumberTranslator.convert(800));
	    System.out.println("*** " + SpanishNumberTranslator.convert(801));
	    System.out.println("*** " + SpanishNumberTranslator.convert(911));
	    System.out.println("*** " + SpanishNumberTranslator.convert(1316));
	    System.out.println("*** " + SpanishNumberTranslator.convert(9999));
	    System.out.println("*** " + SpanishNumberTranslator.convert(9090));
	    System.out.println("*** " + SpanishNumberTranslator.convert(1234));
	    System.out.println("*** " + SpanishNumberTranslator.convert(501000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(501000000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(10001));
	    System.out.println("*** " + SpanishNumberTranslator.convert(1000000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(2000000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(3000200));
	    System.out.println("*** " + SpanishNumberTranslator.convert(700000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(800000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(900000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(100000));	    
	    System.out.println("*** " + SpanishNumberTranslator.convert(9000000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(9001000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(21000000));
	    System.out.println("*** " + SpanishNumberTranslator.convert(123456789));
	    System.out.println("*** " + SpanishNumberTranslator.convert(2147483647));
	    System.out.println("*** " + SpanishNumberTranslator.convert(3000000010L));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(0.5),true));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(100),true));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(10.6),true));
   		System.out.println("*** " + 		
	    		SpanishNumberTranslator.convert(new BigDecimal(1000258.66),true));
   		System.out.println("*** " + 
   				SpanishNumberTranslator.convert(new BigDecimal(0.5),false));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(100),false));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(10.6),false));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(1000258.66),false));
	    System.out.println("*** " + 
	    		SpanishNumberTranslator.convert(new BigDecimal(2147483647.44),true));
	    /*
	     *** cero
	     *** uno
	     *** dieciseis
	     *** cien
	     *** ciento dieciocho
	     *** doscientos
	     *** doscientos diecinueve
	     *** ochocientos
	     *** eight hundred one
	     *** one thousand three hundred sixteen
	     *** one million
	     *** two millions
	     *** three millions two hundred
	     *** seven hundred thousand
	     *** nine millions
	     *** nine millions one thousand
	     *** one hundred twenty three millions four hundred
	     **      fifty six thousand seven hundred eighty nine
	     *** two billion one hundred forty seven millions
	     **      four hundred eighty three thousand six hundred forty seven
	     *** three billion ten
	     **/
	  }
}
