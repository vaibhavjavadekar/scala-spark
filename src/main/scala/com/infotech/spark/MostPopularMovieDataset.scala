package com.infotech.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import scala.io.Source
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object MostPopularMovieDataset {

  case class MovieMap(id: Int, name: String)
  case class MovieData(userid: Int, movieId: Int, rating: Int, timestamp: Long)
  case class MovieCount(movieId: Int, movieOccurances: Int)

  def main(args: Array[String]) {

    val session = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp") // Necessary to work around a Windows bug in Spark 2.0.0; omit if you're not on Windows.
      .getOrCreate()

    val movieFact = session.sparkContext.textFile("in/u.data")
    val movieDataStructured = movieFact.map(movieDataMapper)

    import session.implicits._
    val movieDS = movieDataStructured.toDS()

    val movieCount = movieDS.groupBy(movieDS("movieId")).count().orderBy(desc("count"))
    
    movieCount.show()

    val topMovies = movieCount.take(10)

    val movieNames = loadMovieNames()

    for (movie <- topMovies) {
      println(movieNames(movie(0).asInstanceOf[Int]) + ": " + movie(1))
    }
    session.stop()
  }

  def movieDataMapper(line: String): MovieData = {

    val fields = line.split("\\W+")

    val movieData = new MovieData(fields(0).toInt, fields(1).toInt, fields(2).toInt, fields(3).toLong)
    return movieData
  }

  def movieCountMapper(line: String): MovieData = {

    val fields = line.split("\\W+")

    val movieData = new MovieData(fields(0).toInt, fields(1).toInt, fields(2).toInt, fields(3).toLong)
    return movieData
  }

  def loadMovieNames(): Map[Int, String] = {

    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    var movieNames: Map[Int, String] = Map()
    val movieData = Source.fromFile("in/u.item").getLines()
    for (item <- movieData) {
      val fields = item.split("\\|")
      if (fields.length > 1) {
        movieNames += (fields(0).toInt -> fields(1))
      }
    }

    return movieNames
  }
}