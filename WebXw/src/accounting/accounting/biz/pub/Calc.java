package accounting.biz.pub;

import java.text.DecimalFormat;

class Calc{

 private static final DecimalFormat df = new DecimalFormat("0.000000");

 private static CalcCore co;

 private static NumberWrapper result;

 public static NumberWrapper doCalc(String exp) throws Exception{

  co = new CalcCore(exp);

  result = co.getResult();

  return result;

 }

 public static String getFormatted() {

  return df.format(result.getValue());

 }

 public static String getStr(){return co.toString();}

}