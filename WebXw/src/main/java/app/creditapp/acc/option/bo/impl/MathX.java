package  app.creditapp.acc.option.bo.impl;


import java.text.DecimalFormat;

/**
 *给定公式字符转，返回结果
 *
 *
*/

public class MathX {

 public static void main(String[] args) {

  String str = "100000.0*0.0050*1*1*(1+0.0050*1*1)^(1-1)/((1+0.0050*1*1)^(24/1/1/1)-1)";

  double d = Calc.doCalc(str).getValue();

  int i = (int) Math.ceil(998/1.00/10);
  System.out.println(i);
  System.out.println(Calc.getStr()+"="+d);

 }

// public static double calc(String s){

//  return Calc.doCalc(s).getValue();

// }

// public static String getStr(){

//  return Calc.getStr();

// }

// public static String getFormatted() {

//  return Calc.getFormatted();

// }

}

class Calc{

 private static final DecimalFormat df = new DecimalFormat("0.000000");

 private static CalcCore co;

 private static NumberWrapper result;

 public static NumberWrapper doCalc(String exp){

  co = new CalcCore(exp);

  result = co.getResult();

  return result;

 }

 public static String getFormatted() {

  return df.format(result.getValue());

 }

 public static String getStr(){return co.toString();}

}

//数据外覆类

class NumberWrapper{

 public static final int IaN = 0;

 public static final int NaN = 1;

 public static final NumberWrapper NOTHING = new NumberWrapper(Double.NaN,NumberWrapper.NaN);

 private double value;

 private int id;

 

 public NumberWrapper(double v){

  this(v,NumberWrapper.IaN);

 }

 public double getValue(){

  return id==NumberWrapper.IaN?value:Double.NaN;

 }

 public NumberWrapper(double v,int id){

  this.value=v;

  this.id=id;

 }

 // +-*/^~

 public NumberWrapper calc(NumberWrapper x,char o){

  if(this.NaN()||x.NaN())

   return NumberWrapper.NOTHING;

  return new NumberWrapper(calc(this.getValue(),x.getValue(),o));

 }

 private double calc(double a,double b,char o){

  try{

   switch(o){

   case OStack.PLUS:return a+b;

   case OStack.SUBTRACT:return a-b;

   case OStack.MULTIPLY:return a*b;

   case OStack.DIVIDE:return a/b;

   case OStack.POWER:return Math.pow(a, b);

   case OStack.EVOLUTION:return Math.pow(a, 1d/b);

   default:return Double.NaN;

   }

  }catch(Exception e){}

  return Double.NaN;

 }

 

 public void setId(int id){this.id=id;}

 public boolean NaN(){return id==NaN;}

}

class CalcCore{

 private NStack n;

 private OStack o;

 private NumberWrapper result;

 private String src;

 

 public CalcCore(String src){

  this.src=src;

  rebuildString();

  this.n=new NStack();

  this.o=new OStack();

  this.split();

  this.calc();

 }

 public String toString(){

  return src;

 }

 private void rebuildString() {

  // (...)(...) --> (...)*(...)

  src=src.replaceAll("\\)\\(",")"+OStack.MULTIPLY+"(");

  // 1234(...) --> 1234*(...)

  // (...)1234 --> (...)*1234

  int i=0;

  while(i<src.length()){

   if(hasNext(i+1)&&this.isNumber(i)&&src.charAt(i+1)==OStack.LB||

     hasNext(i+1)&&this.isNumber(i+1)&&src.charAt(i)==OStack.RB){

    src=src.substring(0,i+1)+OStack.MULTIPLY+src.substring(i+1);

   }

   ++i;

  }

  //~1234 -->2~1234

  //(~1234) -->(2~1234)

  i=0;

  while(i<src.length()){

   if(src.charAt(i)==OStack.EVOLUTION && pio(i)){

    src=src.substring(0,i)+"2"+src.substring(i);

   }

   ++i;

  }

 }

 private void calc() {

  for(int i=0; i<o.size(); i++){

   char ch = o.get(i);

   if(ch==OStack.EVOLUTION||ch==OStack.POWER){

    NumberWrapper n0 = n.remove(i);

    NumberWrapper n1 = n.remove(i);

    ch = o.remove(i);

    NumberWrapper rs;

    if(ch==OStack.EVOLUTION)

     rs = n0.calc(n1, ch);

    else

     rs = n1.calc(n0, ch);

    n.insert(i, rs);

    --i;

   }

  }

  for(int i=o.size()-1; i>=0; i--){

   char ch = o.get(i);

   if(ch==OStack.MULTIPLY||ch==OStack.DIVIDE){

    NumberWrapper n0 = n.remove(i+1);

    NumberWrapper n1 = n.remove(i);

    ch = o.remove(i);

    NumberWrapper rs = n0.calc(n1, ch);

    n.insert(i, rs);

   }

  }

  for(int i=o.size()-1; i>=0; i--){

   char ch = o.get(i);

   NumberWrapper n0 = n.remove(i+1);

   NumberWrapper n1 = n.remove(i);

   ch = o.remove(i);

   NumberWrapper rs = n0.calc(n1, ch);

   n.insert(i, rs);

  }

  if(n.isEmpty()||n.size()>1)

   result = NumberWrapper.NOTHING;

  else

   result = n.pop();

 }

 public NumberWrapper getResult(){

  return result;

 }

 private void split(){

  int cont;

  for(int i=0; i<src.length(); i++){

   char c = src.charAt(i);

   switch(c){

   case '(':

    int pair = nextPair(src,i+1);

    String sub = substring(i+1,pair);

    n.push(Calc.doCalc(sub));

    i=pair;

    break;

   case '-':

   case '+':

    boolean iso = pio(i);

    cont = continuous(i+1);

    if(iso){

     n.push(new NumberWrapper(parse(substring(i,cont))));

     i=cont-1;

     break;

    }

   case '*':

   case '/':

   case '%':

   case '^':

   case '~':o.push(c);break;

   default :

    cont = continuous(i+1);

    n.push(new NumberWrapper(parse(substring(i,cont))));

    i=cont-1;

   }

  }

 }

 private double parse(String s){

  try{

   return Double.parseDouble(s);

  }catch(Exception e){}

  return Double.NaN;

 }

 private String substring(int i, int cont) {

  return src.substring(i,cont);

 }

 private boolean hasNext(int i){

  return src.length()>i;

 }

 private int continuous(int i) {

  while(hasNext(i) && isNumber(i))

    ++i;

  return i;

 }

 private boolean pio(int i){

  return i<1?true:OStack.iso(src.charAt(i-1));

 }

 public boolean isNumber(int pos){

  char c = src.charAt(pos);

  return c<='9' && c>='0' || c=='.';

 }

 public int nextPair(String src,int pos){

  int inner = 0;

  int len = src.length();

  for(int i=pos; i<len; i++){

   char c = src.charAt(i);

   if(c==')')

    if(inner==0)return i;

    else --inner;

   else if(c=='(')++inner;

  }

  return -1;

 }

}

//操作符栈(半角符号)

class OStack{

 public static final char PLUS = '+';

 public static final char SUBTRACT = '-';

 public static final char MULTIPLY = '*';

 public static final char DIVIDE = '/';

 public static final char POWER = '^';

 public static final char EVOLUTION = '~';

 public static final char LB = '(';

 public static final char RB = ')';

 public static boolean iso(char c) {

  switch(c){

  case PLUS:

  case SUBTRACT:

  case MULTIPLY:

  case DIVIDE:

  case POWER:

  case EVOLUTION:

  case LB:

//  case RB:

   return true;

  }

  return false;

 }

 

 public boolean hasNext(int i) {

  return this.length>i+1;

 }

 private char[] stack;

 private int length;

 

 public OStack(){

  this.stack=new char[0];

  this.length=0;

 }

 public boolean isEmpty(){

  return length==0;

 }

 public void push(char c){

  char[] tmp = new char[this.length+1];

  tmp[0]=c;

  System.arraycopy(stack, 0, tmp, 1, length);

  ++length;

  this.stack=tmp;

 }

 public char pop(){

  if(this.isEmpty())throw new StackOverflowError();

  char c = stack[0];

  char[] tmp = new char[length-1];

  System.arraycopy(stack, 1, tmp, 0, tmp.length);

  --length;

  this.stack=tmp;

  return c;

 }

 public char remove(int i){

  if(i<0||i>=length)throw new StackOverflowError();

  char[] tmp = new char[length-1];

  System.arraycopy(stack,0,tmp,0,i);

  System.arraycopy(stack,i+1,tmp,i,tmp.length-i);

  this.length--;

  char n=stack[i];

  this.stack=tmp;

  return n;

 }

 public void insert(int i,char n){

  if(i<0||i>length)throw new StackOverflowError();

  char[] tmp = new char[length+1];

  System.arraycopy(stack,0,tmp,0,i);

  System.arraycopy(stack,i,tmp,i+1,this.length-i);

  tmp[i]=n;

  this.length++;

  this.stack=tmp;

 }

 public char get(int i){

  return this.stack[i];

 }

 public int size(){return this.length;}

}

//数据栈

class NStack{

 private NumberWrapper[] stack;

 private int length;

 public NStack(){

  this.stack=new NumberWrapper[0];

  this.length=0;

 }

 public NStack(NStack n){

  this.stack=new NumberWrapper[n.length];

  this.length=n.length;

  for(int i=0; i<length; i++){

   this.stack[i]=n.stack[i];

  }

 }

 public void push(NumberWrapper d){

  NumberWrapper[] tmp = new NumberWrapper[this.length+1];

  System.arraycopy(stack, 0, tmp, 1, this.length);

  tmp[0] = d;

  this.stack=tmp;

  this.length++;

 }

 public NumberWrapper pop() throws StackOverflowError{

  if(this.isEmpty())throw new StackOverflowError();

  NumberWrapper[] tmp = new NumberWrapper[this.length-1];

  System.arraycopy(stack, 1, tmp, 0, tmp.length);

  NumberWrapper d = stack[0];

  this.stack=tmp;

  this.length--;

  return d;

 }

 public NumberWrapper get(int i){

  return this.stack[i];

 }

 public NumberWrapper remove(int i){

  if(i<0||i>=length)throw new StackOverflowError();

  NumberWrapper[] tmp = new NumberWrapper[length-1];

  System.arraycopy(stack,0,tmp,0,i);

  System.arraycopy(stack,i+1,tmp,i,tmp.length-i);

  this.length--;

  NumberWrapper n=stack[i];

  this.stack=tmp;

  return n;

 }

 public void insert(int i,NumberWrapper n){

  if(i<0||i>length)throw new StackOverflowError();

  NumberWrapper[] tmp = new NumberWrapper[length+1];

  System.arraycopy(stack,0,tmp,0,i);

  System.arraycopy(stack,i,tmp,i+1,this.length-i);

  tmp[i]=n;

  this.length++;

  this.stack=tmp;

 }

 public boolean isEmpty(){

  return this.length==0;

 }

 public int size(){return this.length;}

}

