package com.infotech

object Main {
  
  def main(args: Array[String]){
      var s = SingleTonTest.displayRandom();
      println(s)
      
      var t = SingleTonTest.displayRandom();
      println(t)
      
      var u = SingleTonTest.displayRandom();
      println(u)
      
      val user = new User("Vj", 12)
      //user.name
  }
  
}

class User( val name:String, private val age:Int)