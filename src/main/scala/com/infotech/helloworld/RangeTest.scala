package com.infotech.helloworld

object RangeTest {

  def main(args: Array[String]) {

    //val x = Range(3, 12, 1)

    //println(x)
    //println(x(0))
    //println(x.foreach(print))

    //    for( w <- 0 to 3; z<- 8 to 10; y<- 4 to 7 )
    //        {
    //            println("Value of w is :" +w +"  Value of z is :" +z+"  Value of y is :" +y);
    //        }

    val name = "mark"
    val age = 187
    println(name + " is " + age + "years old")
    println(s"$name is $age years old")
    println(f"$name%s is $age%f years old")
    println(raw"Hello \nWorld")
    println(s"Hello \nWorld")
    
    val bool = true
    
    if(age > 18.5){
      println("True it is")
    }else{
      println("False it is")
    }
    
    var x = 1.1;
    while(x < age){
      println(x)
      x += 1
    }
    
    for (i <- 1 until 6){
      println(i)
    }
    
    val list = List(1,2,3,4,5)
    
    val result = for{i <- list; if i < 3} yield {
      i * i
    }
    
    println(result)
    
    
    age match{
      case 18 => print(18)
      case _ => print("default");
    }

  }

}