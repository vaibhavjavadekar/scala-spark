package com.infotech

class Triangle(val width : Double, val height: Double) extends Polygon with Shape{
  override def area : Double = width * height / 2;
   def color : String = "Red";
}