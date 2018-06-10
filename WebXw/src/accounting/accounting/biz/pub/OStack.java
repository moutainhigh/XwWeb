package accounting.biz.pub;

//²Ù×÷·ûÕ»(°ë½Ç·ûºÅ)

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

 public char remove(int i) throws Exception{

  if(i<0||i>=length)throw new Exception("×Ö¶Î³¤¶ÈÓÐÎó");

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
