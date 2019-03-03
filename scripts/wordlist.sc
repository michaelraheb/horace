import edu.holycross.shot.tabulae.builder._

import sys.process._
import scala.language.postfixOps

import better.files._
import java.io.{File => JFile}
import better.files.Dsl._

val compiler = "/usr/bin/fst-compiler-utf8"
val fstinfl = "/usr/bin/fst-infl"
val make = "/usr/bin/make"

def makeWordList(file: String): Unit = {
  import java.io._
  import java.lang._
  val corpus = scala.io.Source.fromFile(file).mkString
  val corpusnospace = corpus.replaceAll("\n"," ").trim.replaceAll("\\s +", " ")
  val corpussplit = corpusnospace.replaceAll("\\p{Punct}|\\d","").toLowerCase.split(" ").toSet
  val finalcorpus = corpussplit.toArray.sorted
  val md = new BufferedWriter(new FileWriter(new File("wordlist.txt")))
  for (diff <- finalcorpus) md.write(diff + "\n")
  md.close()
  println(finalcorpus.size)
}
