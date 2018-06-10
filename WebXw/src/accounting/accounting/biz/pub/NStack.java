package accounting.biz.pub;

//Êý¾ÝÕ»

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