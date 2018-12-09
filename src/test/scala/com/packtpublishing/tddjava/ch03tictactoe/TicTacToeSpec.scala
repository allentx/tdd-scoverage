package com.packtpublishing.tddjava.ch03tictactoe

import org.junit.rules.ExpectedException
import org.junit.{Before, Rule, Test}
import org.junit.Assert._

class TicTacToeSpec {

  private var ticTacToe: TicTacToe = _

  private val _exceptionRule = ExpectedException.none()

  @Rule
  def exceptionRule = _exceptionRule

  @Before
  def before(): Unit = {
    ticTacToe = new TicTacToe()
  }

  @Test
  def whenXOutsideBoardThenRuntimeException(): Unit = {
    exceptionRule.expect(classOf[RuntimeException])
    ticTacToe.play(5, 2)
  }

  @Test
  def whenYOutsideBoardThenRuntimeException(): Unit = {
    exceptionRule.expect(classOf[RuntimeException])
    ticTacToe.play(2, 5)
  }

  @Test
  def whenOccupiedThenRuntimeException(): Unit = {
    ticTacToe.play(2, 1)
    exceptionRule.expect(classOf[RuntimeException])
    ticTacToe.play(2, 1)
  }

  @Test
  def givenFirstTurnWhenNextPlayerThenX(): Unit = {
    assertEquals('X', ticTacToe.nextPlayer())
  }

  @Test
  def givenLastTurnWasXWhenNextPlayerThenO(): Unit = {
    ticTacToe.play(1, 1)
    assertEquals('O', ticTacToe.nextPlayer())
  }

  @Test
  def whenPlayThenNoWinner(): Unit = {
    val actual = ticTacToe.play(1, 1)
    assertEquals("No winner", actual)
  }

  @Test
  def whenPlayAndWholeHorizontalLineThenWinner(): Unit = {
    ticTacToe.play(1, 1) // X
    ticTacToe.play(1, 2) // O
    ticTacToe.play(2, 1) // X
    ticTacToe.play(2, 2) // O
    val actual = ticTacToe.play(3, 1) // X
    assertEquals("X is the winner", actual)
  }

  @Test
  def whenPlayAndWholeVerticalLineThenWinner(): Unit = {
    ticTacToe.play(2, 1) // X
    ticTacToe.play(1, 1) // O
    ticTacToe.play(3, 1) // X
    ticTacToe.play(1, 2) // O
    ticTacToe.play(2, 2) // X
    val actual = ticTacToe.play(1, 3) // O
    assertEquals("O is the winner", actual)
  }

  @Test
  def whenPlayAndTopBottomDiagonalLineThenWinner(): Unit = {
    ticTacToe.play(1, 1) // X
    ticTacToe.play(1, 2) // O
    ticTacToe.play(2, 2) // X
    ticTacToe.play(1, 3) // O
    val actual = ticTacToe.play(3, 3) // X
    assertEquals("X is the winner", actual)
  }

  @Test
  def whenPlayAndBottomTopDiagonalLineThenWinner(): Unit = {
    ticTacToe.play(1, 3) // X
    ticTacToe.play(1, 1) // O
    ticTacToe.play(2, 2) // X
    ticTacToe.play(1, 2) // O
    val actual = ticTacToe.play(3, 1) // X
    assertEquals("X is the winner", actual)
  }

  @Test
  def whenAllBoxesAreFilledThenDraw(): Unit = {
    ticTacToe.play(1, 1) // X
    ticTacToe.play(1, 2) // O
    ticTacToe.play(1, 3) // X
    ticTacToe.play(2, 1) // O
    ticTacToe.play(2, 3) // X
    ticTacToe.play(2, 2) // O
    ticTacToe.play(3, 1) // X
    ticTacToe.play(3, 3) // O
    val actual = ticTacToe.play(3, 2) // X
    assertEquals("The result is draw", actual)
  }

}