package accounting.biz.pub;

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

  return id==NumberWrapper.IaN?value:NumberWrapper.NaN;

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