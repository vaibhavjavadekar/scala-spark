package com.infotech.helloworld

object CurryingDemo {
  
  def add(x:Int, y:Int) = x+y;
  
  def add2(x: Int) = (y:Int) => x+y
  
  def main(args: Array[String]){
    println(add(10,20))
    println(add2(10)(20))
    
    val sum10 = add2(10);
    println(sum10(20))
    
  }
}