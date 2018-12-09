package com.packtpublishing.tddjava.ch03tictactoe

class TicTacToe {

  private val board = Array.ofDim[Char](3, 3)
  board(0)(0) = '-'
  board(0)(1) = '-'
  board(0)(2) = '-'
  board(1)(0) = '-'
  board(1)(1) = '-'
  board(1)(2) = '-'
  board(2)(0) = '-'
  board(2)(1) = '-'
  board(2)(2) = '-'

  private var lastPlayer: Char = '-'

  def play(x: Int, y: Int): String = {
    checkAxis(x)
    checkAxis(y)
    lastPlayer = nextPlayer()
    setBox(x, y, lastPlayer)

    var ret = "No winner"
    if (isWin)
      ret = s"$lastPlayer is the winner"

    if (isDraw)
      ret = s"The result is draw"

    ret
  }

  def nextPlayer(): Char = {
    if (lastPlayer == 'X')
      'O'
    else
      'X'
  }

  private def isWin: Boolean = {
    val playerTotal = lastPlayer * TicTacToe.SIZE
    var ret = false
    for (index <- 0 until TicTacToe.SIZE) {
      if (board(0)(index) + board(1)(index) + board(2)(index) == playerTotal)
        ret = true
      else if (board(index)(0) + board(index)(1) + board(index)(2) == playerTotal)
        ret = true
    }

    if (board(0)(0) + board(1)(1) + board(2)(2) == playerTotal)
      ret = true

    if (board(0)(2) + board(1)(1) + board(2)(0) == playerTotal)
      ret = true

    ret
  }

  private def isDraw: Boolean = {
    var ret = true
    for (x <- 0 until TicTacToe.SIZE)
      for (y <- 0 until TicTacToe.SIZE)
        if (board(x)(y) == '-')
          ret = false

    ret
  }

  private def checkAxis(axis: Int): Unit = {
    if (axis < 1 || axis > 3)
      throw new RuntimeException("X is outside board.")
  }

  private def setBox(x: Int, y: Int, lastPlayer: Char): Unit = {
    if (board(x-1)(y-1) != '-') {
      throw new RuntimeException("Box is occupied.")
    } else {
      board(x-1)(y-1) = lastPlayer
    }
  }

}

object TicTacToe {
  val SIZE = 3
}